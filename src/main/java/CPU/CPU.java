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
    private Queue readyQueue;
    private Queue blockedQueue;
    private ProcessList exitList;
    private String processName;
    
    public CPU(Integer ID, ClockManager clockManager, Queue readyQueue, Queue blockedQueue, ProcessList exitList) {
        this.ID = ID;
        this.runningProcess = "OS";
        this.PC = 0;
        this.MAR = 0;
        this.clockManager=clockManager;
        this.readyQueue=readyQueue;
        this.blockedQueue=blockedQueue;
        this.exitList=exitList;
        this.processName="";
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

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public ClockManager getClockManager() {
        return clockManager;
    }

    public void setClockManager(ClockManager clockManager) {
        this.clockManager = clockManager;
    }
    
    public void run(Process process) {
        setProcess(process);
        setProcessName(process.getprocessName());
        setRunningProcess("P" + process.getID());  // Ensure getID() method exists in Process class
        
        new Thread(() -> {
        int startCycle = clockManager.getClockCycles();
        int targetCycle;

        if (process.isIObound() && process.getCyclesToExcept() < process.getInstructionCount()) {
            
            targetCycle = startCycle + process.getCyclesToExcept();
        } else {
            
            targetCycle = startCycle + process.getInstructionCount();
        }

        
        while (clockManager.getClockCycles() < targetCycle) {
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        if (process.isIObound() && process.getCyclesToExcept() < process.getInstructionCount()) {
            block();
        } else {
            terminate();
        }

        }).start();
    }

    public void block(){
        Process process=getProcess();
        process.setStatus("Blocked");
        blockedQueue.enqueue(process);
        blockedQueueHandler(process);
        runningOS();
    }
    
    public void terminate(){
        Process process=getProcess();
        process.setStatus("Exit");
        process.setMAR(this.MAR);
        process.setPC(this.PC);
        exitList.add(process); 
        runningOS();
    }
    
    public void runningOS(){
        setRunningProcess("OS");
        setProcessName("");
    }
    
    public void update() {
        this.PC = clockManager.getClockCycles();
        this.MAR = clockManager.getClockCycles();
        }

public void blockedQueueHandler(Process process) {
    new Thread(() -> {
        int blockedUntil = clockManager.getClockCycles() + process.getCyclesToCompleteRequest();
        process.setInstructionCount(process.getInstructionCount() - process.getCyclesToExcept());

        // Esperamos el tiempo que el proceso necesita estar bloqueado
        while (clockManager.getClockCycles() < blockedUntil) {
            try {
                Thread.sleep(100); // Simula el paso del tiempo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Una vez que el tiempo de bloqueo haya terminado
        blockedQueue.dequeueById(process.getID());
        process.setStatus("Ready"); // El proceso ahora está listo para ejecutarse
        readyQueue.enqueue(process); // Lo agregamos a la cola de listos

        // Aseguramos que el Scheduler pueda detectar este cambio
        // Si tienes un método de despachar procesos, deberías llamarlo aquí.
        scheduler.dispatch();
    }).start();
}

    public Queue getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(Queue readyQueue) {
        this.readyQueue = readyQueue;
    }

    public Queue getBlockedQueue() {
        return blockedQueue;
    }

    public void setBlockedQueue(Queue blockedQueue) {
        this.blockedQueue = blockedQueue;
    }
}
    
