/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebussolution;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SaBeeNa
 */
public class Bus extends Thread {

    private Counter waiting;
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;

    private int noOfRiders;

    public Bus(int noOfRiders, Counter waiting, Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.waiting = waiting;
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.noOfRiders = noOfRiders;
    }

    @Override
    public void run() {

        while (true) {
            
            try {
                mutex.acquire();  
                System.out.println("Bus gets the mutex");
            } catch (InterruptedException ex) {
                Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
            }

            int noOfRidersWent = Math.min(waiting.getCount(), 50);
             
            noOfRiders -= noOfRidersWent;   //riders left
            
            for (int i = 0; i < noOfRidersWent; i++) {
                bus.release();  //signals the riders
                try {
                    boarded.acquire(); // rider has boarded
                } catch (InterruptedException ex) {
                    Logger.getLogger(Bus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            waiting.setCount(Math.max(waiting.getCount() - 50, 0));
             
            mutex.release(); 
            System.out.println("Bus releases the mutex");
            
            if(noOfRiders==0){
                break;
            }
        }
        
    }

}
