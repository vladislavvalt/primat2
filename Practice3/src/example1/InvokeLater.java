package example1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InvokeLater extends JFrame {

    private JButton button;
    private JLabel  label;

    public InvokeLater() {
        super("InvokeLater");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        button = new JButton("Виконати складні обчислення");
        label  = new JLabel("Немає складної роботи : Thread-"+
                Thread.currentThread().getName());
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // запускаємо окремий поток
            new ComplexJobThread().start();
            label.setText("Почекайте...: Thread-"+
                Thread.currentThread().getName()); }
            });

        getContentPane(). setLayout(new FlowLayout());
        getContentPane().add(label);
        getContentPane().add(button);
        setSize(400, 100);
        setVisible(true);
    }
    // поток, виконує складну роботу
    class ComplexJobThread extends Thread {
        public void run() {
            try {
                for(int i=0; i<5; i++) {
                    sleep(500);
                    System.out.println(Thread.currentThread().getName());
                }

                /*Дуже груба помилка
                  ми звертаємося до Label з іншого потоку
                  label.setText("Робота завершена : Thread-"+
                        Thread.currentThread().getName());
                */ 
                
                // робота закінчена
                //і ми повертаємося в поток UI
                EventQueue.invokeLater(new Runnable() {
                public void run() {
                    label.setText("Робота завершена : Thread-"+
                        Thread.currentThread().getName());
                }});
                
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new InvokeLater(); // ПОГАНО
    }
}
