/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.text.html.CSS;

public class DemoLayerPanel extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    JLayeredPane lPanel = new JLayeredPane();
    ImageIcon image = 
            new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\Car.png");
    ImageIcon backgroundImage = 
            new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\ElevatorBackground.png");
    JLabel background = new JLabel(backgroundImage);
    JLabel elevator = new JLabel(image);
    int k = 40, z = 40;
    int X = 1, x = 1, Y = 1, m = 1;

    public DemoLayerPanel() {
        super("chuyen dong");
        this.setSize(400, 400);
        this.setResizable(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp.setSize(image.getIconWidth(), image.getIconHeight());
        jp.setBackground(Color.YELLOW);
        jp.setLocation(0, 0);
        jp.add(new JLabel(image));
        //jp1.add(new JLabel(backgroundImage));
        //jp1.setSize(400,400);
        
        //this.add(jp1);
        //this.add(jp);   
        lPanel.add(new JLabel(backgroundImage), 0,1);
        lPanel.add(new JLabel(image),1,2);
       
        lPanel.setVisible(true);
        lPanel.setBackground(Color.red);
        this.add(lPanel);
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        /*
                         * Kiem tra va cham, neu di toi duong bien o 2 ben thi di nguoc lai
                         */
                        if (jp.getX() == 00) {
                            X = -X;

                        }
                        if (jp.getY() == 0 || jp.getY() == 200) {
                            Y = -Y;
                        }
                        //Di chuyen sang ngan
                        k += 0;
                        z += Y;
                        //thiet lap toa do moi
                        System.out.println(jp.getY());
                        jp.setLocation(k, z);
                        
                            //Luong tam dung
                        Thread.sleep(30);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t1.start();
        
}

    public static void main(String[] arg) {
        DemoLayerPanel cd = new DemoLayerPanel();
        cd.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showConfirmDialog(null, "dieu khien");
    }
}
