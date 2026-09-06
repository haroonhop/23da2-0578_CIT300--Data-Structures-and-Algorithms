package structures;

import model.Patient;

/**
 * A custom FIFO Queue implementation for patients waiting in the emergency unit.
 * Built using a singly linked structure (not java.util) to demonstrate
 * understanding of the underlying data structure.
 */
public class EmergencyQueue {

    // Internal node class
    private class QueueNode {
        Patient patient;
        QueueNode next;

        QueueNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QueueNode front; // where we dequeue from
    private QueueNode rear;  // where we enqueue to
    private int size;

    // ---------- ENQUEUE ----------
    public void enqueue(Patient patient) {
        QueueNode newNode = new QueueNode(patient);

        if (rear == null) {
            // Queue was empty
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " added to the waiting queue.");
    }

    // ---------- DEQUEUE ----------
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty. No patient to treat.");
            return null;
        }

        Patient patient = front.patient;
        front = front.next;

        if (front == null) {
            rear = null; // Queue is now empty
        }
        size--;
        return patient;
    }

    // ---------- DISPLAY ----------
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting.");
            return;
        }

        System.out.println("Patients currently waiting (front to rear):");
        QueueNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    // ---------- HELPERS ----------
    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}