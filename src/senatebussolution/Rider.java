/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebussolution;

/**
 *
 * @author SaBeeNa
 */

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rider extends Thread {

    private int id;
    private Counter waiting;
    private Semaphore mutex;
    private Semaphore bus;
    private Semaphore boarded;

    public Rider(int id, Counter waiting, Semaphore mutex, Semaphore bus, Semaphore boarded) {
        this.waiting = waiting;
        this.mutex = mutex;
        this.bus = bus;
        this.boarded = boarded;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        waiting.incrementCount();
        mutex.release();
        try {
            bus.acquire(); // waiting for the bus
        } catch (InterruptedException ex) {
            Logger.getLogger(Rider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("rider has boarded");
        boarded.release(); // signals when the rider has boarded
 
    }
    

}
