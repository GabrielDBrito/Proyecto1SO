/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Gabriel
 */
public class Queue<T> {

    private static class Node<T> {
        T data;       
        Node<T> next; 

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front; // Pointer to the front of the queue
    private Node<T> rear;  // Pointer to the rear of the queue
    private int size;      

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    // Method to add an element to the rear of the queue
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode; // If the queue is empty, front and rear point to the new node
            rear = newNode;
        } else {
            rear.next = newNode; // Link the new node to the end of the queue
            rear = newNode;      // Update the rear pointer
        }
        size++;
    }

    // Method to remove and return the element at the front of the queue
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        T data = front.data;     // Get the data from the front node
        front = front.next;      // Move the front pointer to the next node
        if (front == null) {     // If the queue is now empty, set rear to null as well
            rear = null;
        }
        size--;
        return data;
    }

    // Method to peek at the front element without removing it
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }
}
