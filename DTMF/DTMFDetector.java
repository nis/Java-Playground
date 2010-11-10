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
	private coeffs = new Float[8];				// Goertzel coeeficients
	private Float[] wham; 						// Hamming window vallues
	public int treshold = 25;					// Treshold value
	
	// Audiocapture
	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	private byte tBuffer[] = new byte[208];
	
	// Internal
	public boolean debug = true;				// More output for debugging
}