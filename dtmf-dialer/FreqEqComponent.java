import java.awt.*;
import javax.swing.*;

public class FreqEqComponent extends JComponent {
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