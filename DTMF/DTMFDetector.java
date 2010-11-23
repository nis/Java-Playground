// import java.awt.*;
// import javax.swing.*;
// import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class DTMFDetector {
	// Sound
	public int mixerIndex = 1;					// 2 for line-in, 1 for mic.
	public float sampleRate = 8000.0F; 			//8000,11025,16000,22050,44100
	public int sampleSizeInBits = 8;			//8,16
	public int channels = 1;					//1,2
	public boolean signed = true;				//true,false
	public boolean bigEndian = false;			//true,false
	
	// Goertzel
	private Double[] coeffs = new Double[8];	// Goertzel coeeficients
	private Float[] wham; 						// Hamming window vallues
	public int tresholdPercent = 25;			// Treshold value, how many percent of the highest magnitude should be let through
	public int tresholdHard = 50;				// Treshold value anything below gets wiped
	public int blockSize = 205;					// Number of samples for Goertzel
	public int[] frequencies = {697, 770, 852, 941, 1209, 1336, 1477, 1633};
	private Double[] magnitudes = {0d, 0d, 0d, 0d, 0d, 0d, 0d, 0d};
	public int toneDetectionCount = 2;			// How many times a tone needs to be heard
	public String currentTone = "";				// The tone currently being played
	public String[][] tones = {					// What the tones are called
								{"1", "2", "3", "A"},
								{"4", "5", "6", "B"},
								{"7", "8", "9", "C"},
								{"*", "0", "#", "D"}
							};
	private int[][] tCount = {					// How many times a tone have been detected in a row
								{0, 0, 0, 0},
								{0, 0, 0, 0},
								{0, 0, 0, 0},
								{0, 0, 0, 0}
							};
	
	// Audiocapture
	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	private byte tBuffer[] = new byte[208];		// Needs to be divisible by 4 and larger than blockSize
	private int bufferDelay = 10;				// How many ms between the buffer is read
	
	// Internal
	public boolean debug = true;				// More output for debugging
	private CaptureThread cT;
	private Thread cThread;
	private Boolean threadDone = false;
	
	public void DTMFDetector () {
		mLog("Detector initiated");
		mLog("");
	}
	
	public void startListener() {
		mLog("Detector started");
		mLog("");
		
		threadDone = false;
		
		computerCoeeficients();
		setupCapture();
		
		cT = new CaptureThread();
		cThread = new Thread(cT);
	    cThread.start();
	}
	
	public void stopListener() {
		mLog("Detector stopped");
		mLog("");
		threadDone = true;
		targetDataLine.stop();
	}
	
	public boolean isRunning() {
		if (!threadDone) {
			return true;
		} else {
			return false;
		}
	}
	
	private void detectTones () {
		int highestLow = -1;
		int highestHigh = -1;
		for (int i = 0; i < 8; i++) {
			if (i < 4) {
				if (magnitudes[i] > 0 && magnitudes[i] > highestLow) {
					highestLow = i;
				}
			} else {
				if (magnitudes[i] > 0 && magnitudes[i] > highestHigh) {
					highestHigh = i-4;
				}
			}
		}
		
		
		// 	1: No tone detected. -1 || -1
		//	Reset and return
		if (highestLow == -1 || highestHigh == -1) {
			resetCounts();
			currentTone = "";
			return;
		}
		
		// 	2: Tone detected, count not high enough
		//	Reset and increment count for the tone
		if (tCount[highestLow][highestHigh] < toneDetectionCount) {
			int temp = tCount[highestLow][highestHigh];
			temp++;
			resetCounts();
			tCount[highestLow][highestHigh] = temp;
			return;
		}
		
		//	3: Tone detected, count high enough, currentTone equal
		//	Do nothing return.
		if (tCount[highestLow][highestHigh] >= toneDetectionCount && currentTone.equals(tones[highestLow][highestHigh])) {
			return;
		}
		
		//	4: Tone detected, count high enough, currentTone not equal
		//	Set current tone
		//	Tone detected!
		//  Return
		if (tCount[highestLow][highestHigh] >= toneDetectionCount && !currentTone.equals(tones[highestLow][highestHigh])) {
			currentTone = tones[highestLow][highestHigh];
			mLog("Tone: " + tones[highestLow][highestHigh]);
			return;
		}
	}
	
	private void filterMagnitudes () {
		Double highestMagnitude = 0d;
		
		// Find the highest magnitude
		for (int i = 0; i < 8; i++) {
			if (magnitudes[i] > highestMagnitude) {
				highestMagnitude = magnitudes[i];
			}
		}
		
		// Mute magnitudes below tresholds
		for (int i = 0; i < 8; i++) {
			if (magnitudes[i] < tresholdHard || ((magnitudes[i]*100) / highestMagnitude) < tresholdPercent) {
				magnitudes[i] = 0d;
			}
		}
	}
	
	private void processBuffer () {
		for (int i = 0; i < 8; i++) {
			magnitudes[i] = goertzel(coeffs[i]);
		}
	}
	
	private Double goertzel (Double coeff) {
		Double q0 = 0d;
		Double q1 = 0d;
		Double q2 = 0d;
		
		for (int i = 0; i < blockSize ; i++) {
			// q0 = ( tBuffer[i]*wham[i] ) + coeff * q1 - q2; // For Hamming windowing the samples.
			q0 = ( tBuffer[i] ) + coeff * q1 - q2;
			q2 = q1;
			q1 = q0;
		}
		return (q1*q1)+(q2*q2)-(q1*q2*coeff);
	}
	
	private void setupCapture() {
		mLog("Setting up datacapture");
		try {
			// available mixers.
			Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();

			//Get everything set up for capture
	      	audioFormat = new AudioFormat(
		                      sampleRate,
		                      sampleSizeInBits,
		                      channels,
		                      signed,
		                      bigEndian);;

	      	DataLine.Info dataLineInfo = new DataLine.Info( TargetDataLine.class, audioFormat);
			Mixer mixer = AudioSystem.getMixer(mixerInfo[mixerIndex]);

			//Get a TargetDataLine on the selected
			// mixer.
			targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
			//Prepare the line for use.
			targetDataLine.open(audioFormat);
			targetDataLine.start();
		
		} catch ( Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	private void computerCoeeficients () {
		mLog("Computing Coefficients:");
		double c;
		
		for (int i = 0; i < frequencies.length; i++) {
			coeffs[i] = 2*Math.cos(((2*Math.PI)/blockSize)*(0.5 + ((blockSize*frequencies[i])/(sampleRate))));
			mLog("Coefficient for frequency " + frequencies[i] + "Hz = " + coeffs[i]);
		}
		mLog("");
	}
	
	private void resetCounts() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tCount[i][j] = 0;
			}
		}
	}
	
	private void mLog (String t) {
		if (debug) {
			System.out.println(t);
		}
	}
	
	public static void main (String[] args) {
		DTMFDetector d = new DTMFDetector();
		d.DTMFDetector();
		d.startListener();
		
		// try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace();}
		// 	
		// 	d.stopListener();
		// 	
		// 	try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace();}
		// 	
		// 	d.startListener();
	}
	
	// Inner class to capture audio
	class CaptureThread implements Runnable {
		public void run () {
			mLog("Capture thread running...");
			try {
				while (!threadDone) {
					int cnt = targetDataLine.read(tBuffer, 0, 208);
					processBuffer();
					filterMagnitudes();
					detectTones();
					//try { Thread.sleep(bufferDelay); } catch (InterruptedException e) { e.printStackTrace();}
				}
			} catch ( Exception e) {
				System.out.println(e);
				System.exit(0);
			}
		}
	}
}