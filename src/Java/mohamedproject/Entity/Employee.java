package Java.mohamedproject.Entity;





import Java.mohamedproject.Enums.Nationality;

import Java.mohamedproject.Enums.PersonCity;


public abstract class Employee extends Person  {

    protected Department department;
    protected double salary ;
    protected int workHours;
    protected int experienceYears;
    public Employee(){
        super();
        this.salary = 0.0;
        this.workHours = 0;
        this.experienceYears = 0;
    }
    public  Employee (String name , PersonCity address , Nationality nationality, String id , double salary , int workHours , int experienceYears  , Department department) throws Exception {
        super(name , address,nationality,id);

            this.salary = salary;

            this.workHours = workHours;

            this.experienceYears = experienceYears;

            this.department = department;

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
