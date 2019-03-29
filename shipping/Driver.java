/* 
Name: Karan Shah
Course: CNT 4714 Spring 2019 
Assignment title: Project 2 – Multi-threaded programming in Java 
Date: February 17, 2019 
Class: Driver
*/ 
package shipping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Driver class

public class Driver {
    
    public static void main(String[] args) throws FileNotFoundException {
    
    //Sets up a scanner for the config file    
    Scanner scanner = new Scanner(new File("src/shipping/config.txt"));
    int count = Integer.parseInt(scanner.next());  
    
    //ArrayList of conveyors
    ArrayList<Conveyor> conveyorList = new ArrayList<Conveyor>(count);
    
    //Initial conveyor values from the file
    int conveyor1 = 0;
    int conveyor2 = count-1;
    int i = 0;
    
    //Looping through and generating conveyor objects
    for(i = 0; i < count; i++)
    {
        Conveyor conveyor = new Conveyor();
        conveyorList.add(conveyor);
    }
    
    //Looping through and generating station threads 
    for(i = 0; i < count; i++)
    {
        //New station thread passing in arguments: number, both conveyor numbers
        //Workload and the conveyor ArrayList
        Station temp = new Station(i, conveyor1, conveyor2, Integer.parseInt(scanner.next()), conveyorList);
        
        //Starting each thread 
        temp.start();
        
        //After the initial pass (where the final conveyor is set to the first station)
        //Reset and increment conveyors by 1
        if(i == 0)
        {
           conveyor1 = 0;
           conveyor2 = 1; 
        }
        else
        {
            conveyor1++;
            conveyor2++;
        }
    }  
    }
}
