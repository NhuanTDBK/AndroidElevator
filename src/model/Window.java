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
public class Window implements IDoor{
    public int _id;
    public String _name;
    public Window(int id, String name)
    {
        this._id = id;
        this._name = name;
    }
    public void open(){
        
    }
    public void close()
    {
        
    }
}
