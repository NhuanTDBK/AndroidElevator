/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import model.*;
import controller.*;
import view.*;
/**
 *
 * @author Nhuan
 */
public class ElevatorSystem {
    /*
        Trạng thái của thang máy 
    */
    private Elevator [] elevators;
    /*
        Giao diện 
    */
    private View view;
    /*
        Điều khiển
    */
    private Controller1 controller;
    public ElevatorSystem()
    {
        elevators = new Elevator[2];
        view = new View();
        controller = new Controller1();
        for(int i = 0;i<2;i++)
        {
            elevators[i]=new Elevator(i);
            /*
                Liên kết Model-View
            */
   
        }
        
    }
    public static void main(String []args)
    {
        ElevatorSystem elevatorSystem = new ElevatorSystem();
    }
    
}
