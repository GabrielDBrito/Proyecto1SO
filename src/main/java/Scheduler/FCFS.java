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
public class FCFS implements SchedulingAlgorithm{
    private Queue readyQueue;

    public FCFS(Queue readyQueue) {
        this.readyQueue=readyQueue;
    }

    @Override
    public void reorder() {
        //do nothing 
    }

    @Override
    public void dispatch(CPU cpu) {
        cpu.run((Process) readyQueue.dequeue());
    } 
    
    public Queue getReadyQueue() {
        return readyQueue;
    }

    public void setReadyQueue(Queue readyQueue) {
        this.readyQueue = readyQueue;
    }
}
