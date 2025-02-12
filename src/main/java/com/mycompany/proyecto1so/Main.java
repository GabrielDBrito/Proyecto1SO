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
import Scheduler.Scheduler;
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
        
        int instructionDuration = 0; 
        String planningAlgorithm = null;
        try (FileReader reader = new FileReader("src/main/java/Settings/settings.txt")) {
            Properties properties = new Properties();
            properties.load(reader);

            instructionDuration = Integer.parseInt(properties.getProperty("instructionDuration")); //in seconds
            planningAlgorithm = properties.getProperty("planningAlgorithm");

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading settings file: " + e.getMessage());
        }
        
        Queue<Process> readyQueue = new Queue<>();
        Queue<Process> blockedQueue = new Queue<>();
        ProcessList exitList= new ProcessList(); // culminated processes
        
        FCFS algorithm =null;
        //Scheduling Algorithm
        if("FCFS".equals(planningAlgorithm)){
            algorithm =new FCFS(readyQueue);
        }
        if("Round Robin".equals(planningAlgorithm)){
            
        }
        if("SPN".equals(planningAlgorithm)){
        }
        if("SRT".equals(planningAlgorithm)){
        }
        if("HRRN".equals(planningAlgorithm)){
        }
        Scheduler scheduler=new Scheduler(algorithm,readyQueue);
        
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
            new Menu(readyQueue,blockedQueue,exitList,settings,cpus,scheduler,clockManager).setVisible(true);
        });
    }
}
