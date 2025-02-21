/*
/*
/*
/*
/*
/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

import CPU.CPU;
import EDD.Queue;
import Process.Process;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Andrea
 */
public class SPN implements SchedulingAlgorithm {
    private Queue<Process> readyQueue; // Cola de procesos
    private Semaphore semaphore;       // Semáforo para sincronización

    // Constructor que acepta la cola de procesos
    public SPN(Queue<Process> readyQueue) {
        this.readyQueue = readyQueue;
        this.semaphore = new Semaphore(1); // Semáforo binario para exclusión mutua
    }

    @Override
    public void reorder() {
        try {
            // Adquirimos el semáforo para garantizar acceso exclusivo a la cola
            semaphore.acquire(); 

            // Crear un arreglo temporal para almacenar los procesos
            Process[] processes = new Process[readyQueue.getSize()];
            int index = 0;

            // Desencolar todos los procesos y almacenarlos en el arreglo
            while (!readyQueue.isEmpty()) {
                processes[index++] = readyQueue.dequeue();
            }

            // Ordenamos los procesos según el número de instrucciones (SPN)
            for (int i = 0; i < processes.length - 1; i++) {
                for (int j = i + 1; j < processes.length; j++) {
                    if (processes[i].getInstructionCount() > processes[j].getInstructionCount()) {
                        // Intercambiar los procesos si el primero tiene más instrucciones
                        Process temp = processes[i];
                        processes[i] = processes[j];
                        processes[j] = temp;
                    }
                }
            }

            // Reinsertar los procesos ordenados de nuevo en la cola
            for (Process process : processes) {
                readyQueue.enqueue(process);
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Manejo de interrupciones
        } finally {
            // Liberamos el semáforo después de la operación
            semaphore.release();
        }
    }

    @Override
    public void dispatch(CPU cpu) {
        try {
            // Adquirimos el semáforo para sincronizar el acceso a la cola
            semaphore.acquire(); 

            // Despachar los procesos según el orden determinado por SPN
            while (!readyQueue.isEmpty()) {
                Process nextProcess = readyQueue.dequeue();  // Obtener el siguiente proceso
                System.out.println("Dispatching: " + nextProcess.getprocessName());
                cpu.run(nextProcess);  // Ejecutar el proceso en la CPU
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Manejo de interrupciones
        } finally {
            // Liberamos el semáforo después del despacho
            semaphore.release();
        }
    }
}