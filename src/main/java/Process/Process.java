package Process;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gabriel
 */
public class Process {
    private Integer ID;
    private String name;
    private Integer instructionCount;
    private boolean CPUbound;
    private boolean IObound;
    private Integer cyclesForExcept;
    private String status;  
    private Integer PC;  
    private Integer MAR; 
    private Integer priority;
    
    
    public Process(String name, int instructionCount, boolean CPUbound, boolean IObound, 
                   Integer cyclesForExcept, Integer priority){
        this.ID = 0;//revisar
        this.name = name;
        this.instructionCount = instructionCount;
        this.CPUbound = CPUbound;
        this.IObound = IObound;
        this.cyclesForExcept = cyclesForExcept;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getCyclesForExcept() {
        return cyclesForExcept;
    }

    public void setCyclesForExcept(Integer ciclesForExcept) {
        this.cyclesForExcept = ciclesForExcept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void printProcessDetails() {
        System.out.println("Process ID: " + ID);
        System.out.println("Name: " + name);
        System.out.println("Instruction Count: " + instructionCount);
        System.out.println("CPU Bound: " + CPUbound);
        System.out.println("I/O Bound: " + IObound);
        System.out.println("Cycles for Exception: " + cyclesForExcept);
        System.out.println("Status: " + status);
        System.out.println("Program Counter (PC): " + PC);
        System.out.println("Memory Address Register (MAR): " + MAR);
        System.out.println("Priority: " + priority);
    }
}
