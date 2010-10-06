import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import javax.sound.sampled.*;

public class MyListener implements Runnable {
	JFrame lFrame;
	FreqEqComponent fEQ = new FreqEqComponent(0, 10, 20, 30, 40, 50, 60, 70);
	Random rand = new Random();
	
	// For Audiocapture
	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	ByteArrayOutputStream byteArrayOutputStream;
	
	public void run() {
		while (true) {
			try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace();}
			repaintGraph();
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
		} catch ( Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	
	public void repaintGraph() {
		int n = 100;
		for (int i = 0; i < 8; i++) {
			fEQ.setMyWidth(i, rand.nextInt(n+1));
		}
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
	    int sampleSizeInBits = 16;
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
	
	class FreqEqComponent extends JComponent {
		int[] widths = {0, 0, 0, 0, 0, 0, 0, 0};

		public void setMyWidth(int aIn, int aVal) {
			widths[aIn] = aVal;
		}

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
			Rectangle bar = new Rectangle(50, 8, widths[0], 5);
			g2.fill(bar);
			for (int i = 1; i < 8; i++) {
				bar.translate(0 , 15);
				bar.setSize(widths[i], 5);
				g2.fill(bar);
			}
		}


		public FreqEqComponent (int val0, int val1, int val2, int val3, int val4, int val5, int val6, int val7) {
			widths[0] = val0;
			widths[1] = val1;
			widths[2] = val2;
			widths[3] = val3;
			widths[4] = val4;
			widths[5] = val5;
			widths[6] = val6;
			widths[7] = val7;
		}
	}
}