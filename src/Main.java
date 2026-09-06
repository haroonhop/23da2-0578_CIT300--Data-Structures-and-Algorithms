import model.Patient;
import model.Visit;
import model.TreatmentRecord;
import structures.PatientBST;
import structures.EmergencyQueue;
import structures.TreatmentStack;

import java.util.Scanner;

/**
 * Console entry point for the Mini Hospital Emergency Management System.
 * Menu wires together: PatientBST, EmergencyQueue, TreatmentStack, VisitLinkedList.
 */
public class Main {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> searchPatient();
                case 3 -> deletePatient();
                case 4 -> patientBST.inorderTraversal();
                case 5 -> enqueuePatient();
                case 6 -> dequeuePatient();
                case 7 -> emergencyQueue.displayQueue();
                case 8 -> completeTreatment();
                case 9 -> undoLastTreatment();
                case 10 -> treatmentStack.displayStack();
                case 11 -> addVisitHistory();
                case 12 -> viewVisitHistory();
                case 13 -> searchVisitHistory();
                case 0 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== Mini Hospital Emergency Management System =====");
        System.out.println(" -- Patient Records (BST) --");
        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient by ID");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients (In-Order)");
        System.out.println(" -- Emergency Queue --");
        System.out.println("5. Add Patient to Waiting Queue");
        System.out.println("6. Call Next Patient for Treatment");
        System.out.println("7. Display Waiting Queue");
        System.out.println(" -- Treatment History (Stack) --");
        System.out.println("8. Complete Treatment (Push Record)");
        System.out.println("9. Undo Last Treatment Record (Pop)");
        System.out.println("10. Display Treatment History");
        System.out.println(" -- Patient Visit History (Linked List) --");
        System.out.println("11. Add Visit to Patient History");
        System.out.println("12. View Patient Visit History");
        System.out.println("13. Search Visit by ID");
        System.out.println("0. Exit");
    }

    // ---------- BST OPERATIONS ----------
    private static void registerPatient() {
        String id = readString("Enter Patient ID: ");
        String name = readString("Enter Patient Name: ");
        int age = readInt("Enter Age: ");
        String contact = readString("Enter Contact Number: ");
        String condition = readString("Enter Medical Condition: ");

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    private static void searchPatient() {
        String id = readString("Enter Patient ID to search: ");
        Patient patient = patientBST.search(id);
        if (patient != null) {
            System.out.println("Found: " + patient);
        } else {
            System.out.println("Patient with ID " + id + " not found.");
        }
    }

    private static void deletePatient() {
        String id = readString("Enter Patient ID to delete: ");
        patientBST.delete(id);
    }

    // ---------- QUEUE OPERATIONS ----------
    private static void enqueuePatient() {
        String id = readString("Enter Patient ID (must already be registered): ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found. Please register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    private static void dequeuePatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient != null) {
            System.out.println("Now treating: " + patient);
        }
    }

    // ---------- STACK OPERATIONS ----------
    private static void completeTreatment() {
        String id = readString("Enter Patient ID: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String details = readString("Enter treatment details: ");
        String date = readString("Enter completion date (e.g. 2026-09-06): ");

        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), details, date);
        treatmentStack.push(record);
    }

    private static void undoLastTreatment() {
        TreatmentRecord record = treatmentStack.pop();
        if (record != null) {
            System.out.println("Removed most recent treatment record: " + record);
        }
    }

    // ---------- LINKED LIST OPERATIONS ----------
    private static void addVisitHistory() {
        String patientId = readString("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        String visitId = readString("Enter Visit ID: ");
        String date = readString("Enter Visit Date: ");
        String doctor = readString("Enter Doctor Name: ");
        String diagnosis = readString("Enter Diagnosis: ");
        String treatment = readString("Enter Treatment: ");

        Visit visit = new Visit(visitId, date, doctor, diagnosis, treatment);
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Visit added to " + patient.getName() + "'s history.");
    }

    private static void viewVisitHistory() {
        String patientId = readString("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        patient.getVisitHistory().displayHistory();
    }

    private static void searchVisitHistory() {
        String patientId = readString("Enter Patient ID: ");
        Patient patient = patientBST.search(patientId);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }
        String visitId = readString("Enter Visit ID to search: ");
        Visit visit = patient.getVisitHistory().searchVisit(visitId);
        if (visit != null) {
            System.out.println("Found: " + visit);
        } else {
            System.out.println("Visit ID " + visitId + " not found in this patient's history.");
        }
    }

    // ---------- INPUT HELPERS ----------
    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        return value;
    }
}