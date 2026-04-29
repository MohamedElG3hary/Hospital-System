package Java.mohamedproject.Nurse;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Employee.Employee;
import Java.mohamedproject.Nationality.Nationality;
import Java.mohamedproject.Person.PersonCity;

import java.io.Serial;
import java.io.Serializable;

public class Nurse extends Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 4L;
    public Nurse() throws Exception {
        super();
        this.department = null;
    }


    public  Nurse (String name , PersonCity address , Nationality nationality, String id, double salary , int workHours , int experienceYears, Department department) throws Exception {
        super(name , address , nationality, id,  salary , workHours , experienceYears );
        this.department = department;

    }


    @Override
    public String toString() {
        return "Nurse {" +
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
