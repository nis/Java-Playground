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
	
	JLabel statusLabel = new JLabel();
	
	public void init () {
	}
	
	public DialerFrame (String title) {
		super(title);
		setSize(175, 175);
		setLocation(450,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void createButtonPanel(String t) {
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
		
		statusLabel.setText(t);
		//statusLabel.setHorizontalAlignment(JTextField.RIGHT);
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		bPanel.add(btn7, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		bPanel.add(btn8, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		bPanel.add(btn9, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		bPanel.add(btnA, c);
		
		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		bPanel.add(btn4, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		bPanel.add(btn5, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		bPanel.add(btn6, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 1;
		bPanel.add(btnB, c);
		
		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		bPanel.add(btn1, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		bPanel.add(btn2, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		bPanel.add(btn3, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 2;
		bPanel.add(btnC, c);
		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		bPanel.add(btnStar, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		bPanel.add(btn0, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 3;
		bPanel.add(btnHash, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 3;
		bPanel.add(btnD, c);


		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.ipady = 4;
		bPanel.add(statusLabel, c);
		
		this.add(bPanel);
		
		
		// JPanel bPanel = new JPanel();
		// bPanel.setLayout(new GridLayout(4,4));
		// bPanel.add(btn7, null);
		// bPanel.add(btn8, null);
		// bPanel.add(btn9, null);
		// bPanel.add(btnA, null);
		// bPanel.add(btn4, null);
		// bPanel.add(btn5, null);
		// bPanel.add(btn6, null);
		// bPanel.add(btnB, null);
		// bPanel.add(btn1, null);
		// bPanel.add(btn2, null);
		// bPanel.add(btn3, null);
		// bPanel.add(btnC, null);
		// bPanel.add(btnStar, null);
		// bPanel.add(btn0, null);
		// bPanel.add(btnHash, null);
		// bPanel.add(btnD, null);
		// 
		// this.add(bPanel);
	}
	
	public void createStatusLabel (String t) {
		//JLabel label = new JLabel(t);
		
		this.add(new JLabel(t));
	}
}