package Scheduler;

import CPU.CPU;
import EDD.Queue;
import Process.Process;
import Scheduler.SchedulingAlgorithm;

/**
 *
 * @author Gabriel
 */

public class Scheduler {
    private Queue<Process> readyQueue;
    private SchedulingAlgorithm algorithm;
    private CPU[] cpus;  // Array of CPUs

    // Constructor accepts the SchedulingAlgorithm, readyQueue, and CPUs array
    public Scheduler(SchedulingAlgorithm algorithm, Queue<Process> readyQueue, CPU[] cpus) {
        this.algorithm = algorithm;
        this.readyQueue = readyQueue;
        this.cpus = cpus;
    }

    // Expose the ready queue if needed
    public Queue<Process> getReadyQueue() {
        return readyQueue;
    }

    // Reorder the queue based on the selected scheduling algorithm
    public void reorder() {
        algorithm.reorder();  // Reorder based on the specific algorithm
    }


public void dispatch() {
    // Iteramos sobre todas las CPUs disponibles
    for (CPU cpu : cpus) {
        // Primero, intentamos despachar un proceso de la cola de listos de la CPU
        if (!cpu.getReadyQueue().isEmpty()) {
            Process process = (Process) cpu.getReadyQueue().dequeue(); // Tomamos el primer proceso de la cola de listos
            cpu.run(process); // Ejecutamos el proceso en esta CPU
        } else if (!cpu.getBlockedQueue().isEmpty()) {
            // Si no hay procesos listos en esta CPU pero hay procesos bloqueados, intentamos moverlos a listos
            Process process = (Process) cpu.getBlockedQueue().dequeue(); // Tomamos un proceso bloqueado
            process.setStatus("Ready"); // Lo cambiamos a estado "Ready"
            cpu.getReadyQueue().enqueue(process); // Lo movemos a la cola de listos
            cpu.run(process); // Ejecutamos el proceso en esta CPU
        } else {
            System.out.println("No hay procesos para despachar en CPU " + cpu.getID());
        }
    }
}
    
// Método auxiliar para verificar si hay procesos bloqueados en cualquiera de las CPUs
private boolean hasBlockedProcesses() {
    for (CPU cpu : cpus) {
        if (!cpu.getBlockedQueue().isEmpty()) {
            return true;
        }
    }
    return false;
}

// Método auxiliar para verificar si todas las colas de procesos están vacías
private boolean allQueuesEmpty() {
    if (!readyQueue.isEmpty()) return false;
    for (CPU cpu : cpus) {
        if (!cpu.getReadyQueue().isEmpty() || !cpu.getBlockedQueue().isEmpty()) {
            return false;
        }
    }
    return true;
}
}


