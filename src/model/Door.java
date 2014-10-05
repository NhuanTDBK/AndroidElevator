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
public class Door implements IDoor{
    private int _id;
    private int opened;
    private Window doorLeft;
    private Window doorRight;
    public Door()
    {
        doorLeft = new Window(0,"left");
        doorRight = new Window(1,"right");
    }

    @Override
    public void open() {
        doorLeft.open();
        doorRight.open();
        System.out.println("Mo cua thang may");
    }

    @Override
    public void close() {
        doorLeft.close();
        doorRight.close();
        System.out.println("Dong cua thang may");
    }
}
