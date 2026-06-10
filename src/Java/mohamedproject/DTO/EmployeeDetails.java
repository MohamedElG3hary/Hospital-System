package Java.mohamedproject.DTO;

import Java.mohamedproject.Enums.Departments;
import Java.mohamedproject.Enums.Nationality;
import Java.mohamedproject.Enums.PersonCity;

public class EmployeeDetails {


    public String name ;
    public PersonCity city ;
    public Nationality nationality ;
    public Departments departmentName ;
    public double salary ;
    public int workHours ;
    public int experienceYears ;

    public EmployeeDetails() {
    }

    public EmployeeDetails(String name, PersonCity city, Nationality nationality, Departments departmentName, double salary, int workHours, int experienceYears) {
        this.name = name;
        this.city = city;
        this.nationality = nationality;
        this.departmentName = departmentName;
        this.salary = salary;
        this.workHours = workHours;
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", nationality='" + nationality + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", salary=" + salary +
                ", workHours=" + workHours +
                ", experienceYears=" + experienceYears +
                '}';
    }
}