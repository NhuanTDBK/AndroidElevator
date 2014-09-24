package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nhuan Lớp thang máy
 */
public class Elevator {

    public static final int MAX_PASSENGER = 10; //số lượng hành khác tối đa trong thang máy
    public static final int UP = 1,   //hướng đi lên
                            DOWN = 0; //hướng thái đi xuống
    public static final int STOP = 0, //trạng thái dừng hoàn toàn, không làm việc. Sử dụng trong trường hợp đặc biệt
                            IDLE = 1, //trạng thái rảnh rỗi, không làm việc.
                            MOVE= 2,  //trạng thái đang làm việc. 
                            PAUSE = 3;//trạng thái tạm dừng. Dùng cho trường hợp đón, trả khách
    public static final int MAX_FLOOR = 6;//số lượng tầng tối đa
    public static final int WEIGHT = 100;//trọng tải của thang máy( không có hành khách)
    public static final int WARNING_WEIGHT = 2000;//trọng tải tối đa
    public ArrayList<Passenger> passengers;//Số hành khách trong thang
    public RequestQueue request;//Yêu cầu của hành khách
    private int _id;            //id của thang máy.
    private int _direction;     //Hướng đi của thang máy hiện tại
    private int _floor;      //Tầng 
    private int _status;
    private int _weight;
    private int _numofpassengers;
    public Elevator(int id) {
        System.out.println("Elevator "+id+" : online");
        this._id = id;
        passengers = new ArrayList<>();
        request = new RequestQueue();
        this._status = IDLE;
    }

    public int getID() {
        return _id;
    }

    public String getDirection() {
        return (_direction==UP)?"len":"xuong";
    }

    public int getFloor() {
        return _floor;
    }

    public int getStatus() {
        return _status;
    }

    public int getTotalWeight() {
        return TotalWeight();
    }
    public int getNumberOfPassenger(){ return passengers.size();  }
    public void setDirection(int direction) {
        this._direction = direction;
    }

    public void setStatus(int status) {
        this._status = status;
    }
    public void setFloor(int floor) {this._floor = floor;}

    public void move(int floor) {
        System.out.println("Elevator"+this._id+": Thang may di "+this.getDirection()+" tang "+floor);
        setStatus(MOVE);
        setFloor(floor);
        //Cài đặt sau
    }
    public void pause()
    {
        System.out.println("Elevator" + this._id+": Thang may tam dung");
        setStatus(PAUSE);
    }

    /*
     Thêm hàng khách, cài đặt lại khi có GUI
     */
    public boolean addPassenger(Passenger p) {
       if(getStatus()!=MOVE) {setStatus(MOVE);}
        boolean result = isOverweight();
        int floorToGo = p.getFloor();
        int direction = p.getDirection();
        System.out.println("Elevator" + this._id+": them hanh khach di "+this.getDirection()+" floor:"+floorToGo);
        if (result == true) {
            return false;
        } else {
            if(!passengers.add(p)) return false;
            if (p.getDirection() == UP) {
                result = request.addUp(floorToGo);
            } else if (p.getDirection() == DOWN) {
                result = request.addDown(floorToGo);
            }
        }
        return result;
    }
    /*
        Cài đặt lại khi có GUI
    */
    public boolean removePassenger()
    {
        
        if(getStatus()!=PAUSE) return false;
        int floor = getFloor();
        int total_exit_passenger = 0;
        Passenger p ;
        /*
           Cho khách đi ra khi số tầng thang máy đang dừng trùng với số tấng khách muốn đến. 
        */
        for (Iterator<Passenger> it = passengers.iterator(); it.hasNext();) {
            p = it.next();
            //Kiểm tra những ai yêu cầu tới tầng này
            if(p.getFloor()==floor)
            {
                //Mở cửa cho ra
                System.out.println("Elevator"+this._id+": Hang khach di ra thang may ");
                System.out.print(" " +p.getFloor());
                passengers.remove(p);
                total_exit_passenger +=1;
            }
        }
        return true;
    }
    /*
     Tính toán tổng khối lượng thang máy
     */

    public int TotalWeight() {
        int result = WEIGHT;
        Passenger p;
        for (Iterator<Passenger> it = passengers.iterator(); it.hasNext();) {
            p = it.next();
            result += p.getWeight();
        }
        return result;
    }
    /*
     Trả về đúng nếu trọng tải thang máy vượt mức cho phép
     */

    public boolean isOverweight() {
        int weight = getTotalWeight();
        return (weight >= WARNING_WEIGHT);
    }
}
