/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Comparator;

/**
 * Đối tượng request chứa thông tin tầng di chuyển và hướng
 * @author Nhuan
 */
public class Request implements Comparable<Request> {
    private int direction;
    private int floor;
    //construct direction and floor
    public Request(int floor, int direction)
    {
        this.direction = direction;
        this.floor = floor;
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

    @Override
    public int compareTo(Request o) {
        if(o.getDirection()==this.direction&&o.getFloor()==this.floor)
            return 0;
        else return 1;
        
    }
}
    
