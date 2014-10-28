/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import model.Elevator;
import model.Floor;
import model.Request;
import view.IFloor;
import view.*;

/**
 *  Cài các hàm xử lí sự kiện của View
 * @author Nhuan
 */
public class Controller1 implements ActionListener {
 
    private Elevator[] elevators;
    private ArrayList<Floor>floors;
    private View view;
    private Strategy strategy;
    public Controller1()
    {
        strategy = new Strategy("Smart");
    }
    public void addElevator(Elevator e)
    {
        if(elevators.length==0)
        elevators[0]=e;
        else elevators[1] = e;
    }
    public void addFloor(Floor floor)
    {
        floors.add(floor);
    }
    public void addView(View view)
    {
        this.view = view;
    }
    public boolean addRequest(int elevator_id,Request request)
    {
        boolean addRequest = elevators[elevator_id].addRequest(request);
        if(addRequest==false) return false;
        //Kích hoạt thang máy
        elevators[elevator_id].setStatus(Elevator.ACTIVE);
        return addRequest;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    //sự kiến bấm nút gọi thang
    public void callButton_Click()
    {
        Request request = new Request(1,1);
        IFloor floor = new FloorUI();
        int elevator_id = strategy.findOptimalElevator(request, elevators);
        addRequest(elevator_id, request);
        floor.setFloor(request.getFloor());
        floor.setDirection(request.getDirection());
    }
    
}
