/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1so;
import EDD.Queue;
import Interfaces.Menu;
import Process.Process;
/**
 *
 * @author Gabriel
 */
public class Main {

    public static void main(String[] args) {
        Queue<Process> readyQueue = new Queue<>();
        java.awt.EventQueue.invokeLater(() -> {
            new Menu(readyQueue).setVisible(true);
            });
    }
}
