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
public class WomanPassenger extends Passenger{
    
    private static int WEIGHT = 50;
    public WomanPassenger(int floor, int direction) {
        super(floor, direction);
        this.setWeight(WEIGHT);
    }
       @Override
    public void getOutElevator(Elevator e) {
        super.getOutElevator(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getInElevator(Elevator e) {
        super.getInElevator(e); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void waitElevator() {
        super.waitElevator(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move() {
        super.move(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
