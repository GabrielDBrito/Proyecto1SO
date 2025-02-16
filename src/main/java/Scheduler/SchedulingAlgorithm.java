/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Scheduler;

import CPU.CPU;

/**
 *
 * @author Gabriel
 */

public interface SchedulingAlgorithm {
    void reorder();
    void dispatch(CPU cpu);
}
