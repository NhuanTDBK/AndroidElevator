package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nhuan
 * Lớp hành khách
 * Có yêu cầu đi tới số tầng và hướng di chuyển
 * Ở đây đặt nó là trừu tượng vì sau này chúng ta sẽ thiết kế 1 lớp PassengerGUI, có các thông số chi tiết hơn 
 * như chiều cao, hoạt ảnh, cử động ra sao?
 */
public class Passenger extends Request {
    private int _weight=40;
    public Passenger(int floor, int direction)
    {
        super(floor,direction);
    }
    public Passenger(int floor,int direction,int weight)
    {   
        
        super(floor,direction);
        this._weight = weight;
    }
    public int getWeight()
    {
        return this._weight;
    }
    public void setWeight(int w)
    {
        _weight = w;
    }
}