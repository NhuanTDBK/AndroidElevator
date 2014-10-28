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
public interface IElevator extends IDoor {
    
    /**
     * Hàm di chuyển
     * @param floorNow
     * @param direction
     */
    
    void move(int floorNow,int direction);
    /*
        IPassenger hiểu như là giao diện của 1 hành khách, thực tế là 1 panel gì đó
    */

    /**
     *  
     * Thêm hành khách vào thang máy
     * @param passenger
     */
    
    void addPassenger(IPassenger passenger);
   
    /**
     *  Cho hành khách đi ra
     * @param passenger
     */
    void removePassenger(IPassenger passenger);
    
}
