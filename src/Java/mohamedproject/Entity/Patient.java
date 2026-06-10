package Java.mohamedproject.Entity;

import Java.mohamedproject.Enums.Nationality;

import Java.mohamedproject.Enums.PersonCity;
import java.util.ArrayList;

public class Patient extends Person {

    private String disease;
    private String bloodType;
    private ArrayList<Integer> orders;


    public Patient() {
        super();
        this.disease = "Empty";
        this.bloodType = "NO DATA !!";


    }

    public Patient(String name, PersonCity address, Nationality nationality, String id, String disease, String bloodType) throws Exception {
        super(name, address, nationality, id);
        this.disease = disease;
        this.bloodType = bloodType;


    }


    public String getDisease() {
        return disease;
    }

    public String getBloodType() {
        return bloodType;
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                "disease='" + disease + '\'' +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
