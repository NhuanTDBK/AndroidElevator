/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.Observable;
import java.util.Observer;
import model.Elevator;



/**
 *
 * @author Nhuan
 */
public class View implements Observer{
    final int HAS_REQUEST = 4;
    IElevator elevator1 = new ElevatorUI(0);
    IElevator elevator2 = new ElevatorUI(1);
    ElevatorUI e1 = new ElevatorUI(1);
    Thread t1 = new Thread();
    Thread t2 = new Thread();
    @Override
    public void update(Observable o, Object arg) {
        if((Integer)arg==HAS_REQUEST)
        {
            Elevator elevator = (Elevator)o;
            e1.run();
        }
    }

 
    
}
