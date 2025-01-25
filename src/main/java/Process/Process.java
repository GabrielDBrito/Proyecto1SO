package Process;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gabriel
 */
public class Process {
    private int ID;
    private String name;
    private int instructionCount;
    private boolean CPUbound;
    private boolean IObound;
    private Integer ciclesToExcept;
    private String status;  // Cambiado de 'state' a 'status'
    
    // Nuevos atributos
    private Integer PC;  // Contador de Programa
    private Integer MAR; // Memoria de Acceso Aleatorio (en mayúsculas)
    
    // Constructor actualizado
    public Process(int id, String name, int instructionCount, boolean CPUbound, boolean IObound, 
                   Integer ciclesToExcept, String status, Integer PC, Integer MAR){
        this.ID = id;
        this.name = name;
        this.instructionCount = instructionCount;
        this.CPUbound = CPUbound;
        this.IObound = IObound;
        this.ciclesToExcept = ciclesToExcept;
        this.status = status;  
        this.PC = PC;  
        this.MAR = MAR; 
    }
    
    public Integer getPC() {
        return PC;
    }

    public void setPC(Integer PC) {
        this.PC = PC;
    }

    public Integer getMAR() {
        return MAR;
    }

    public void setMAR(Integer MAR) {
        this.MAR = MAR;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInstructionCount() {
        return instructionCount;
    }

    public void setInstructionCount(int instructionCount) {
        this.instructionCount = instructionCount;
    }

    public boolean isCPUbound() {
        return CPUbound;
    }

    public void setCPUbound(boolean CPUbound) {
        this.CPUbound = CPUbound;
    }

    public boolean isIObound() {
        return IObound;
    }

    public void setIObound(boolean IObound) {
        this.IObound = IObound;
    }

    public Integer getCiclesToExcept() {
        return ciclesToExcept;
    }

    public void setCiclesToExcept(Integer ciclesToExcept) {
        this.ciclesToExcept = ciclesToExcept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
