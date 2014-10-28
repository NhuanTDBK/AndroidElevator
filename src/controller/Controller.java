/*
 
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import model.Elevator;
import model.Floor;
import model.Passenger;
import model.Request;

/**
 *
 * @author Nhuan
 */
public class Controller  {

    public Elevator[] elevators;
    public Elevator[] getElevators() {
        return elevators;
    }
    /**
     *
     * @param id bat dau tu 0
     * @return
     */
    public Elevator getElevatorWithID(int id)
    {
        return elevators[id];
    }
    private Floor[] floors;

    public Floor[] getFloors() {
        return floors;
    }
    public static final int MAX_FLOOR = 10;//số lượng tầng tối đa
    public static final int MAX_ELEVATOR =2;
    /**
     *
     * Khởi tạo số thang mays
     * @param num_of_elevators
     */
    public Controller(int num_of_elevators) {

        elevators = new Elevator[num_of_elevators];
        //So tang duoc config
        for (int i = 0; i < num_of_elevators; i++) {
            elevators[i] = new Elevator(i);
            elevators[i].setFloor(0);
            elevators[i].setStatus(Elevator.IDLE);
        }
        floors = new Floor[Controller.MAX_FLOOR];
        /*
         * Khoi tao floor, gan so hieu
         */
        for (int i = 0; i < MAX_FLOOR; i++) {
            floors[i] = new Floor(i);
        }
    }
   
    /**
     *Thêm yêu cầu vào thang máy nhập vào
     * @param elevator
     * @param request
     * @return
     */
    
    public boolean addRequest(Elevator elevator, Request request)
    {
       elevator.setStatus(Elevator.ACTIVE);
       elevator.setDirection(request.getDirection());
       return elevator.addRequest(request);
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
    
    /**
     * Đầu vào: Tầng hiện tại
     * Đầu ra: Lấy tầng cao nhất mà nhỏ hơn tầng hiện tại
     * @param floorNow
     * @return
     */
    public int getMaxDown(int floorNow) {
        int max = 0;
        for (Floor temp : floors) {
            int floor = temp.id;
            if (floor <= floorNow && floor > max && !floors[floor].requests.isEmpty()) {
                max = floor;
            }
        }
        return max;
    }

    /**
     *  Đầu ra: Kiểm tra tín hiệu từ các thang
     * @return
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
   
    /**
     * Thêm 1 yêu cầu xuất phát từ tầng floorNow, hướng direction
     * @param floorNow
     * @param direction
     * @return
     */
    
    public boolean addRequest(int floorNow, int direction) {
        boolean check = true;
        if(floorNow==MAX_FLOOR) 
        {
            check=false;
            System.out.println("Bam nham roi?");
            return check;
        }
        if (floors[floorNow].requests.size() == 2) {
            System.out.println("Khong the bam them duoc");
        } else {
            System.out.println("Co yeu cau tu tang: " + floorNow);
            if (floorNow != Controller.MAX_FLOOR) {
                Request r = new Request(floorNow, direction);
                check = floors[floorNow].requests.add(r);
            } else if (floorNow == Controller.MAX_FLOOR - 1) {
                check = floors[floorNow].requests.add(new Request(floorNow, Elevator.DOWN));
            } else if (floorNow == 0) {
                check = floors[floorNow].requests.add(new Request(floorNow, Elevator.UP));
            }
        }
        return check;
        //System.out.println(check);
    }

    /**
     *Đầu vào: tầng hiện tại
     *Đầu ra: Xóa yêu cầu ở tầng đó
     * @param floorNow
     * @param direction
     * @return
     */
    public int removeRequest(int floorNow, int direction) {
        floors[floorNow].requests.removeIf((Request t) -> (t.getFloor() == floorNow && t.getDirection() == direction));
        return 1;
    }

    /**
     * Tạo 1 hành khách đứng ở tầng floorNow, có yêu cầu 
     * @param floorNow
     * @param p
     * @return
     */
    
    public boolean addPassenger(int floorNow, Passenger p) {
        boolean check = true;
        int direction = p.getDirection();
        if (p.getFloor() <= floorNow && p.getDirection() == Elevator.UP) {
            System.out.println("What's wrong with you?");
            return false;
        }
        if (!floors[floorNow].requests.contains(new Request(floorNow, direction))) {
            floors[floorNow].requests.add(new Request(floorNow, direction));
        }
        check = floors[floorNow].passengers.add(p);
        return check;
    }

    /**
     *  Kiểm tra tầng floorNow có yêu cầu nào hay không?
     * @param floorNow
     * @param direction
     * @return
     */
    public boolean hasRequest(int floorNow, int direction) {

        Request t = new Request(floorNow, direction);
        return floors[floorNow].requests.stream().anyMatch((temp) -> (temp.compareTo(t) == 0));
    }


    /**
     *
        *Đầu vào: Tầng hiện tại và hướng
        *Đầu ra: Danh sách khách chờ
     * @param floor
     * @param direction
     * @return
     */
    
    public ArrayList<Passenger> getPassengerFromFloor(int floor, int direction) {
        ArrayList<Passenger> temp = new ArrayList<>();
        floors[floor].passengers.stream().filter((p) -> (p.getDirection() == direction)).forEach((p) -> {
            temp.add(p);
        });
        floors[floor].passengers.removeAll(temp);
        return temp;
    }
    
//    public int moveDown(Elevator elevator) {
//        int floorNow = elevator.getFloor();
//        int direction;
//        while (this.getMaxDown(floorNow) != 0 || elevator.request.checkRequest(Elevator.DOWN)) {
//
//            direction = Elevator.DOWN;
//            elevator.setDirection(direction);
//            elevator.move(floorNow);
//
//            if (hasRequest(floorNow, direction) || elevator.request.containFloor(floorNow, direction)) {
//                elevator.door.open();
//                List<Passenger> passengers = getPassengerFromFloor(floorNow, direction);
//                if (!passengers.isEmpty()) {
//                    for (Passenger passenger : passengers) {
//                        elevator.addPassenger(passenger);
//                    }
//                }
//                elevator.door.close();
//                removeRequest(floorNow, direction);
//            }
//            floorNow--;
//            if (floorNow == -1) {
//                break;
//            }
//        }
//        return floorNow;
//    }
//
//    public int moveUp(Elevator elevator) {
//        int floorNow = elevator.getFloor(), direction;
//        floorNow = elevator.getFloor();
//        while (getMinUp(floorNow) != Integer.MAX_VALUE || elevator.request.checkRequest(Elevator.UP)) {
//
//            direction = Elevator.UP;
//            elevator.setDirection(direction);
//            elevator.move(floorNow);
//
//            if (hasRequest(floorNow, direction) || elevator.request.containFloor(floorNow, direction)) {
//                elevator.door.open();
//                //add khach o tang do' neu co'      
//                List<Passenger> passengers = getPassengerFromFloor(floorNow, direction);
//                if (!passengers.isEmpty()) {
//                    for (Passenger passenger : passengers) {
//                        elevator.addPassenger(passenger);
//                    }
//                }
//                elevator.door.close();
//                removeRequest(floorNow, direction);
//            }
//            if (floorNow == Controller.MAX_FLOOR - 1) {
//                break;
//            }
//            floorNow++;
//        }
//        return floorNow;
//    }
    
    
}
