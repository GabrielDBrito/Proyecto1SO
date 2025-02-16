/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyecto1so;

import CPU.CPU;
import Clock.ClockManager;
import EDD.ProcessList;
import EDD.Queue;
import Interfaces.Menu;
import Process.Process;
import Scheduler.FCFS;
import Scheduler.RoundRobin;
import Scheduler.SRT;
import Scheduler.Scheduler;
import Scheduler.SchedulingAlgorithm;
import javax.swing.JOptionPane;
import Settings.Settings;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Gabriel
 */
public class Main {

    public static void main(String[] args) {
        /*TESTING FOR SRT
        
        Queue<Process> queue = new Queue<>();
        
        
        // Create and enqueue some processes
        // Make sure you're creating 'Process' objects, not 'Thread'
        Process p1 = new Process("Process1", 10, true, false, 0, 0, 1, 0);
        Process p2 = new Process("Process2", 5, false, true, 0, 0, 2, 0);
        Process p3 = new Process("Process3", 15, true, false, 0, 0, 3, 0);
        
        queue.enqueue(p1);
        queue.enqueue(p2);
        queue.enqueue(p3);
        
        // Create the SRT scheduling algorithm
        SRT srt = new SRT(queue);
        
        // Display the queue before reordering
        System.out.println("Before Reorder:");
        queue.printQueue();  // This should print the Process names, not Thread names
        
        // Reorder the processes based on SRT (Shortest Remaining Time)
        srt.reorder();
        
        // Display the queue after reordering
        System.out.println("After Reorder:");
        queue.printQueue();  // This should show processes reordered based on instruction count
        
        // Create a dummy CPU (you'll need to implement it in your system)
        CPU cpu = new CPU(1);
        
        // Dispatch the process to the CPU
        srt.dispatch(cpu);
        }
        }
         */
 /*
        
        TEST FOR ROUND ROBIN
        Process process1 = new Process("Process1", 10, true, false, 0, 0, 1, 0);
        Process process2 = new Process("Process2", 15, false, true, 0, 0, 2, 0);
        Process process3 = new Process("Process3", 20, true, false, 0, 0, 3, 0);
        
        // Create a Queue to hold the processes
        Queue<Process> processQueue = new Queue<>();
        
        // Add processes to the queue
        processQueue.enqueue(process1);
        processQueue.enqueue(process2);
        processQueue.enqueue(process3);
        
        // Initialize CPU and Round Robin scheduling algorithm
        CPU cpu = new CPU(1);
        RoundRobin roundRobin = new RoundRobin();
        
        // Execute Round Robin
        roundRobin.executeRoundRobin(processQueue); // Call the Round Robin scheduler
        
        // After the Round Robin scheduling completes, check the final states
        System.out.println("All processes have been executed.");
        }}*/

        double instructionDuration = 0;
        String planningAlgorithm = null;
        try (FileReader reader = new FileReader("src/main/java/Settings/settings.txt")) {
            Properties properties = new Properties();
            properties.load(reader);

            instructionDuration = Double.parseDouble(properties.getProperty("instructionDuration")); //in seconds
            planningAlgorithm = properties.getProperty("planningAlgorithm");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading settings file: " + e.getMessage());
        }

        Queue<Process> readyQueue = new Queue<>();
        Queue<Process> blockedQueue = new Queue<>();
        ProcessList exitList = new ProcessList(); // culminated processes

        FCFS algorithm = null;
//Scheduling Algorithm
        if ("FCFS".equals(planningAlgorithm)) {
            algorithm = new FCFS(readyQueue);
        }
        if ("Round Robin".equals(planningAlgorithm)) {

        }
        if ("SPN".equals(planningAlgorithm)) {
        }
        if ("SRT".equals(planningAlgorithm)) {
        }
        if ("HRRN".equals(planningAlgorithm)) {
        }
        Scheduler scheduler = new Scheduler(algorithm, readyQueue);

        String[] options = {"2 CPUs", "3 CPUs"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Select the number of CPUs to use:",
                "CPU Selection",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

// Store the number of CPUs selected
        int numberOfCPUs = (choice == 1) ? 3 : 2; // Default to 2 if canceled or closed

        CPU[] cpus = new CPU[numberOfCPUs];
        for (int i = 0; i < numberOfCPUs; i++) {
            cpus[i] = new CPU(i + 1);
        }
        ClockManager clockManager = new ClockManager(instructionDuration); //clockCycles
        Settings settings = new Settings(numberOfCPUs, instructionDuration, planningAlgorithm, clockManager);

        java.awt.EventQueue.invokeLater(() -> {
            new Menu(readyQueue, blockedQueue, exitList, settings, cpus, scheduler, clockManager).setVisible(true);
        });
    }
}
