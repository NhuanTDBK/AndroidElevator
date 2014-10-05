package model;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nhuan
 */
public class RequestQueue extends PriorityQueue<Integer>{
    
    public static class NumberComparator implements Comparator<Integer> {

        /*
         Sắp xếp giảm dần
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            int result = o2 - o1;
            return result;
        }
    };

    private PriorityQueue<Integer> upQueue;
    private PriorityQueue<Integer> downQueue;

    public RequestQueue() {
        upQueue = new PriorityQueue<Integer>(100);
        NumberComparator com = new NumberComparator();
        downQueue = new PriorityQueue<Integer>(100, com);
    }
    /*
     Lấy tầng lớn nhất ở hàng đợi đi xuống
     */

    public int getMaxDown() {
        int result = 0;
        result = downQueue.peek();
        return result;
    }
    /*
     Lấy tầng thấp nhất ở hàng đợi đi lên
     */

    public int getMinUp() {
        int result = upQueue.peek();
        return result;
    }
    public int checkRequest()
    {
       if(!isUpRequestEmpty()) return Elevator.UP;
        else 
            {
                if(!isDownRequestEmpty()) return Elevator.DOWN;
            }
        return 0;
    }
    public int getNextFloor(int direction)
    {
        if(direction == Elevator.UP) return getMinUp();
        else if(direction==Elevator.DOWN) return getMaxDown();
        return 0;
    }
    public boolean removeFloor(int direction,int floor)
    {
        if(direction==Elevator.UP) 
            return removeUp(floor);
        else if(direction==Elevator.DOWN)
            return removeDown(floor);
        return true;
    }
    /*
     Thêm tầng yêu cầu vào hàng đợi, kiểm tra để tránh các yêu cầu trùng lặp
     */

    /**
     * Thêm yêu cầu tầng vào hàng đợi đi lên
     * @param floor
     * @return
     */
    public boolean addUp(int floor) {
       if(!upQueue.contains(floor))
        return upQueue.add(floor);
       else return false;
    }

    /**
     *Thêm yêu cầu tầng vào hàng đợi đi xuống
     * @param floor
     * @return
     */
    public boolean addDown(int floor) {
        if(!downQueue.contains(floor))
        return downQueue.add(floor);
        return false;
    }
    /*
     Xóa bỏ những tầng đã được phục vụ
     */

    /**
     *     
     * Xóa bỏ những tầng đã được phục vụ

     * @param floor
     * @return
     */
    public boolean removeUp(int floor) {
        return upQueue.remove(floor);
    }

    public boolean removeDown(int floor) {
        return downQueue.remove(floor);
    }
    @Override
    public boolean isEmpty()
    {
        if(upQueue.isEmpty()&&downQueue.isEmpty())
        return true;
        return false;
    }
    /*
    public boolean isEmpty(int direction)
    {
        if(direction==Elevator.UP) return isUpRequestEmpty();
        else return isDownRequestEmpty();
    } */
    public boolean isUpRequestEmpty()
    {
        if(upQueue.isEmpty()) return true;
        return false;
    }
    public boolean isDownRequestEmpty()
    {
        if(downQueue.isEmpty()) return true;
        return false;
    }
}
