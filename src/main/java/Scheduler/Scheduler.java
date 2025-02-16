package Scheduler;

import CPU.CPU;
import EDD.Queue;
import Process.Process;

/**
 *
 * @author Gabriel
 */
public class Scheduler {
    private Queue<Process> readyQueue;
    private SchedulingAlgorithm algorithm;

    // Constructor now accepts the SchedulingAlgorithm and readyQueue
    public Scheduler(SchedulingAlgorithm algorithm, Queue<Process> readyQueue) {
        this.algorithm = algorithm;
        this.readyQueue = readyQueue;
    }

    // Expose the ready queue if needed
    public Queue<Process> getReadyQueue() {
        return readyQueue;
    }

    // Reorder the queue based on the selected scheduling algorithm
    public void reorder() {
        algorithm.reorder();  // Reorder based on the specific algorithm
    }

    // Dispatch the process to the CPU for execution
    public void dispatch(CPU cpu) {
        reorder();  // Reorder the queue before dispatching
        Process process = readyQueue.dequeue();  // Get the next process
        cpu.run(process);  // Let the CPU execute the process
    }
}
