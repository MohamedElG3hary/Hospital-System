package Java.mohamedproject.Doctor;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Employee.Employee;

import java.io.Serial;
import java.io.Serializable;

public class Doctor extends Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;

    public Doctor() throws Exception {
        super();
        this.department = new Department("!!!");
    }


    public  Doctor (String name , String address , String nationality, String id, double salary , int workHours , int experienceYears, Department department) throws Exception {
        super(name , address , nationality, id,  salary , workHours , experienceYears);
        this.department = department;

    }



    @Override
    public String toString() {
        return "Doctor{" +
                "  Name = "+ getName() +
                ", Address = "+ getAddress()+
                ", Nationality = " + getNationality() +
                ", id='" + id + '\'' +
                ", salary=" + salary +
                ", workHours=" + workHours +
                ", experienceYears=" + experienceYears +
                ", department=" + department +
                '}';
    }
}
