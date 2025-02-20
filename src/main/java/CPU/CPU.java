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
    
    public CPU(Integer ID, ClockManager clockManager, Queue readyQueue, Queue blockedQueue, ProcessList exitList) {
        this.ID = ID;
        this.runningProcess = "OS";
        this.PC = 0;
        this.MAR = 0;
        this.clockManager=clockManager;
        this.readyQueue=readyQueue;
        this.blockedQueue=blockedQueue;
        this.exitList=exitList;
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
        
        new Thread(() -> {
        int startCycle = clockManager.getClockCycles();
        int targetCycle;

        if (process.isIObound() && process.getCyclesToExcept() < process.getInstructionCount()) {
            // Si es I/O bound y el tiempo de excepción es menor a las instrucciones totales
            targetCycle = startCycle + process.getCyclesToExcept();
        } else {
            // Si no se cumplen ambas condiciones, terminar el proceso
            targetCycle = startCycle + process.getInstructionCount();
        }

        // Esperar hasta que clockManager alcance el ciclo deseado
        while (clockManager.getClockCycles() < targetCycle) {
            try {
                Thread.sleep(100); // Reduce la carga de la CPU
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Decidir si bloquear o terminar
        if (process.isIObound() && process.getCyclesToExcept() < process.getInstructionCount()) {
            block(); // Mueve el proceso a la cola de bloqueados
        } else {
            terminate(); // Termina el proceso
        }

        }).start();
    }


    public void block(){
        Process process=getProcess();
        blockedQueue.enqueue(process);
        blockedQueueHandler(process);
        runningOS();
    }
    
    public void terminate(){
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


 public void blockedQueueHandler(Process process) {
    new Thread(() -> {
        int blockedUntil = clockManager.getClockCycles() + process.getCyclesToCompleteRequest();
        process.setInstructionCount(process.getInstructionCount()-process.getCyclesToExcept());
        while (clockManager.getClockCycles() < blockedUntil) {
            System.out.println(clockManager.getClockCycles());
            System.out.println(blockedUntil);
            System.out.println("------------------------------");
            try {
                Thread.sleep(100); // Espera un poco antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return; // Si el hilo es interrumpido, salir del método
            }
        }
        // Una vez que los ciclos han pasado, mover el proceso de bloqueado a listo
        blockedQueue.dequeueById(process.getID());
        readyQueue.enqueue(process);
        System.out.println("Process " + process.getID() + " is now ready.");

    }).start();
    }
}
    
