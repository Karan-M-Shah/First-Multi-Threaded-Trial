/* 
Name: Karan Shah
Course: CNT 4714 Spring 2019 
Assignment title: Project 2 – Multi-threaded programming in Java 
Date: February 17, 2019 
Class: Station
*/ 
package shipping;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

//Station Class

public class Station extends Thread {
    
    private int number;
    private int conveyor1;
    private int conveyor2;
    private int workload;
    private ArrayList<Conveyor> conveyorList;
    PrintStream printStream;
    
    //Constructor with 5 parameters
    public Station(int number, int conveyor1, int conveyor2, int workload, ArrayList<Conveyor> conveyorList)
    {
        //Assigning each argument to its corresponding variable
        this.number = number;
        this.conveyor1 = conveyor1;
        this.conveyor2 = conveyor2;
        this.workload = workload;
        this.conveyorList = conveyorList;
        
        try {
            printStream = new PrintStream(new FileOutputStream("src/shipping/output.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        System.setOut(printStream); 
        System.out.println("** Simulation Begins **\n"); 
    }
    
    //Run method of the thread
    @Override
    public void run(){
        
        //Initial output statements
        System.out.println("Station " + number + ": In-Connection set to conveyor " + conveyor1);
        System.out.println("Station " + number + ": Out-Connection set to conveyor " + conveyor2);
        System.out.println("Station " + number + ": Workload set. Station " + number + " has " + workload + " package "
                + "groups to move.");
        
        //The thread continues looping so long as the workload exceeds 0
        while(this.workload > 0)
        {
            //Attempting to obtain the first lock
            if(conveyorList.get(conveyor1).lock())
            {
                System.out.println("Station " + number + ": Granted access to conveyor " + conveyor1 + ".");
                
                //If the first lock is acquired, attempts to obtain the second lock
                if(conveyorList.get(conveyor2).lock())
                {
                    //If the second lock is required, print necessary statements
                    //Then call the doWork method
                    System.out.println("Station " + number + ": Granted access to conveyor " + conveyor2 + ".");
                    this.doWork();
                    
                    //Unlock locks after work completed
                    conveyorList.get(conveyor1).unLock();
                    System.out.println("Station " + number + ": Released access to conveyor " + conveyor1 + ".");
                    conveyorList.get(conveyor2).unLock();
                    System.out.println("Station " + number + ": Released access to conveyor " + conveyor2 + ".");
                }
                //If unable to acquire the second lock, the code below runs
                else
                {
                    //The first lock is released
                    conveyorList.get(conveyor1).unLock();
                    System.out.println("Station " + number + ": Released access to conveyor " + conveyor1 + ".");
                    //The thread is put to sleep for a certain amount of time
                    try { 
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep failed");
                    }
                }
            }
        }
        //Once all workload has been completed
        System.out.println("* * Station " + this.number + ": Workload successfully completed. * *");
    }
    
    //doWork method
    public void doWork() {
        //Decrements workload at each call
        this.workload--;
        try { 
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Sleep failed");
        }
        //Calls packagesIn and packagesOut methods
        packagesIn();
        packagesOut();
        System.out.println("Station " + this.number + ": has " + this.workload + " packages groups left to move.");
    }
    
    //packagesIn method
    public void packagesIn() {
        System.out.println("Station " + number + ": Successfully moves packages on " + conveyor1 + ".");
    }
    
    //packagesOut method
    public void packagesOut() {
        System.out.println("Station " + number + ": Successfully moves packages on " + conveyor2 + ".");
    }
}
