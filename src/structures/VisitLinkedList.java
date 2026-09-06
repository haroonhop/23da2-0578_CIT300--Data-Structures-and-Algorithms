package structures;

import model.Visit;

/**
 * Custom Singly Linked List holding a patient's previous visit history.
 * Each Patient object owns exactly one of these.
 */
public class VisitLinkedList {

    // Internal node class
    private class VisitNode {
        Visit visit;
        VisitNode next;

        VisitNode(Visit visit) {
            this.visit = visit;
        }
    }

    private VisitNode head;

    // ---------- ADD (append to end, so history stays in chronological order) ----------
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);

        if (head == null) {
            head = newNode;
            return;
        }

        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // ---------- REMOVE ----------
    public boolean removeVisit(String visitId) {
        if (head == null) {
            return false;
        }

        // If the head itself is the one to remove
        if (head.visit.getVisitId().equals(visitId)) {
            head = head.next;
            return true;
        }

        VisitNode current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId().equals(visitId)) {
                current.next = current.next.next; // Skip over the removed node
                return true;
            }
            current = current.next;
        }
        return false; // Not found
    }

    // ---------- SEARCH ----------
    public Visit searchVisit(String visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.visit.getVisitId().equals(visitId)) {
                return current.visit;
            }
            current = current.next;
        }
        return null; // Not found
    }

    // ---------- DISPLAY ----------
    public void displayHistory() {
        if (head == null) {
            System.out.println("No visit history recorded.");
            return;
        }

        VisitNode current = head;
        int count = 1;
        while (current != null) {
            System.out.println(count + ". " + current.visit);
            current = current.next;
            count++;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}