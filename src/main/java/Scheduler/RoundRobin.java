/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

import EDD.Queue;
import Process.Process;
import CPU.CPU;


/**
 *
 * @author Andrea
 */

public class RoundRobin implements SchedulingAlgorithm {
    private int quantum = 5;

    @Override
    public void reorder() {
        // Implement reordering logic if necessary (depends on your algorithm)
    }

   @Override
public void dispatch(CPU cpu) {
    Queue<Process> readyQueue = cpu.getScheduler().getReadyQueue();  // Get the ready queue from Scheduler
    System.out.println("Dispatching process...");  // Debug print

    if (!readyQueue.isEmpty()) {
        Process currentProcess = readyQueue.dequeue();
        System.out.println("Running process: " + currentProcess.getprocessName());  // Debug print
        cpu.run(currentProcess);  // Run the process
    }
}

    public void executeRoundRobin(Queue<Process> queue) {
        Queue<Process> readyQueue = new Queue<>();
        int currentTime = 0;

        // Move processes into the readyQueue
        while (!queue.isEmpty()) {
            readyQueue.enqueue(queue.dequeue());
        }

        // Execute processes based on Round Robin scheduling
        while (!readyQueue.isEmpty()) {
            Process currentProcess = readyQueue.dequeue();
            int remainingBurstTime = currentProcess.getInstructionCount();
            System.out.println("Time: " + currentTime + " - Running process: " + currentProcess.getprocessName());

            if (remainingBurstTime > quantum) {
                currentProcess.setInstructionCount(remainingBurstTime - quantum);  // Update remaining time
                currentTime += quantum;
                System.out.println("Time: " + currentTime + " - Process " + currentProcess.getprocessName() + " paused, time left: " + currentProcess.getInstructionCount());
                readyQueue.enqueue(currentProcess);  // Reinsert process back into the queue
            } else {
                currentTime += remainingBurstTime;
                System.out.println("Time: " + currentTime + " - Process " + currentProcess.getprocessName() + " completed");
            }
        }

        System.out.println("All processes have been executed.");
    }
}
