package model;

import java.util.ArrayList;

/**
 *
 * @author Nhuan
 */
public class Floor{ 

    public int id;//vi tri cua tang
    public ArrayList<Request> requests;
    public ArrayList<Passenger> passengers;
  
    public Floor(int id) {
        this.id = id;
        this.requests = new ArrayList<>(2);
        this.passengers = new ArrayList<Passenger>();
    }
}
