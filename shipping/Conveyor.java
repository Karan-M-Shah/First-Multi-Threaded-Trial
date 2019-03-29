/* 
Name: Karan Shah
Course: CNT 4714 Spring 2019 
Assignment title: Project 2 – Multi-threaded programming in Java 
Date: February 17, 2019 
Class: Conveyer
*/ 
package shipping;

import java.util.concurrent.locks.ReentrantLock;

//Conveyor class

public class Conveyor {
    
    //Lock variable
    private ReentrantLock lock = new ReentrantLock();
    
    //Lock method, calling the reentrantLock
    public boolean lock() {       
        return lock.tryLock();       
    }
    
    //Unlock method, unlocking the reentrantLock
    public void unLock(){
        lock.unlock();
    }  
}