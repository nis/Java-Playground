import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class MyListener implements Runnable {
	JFrame lFrame;
	FreqEqComponent fEQ = new FreqEqComponent();
	Random rand = new Random();
	Float coeffs[] = {1.69159f, 1.62766f, 1.54949f, 1.45739f, 1.13905f, 0.969679f, 0.770426f, 0.539082f};
	int fValues[] = {0, 0, 0, 0, 0, 0, 0, 0};
	
	int treshold = 25;
	
	// For Audiocapture
	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	byte tBuffer[] = new byte[208];
	
	public void run() {
		while (true) {
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace();}
			//repaintGraph();
			fEQ.repaint();
		}
	}
	
	public MyListener () {
		lFrame = makeFrame();
		lFrame.add(fEQ);
		lFrame.setVisible(true); 
		setupCapture();
	}
	
	private void setupCapture() {
		try {
			// available mixers.
			Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();

			//Get everything set up for capture
	      	audioFormat = getAudioFormat();

	      	DataLine.Info dataLineInfo = new DataLine.Info( TargetDataLine.class, audioFormat);
			Mixer mixer = AudioSystem.getMixer(mixerInfo[1]);

			//Get a TargetDataLine on the selected
			// mixer.
			targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
			//Prepare the line for use.
			targetDataLine.open(audioFormat);
			targetDataLine.start();
			
			CaptureThread cT = new CaptureThread();
			
			Thread cThread = new Thread(cT);
		    cThread.start();
		} catch ( Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public void repaintGraph() {
		// int n = 100;
		// for (int i = 0; i < 8; i++) {
		// 	fEQ.setMyWidth(i, rand.nextInt(n+1));
		// }
		fEQ.repaint();
	}
	
	  //This method creates and returns an
	  // AudioFormat object for a given set of format
	  // parameters.  If these parameters don't work
	  // well for you, try some of the other
	  // allowable parameter values, which are shown
	  // in comments following the declartions.
	  private AudioFormat getAudioFormat(){
	    float sampleRate = 8000.0F;
	    //8000,11025,16000,22050,44100
	    int sampleSizeInBits = 8;
	    //8,16
	    int channels = 1;
	    //1,2
	    boolean signed = true;
	    //true,false
	    boolean bigEndian = false;
	    //true,false
	    return new AudioFormat(
	                      sampleRate,
	                      sampleSizeInBits,
	                      channels,
	                      signed,
	                      bigEndian);
	  }//end getAudioFormat
	//=============================================//
	
	public JFrame makeFrame() {
		JFrame frame = new JFrame("Listener");
		
		frame.setSize(175, 175);
		frame.setLocation(650,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return frame;
	}
	
	private int goertzel (Float coeff) {
		Float q0 = 0f;
		Float q1 = 0f;
		Float q2 = 0f;
		int magnitude = 0;
		
		for (int i = 0; i < 205 ; i++) {
			q0 = tBuffer[i] + coeff * q1 - q2;
			q2 = q1;
			q1 = q0;
			// System.out.print(tBuffer[i]);
			// System.out.print(" ");
		}
		// System.out.print("\n");
		//magnitude = Math.sqrt();
		magnitude = (int) Math.sqrt((q1*q1)+(q2*q2)-(q1*q2*coeff));
		
		System.out.print("" + roundTwoDecimals(Math.sqrt((q1*q1)+(q2*q2)-(q1*q2*coeff))) + "\t");
		
		if (magnitude < treshold) {
			magnitude = 0;
		}
		
		// if (magnitude > 500) {
		// 	//System.out.print("" + magnitude + "\n");
		// 	magnitude = 10000;
		// }
		return magnitude;
	}
	
	private void testFrequencies () {
		int largestVal = 0;
		int cVal = 0;
		for (int i = 0; i < 8; i++) {
			cVal = goertzel(coeffs[i]);
			fValues[i] = cVal;
			if (cVal > largestVal) {
				largestVal = cVal;
			}
		}
		
		for (int i = 0; i < 8; i++) {
			if (largestVal > 0) {
				fValues[i] = (fValues[i]*100) / largestVal;
			} else {
				fValues[i] = 0;
			}
			//System.out.print(fValues[i] + " ");
		}
		
		if ((largestVal/10) > 25) {
			treshold = largestVal/10;
		} else {
			treshold = 25;
		}
		
		System.out.print("\n");
	}
	
	double roundTwoDecimals(double d) {
	        	DecimalFormat twoDForm = new DecimalFormat("#.##");
			return Double.valueOf(twoDForm.format(d));
	}
	
	// Inner class to capture audio
	class CaptureThread implements Runnable {
		
		public void run () {
			try {
				
				//int cnt = targetDataLine.read(tBuffer, 0, 204);
				//targetDataLine.flush();
				while (true) {
					int cnt = targetDataLine.read(tBuffer, 0, 208);
					// System.out.println("Available: " + targetDataLine.available());
					// System.out.println("Buffer length: " + tBuffer.length);
					// System.out.println("First value: " + tBuffer[0]);
					// System.out.println("Last value: " + tBuffer[203]);
					testFrequencies();
					//System.out.println(goertzel(1.45739f));
					// Do Goertzel here. On tBuffer.
					//targetDataLine.flush();
					try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace();}
				}
			} catch ( Exception e) {
				System.out.println(e);
				System.exit(0);
			}
		}
	}
	
	// Inner class for the Frequency Equalizer component
	class FreqEqComponent extends JComponent {

		public void paintComponent (Graphics g) {
			Graphics2D g2 = (Graphics2D) g;

			g2.setColor(Color.BLUE);
			g2.drawString("  697", 5, 15);
			g2.drawString("  770", 5, 30);
			g2.drawString("  852", 5, 45);
			g2.drawString("  941", 5, 60);
			g2.drawString("1209", 5, 75);
			g2.drawString("1336", 5, 90);
			g2.drawString("1477", 5, 105);
			g2.drawString("1633", 5, 120);

			g2.setColor(Color.BLACK);
			Rectangle bar = new Rectangle(50, 8, fValues[0], 5);
			g2.fill(bar);
			for (int i = 1; i < 8; i++) {
				bar.translate(0 , 15);
				bar.setSize(fValues[i], 5);
				g2.fill(bar);
			}
		}
	}
}