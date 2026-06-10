package Java.mohamedproject.Entity;

import Java.mohamedproject.Enums.Nationality;
import Java.mohamedproject.Enums.PersonCity;

public class Reception extends Employee {

    public Reception() {
        super();
    }

    public Reception(String name, PersonCity address, Nationality nationality, String id, double salary, int workHours, int experienceYears, Department department ) throws Exception {
        super(name, address, nationality, id, salary, workHours, experienceYears, department );
    }

    @Override
    public String toString() {
        return "Reception{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", address=" + getAddress() +
                ", nationality=" + getNationality() +
                ", salary=" + getSalary() +
                ", workHours=" + getWorkHours() +
                ", experienceYears=" + getExperienceYears() +
                ", department=" + getDepartment() +
                '}';
    }
}