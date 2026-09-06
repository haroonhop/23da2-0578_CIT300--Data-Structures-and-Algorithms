package structures;

import model.TreatmentRecord;

/**
 * A custom LIFO Stack implementation for completed treatment records.
 * Built using a singly linked structure (not java.util).
 */
public class TreatmentStack {

    // Internal node class
    private class StackNode {
        TreatmentRecord record;
        StackNode next;

        StackNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private StackNode top;
    private int size;

    // ---------- PUSH ----------
    public void push(TreatmentRecord record) {
        StackNode newNode = new StackNode(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for " + record.getPatientName() + " added to history.");
    }

    // ---------- POP ----------
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("No treatment records available to remove.");
            return null;
        }

        TreatmentRecord record = top.record;
        top = top.next;
        size--;
        return record;
    }

    // ---------- DISPLAY ----------
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records yet.");
            return;
        }

        System.out.println("Treatment history (most recent first):");
        StackNode current = top;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.record);
            current = current.next;
            position++;
        }
    }

    // ---------- HELPERS ----------
    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}