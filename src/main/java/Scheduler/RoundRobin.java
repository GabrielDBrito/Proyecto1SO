/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

import EDD.Queue;
import Process.Process;

/**
 *
 * @author Andrea
 */
public class RoundRobin {
      private int quantum= 5;  // Hay q agregar algo en la interfaz que permita preguntarle al usuario esto puede ser 5,10,15

    // Constructor for the quantum
    public RoundRobin(int quantum) {
        this.quantum = quantum;
    }

    public void executeRoundRobin(Queue<Process> queue) {
        Queue<Process> readyQueue = new Queue<>();  //Ready queue
        int currentTime = 0;

        // Adds proccess to queue
        while (!queue.isEmpty()) {
            readyQueue.enqueue(queue.dequeue());
        }

        // runs processes following roundrobin
        while (!readyQueue.isEmpty()) {
            Process currentProcess = readyQueue.dequeue();
            int remainingBurstTime = currentProcess.getInstructionCount();

            System.out.println("Time: " + currentTime + " - Running process: " + currentProcess.getprocessName());

            if (remainingBurstTime > quantum) {
                // If time > quantum
                currentProcess.setInstructionCount(remainingBurstTime - quantum);  // minus left time
                currentTime += quantum;  // Adds to the global time of the quantum
                System.out.println("Time: " + currentTime + " - Running process " + currentProcess.getprocessName() + " paused, time left: " + currentProcess.getInstructionCount());
                readyQueue.enqueue(currentProcess);  // Reinsterts the process in the queue
            } else {
                // process time < quantum
                currentTime += remainingBurstTime;  // Adds to the global time
                System.out.println("Time: " + currentTime + " - Process " + currentProcess.getprocessName() + " completed");
            }
        }
        System.out.println("All processes have been executed.");
    }
}
