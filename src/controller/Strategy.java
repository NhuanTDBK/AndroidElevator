/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Elevator;
import model.Request;

/**
 *
 * @author Nhuan
 */
public class Strategy implements IStrategy {

    private String name;
    private Request request;
    private Elevator[] elevators;

    public Strategy(String name) {
        this.name = name;
    }

    /*
     Tim thang may phu hop
        
     */
    /**
     *
     * @param request
     * @param elevators (Cac thang may)
     * @return (Thang may phu hop)
     */
    @Override
    public int findOptimalElevator(Request request, Elevator[] elevators) {
        int optimal_elevator = IStrategy.ELEVATOR_NOT_FOUND;

        this.request = request;
        this.elevators = elevators;

        optimal_elevator = findElevatorGoToFloor();
        if (optimal_elevator != IStrategy.ELEVATOR_NOT_FOUND) {
            return optimal_elevator;
        }

        optimal_elevator = findElevatorIdle();
        if (optimal_elevator != IStrategy.ELEVATOR_NOT_FOUND) {
            return optimal_elevator;
        }

        optimal_elevator = findElevatorNotDirection();
        if (optimal_elevator != IStrategy.ELEVATOR_NOT_FOUND) {
            return optimal_elevator;
        }
        optimal_elevator = findElevatorMostFree();
        if (optimal_elevator != IStrategy.ELEVATOR_NOT_FOUND) {
            return optimal_elevator;
        }

        return optimal_elevator;
    }

    private int findElevatorNotDirection() {
        int id = IStrategy.ELEVATOR_NOT_FOUND;
        ArrayList<Elevator> list_of_elevators = new ArrayList<>();
        for (Elevator e : elevators) {
            if (e.getDirection() != request.getDirection()) {
                list_of_elevators.add(e);
            }
        }
        id = minDistance(list_of_elevators);
        return id;
    }

    private int minDistance(ArrayList<Elevator> list_of_elevators) {
        int min = Integer.MAX_VALUE, distance, id = IStrategy.ELEVATOR_NOT_FOUND;
        for (Elevator e : list_of_elevators) {
            distance = Math.abs(e.getFloor() - request.getFloor());
            if (distance < min) {
                min = distance;
                id = e.getID();
            }
        }
        return id;
    }
    /*
     Tim thang may ranh roi gan nhat
     */

    private int findElevatorIdle() {
        int elevator_id = IStrategy.ELEVATOR_NOT_FOUND;
        int min = Integer.MAX_VALUE, floorNow, distance;
        ArrayList<Elevator> list_of_elevators = new ArrayList<>();
        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Elevator.IDLE && elevator.request.isEmpty()) {
                return elevator.getID();
            }
        }
        return elevator_id;
    }
    /*
     Tim thang may cung huong di chuyen voi request
     */

    private int findElevatorGoToFloor() {
        int elevator_id = IStrategy.ELEVATOR_NOT_FOUND;
        ArrayList<Elevator> list_of_elevator = new ArrayList<>();
        /*
         Xet nhung thang may dang lam viec
         */
        for (Elevator elevator : elevators) {
            if (elevator.getStatus() == Elevator.ACTIVE && elevator.getDirection() == request.getDirection()) {
                list_of_elevator.add(elevator);
            }
        }
        elevator_id = minDistance(list_of_elevator);
        return elevator_id;
    }
    /*
     Tim thang may co hang cho yeu cau nho nhat/it ban ron nhat
     */

    private int findElevatorMostFree() {
        int elevator_id = IStrategy.ELEVATOR_NOT_FOUND;
        int min = Integer.MAX_VALUE;
        int distance;
        for (Elevator elevator : elevators) {
            int temp = elevator.request.getSize();
            if (min > temp) {
                min = temp;
                elevator_id = elevator.getID();
            }
        }
        return elevator_id;
    }
}
