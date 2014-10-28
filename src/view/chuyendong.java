package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Image;
import javafx.stage.Screen;
import javax.swing.*;
import javax.swing.text.html.CSS;

public class chuyendong extends JFrame implements ActionListener {

   
    //JPanel jp1 = new JPanel();
    ImageIcon image = new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\Car.png");
    ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\ElevatorBackground.png");
    JLabel background = new JLabel();
    //Toa do cua panel
    int k = 1, z = 1;
    //Buoc nhay
    int X = 1, x = 1, Y = 1, m = 1;
    int sizeX = 640, sizeY = 640;
    int [] floor ;
    ElevatorUI elevator;
    public chuyendong() {
        super("chuyen dong");
        floor = new int[4];
        for(int i = 0;i<4;i++)
        {
            floor[i] = sizeY/4;
        }
        this.elevator = new ElevatorUI(1);
        this.setSize(sizeX,sizeY);
//this.setResizable(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(elevator);
        elevator.setLocation(0, 0);
        
        Thread t1;
        t1 = new Thread() {
            @Override
            public void run() {
                int index=0;
                try {
                    while (true) {
                        
                        /*
                        * Kiem tra va cham, neu di toi duong bien o 2 ben thi di nguoc lai
                        */
                        if (elevator.getX() == 00) {
                            X = -X;

                        }
                        if (elevator.getY() == 0 || elevator.getY() == sizeY-226) {
                            Y = -Y;
                        }
                        if(elevator.getY()==floor[index])
                        {
                            index+=1;
                            Thread.sleep(1000);
                            
                        }
                        //Di chuyen sang ngan
                        k += 0;
                        z += Y;
                        //thiet lap toa do moi
                        System.out.println(elevator.getY());
                        elevator.setLocation(k, z);
                        
                        //Luong tam dung
                        Thread.sleep(10);
                        if(index>=4) 
                            break;
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
