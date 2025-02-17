package Process;

import java.util.concurrent.Semaphore;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gabriel
 */


public class Process extends Thread {
    private Integer ID;
    private String processName;
    private Integer instructionCount; // Total instruction count (burst time)
    private Integer remainingBurstTime; // Remaining burst time for SRT
    private boolean CPUbound;
    private boolean IObound;
    private Integer cyclesToExcept;
    private Integer cyclesToCompleteRequest;
    private String status;
    private Integer PC;
    private Integer MAR;
    private Integer priority;
    private Semaphore mutex;
    private int arrivalTime;
    private double responseRatio;

    public Process(String processName, int instructionCount, boolean CPUbound, boolean IObound,
                   Integer cyclesToExcept, Integer cyclesToCompleteRequest, Integer priority, int arrivalTime) {
        this.ID = 0;  // Check if needed
        this.processName = processName;
        this.instructionCount = instructionCount;
        this.remainingBurstTime = instructionCount; // Initialize remaining burst time to total instruction count
        this.CPUbound = CPUbound;
        this.IObound = IObound;
        this.cyclesToExcept = cyclesToExcept;
        this.cyclesToCompleteRequest = cyclesToCompleteRequest;
        this.status = "Ready";
        this.PC = 0;
        this.MAR = 0;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.responseRatio=0;
    }

    // Getter and setter methods
    public Integer getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void decrementRemainingBurstTime(int time) {
        this.remainingBurstTime -= time; // Decrement the remaining burst time as the process runs
    }

    public Integer getInstructionCount() {
        return instructionCount;
    }

    public double getResponseRatio() {
        return responseRatio;
    }

    public void setResponseRatio(double responseRatio) {
        this.responseRatio = responseRatio;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public String getprocessName() {
        return processName;
    }

    public void setprocessName(String processName) {
        this.processName = processName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

 

public Integer getID() {
    return ID;
}

public Integer getPC() {
    return PC;
}

public Integer getMAR() {
    return MAR;
}

    

    public void printProcessDetails() {
        System.out.println("Process ID: " + ID);
        System.out.println("Name: " + processName);
        System.out.println("Instruction Count: " + instructionCount);
        System.out.println("Remaining Burst Time: " + remainingBurstTime);
        System.out.println("CPU Bound: " + CPUbound);
        System.out.println("I/O Bound: " + IObound);
        System.out.println("# Cycles for Exception: " + cyclesToExcept);
        System.out.println("# Cycles to complete the request: " + cyclesToCompleteRequest);
        System.out.println("Status: " + status);
        System.out.println("Program Counter (PC): " + PC);
        System.out.println("Memory Address Register (MAR): " + MAR);
        System.out.println("Priority: " + priority);
    }

    @Override
    public String toString() {
        return "Process Name: " + this.processName + ", Instruction Count: " + this.instructionCount;
    }
}
