/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Settings;

/**
 *
 * @author Gabriel
 */
public class Settings {

    private int CPUs;
    private int instructionDuration;
    private String planningAlgorithm;

    public Settings (int CPUs, int instructionDuration, String planningAlgorithm){
        this.CPUs=CPUs;
        this.instructionDuration=instructionDuration;
        this.planningAlgorithm=planningAlgorithm;
    }
    
    public int getCPUs() {
        return CPUs;
    }

    public void setCPUs(int CPUs) {
        this.CPUs = CPUs;
    }

    public int getInstructionDuration() {
        return instructionDuration;
    }

    public void setInstructionDuration(int instructionDuration) {
        this.instructionDuration = instructionDuration;
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

