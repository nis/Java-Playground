// import java.awt.*;
// import javax.swing.*;
// import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class DTMFDetector implements Runnable {
	// Sound
	public int mixerIndex = 1;
	public float sampleRate = 8000.0F; 			//8000,11025,16000,22050,44100
	public int sampleSizeInBits = 8;			//8,16
	public int channels = 1;					//1,2
	public boolean signed = true;				//true,false
	public boolean bigEndian = false;			//true,false
	
	// Goertzel
	private Double[] coeffs = new Double[8];	// Goertzel coeeficients
	private Float[] wham; 						// Hamming window vallues
	public int treshold = 25;					// Treshold value
	public int blockSize = 205;					// Number of samples for Goertzel
	public int[] frequencies = {697, 770, 852, 941, 1209, 1336, 1477, 1633};
	
	// Audiocapture
	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	private byte tBuffer[] = new byte[208];		// Needs to be divisible by 4 and larger than blockSize
	
	// Internal
	public boolean debug = true;				// More output for debugging
	
	public void DTMFDetector () {
		mLog("Detector initiated");
		mLog("");
		computerCoeeficients();
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
	
	
	public void run () {
		
	}
	
	private void mLog (String t) {
		if (debug) {
			System.out.println(t);
		}
	}
	
	public static void main (String[] args) {
		DTMFDetector d = new DTMFDetector();
		d.DTMFDetector();
	}
}