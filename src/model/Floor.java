package model;

import controller.Controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nhuan
 */
public class Floor{ 

    public int id;//vi tri cua tang
    public Set<Request> requests;
    public ArrayList<Passenger> passengers;
  
    public Floor(int id) {
        this.id = id;
        this.requests = new HashSet<>(2);
        this.passengers = new ArrayList<Passenger>();
    }
 
    /*
        Them yeu cau vao tang
    */
    public boolean addRequest(int direction) {
        boolean check=true;
        if (requests.size() == 2) {
            System.out.println("Khong the bam them duoc");
        } else {
            System.out.println("Co yeu cau tu tang: " + id);
            if (id != Controller.MAX_FLOOR) {
                Request r = new Request(id,direction);
                check = requests.add(r);
            } else if (id == Controller.MAX_FLOOR) {
                check = requests.add(new Request(id,Elevator.DOWN));
            } else if (id == 1) {
                check = requests.add(new Request(id,Elevator.UP));
            }
        }
        return check;
       //System.out.println(check);
    }
    /*
        Them khach dung cho o tang
    */
    public boolean addPassenger(Passenger p )
    {
        boolean check=true;
        check = this.passengers.add(p);
        return check;
    }
    public boolean removeRequest()
    {
        boolean check=true;
        return check;
    }
    public boolean isEmptyRequest()
    {
        return requests.isEmpty();
    }
    

}
