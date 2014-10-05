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
public interface IElevator {
    public void move(int floor);//Thang máy di chuyển
    public void pause();        //Thang máy dừng tạm thời đón/trả khách
    public void stop();         //Thang máy dừng hẳn
}
