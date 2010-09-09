import java.awt.*;
import javax.swing.*;

public class DialerFrame extends JFrame {
	Button btn0 = new Button();
	Button btn1 = new Button();
	Button btn2 = new Button();
	Button btn3 = new Button();
	Button btn4 = new Button();
	Button btn5 = new Button();
	Button btn6 = new Button();
	Button btn7 = new Button();
	Button btn8 = new Button();
	Button btn9 = new Button();
	Button btnStar = new Button();
	Button btnHash = new Button();
	Button btnA = new Button();
	Button btnB = new Button();
	Button btnC = new Button();
	Button btnD = new Button();
	
	public void init () {
	}
	
	public DialerFrame (String title) {
		super(title);
		setSize(175, 175);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void createButtonPanel() {
		btn0.setLabel("0");
		btn1.setLabel("1");
		btn2.setLabel("2");
		btn3.setLabel("3");
		btn4.setLabel("4");
		btn5.setLabel("5");
		btn6.setLabel("6");
		btn7.setLabel("7");
		btn8.setLabel("8");
		btn9.setLabel("9");
		btnA.setLabel("A");
		btnB.setLabel("B");
		btnC.setLabel("C");
		btnD.setLabel("D");
		btnStar.setLabel("*");
		btnHash.setLabel("#");
		
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(4,4));
		bPanel.add(btn7, null);
		bPanel.add(btn8, null);
		bPanel.add(btn9, null);
		bPanel.add(btnA, null);
		bPanel.add(btn4, null);
		bPanel.add(btn5, null);
		bPanel.add(btn6, null);
		bPanel.add(btnB, null);
		bPanel.add(btn1, null);
		bPanel.add(btn2, null);
		bPanel.add(btn3, null);
		bPanel.add(btnC, null);
		bPanel.add(btnStar, null);
		bPanel.add(btn0, null);
		bPanel.add(btnHash, null);
		bPanel.add(btnD, null);
		
		this.add(bPanel);
	}
}