package Java.mohamedproject.Nurse;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Employee.Employee;

public class Nurse extends Employee {
    public Nurse(){
        super();
        this.department = new Department("!!!");
    }


    public  Nurse (String name , String address , String nationality, String id, double salary , int workHours , int experienceYears, Department department){
        super(name , address , nationality, id,  salary , workHours , experienceYears );
        this.department = department;

    }


    @Override
    public String toString() {
        return "Nurse{" +
                super.toString() +
                "department=" + department.getDepartmentName() +
                '}';
    }
}
