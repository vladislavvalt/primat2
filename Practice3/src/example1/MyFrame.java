package example1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyFrame extends JFrame {

	private JPanel jContentPane = null;
	private JButton myButton = null;
    private JLabel myLabel = null;
	private int buttonPressedN = 0;

	private JButton getMyButton() {
		if (myButton == null) {
			myButton = new JButton();
			myButton.setText("Кнопка");
			myButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String msg = e.getActionCommand();
					buttonPressedN++;
					myLabel.setText("Кнопка натиснута " + buttonPressedN + " раз");
					System.out.println("actionPerformed() :" + msg);
                    System.out.println(Thread.currentThread().getName());
				}
			});

// Можем обрабатывать и нажатия кнопок на клавиатуре и др.,
// т.к. JButton наследует от Component
			myButton.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						buttonPressedN++;
						myLabel.setText("Кнопка Enter" + buttonPressedN + " раз");
					}
					System.out.print  ("keyPressed()");
                    System.out.println(Thread.currentThread().getName());
				}
			});
		}
		return myButton;
	}

	public static void main(String[] args) {

// Виклик переданого методу з потоку розсилки
		
			public void run() {
                System.out.println(Thread.currentThread().getName());
				MyFrame thisClass = new MyFrame();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	private void initialize() {
		this.setSize(300, 200);
		this.setName("Что это?");
		this.setContentPane(getJContentPane());
		this.setTitle("Якесь вікно");
	}
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			myLabel = new JLabel();
			myLabel.setText("Текст");
			myLabel.setPreferredSize(new Dimension(250, 30));
			myLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			
			
			jContentPane.setLayout(new FlowLayout());
			jContentPane.add(myLabel, null);
			jContentPane.add(getMyButton(), null);
		}
		return jContentPane;
	}
}
