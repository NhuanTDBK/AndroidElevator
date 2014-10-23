/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Elevator;
import model.Request;

/**
 *
 * @author Nhuan
 */
public interface IStrategy {
    int findOptimalElevator(Request request,Elevator[] elevators);
    int ELEVATOR_NOT_FOUND = -1;
}
