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
    private int instructionDuration;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Runnable clockTask;

    public ClockManager(int instructionDuration) {
        this.instructionDuration = instructionDuration;
        startClock();
    }

    private void startClock() {
        clockTask = () -> {
            clockCycles++;
            System.out.println("Clock Cycle: " + clockCycles);
        };

        scheduler.scheduleAtFixedRate(clockTask, 0, instructionDuration, TimeUnit.SECONDS);
    }

    public void updateInstructionDuration(int newDuration) {
        if (newDuration == instructionDuration) return; // No changes

        this.instructionDuration = newDuration;
        restartClockWithNewRate();
    }

    private void restartClockWithNewRate() {
        scheduler.shutdown(); // Stop current execution without restarting clockCycles

        
        try {
            if (!scheduler.awaitTermination(300, TimeUnit.MILLISECONDS)) {
                scheduler.shutdownNow(); // 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // new clockScheduler with new instructionDuration
        ScheduledExecutorService newScheduler = Executors.newScheduledThreadPool(1);
        newScheduler.scheduleAtFixedRate(clockTask, 0, instructionDuration, TimeUnit.SECONDS);
    }

    public int getClockCycles() {
        return clockCycles;
    }
}
