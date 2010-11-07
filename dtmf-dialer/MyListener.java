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
	//Float coeffs[] = {1.69159f, 1.62766f, 1.54949f, 1.45739f, 1.13905f, 0.969679f, 0.770426f, 0.539082f}; // 8k
	Float coeffs[] = {1.8322f, 1.79727f, 1.75432f, 1.70337f, 1.52408f, 1.42644f, 1.30931f, 1.1699f}; // 11.025k
	//Float coeffs[] = {1.91704f, 1.89991f, 1.87881f, 1.85371f, 1.76457f, 1.71545f, 1.65591f, 1.58414f}; // 16k
	//Float coeffs[] = {1.95441f, 1.94515f, 1.93375f, 1.92018f, 1.87191f, 1.84521f, 1.81273f, 1.77339f}; // 22.05k
	//Float coeffs[] = {1.98687f, 1.98439f, 1.98134f, 1.97772f, 1.96492f, 1.95785f, 1.94925f, 1.93882f}; // 44.1k 
	int fValues[] = {0, 0, 0, 0, 0, 0, 0, 0};
	Float wham[] = {0.08f, 0.0802182f, 0.0808725f, 0.0819623f, 0.0834866f, 0.0854439f, 0.0878324f, 0.0906498f, 0.0938934f, 0.0975602f, 0.101647f, 0.106149f, 0.111063f, 0.116383f, 0.122106f, 0.128225f, 0.134734f, 0.141628f, 0.1489f, 0.156543f, 0.164549f, 0.172912f, 0.181623f, 0.190674f, 0.200056f, 0.209761f, 0.219778f, 0.2301f, 0.240716f, 0.251615f, 0.262788f, 0.274224f, 0.285912f, 0.297841f, 0.31f, 0.322377f, 0.33496f, 0.347738f, 0.360698f, 0.373829f, 0.387117f, 0.40055f, 0.414115f, 0.4278f, 0.441591f, 0.455475f, 0.46944f, 0.483471f, 0.497557f, 0.511682f, 0.525834f, 0.54f, 0.554166f, 0.568318f, 0.582443f, 0.596529f, 0.61056f, 0.624525f, 0.638409f, 0.6522f, 0.665885f, 0.67945f, 0.692883f, 0.706171f, 0.719302f, 0.732262f, 0.74504f, 0.757623f, 0.77f, 0.782159f, 0.794088f, 0.805776f, 0.817212f, 0.828385f, 0.839284f, 0.8499f, 0.860222f, 0.870239f, 0.879944f, 0.889326f, 0.898377f, 0.907088f, 0.915451f, 0.923457f, 0.9311f, 0.938372f, 0.945266f, 0.951775f, 0.957894f, 0.963617f, 0.968937f, 0.973851f, 0.978353f, 0.98244f, 0.986107f, 0.98935f, 0.992168f, 0.994556f, 0.996513f, 0.998038f, 0.999128f, 0.999782f, 1.f, 0.999782f, 0.999128f, 0.998038f, 0.996513f, 0.994556f, 0.992168f, 0.98935f, 0.986107f, 0.98244f, 0.978353f, 0.973851f, 0.968937f, 0.963617f, 0.957894f, 0.951775f, 0.945266f, 0.938372f, 0.9311f, 0.923457f, 0.915451f, 0.907088f, 0.898377f, 0.889326f, 0.879944f, 0.870239f, 0.860222f, 0.8499f, 0.839284f, 0.828385f, 0.817212f, 0.805776f, 0.794088f, 0.782159f, 0.77f, 0.757623f, 0.74504f, 0.732262f, 0.719302f, 0.706171f, 0.692883f, 0.67945f, 0.665885f, 0.6522f, 0.638409f, 0.624525f, 0.61056f, 0.596529f, 0.582443f, 0.568318f, 0.554166f, 0.54f, 0.525834f, 0.511682f, 0.497557f, 0.483471f, 0.46944f, 0.455475f, 0.441591f, 0.4278f, 0.414115f, 0.40055f, 0.387117f, 0.373829f, 0.360698f, 0.347738f, 0.33496f, 0.322377f, 0.31f, 0.297841f, 0.285912f, 0.274224f, 0.262788f, 0.251615f, 0.240716f, 0.2301f, 0.219778f, 0.209761f, 0.200056f, 0.190674f, 0.181623f, 0.172912f, 0.164549f, 0.156543f, 0.1489f, 0.141628f, 0.134734f, 0.128225f, 0.122106f, 0.116383f, 0.111063f, 0.106149f, 0.101647f, 0.0975602f, 0.0938934f, 0.0906498f, 0.0878324f, 0.0854439f, 0.0834866f, 0.0819623f, 0.0808725f, 0.0802182f, 0.08f};
	
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
	    float sampleRate = 11025.0F;
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
			q0 = ( tBuffer[i]*wham[i] ) + coeff * q1 - q2;
			q2 = q1;
			q1 = q0;
			// System.out.print(tBuffer[i]);
			// System.out.print(" ");
		}
		// System.out.print("\n");
		//magnitude = Math.sqrt();
		magnitude = (int) Math.sqrt((q1*q1)+(q2*q2)-(q1*q2*coeff));
		
		// System.out.print("" + roundTwoDecimals(Math.sqrt((q1*q1)+(q2*q2)-(q1*q2*coeff))) + "\t");
		
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
		
		//System.out.print("\n");
	}
	
	double roundTwoDecimals(double d) {
	        	DecimalFormat twoDForm = new DecimalFormat("#.##");
			return Double.valueOf(twoDForm.format(d));
	}
	
	// Inner class to capture audio
	class CaptureThread implements Runnable {
		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();
		public void run () {
			try {
				
				//int cnt = targetDataLine.read(tBuffer, 0, 204);
				//targetDataLine.flush();
				while (true) {
					start = System.currentTimeMillis();
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
					end = System.currentTimeMillis();
					System.out.println("Execution time was "+(end-start)+" ms.");
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