package model;

/**
 * Represents a completed treatment record.
 * Pushed onto the TreatmentStack once a patient's treatment is finished.
 */
public class TreatmentRecord {

    private String patientId;
    private String patientName;
    private String treatmentDetails;
    private String completionDate;

    public TreatmentRecord(String patientId, String patientName, String treatmentDetails, String completionDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentDetails = treatmentDetails;
        this.completionDate = completionDate;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
               " | Name: " + patientName +
               " | Treatment: " + treatmentDetails +
               " | Completed: " + completionDate;
    }
}