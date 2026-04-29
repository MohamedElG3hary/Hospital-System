package Java.mohamedproject.Order;

import Java.mohamedproject.Patient.Patient;

import java.io.Serial;
import java.io.Serializable;


public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 6L;
    private Patient patient;


    private int value;

    public Order() {
        this.value = -1;
    }

    public Order(int value, Patient patient) {
        this.value = value;
        this.patient = patient;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "Java.mohamedproject.Order.Order{" +
                "patient=" + patient.toString() +
                ", value=" + value +
                '}';
    }


}
