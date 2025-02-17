/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

import CPU.CPU;
import EDD.Queue;
import Process.Process;

/**
 *
 * @author Andrea
 */

public class HRRN implements SchedulingAlgorithm {
    private Queue<Process> readyQueue;
    private int currentTime;

    public HRRN(Queue<Process> readyQueue) {
        this.readyQueue = readyQueue;
        this.currentTime = 0;
    }

    @Override
    public void reorder() {
        if (readyQueue.isEmpty()) return; 

        Process[] processes = new Process[readyQueue.getSize()];
        int index = 0;

        // Dequeue all processes for sorting
        while (!readyQueue.isEmpty()) {
            processes[index++] = readyQueue.dequeue();
        }

        // Calculate response ratio for each process
        for (Process p : processes) {
            int waitingTime = Math.max(0, currentTime - p.getArrivalTime()); 
            int burstTime = p.getInstructionCount();
            double responseRatio = (waitingTime + burstTime) / (double) burstTime;
            p.setStatus(String.valueOf(responseRatio)); // Optionally, we can store the response ratio as part of the process
        }

        // Sort by highest response ratio (responseRatio in String format for display purposes)
        for (int i = 0; i < processes.length - 1; i++) {
            for (int j = i + 1; j < processes.length; j++) {
                double responseRatioI = Double.parseDouble(processes[i].getStatus());
                double responseRatioJ = Double.parseDouble(processes[j].getStatus());
                
                if (responseRatioI < responseRatioJ) {
                    Process temp = processes[i];
                    processes[i] = processes[j];
                    processes[j] = temp;
                }
            }
        }

        // Reinsert sorted processes into queue
        for (Process p : processes) {
            readyQueue.enqueue(p);
        }
    }

    @Override
    public void dispatch(CPU cpu) {
        reorder(); 

        if (!readyQueue.isEmpty()) {
            Process nextProcess = readyQueue.dequeue();
            System.out.println("Dispatching: " + nextProcess.getprocessName());
            cpu.run(nextProcess);

            currentTime += nextProcess.getInstructionCount();
        }
    }
}
