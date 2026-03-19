import java.util.ArrayList;

public class Order {

   private Patient patient;


    private int value;

    public Order() {
        this.value =-1;
    }

    public Order(int value , Patient patient) {
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
        return "Order{" +
                "patient=" + patient.toString() +
                ", value=" + value +
                '}';
    }
}
