# 23da2-0578_CIT300--Data-Structures-and-Algorithms

# Mini Hospital Emergency Management System

A Java console application simulating hospital patient management using core data structures.

## Data Structures Used
- **Binary Search Tree (BST)** — stores patient records keyed by Patient ID (src/structures/PatientBST.java)
- **Queue** — manages patients waiting in the emergency unit, FIFO (src/structures/EmergencyQueue.java)
- **Stack** — stores completed treatment history, LIFO (src/structures/TreatmentStack.java)
- **Singly Linked List** — stores each patient's visit history (src/structures/VisitLinkedList.java)

## How to Run
1. Open the project in VS Code (with Java Extension Pack installed)
2. Open src/Main.java
3. Click Run above the main method

## Project Structure
src/model — Patient, Visit, TreatmentRecord classes
src/structures — PatientBST, EmergencyQueue, TreatmentStack, VisitLinkedList
src/Main.java — console menu tying everything together
