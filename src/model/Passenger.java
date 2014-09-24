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
public class Passenger {
    private int _floor;//Destination floor
    private int _direction;
    private Passenger _passenger;
    private int _weight;
    public Passenger(int floor, int direction)
    {
        this._weight = 40;
        this._floor = floor;
        this._direction = direction;
    }
    public Passenger(int floor,int direction,int weight)
    {   
        
        this._floor = floor;
        this._direction = direction;
        this._weight = weight;
    }
    public int getFloor() {
        return _floor;
    }
    public int getDirection()
    {
        return _direction;
    }
    public int getWeight()
    {
        return _weight;
    }
    public void setFloor(int floor)
    {
        _floor = floor;
    }
    public void setDirection(int direction)
    {
        _direction = direction;
    }
    public void setWeight(int w)
    {
        _weight = w;
    }
}