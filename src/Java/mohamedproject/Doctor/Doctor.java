package Java.mohamedproject.Doctor;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Employee.Employee;

public class Doctor extends Employee {

    public Doctor(){
        super();
        this.department = new Department("!!!");
    }


    public  Doctor (String name , String address , String nationality, String id, double salary , int workHours , int experienceYears, Department department){
        super(name , address , nationality, id,  salary , workHours , experienceYears);
        this.department = department;

    }


    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() + "}" ;
    }

}
