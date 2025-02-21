package Scheduler;

import CPU.CPU;
import EDD.Queue;
import Process.Process;

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

    // Dispatch processes to each CPU
    public void dispatch() {
        for (CPU cpu : cpus) {
            System.out.println("Dispatching processes to CPU: " + cpu.getID());
            algorithm.dispatch(cpu);  // Pass each CPU to the dispatch method of the algorithm
        }
    }
}
