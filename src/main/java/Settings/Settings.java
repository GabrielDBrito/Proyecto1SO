/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Settings;

import Clock.ClockManager;

/**
 *
 * @author Gabriel
 */
public class Settings {

    private int CPUs;
    private double instructionDuration;
    private String planningAlgorithm;
    private ClockManager clockManager;

    public Settings (int CPUs, double instructionDuration, String planningAlgorithm,ClockManager clockManager){
        this.CPUs=CPUs;
        this.instructionDuration=instructionDuration;
        this.planningAlgorithm=planningAlgorithm;
        this.clockManager = clockManager;
    }
    
    public int getCPUs() {
        return CPUs;
    }

    public void setCPUs(int CPUs) {
        this.CPUs = CPUs;
    }

    public double getInstructionDuration() {
        return instructionDuration;
    }

    public void setInstructionDuration(double instructionDuration) {
        this.instructionDuration = instructionDuration;
        clockManager.updateInstructionDuration(instructionDuration);
    }

    public String getPlanningAlgorithm() {
        return planningAlgorithm;
    }

    public void setPlanningAlgorithm(String planningAlgorithm) {
        this.planningAlgorithm = planningAlgorithm;
    }

    public void printSettings() {
        System.out.println("CPUs: " + CPUs);
        System.out.println("Instruction Duration: " + instructionDuration);
        System.out.println("Planning Algorithm: " + planningAlgorithm);
    }
    
}

