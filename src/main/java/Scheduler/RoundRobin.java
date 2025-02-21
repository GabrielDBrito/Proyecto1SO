package Scheduler;

import EDD.Queue;
import Process.Process;
import CPU.CPU;
import java.util.concurrent.Semaphore;

public class RoundRobin implements SchedulingAlgorithm {
    private Queue<Process> readyQueue;
    private int quantum;
    private int currentTime;
    private Semaphore semaphore; // Semáforo para sincronizar el acceso al CPU

    public RoundRobin(Queue<Process> readyQueue, int quantum) {
        this.readyQueue = readyQueue;
        this.quantum = quantum;
        this.currentTime = 0;
        this.semaphore = new Semaphore(1); // Inicializar el semáforo con 1 permiso
    }

    @Override
    public void reorder() {
        // El algoritmo Round Robin no necesita reorganizar la cola como HRRN
        // Simplemente pasa los procesos al CPU cuando sea su turno
    }

    @Override
    public void dispatch(CPU cpu) {
        try {
            // Intentamos adquirir el semáforo antes de proceder con la ejecución
            semaphore.acquire();

            // Ejecutamos los procesos de la cola de listos
            if (!readyQueue.isEmpty()) {
                Process currentProcess = readyQueue.dequeue(); // Extraemos el proceso

                // Mostramos información del proceso en ejecución
                System.out.println("Dispatching: " + currentProcess.getprocessName() + " at time: " + currentTime);

                // Simulamos el tiempo de ejecución del proceso según el quantum
                int remainingBurstTime = currentProcess.getInstructionCount();
                int executionTime = Math.min(quantum, remainingBurstTime); // Si el proceso no termina en este quantum, se ejecutará parcialmente

                currentProcess.setInstructionCount(remainingBurstTime - executionTime); // Actualizamos el tiempo restante del proceso

                currentTime += executionTime; // Aumentamos el tiempo actual en función del tiempo de ejecución

                // Si el proceso no ha terminado, lo reincorporamos a la cola
                if (currentProcess.getInstructionCount() > 0) {
                    readyQueue.enqueue(currentProcess);
                    System.out.println("Time: " + currentTime + " - Process " + currentProcess.getprocessName() + " paused, time left: " + currentProcess.getInstructionCount());
                } else {
                    System.out.println("Time: " + currentTime + " - Process " + currentProcess.getprocessName() + " completed");
                }

                // Simulamos que el CPU corre el proceso
                cpu.run(currentProcess);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Liberamos el semáforo para que otro proceso pueda acceder al CPU
            semaphore.release();
        }
    }
}
