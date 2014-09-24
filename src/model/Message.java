package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Gui thong diep 
 * @author Nhuan
 */
public class Message {
    
    private int _id;
    private int _direction;
    private int _floor;
    public Message(int id,int direction,int floor)
    {
        this._id = id;
        this._direction = direction;
        this._floor = floor;
    }
}
