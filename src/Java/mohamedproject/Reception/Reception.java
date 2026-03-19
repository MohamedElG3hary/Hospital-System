package Java.mohamedproject.Reception;

import Java.mohamedproject.Employee.Employee;
import Java.mohamedproject.Order.Order;
import Java.mohamedproject.SortValues.SortByValue;
import Java.mohamedproject.Patient.Patient;

import java.util.*;

public class Reception extends Employee {

    private ArrayList<Patient> patients  = new ArrayList <>();
    private ArrayList<Order> orders  = new ArrayList <>();

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


    public void addOrderedPatient(Order order){
            orders.addLast(order);
            Collections.sort(orders, new SortByValue());



    }

    public ArrayList<Order> getOrders (){
        return orders;
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
