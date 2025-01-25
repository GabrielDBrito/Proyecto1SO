/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1so;
import EDD.Queue;

/**
 *
 * @author Gabriel
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(22);
        Integer peek=queue.peek();
        System.out.println(peek);
        queue.enqueue(40);
        queue.enqueue(55);
        queue.dequeue();
        peek=queue.peek();
        System.out.println(peek);
        queue.dequeue();
        peek=queue.peek();
        System.out.println(peek);
        queue.dequeue();
        peek=queue.peek();
        System.out.println(peek);
        
    }
}
