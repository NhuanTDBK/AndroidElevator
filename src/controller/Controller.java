/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;
import javax.swing.text.View;
import model.Elevator;
import model.Floor;
import model.Passenger;
import model.Request;
import view.*;

/**
 *
 * @author Nhuan
 */
public class Controller implements ActionListener {

    private Elevator elevators;
    public Floor[] floors;

    private View view;
    public static final int MAX_FLOOR = 10;//số lượng tầng tối đa

    public Controller() {

        elevators = new Elevator(1);
        //So tang duoc config
        floors = new Floor[Controller.MAX_FLOOR];
        /*
         * Khoi tao floor, gan so hieu
         */
        for (int i = 0; i < MAX_FLOOR; i++) {
            floors[i] = new Floor(i);
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * Đầu vào : Tầng hiện tại Đầu ra: Lấy tầng thấp nhất mà cao hơn tầng hiện
     * tại
     *
     * @param floorNow
     */
    public int getMinUp(int floorNow) {
        int min = Integer.MAX_VALUE;
        if (floorNow == MAX_FLOOR) {
            return min;
        }
        for (Floor temp : floors) {
            int floor = temp.id;
            
            if (floor >= floorNow && floor < min && !floors[floor].requests.isEmpty()) {
                min = floor;
                break;
            }
        }
        return min;
    }
    /*
     Đầu vào: Tầng hiện tại
     Đầu ra: Lấy tầng cao nhất mà nhỏ hơn tầng hiện tại
     */

    public int getMaxDown(int floorNow) {
        int max = 0;
        for (Floor temp : floors) {
            int floor = temp.id;
            if (floor <=floorNow && floor > max&&!floors[floor].requests.isEmpty()) {
                max = floor;
            }
        }
        return max;
    }

    /*
     Đầu ra: Kiểm tra tín hiệu từ các thang
     */
    public boolean isEmpty() {
        int i = 0;
        boolean check = true;
        while (i < Controller.MAX_FLOOR) {
            if (floors[i].requests.isEmpty()) {
                i++;
            } else {
                check = false;
                break;
            }

        }
        return check;
    }

    public boolean addRequest(int floorNow, int direction) {
        boolean check = true;
        if (floors[floorNow].requests.size() == 2) {
            System.out.println("Khong the bam them duoc");
        } else {
            System.out.println("Co yeu cau tu tang: " + floorNow);
            if (floorNow != Controller.MAX_FLOOR) {
                Request r = new Request(floorNow, direction);
                check = floors[floorNow].requests.add(r);
            } else if (floorNow== Controller.MAX_FLOOR-1) {
                check = floors[floorNow].requests.add(new Request(floorNow, Elevator.DOWN));
            } else if (floorNow == 0) {
                check = floors[floorNow].requests.add(new Request(floorNow, Elevator.UP));
            }
        }
        return check;
        //System.out.println(check);
    }
    /*
     Đầu vào: tầng hiện tại
     Đầu ra: Xóa yêu cầu ở tầng đó
     */

    public int removeRequest(int floorNow, int direction) {
        floors[floorNow].requests.removeIf((Request t) -> (t.getFloor() == floorNow && t.getDirection() == direction));
        return 1;
    }

    /*
     Them khach dung cho o tang
     */
    public boolean addPassenger(int floorNow, Passenger p) {
        boolean check = true;
        int direction = p.getDirection();
        if(p.getFloor()<=floorNow&&p.getDirection()==Elevator.UP) 
        {
            System.out.println("What's wrong with you?");
            return false;
        }
        if (!floors[floorNow].requests.contains(new Request(floorNow, direction))) {
            floors[floorNow].requests.add(new Request(floorNow, direction));
        }
        check = floors[floorNow].passengers.add(p);
        return check;
    }

    public boolean hasRequest(int floorNow, int direction) {
        
        Request t = new Request(floorNow, direction);
        return floors[floorNow].requests.stream().anyMatch((temp) -> (temp.compareTo(t) == 0));
    }
    //Đầu vào: Tầng hiện tại và hướng
    //Đầu ra: Danh sách khách chờ
    public ArrayList<Passenger> getPassengerFromFloor(int floor,int direction)
    {
        ArrayList<Passenger> temp = new ArrayList<>();
        for(Passenger p:floors[floor].passengers)
        {
            if(p.getDirection()==direction) 
            {
                temp.add(p);
                
            }
        }
        floors[floor].passengers.removeAll(temp);
        return temp;
    }

}
