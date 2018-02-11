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
public class Counter {
 
    private int count;
 
    public Counter(int count) {
        this.count = count;
    }
 
    public int getCount() {
        return count;
    }
 
    // reasign a value to the counter
    public void setCount(int count) {
        this.count = count;
    }
 
    // add 1 to the present value
    public void incrementCount() {
        this.count = ++count;
    }
}
