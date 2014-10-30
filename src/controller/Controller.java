/*
 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import model.Elevator;
import static model.Elevator.DOWN;
import static model.Elevator.UP;
import model.Floor;
import model.Passenger;
import model.Request;
import model.WomanPassenger;
import view.IPassenger;
import view.PassengerDialog;

/**
 *
 * @author Nhuan
 */
public class Controller implements ActionListener {

    private Elevator[] elevators;

    public void addElevators(Elevator[] elevators) {
        this.elevators = elevators;
    }

    public void addFloors(Floor[] floors) {
        this.floors = floors;
    }

    public Elevator[] getElevators() {
        return elevators;
    }
    public PassengerDialog passengerDialog;
    public Strategy strategy;

    /**
     *
     * @param id bat dau tu 0
     * @return
     */
    public Elevator getElevatorWithID(int id) {
        return elevators[id];
    }
    private Floor[] floors;

    public Floor[] getFloors() {
        return floors;
    }
    public static final int MAX_FLOOR = 5;//số lượng tầng tối đa
    public static final int MAX_ELEVATOR = 2;

    /**
     *
     * Khởi tạo số thang mays
     *
     * @param num_of_elevators
     */
    public Controller(int num_of_elevators) {

        int[] p = {Elevator.BLOCK, Elevator.BLOCK, 0, 0, 0};
        Elevator.setBlockFloors(p);
        strategy = new Strategy("Smart");
    }

    public void addPassengerDialog(PassengerDialog p) {
        this.passengerDialog = p;
    }

    /**
     * Thêm yêu cầu vào thang máy nhập vào
     *
     * @param elevator
     * @param request
     * @return
     */
    public boolean addRequest(Elevator elevator, Request request) {
        //elevator.setStatus(Elevator.ACTIVE);
        //elevator.setDirection(request.getDirection());
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
     * Đầu vào: Tầng hiện tại Đầu ra: Lấy tầng cao nhất mà nhỏ hơn tầng hiện tại
     *
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
     * Đầu ra: Kiểm tra tín hiệu từ các thang
     *
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
     *
     * @param floorNow
     * @param direction
     * @return
     */
    public boolean addRequest(int floorNow, int direction) {
        boolean check = true;
        if (floorNow == MAX_FLOOR) {
            check = false;
            System.out.println("Bam nham roi?");
            return check;
        }
        Request r = new Request(floorNow, direction);
        if (floors[floorNow].requests.contains(r)) {
            return false;
        }
        if (floors[floorNow].requests.size() == 2) {
            System.out.println("Khong the bam them duoc");
        } else {
            System.out.println("Co yeu cau tu tang: " + floorNow);
            if (floorNow != Controller.MAX_FLOOR) {
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
     * Đầu vào: tầng hiện tại Đầu ra: Xóa yêu cầu ở tầng đó
     *
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
     *
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
     * Kiểm tra tầng floorNow có yêu cầu nào hay không?
     *
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
     * Đầu vào: Tầng hiện tại và hướng Đầu ra: Danh sách khách chờ
     *
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

    /*
        Cài đặt chức năng nhập thông tin
        Chưa có kiểm soát đầu vào
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField floorNowTxt = passengerDialog.getFloorNow();
        JTextField floorRequestTxt = passengerDialog.getFloorRequest();

        int floorNow = Integer.parseInt(floorNowTxt.getText()) - 1;
        int floorRequest = Integer.parseInt(floorRequestTxt.getText())-1;

        int direction = (floorNow - floorRequest) < 0 ? Elevator.UP : Elevator.DOWN;
        Passenger p = new WomanPassenger(floorRequest, direction);
        Request r = new Request(floorNow, direction);
        int elevator_id = strategy.findOptimalElevator(r, elevators);
        addRequest(getElevatorWithID(elevator_id), r);
        addPassenger(floorNow, p);
    }

    public void work(Elevator elevator) {
        int floorNow = elevator.getFloor();
        while (elevator.request.getSize() != 0) {
            while (!elevator.request.isUpRequestEmpty()) {
                int destination = elevator.request.getMinUp();
                int direction = (destination - floorNow) >= 0 ? UP : DOWN;
                elevator.setDirection(direction);
                while (floorNow != destination) {
                    floorNow += 1 * direction;
                    elevator.move(direction);
                    elevator.setDirection(direction);
                }
                //Mở cửa
                elevator.setDirection(Elevator.UP);
                elevator.door.open();
                elevator.setStatus(Elevator.PAUSE);
                //Cho hành khách đi ra nếu có
                if (!elevator.passengers.isEmpty()) {
                    //passenger.getOutElevator();
                    elevator.removePassenger();
                }
                for (Passenger p : floors[floorNow].passengers) {
                    if (p.getDirection() == elevator.getDirection()) {
                        boolean result = elevator.addPassenger(p);
                        //hành khách đi vào thang máy
                        p.getInElevator(elevator);
                        //Quá tải
                        if (result == false) //Hành khách đi ra
                        {
                            p.getOutElevator(elevator);
                            //Dừng việc đón khách
                            break;
                        }
                    }
                }

                elevator.door.close();
                elevator.request.removeUp(floorNow);
            }
            while (!elevator.request.isDownRequestEmpty()) {
                int destination = elevator.request.getMaxDown();
                int direction = (destination - floorNow) >= 0 ? UP : DOWN;
                
                while (floorNow != destination) {
                    floorNow += 1 * direction;
                    elevator.move(direction);
                    elevator.setDirection(direction);
                }
                elevator.setDirection(Elevator.DOWN);
                elevator.door.open();
               //Cho hành khách đi ra nếu có
                if (!elevator.passengers.isEmpty()) {
                    //passenger.getOutElevator();
                    elevator.removePassenger();
                }
                for (Passenger p : floors[floorNow].passengers) {
                    if (p.getDirection() == elevator.getDirection()) {
                        boolean result = elevator.addPassenger(p);
                        //hành khách đi vào thang máy
                        p.getInElevator(elevator);
                        //Quá tải
                        if (result == false) //Hành khách đi ra
                        {
                            p.getOutElevator(elevator);
                            //Dừng việc đón khách
                            break;
                        }
                    }
                }
                elevator.door.close();
                elevator.request.removeDown(floorNow);
            }

        }
    }
}
