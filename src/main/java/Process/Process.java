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
    private Integer instructionCount;
    private boolean CPUbound;
    private boolean IObound;
    private Integer cyclesToExcept;
    private Integer cyclesToCompleteRequest;
    private String status;  
    private Integer PC;  
    private Integer MAR; 
    private Integer priority;
    private Semaphore mutex;
   
    
    
    public Process(String processName, int instructionCount, boolean CPUbound, boolean IObound, 
                   Integer cyclesToExcept,Integer cyclesToCompleteRequest, Integer priority){
        this.ID = 0;//revisar
        this.processName = processName;
        this.instructionCount = instructionCount;
        this.CPUbound = CPUbound;
        this.IObound = IObound;
        this.cyclesToExcept = cyclesToExcept;
        this.cyclesToCompleteRequest=cyclesToCompleteRequest;
        this.status = "Ready";  
        this.PC = 0;  //revisar
        this.MAR = 0; //revisar
        this.priority=priority;
    }
    
    public Integer getPC() {
        return PC;
    }

    public void setPC(Integer PC) {
        this.PC = PC;
    }

    public Integer getMAR() {
        return MAR;
    }

    public void setMAR(Integer MAR) {
        this.MAR = MAR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getprocessName() {
        return processName;
    }

    public void setprocessName(String processName) {
        this.processName = processName;
    }

    

    public int getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public boolean isCPUbound() {
        return CPUbound;
    }

    public void setCPUbound(boolean CPUbound) {
        this.CPUbound = CPUbound;
    }

    public boolean isIObound() {
        return IObound;
    }

    public void setIObound(boolean IObound) {
        this.IObound = IObound;
    }

    public Integer getCyclesToExcept() {
        return cyclesToExcept;
    }

    public void setCyclesToExcept(Integer ciclesToExcept) {
        this.cyclesToExcept = ciclesToExcept;
    }
    
    public Integer getCyclesToCompleteRequest() {
        return cyclesToCompleteRequest;
    }

    public void setCyclesToCompleteRequest(Integer cyclesToCompleteRequest) {
        this.cyclesToCompleteRequest = cyclesToCompleteRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }
    
    
    
    public void printProcessDetails() {
        System.out.println("Process ID: " + ID);
        System.out.println("Name: " + processName);
        System.out.println("Instruction Count: " + instructionCount);
        System.out.println("CPU Bound: " + CPUbound);
        System.out.println("I/O Bound: " + IObound);
        System.out.println("# Cycles for Exception: " + cyclesToExcept);
        System.out.println("# Cycles to complete the request: " + cyclesToCompleteRequest);
        System.out.println("Status: " + status);
        System.out.println("Program Counter (PC): " + PC);
        System.out.println("Memory Address Register (MAR): " + MAR);
        System.out.println("Priority: " + priority);
    }
}
