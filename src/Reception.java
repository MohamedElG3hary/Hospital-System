import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Reception extends Employee {

    private ArrayList<Patient> patients  = new ArrayList <>();

    private static int numberOfPatients;

    public Reception(){
        super();

    }

    public Reception(String name , String address , String nationality, String id , double salary , int workHours , int experienceYears ){
        super(name ,address , nationality, id,  salary , workHours , experienceYears);

    }

    public void addPatient(Patient patient){
        patients.add(patient);
        numberOfPatients++;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public ArrayList<Patient>getPatients(){
        return patients;
    }

    @Override
    public String toString() {
        return "Reception{" +
                super.toString() +
                "patients=" + patients +
                ", numberOfPatients=" + numberOfPatients +
                '}';
    }
}
