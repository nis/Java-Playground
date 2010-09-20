import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dialer {
	String windowTitle = "Test";
	JFrame mFrame;
	
	JPanel dPanel;
	
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
	
	MySound lyd = new MySound();
	
	public static void main (String[] args) {
		Dialer d = new Dialer();
		d.init();
	}
	
	public void init() {
		mFrame = makeFrame();
		dPanel = makePanel();
		mFrame.setFocusable(true);
		mFrame.add(dPanel);
		
		addListeners();
		
		mFrame.setVisible(true);
	}
	
	public void addListeners() {
		
		class KeyListener extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
		        case '0': lyd.s0(100, 100); break;
		        case '1': lyd.s1(100, 100); break;
		        case '2': lyd.s2(100, 100); break;
		        case '3': lyd.s3(100, 100); break;
		        case '4': lyd.s4(100, 100); break;
		        case '5': lyd.s5(100, 100); break;
		        case '6': lyd.s6(100, 100); break;
		        case '7': lyd.s7(100, 100); break;
		        case '8': lyd.s8(100, 100); break;
		        case '9': lyd.s9(100, 100); break;
		        case '#': lyd.sHash(100, 100); break;
		        case '*': lyd.sStar(100, 100); break;
		        case 'a':
		        case 'A': lyd.sA(100, 100); break;
		        case 'b':
		        case 'B': lyd.sB(100, 100); break;
		        case 'c':
		        case 'C': lyd.sC(100, 100); break;
		        case 'd':
		        case 'D': lyd.sD(100, 100); break;
		        default:
		      }
			}
		}
		
		class ButtonListener implements ActionListener {
			public void actionPerformed (ActionEvent event) {
				String st = "";
				if (event.getSource() == btn0) { 
					st = "0";
					lyd.s0(100, 100);
				}
				if (event.getSource() == btn1) {
					st = "1";
					lyd.s1(100, 100);
				}
				if (event.getSource() == btn2) { 
					st = "2";
					lyd.s2(100, 100);
				}
				if (event.getSource() == btn3) { 
					st = "3";
					lyd.s3(100, 100);
				}
				if (event.getSource() == btn4) { 
					st = "4";
					lyd.s4(100, 100);
				}
				if (event.getSource() == btn5) { 
					st = "5";
					lyd.s5(100, 100);
				}
				if (event.getSource() == btn6) { 
					st = "6";
					lyd.s6(100, 100);
				}
				if (event.getSource() == btn7) { 
					st = "7";
					lyd.s7(100, 100);
				}
				if (event.getSource() == btn8) { 
					st = "8";
					lyd.s8(100, 100);
				}
				if (event.getSource() == btn9) { 
					st = "9";
					lyd.s9(100, 100);
				}
				if (event.getSource() == btnA) { 
					st = "A";
					lyd.sA(100, 100);
				}
				if (event.getSource() == btnB) { 
					st = "B";
					lyd.sB(100, 100);
				}
				if (event.getSource() == btnC) { 
					st = "C";
					lyd.sC(100, 100);
				}
				if (event.getSource() == btnD) { 
					st = "D";
					lyd.sD(100, 100);
				}
				if (event.getSource() == btnStar) { 
					st = "*";
					lyd.sStar(100, 100);
				}
				if (event.getSource() == btnHash) { 
					st = "#";
					lyd.sHash(100, 100);
				}
				statusLabel.setText("Status: " + st);
			}
		}
		
		btn0.addActionListener(new ButtonListener());
		btn1.addActionListener(new ButtonListener());
		btn2.addActionListener(new ButtonListener());
		btn3.addActionListener(new ButtonListener());
		btn4.addActionListener(new ButtonListener());
		btn5.addActionListener(new ButtonListener());
		btn6.addActionListener(new ButtonListener());
		btn7.addActionListener(new ButtonListener());
		btn8.addActionListener(new ButtonListener());
		btn9.addActionListener(new ButtonListener());
		btnA.addActionListener(new ButtonListener());
		btnB.addActionListener(new ButtonListener());
		btnC.addActionListener(new ButtonListener());
		btnD.addActionListener(new ButtonListener());
		btnHash.addActionListener(new ButtonListener());
		btnStar.addActionListener(new ButtonListener());
		
		btn0.addKeyListener(new KeyListener());
		btn1.addKeyListener(new KeyListener());
		btn2.addKeyListener(new KeyListener());
		btn3.addKeyListener(new KeyListener());
		btn4.addKeyListener(new KeyListener());
		btn5.addKeyListener(new KeyListener());
		btn6.addKeyListener(new KeyListener());
		btn7.addKeyListener(new KeyListener());
		btn8.addKeyListener(new KeyListener());
		btn9.addKeyListener(new KeyListener());
		btnA.addKeyListener(new KeyListener());
		btnB.addKeyListener(new KeyListener());
		btnC.addKeyListener(new KeyListener());
		btnD.addKeyListener(new KeyListener());
		btnHash.addKeyListener(new KeyListener());
		btnStar.addKeyListener(new KeyListener());
		
		dPanel.addKeyListener(new KeyListener());
		
		mFrame.addKeyListener(new KeyListener());
	}
	
	public JPanel makePanel() {
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
		
		statusLabel.setText("Status: ");
		
		dPanel = new JPanel();
		dPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		dPanel.add(btn7, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		dPanel.add(btn8, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		dPanel.add(btn9, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 0;
		dPanel.add(btnA, c);
		
		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		dPanel.add(btn4, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		dPanel.add(btn5, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		dPanel.add(btn6, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 1;
		dPanel.add(btnB, c);
		
		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		dPanel.add(btn1, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 2;
		dPanel.add(btn2, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		dPanel.add(btn3, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 2;
		dPanel.add(btnC, c);
		
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		dPanel.add(btnStar, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		dPanel.add(btn0, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 3;
		dPanel.add(btnHash, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 3;
		dPanel.add(btnD, c);


		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.ipady = 4;
		dPanel.add(statusLabel, c);
		
		return dPanel;
	}
	
	public JFrame makeFrame() {
		JFrame frame = new JFrame(windowTitle);
		
		frame.setSize(175, 175);
		frame.setLocation(450,250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return frame;
	}
}