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

public class SenateBusSolution {

    //private volatile static int waiting = 0;                         //how many riders are waiting
    private static Counter waiting = new Counter(0);
    private static Semaphore mutex = new Semaphore(1);      //
    private static Semaphore bus = new Semaphore(0);        //signals when the bus has arrived
    private static Semaphore boarded = new Semaphore(0);    //signals when a rider has boarded
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("Enter the total number of Riders : ");
        int n = sc.nextInt(); 
        System.out.println(" ");
         
        // Demonstrate the behaviour of the Bus. this thread's run method will keep running
        new Bus(n, waiting, mutex, bus, boarded).start();
         
        // We created 'n'number of riders to  demonstrate the behaviour of the riders pool 
        for (int i = 0; i < n; i++) {
            new Rider(i, waiting, mutex, bus, boarded).start();
        }
    }
    
}
