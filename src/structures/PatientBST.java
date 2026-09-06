package structures;

import model.Patient;

/**
 * Binary Search Tree storing Patient records, keyed by Patient ID.
 * Supports insert, search, delete, and in-order traversal.
 */
public class PatientBST {

    // Internal node class - each node holds one Patient
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;

    // ---------- INSERT ----------
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }

        int comparison = patient.getPatientId().compareTo(node.patient.getPatientId());

        if (comparison < 0) {
            node.left = insertRec(node.left, patient);
        } else if (comparison > 0) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return node;
    }

    // ---------- SEARCH ----------
    public Patient search(String patientId) {
        return searchRec(root, patientId);
    }

    private Patient searchRec(Node node, String patientId) {
        if (node == null) {
            return null; // Not found
        }

        int comparison = patientId.compareTo(node.patient.getPatientId());

        if (comparison == 0) {
            return node.patient;
        } else if (comparison < 0) {
            return searchRec(node.left, patientId);
        } else {
            return searchRec(node.right, patientId);
        }
    }

    // ---------- DELETE ----------
    public void delete(String patientId) {
        if (search(patientId) == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing to delete.");
            return;
        }
        root = deleteRec(root, patientId);
        System.out.println("Patient ID " + patientId + " deleted successfully.");
    }

    private Node deleteRec(Node node, String patientId) {
        if (node == null) {
            return null;
        }

        int comparison = patientId.compareTo(node.patient.getPatientId());

        if (comparison < 0) {
            node.left = deleteRec(node.left, patientId);
        } else if (comparison > 0) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Found the node to delete - 3 cases:

            // Case 1: no children
            if (node.left == null && node.right == null) {
                return null;
            }

            // Case 2: one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            // Case 3: two children
            // Find the smallest value in the right subtree (in-order successor)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            // Delete the successor from the right subtree
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL (ascending Patient ID order) ----------
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patients registered yet.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.patient);
            inorderRec(node.right);
        }
    }
}