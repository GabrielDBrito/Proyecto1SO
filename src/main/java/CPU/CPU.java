/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CPU;
import Clock.ClockManager;
import EDD.ProcessList;
import EDD.Queue;
import Process.Process;
import Scheduler.Scheduler;
/**
 *
 * @author Gabriel
 */
public class CPU {

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
    
    private Integer ID;
    private String runningProcess;
    private Integer PC;
    private Integer MAR;
    private Process process;
    private Scheduler scheduler;
    private ClockManager clockManager;
    
    public CPU(Integer ID, ClockManager clockManager) {
        this.ID = ID;
        this.runningProcess = "OS";
        this.PC = 0;
        this.MAR = 0;
        this.clockManager=clockManager;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getRunningProcess() {
        return runningProcess;
    }

    public void setRunningProcess(String runningProcess) {
        this.runningProcess = runningProcess;
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
    
    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
    
    public void run(Process process) {
        System.out.println("CPU is running: " + process.getprocessName());  // Ensure the method name matches exactly in the Process class
        setProcess(process);
        setRunningProcess("P" + process.getID());  // Ensure getID() method exists in Process class
    }

    public void block(Queue blockQueue){
        Process process=getProcess();
        blockQueue.enqueue(process);  
        runningOS();
    }
    
    public void terminate(ProcessList exitList){
        Process process=getProcess();
        exitList.add(process); 
        runningOS();
    }
    public void runningOS(){
        setRunningProcess("OS");
    }
    
    public void update() {
        this.PC = clockManager.getClockCycles();
        this.MAR = clockManager.getClockCycles();
    }
}