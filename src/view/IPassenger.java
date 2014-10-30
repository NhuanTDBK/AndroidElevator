/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import model.Elevator;

/**
 *
 * @author Nhuan
 */
public interface IPassenger {
    public void move();//người di chuyển từ hành lang vào thang máy và ngược lại
    public void getInElevator(Elevator e);//Người di chuyển vào thang máy
    public void getOutElevator(Elevator e);//Người di chuyển ra thang máy
    public void waitElevator();//Đứng đợi thang
}
