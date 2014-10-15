/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.View;
import model.Elevator;
import view.*;
/**
 *
 * @author Nhuan
 */
public class Controller implements ActionListener {
    private Elevator[] elevators;
    private int [] floors;
    private View view;
    public static final int MAX_FLOOR = 10;//số lượng tầng tối đa
    public Controller(int count_elevator)
    {
        
        elevators = new Elevator[count_elevator];
        //So tang duoc config
        floors = new int[5];
    }
    public void setView(View view)
    {
      this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
