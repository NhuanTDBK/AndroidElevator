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
     * Hàm di chuyển lên/xuống 1 tầng
     * @param floorNow
     * @param direction
     */
    
    void move(int floorNow);
    /*
        IPassenger hiểu như là giao diện của 1 hành khách, thực tế là 1 panel gì đó
    */

    /**
     *  
     * Thêm hành khách vào thang máy
     * @param passenger
     */
    
    boolean addPassenger(IPassenger passenger);
   
    /**
     *  Cho hành khách đi ra
     * @param passenger
     */
    void removePassenger();
    
}
