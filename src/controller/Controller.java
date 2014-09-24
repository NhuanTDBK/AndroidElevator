/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import model.Elevator;
import view.*;
/**
 *
 * @author Nhuan
 */
public class Controller {
    private Elevator[] elevators;
    public Controller(int count_elevator)
    {
        elevators = new Elevator[count_elevator];
    }
    
}
