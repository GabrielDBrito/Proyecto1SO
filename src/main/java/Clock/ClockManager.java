/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClockManager {
    private int clockCycles = 0;
    private double instructionDuration;
    private ScheduledExecutorService scheduler;
    private Runnable clockTask;

    public ClockManager(double instructionDuration) {
        this.instructionDuration = instructionDuration;
        startClock();
    }

    private void startClock() {
        scheduler = Executors.newScheduledThreadPool(1);
        clockTask = () -> {
            synchronized (this) { // Synchronization to avoid problems in multiple threads
                clockCycles++;
                //System.out.println("Clock Cycle: " + clockCycles);
            }
        };

        long initialDelay = 0;
        long period = (long) (instructionDuration * 1000); // s to ms

        scheduler.scheduleAtFixedRate(clockTask, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    public synchronized void updateInstructionDuration(double newDuration) {
        if (newDuration == instructionDuration) return;

        this.instructionDuration = newDuration;
        restartClockWithNewRate();
    }

    private synchronized void restartClockWithNewRate() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                if (!scheduler.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                    scheduler.shutdownNow();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        startClock(); // new time interval 
    }

    public synchronized int getClockCycles() {
        return clockCycles;
    }
}
