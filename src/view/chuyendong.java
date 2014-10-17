package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Image;
import javax.swing.*;
import javax.swing.text.html.CSS;

public class chuyendong extends JFrame implements ActionListener {

    JPanel jp = new JPanel();
    JPanel jp1 = new JPanel();
    ImageIcon image = new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\Car.png");
    ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\ElevatorBackground.png");
    JLabel background = new JLabel();
    int k = 40, z = 40;
    int X = 1, x = 1, Y = 1, m = 1;

    public chuyendong() {
        super("chuyen dong");
        this.setSize(400, 400);
        this.setResizable(true);
        this.setLayout(new CardLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp.setSize(image.getIconWidth(), image.getIconHeight());
        jp.setBackground(Color.YELLOW);
        jp.setLocation(0, 0);
        jp.add(new JLabel(image));
        jp1.add(new JLabel(backgroundImage));
        jp1.setSize(400,400);
        
        this.add(jp1);
        this.add(jp);   
        this.repaint();
        this.validate();
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
        chuyendong cd = new chuyendong();
        cd.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JOptionPane.showConfirmDialog(null, "dieu khien");
    }
}
