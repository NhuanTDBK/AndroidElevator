/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Elevator;
import view.IElevator;
import view.IPassenger;

/**
 *
 * @author Nhuan
 */
public class ElevatorUI extends JPanel implements Runnable,IElevator {

    private ImageIcon image = new ImageIcon("C:\\Users\\Nhuan\\Documents\\NetBeansProjects\\AndroidElevator\\src\\view\\Images\\Car.png");
    private Elevator elevator;
    private int step = 1, z = 1;

    public ElevatorUI(int id) {
        int WIDTH = image.getIconWidth();
        int HEIGHT = image.getIconHeight();
        this.add(new JLabel(image));
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(0, 0);
        this.elevator = new Elevator(id);
    }

   

    @Override
    public void run() {
        while (true) {
            try {
                if (this.getY() == 0 || this.getY() == 400) {
                    step = -step;
                }
                z += step;
                this.setLocation(1, z);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(ElevatorUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    

    @Override
    public void open() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move(int floorNow) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addPassenger(IPassenger passenger) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public void removePassenger() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

}
