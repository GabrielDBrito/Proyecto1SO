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
public class SPN implements SchedulingAlgorithm {
    private Queue<Process> readyQueue; // Custom queue implementation

    public SPN(Queue<Process> readyQueue) {
        this.readyQueue = readyQueue;
    }

    @Override
    public void reorder() {
        // Create a temporary array to store the processes
        Process[] processes = new Process[readyQueue.getSize()];
        int index = 0;

        // Dequeue all elements from the readyQueue to array
        while (!readyQueue.isEmpty()) {
            processes[index++] = readyQueue.dequeue();
        }

        // Sort the processes based on instruction count for SPN
        for (int i = 0; i < processes.length - 1; i++) {
            for (int j = i + 1; j < processes.length; j++) {
                if (processes[i].getInstructionCount() > processes[j].getInstructionCount()) {
                    // Swap the processes if the first has more instruction count
                    Process temp = processes[i];
                    processes[i] = processes[j];
                    processes[j] = temp;
                }
            }
        }

        // Reinsert the sorted processes back into the queue
        for (Process process : processes) {
            readyQueue.enqueue(process);
        }
    }

    @Override
public void dispatch(CPU cpu) {
    reorder();  // Reorder the processes based on SPN (Shortest Process Next)

    // Continue dispatching processes in order
    while (!readyQueue.isEmpty()) {
        Process nextProcess = readyQueue.dequeue();  // Get the next process in the ordered queue
        System.out.println("Dispatching: " + nextProcess.getprocessName());
        cpu.run(nextProcess);  // Dispatch the process to the CPU
    }
}}
