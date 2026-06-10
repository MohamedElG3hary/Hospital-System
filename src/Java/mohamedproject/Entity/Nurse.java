package Java.mohamedproject.Entity;

import Java.mohamedproject.Enums.Nationality;


import java.io.Serial;
import java.io.Serializable;
import Java.mohamedproject.Enums.PersonCity;

public class Nurse extends Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 4L;
    public Nurse() throws Exception {
        super();
        this.department = null;
    }


    public  Nurse (String name , PersonCity address , Nationality nationality, String id, double salary , int workHours , int experienceYears, Department department) throws Exception {
        super(name , address , nationality, id,  salary , workHours , experienceYears , department);

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
