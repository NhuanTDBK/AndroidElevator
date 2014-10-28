/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Elevator;
import model.Floor;
import view.View;

/**
 *  Cài các hàm xử lí sự kiện của View
 * @author Nhuan
 */
public class Controller1 {
 
    private ArrayList<Elevator>elevators;
    private ArrayList<Floor>floors;
    private View view;
    public Controller1()
    {
        elevators = new ArrayList<>();
        floors = new ArrayList<>();
    }
    public void addElevator(Elevator e)
    {
        elevators.add(e);
    }
    public void addFloor(Floor floor)
    {
        floors.add(floor);
    }
    public void addView(View view)
    {
        this.view = view;
    }
    
}
