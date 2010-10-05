import java.awt.*;
import javax.swing.*;
import java.util.Random;

public class MyListener implements Runnable {
	JFrame lFrame;
	FreqEqComponent fEQ = new FreqEqComponent(0, 10, 20, 30, 40, 50, 60, 70);
	Random rand = new Random();
	
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
	}
	
	public void repaintGraph() {
		int n = 100;
		for (int i = 0; i < 8; i++) {
			fEQ.setMyWidth(i, rand.nextInt(n+1));
		}
		fEQ.repaint();
	}
	
	public JFrame makeFrame() {
		JFrame frame = new JFrame("Listener");
		
		frame.setSize(175, 175);
		frame.setLocation(650,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return frame;
	}
}