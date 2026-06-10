package Java.mohamedproject.Services.Results;

public class PatientWithOrderResult {

    private final int patientId;
    private final int orderId;

    public PatientWithOrderResult(int patientId, int orderId) {
        this.patientId = patientId;
        this.orderId = orderId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "PatientWithOrderResult{" +
                "patientId=" + patientId +
                ", orderId=" + orderId +
                '}';
    }

}
