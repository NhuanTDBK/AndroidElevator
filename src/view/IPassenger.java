/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

/**
 *
 * @author Nhuan
 */
public interface IPassenger {
    public void move();//người di chuyển từ hành lang vào thang máy và ngược lại
    public void getInElevator();//Người di chuyển vào thang máy
    public void getOutElevator();//Người di chuyển ra thang máy
    public void waitElevator();//Đứng đợi thang
}
