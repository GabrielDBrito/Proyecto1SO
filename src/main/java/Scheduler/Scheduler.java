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
 * @author Gabriel
 */
public class Scheduler {

    private Queue<Process> readyQueue;
    private SchedulingAlgorithm algorithm;

    public Scheduler(SchedulingAlgorithm algorithm, Queue readyQueue) {
        this.algorithm = algorithm;
        this.readyQueue = readyQueue;
    }
    
    //if is needed (depending on the algorithm)
    public void reorder() {
        algorithm.reorder();
    }

    public void dispatch(CPU cpu){
        reorder();
        Process process =readyQueue.dequeue();
        cpu.run(process);
    }
}
