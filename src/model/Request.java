/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Nhuan
 */
public class Request {
    private int direction;
    private int floor;
    //construct direction and floor
    public Request(int d, int f)
    {
        this.direction = d;
        this.floor = f;
    }
    public int getDirection()
    {
        return this.direction;
    }
    public int getFloor()
    {
        return this.floor;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    public void setFloor(int floor)
    {
        this.floor = floor;
    }
}
    
