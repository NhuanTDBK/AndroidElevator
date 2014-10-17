package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.PriorityQueue;


/**
 *
 * @author Nhuan Lớp thang máy
 */
public class Elevator extends Observable{

    public static final int MAX_PASSENGER = 10; //số lượng hành khác tối đa trong thang máy
    public static final int UP = 1,   //hướng đi lên
                            DOWN = -1; //hướng đi xuống
    public static final int STOP = 0, //trạng thái dừng hoàn toàn, không làm việc. Sử dụng trong trường hợp đặc biệt
                            IDLE = 1, //trạng thái rảnh rỗi, không làm việc.
                            ACTIVE= 2,  //trạng thái đang làm việc. 
                            PAUSE = 3;//trạng thái tạm dừng. Dùng cho trường hợp đón, trả khách
    
    public static final int WEIGHT = 100;//trọng tải của thang máy( không có hành khách)
    public static final int WARNING_WEIGHT = 150;//trọng tải tối đa
    public ArrayList<Passenger> passengers;//Số hành khách trong thang
    public RequestQueue request;//Yêu cầu của hành khách
    public Door door;           //Cửa thang máy
    private int _id;            //id của thang máy. Dùng để phân biệt các thang máy
    private int _direction;     //Hướng đi của thang máy hiện tại
    private int _floor;         //Tầng 
    private int _status;        //trạng thái của thang máy
    private int _weight;        //trọng lượng thang máy
    private int _numofpassengers;//Tổng số khách trong 1 lượt đi
    public Elevator(int id) {
        System.out.println("Elevator "+id+" : online");
        this.door = new Door();
        this._id = id;
        passengers = new ArrayList<>();
        request = new RequestQueue();
        this._status = IDLE;
    }
    
    /**
     *  Trả về số hiệu thang máy
     * @return id
     */
    public int getID() {
        return _id;
    }
    
    /**
     *  
     * @return hướng di chuyển
     */
    public int getDirection()
    {
        return this._direction;
    }
    /**
     *
     * @return trả về số tầng hiện tại
     */
    public int getFloor() {
        return _floor;
    }

    /**
     *
     * @return trả về trạng thái hiện tại của thang máy
     */
    public int getStatus() {
        return _status;
    }

    /**
     *
     * @return trả về tổng trọng lượng: thang máy + hành khách
     */
    public int getTotalWeight() {
        return TotalWeight();
    }
    
    /**
     *
     * @return số hàng khách hiện tại
     */
    public int getNumberOfPassenger(){ return passengers.size();  }
    public void setDirection(int direction) {
        this._direction = direction;
    }

    /**
     *
     * @return 
     */
    public void setStatus(int status) {
        this._status = status;
    }
    public void setFloor(int floor) {this._floor = floor;}

    public void move(int floor) {
        System.out.println("Elevator"+this._id+": Thang may di "+convert(this.getDirection())+" tang "+floor);
        setStatus(Elevator.ACTIVE);
        setFloor(floor);
        //Cài đặt sau
    }
    public void pause()
    {
        System.out.println("Elevator" + this._id+": Thang may tam dung");
        setStatus(PAUSE);
    }
    public String convert(int direction)
    {
        return direction==Elevator.UP?"len":"xuong";
    }
    public boolean addRequest(Request request)
    {
        if(getStatus()!=ACTIVE) {
            setStatus(Elevator.ACTIVE);
        }
        boolean result = false;
        int floorToGo = request.getFloor();
        int direction = request.getDirection();
        if(direction==Elevator.UP)
        {
            result = this.request.addUp(floorToGo);
        }
        else if(direction==Elevator.DOWN)
        {
            result = this.request.addDown(floorToGo);
        }
        return result;
    }
     /*
     Thêm hàng khách, cài đặt lại khi có GUI
     */
    public boolean addPassenger(Passenger p) {
       /*if(getStatus()!=ACTIVE) {setStatus(Elevator.ACTIVE);}
        boolean result = false;
        int floorToGo = p.getFloor();
        int direction = p.getDirection();
        System.out.println("Elevator" + this._id+": them hanh khach di "+ convert(p.getDirection())+ " floor:"+floorToGo);
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
        result = result&&isOverweight();
        if(result==false) System.out.println("Error overloadiing");
        */
        boolean result = false;
        if(!this.passengers.add(p)) return result;
        result = this.addRequest(new Request(p.getFloor(),p.getDirection()));
        result = result && isOverweight();
        return result;
    }
    /*
        Cài đặt lại khi có GUI
    */
    public boolean removePassenger(int floor)
    {
        
        //if(getStatus()!=STOP) return false;
        int direction = this._direction;
        int total_exit_passenger = 0;
        for (Passenger p: passengers) {
                
            if(this._direction==p.getDirection()&&p.getFloor()==floor)
            {
                //Mở cửa cho ra
                System.out.println("Elevator"+this._id+": Hang khach di ra thang may ");
                passengers.remove(p);
                _weight-=p.getWeight();
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
      Hành khách vào sau cùng sẽ đi ra nếu thang máy quá tải
     */

    public boolean isOverweight() {
        int weight = getTotalWeight();
        return (weight >= WARNING_WEIGHT);
    }
}
