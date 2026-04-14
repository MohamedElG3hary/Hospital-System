package Java.mohamedproject.Employee;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Person.Person;

import java.io.Serial;
import java.io.Serializable;

public abstract class Employee extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    protected Department department;
    protected double salary ;
    protected int workHours;
    protected int experienceYears;
    private String password;

    public Employee(){
        super();
        this.salary = 0.0;
        this.workHours = 0;
        this.experienceYears = 0;
        this.password = "";
    }
    public  Employee (String name , String address , String nationality, String id , double salary , int workHours , int experienceYears ) throws Exception {
        super(name , address,nationality,id);
        this.salary = salary;
        this.workHours = workHours;
        this.experienceYears = experienceYears;


    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public double getSalary() {
        return salary;
    }

    public int getWorkHours() {
        return workHours;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString()+
                "salary=" + salary +
                ", workHours=" + workHours +
                ", Department =" + department +
                ", experienceYears=" + experienceYears +
                '}';
    }
}
