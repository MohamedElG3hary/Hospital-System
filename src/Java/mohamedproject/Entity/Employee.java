package Java.mohamedproject.Employee;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Nationality.Nationality;
import Java.mohamedproject.Person.Person;
import Java.mohamedproject.Person.PersonCity;
import LoginService.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.InputMismatchException;

public abstract class Employee extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    protected Department department;
    protected double salary ;
    protected int workHours;
    protected int experienceYears;
    protected User user = new User();
    public Employee(){
        super();
        this.salary = 0.0;
        this.workHours = 0;
        this.experienceYears = 0;
    }
    public  Employee (String name , PersonCity address , Nationality nationality, String id , double salary , int workHours , int experienceYears ) throws Exception {
        super(name , address,nationality,id);
        try {

            this.salary = salary;
            this.workHours = workHours;
            this.experienceYears = experienceYears;
        }catch (InputMismatchException e){
            System.out.println("Invalid Data !!");
        }


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

    public User getUser() {
        return user;
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
