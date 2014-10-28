package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 *
 * @author Nhuan Lớp thang máy
 */
public class Elevator extends Observable {

    public static final int MAX_PASSENGER = 10; //số lượng hành khác tối đa trong thang máy
    public static final int UP = 1, //hướng đi lên
            DOWN = -1; //hướng đi xuống
    public static final int STOP = 0, //trạng thái dừng hoàn toàn, không làm việc. Sử dụng trong trường hợp đặc biệt
            IDLE = 1, //trạng thái rảnh rỗi, không làm việc.
            ACTIVE = 2, //trạng thái đang làm việc. 
            PAUSE = 3;//trạng thái tạm dừng. Dùng cho trường hợp đón, trả khách

    public static final int WEIGHT = 100;//trọng tải của thang máy( không có hành khách)
    public static final int WARNING_WEIGHT = 150;//trọng tải tối đa
    public ArrayList<Passenger> passengers;//Số hành khách trong thang
    public RequestQueue request; //Yêu cầu của hành khách
    public Door door;            //Cửa thang máy
    private final int _id;       //id của thang máy. Dùng để phân biệt các thang máy
    private int _direction;      //Hướng đi của thang máy hiện tại
    private int _floor;          //Tầng 
    private int _status;         //trạng thái của thang máy
    private int _weight;         //trọng lượng thang máy
    private int _numofpassengers;//Tổng số khách trong 1 lượt đi

    public Elevator(int id) {
        System.out.println("Elevator " + (id + 1) + " : online");
        this.door = new Door();
        this._id = id;
        passengers = new ArrayList<>();
        request = new RequestQueue();
        this._status = IDLE;
    }

    /**
     * Trả về số hiệu thang máy
     *
     * @return id
     */
    public int getID() {
        return _id;
    }

    /**
     *
     * @return hướng di chuyển
     */
    public int getDirection() {
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
    public int getNumberOfPassenger() {
        return passengers.size();
    }

    public void setDirection(int direction) {
        this._direction = direction;
    }

    /*
        Thông báo thông tin trạng thái thang
    */
    public void setStatus(int status) {
        this._status = status;
        setChanged();
        notifyObservers();
    }
    /*
        Thông báo tầng hiện tại của thang
    */
    public void setFloor(int floor) {
        this._floor = floor;
        setChanged();
        notifyObservers();
    }

    public void move(int direction) {
        int floorNow = this.getFloor();
        if (direction == UP) {
            floorNow++;
        } else {
            floorNow--;
        }
        System.out.println("Elevator" + this._id + ": Thang may di " + convert(direction) + " tang " + floorNow);

        this.setFloor(floorNow);
    }
    /*
     Đầu vào: Các yêu cầu từ các tầng hiện tại (Liên tục cập nhật)
     Chức năng: Đi đến tầng có yêu cầu
     */

    public void work() throws InterruptedException {

        int floorNow = this.getFloor();
        while (request.getSize() != 0) {
            while (!request.isUpRequestEmpty()) {
                int destination = request.getMinUp();
                int direction = (destination - floorNow) >= 0 ? UP : DOWN;
                while (floorNow != destination) {
                    floorNow += 1*direction;
                    this.move(direction);
                }
                this.door.open();
                this.setStatus(Elevator.PAUSE);
                /*
                while(this.getStatus()!=Elevator.PAUSE)
                {
                    //wait();
                    //notify();
                }
                */
                this.door.close();
                this.request.removeUp(floorNow);
            }
            while (!request.isDownRequestEmpty()) {
                int destination = request.getMaxDown();
                int direction = (destination - floorNow) >= 0 ? UP : DOWN;
                while (floorNow != destination) {
                    floorNow += 1*direction;
                    this.move(direction);
                }
                this.door.open();
                /*
                while(this.getStatus()!=Elevator.PAUSE)
                {
                    //wait();
                    //notify();
                }
                */
                this.door.close();
                this.request.removeDown(floorNow);
            }

        }
        //Cài đặt sau
    }

    public String convert(int direction) {
        return direction == Elevator.UP ? "len" : "xuong";
    }

    /**
     * Add request vào queue
     *
     * @param request
     * @return
     */
    public boolean addRequest(Request request) {
        if (getStatus() != ACTIVE) {
            setStatus(Elevator.ACTIVE);
        }
        boolean result = false;
        int floorToGo = request.getFloor();
        int direction = request.getDirection();
        if (direction == Elevator.UP) {
            result = this.request.addUp(floorToGo);
        } else if (direction == Elevator.DOWN) {
            result = this.request.addDown(floorToGo);
        }
        return result;
    }

    /**
     * Thêm hàng khách có thiết lập rule, cài đặt lại khi có GUI
     * @param p
     * @return
     */
    public boolean addPassenger(Passenger p) {

        boolean result = false;
        if (!this.passengers.add(p)) {
            return result;
        }
        result = this.addRequest(new Request(p.getFloor(), p.getDirection()));
        result = result && isOverweight();
        return result;
    }
    /*
    
     */

    /**
     * Cho hành khách đi ra khỏi thang
     * @param floor
     * @return
     */
    public boolean removePassenger(int floor) {

        //if(getStatus()!=STOP) return false;
        int direction = this._direction;
        int total_exit_passenger = 0;
        for (Passenger p : passengers) {

            if (this._direction == p.getDirection() && p.getFloor() == floor) {
                //Mở cửa cho ra
                System.out.println("Elevator" + this._id + ": Hang khach di ra thang may ");
                passengers.remove(p);
                _weight -= p.getWeight();
                total_exit_passenger += 1;
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
