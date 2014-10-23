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
    //private ArrayList<Integer>floorQueue;
    public RequestQueue() {
        upQueue = new PriorityQueue<>(20);
        NumberComparator com = new NumberComparator();
        
        downQueue = new PriorityQueue<>(20, com);
        //floorQueue = new ArrayList<Integer>(Controller.MAX_FLOOR);
    }
    public boolean containFloor(int floor,int direction)
    {
        boolean check=false;
        if(direction==Elevator.UP) check=upQueue.contains(floor);
        else check=downQueue.contains(floor);
        return check;
    }
    public boolean checkFloorInRequest(int floor,int direction)
    {
        if(direction==Elevator.UP) return upQueue.contains(floor);
        else return downQueue.contains(floor);
                
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
    /*
        Lớn nhất còn nhỏ hơn floor
    */
    public int getMaxDown(int floorNow)
    {
        int max = 1;if(floorNow==1) return 1;
        for(Integer i:downQueue)
        {
            if(i<floorNow&&i>max) max = i;
        }
        return max;
    }
    /*
        Nhỏ nhất còn lớn nhất hơn floor
    */
    public int getMinUp(int floorNow)
    {
        int min = 1;
        for(Integer i:upQueue)
        {
            if(i>floorNow&&i<min) min = i;
        }
        return min;
    }
    public boolean checkRequest(int direction)
    {
        if(direction==Elevator.UP) 
        {
            return isUpRequestEmpty() != true;
        }
        else 
        {
            return isDownRequestEmpty() != true;
        }
    }
   
    public boolean removeFloor(int direction,int floor)
    {
        if(direction==Elevator.UP) 
            return removeUp(floor);
        else return removeDown(floor);

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
        return upQueue.isEmpty()&&downQueue.isEmpty();
    }
    /*
    public boolean isEmpty(int direction)
    {
        if(direction==Elevator.UP) return isUpRequestEmpty();
        else return isDownRequestEmpty();
    } */
    public boolean isUpRequestEmpty()
    {
        return upQueue.isEmpty();
    }
    public boolean isDownRequestEmpty()
    {
        return downQueue.isEmpty();
    }
    public int getSize()
    {
        return upQueue.size() + downQueue.size();
    }
}
