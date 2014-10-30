/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Nhuan
 */
public class ManPassenger extends Passenger{
    private static int WEIGHT = 70;
    public ManPassenger(int floor, int direction) {
        super(floor, direction);
        this.setWeight(WEIGHT);
    }
    
    
}
