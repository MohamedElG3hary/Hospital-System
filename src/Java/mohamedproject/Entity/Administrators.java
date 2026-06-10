package Java.mohamedproject.Entity;




import Java.mohamedproject.Enums.Nationality;

import Java.mohamedproject.Enums.PersonCity;


import java.io.Serializable;



public class Administrators extends Employee implements Serializable {

    private int numberOfDoctorsAdded;
    private int numberOfNursesAdded;

    public Administrators() {
        super();

    }


    public Administrators(String name, PersonCity address, Nationality nationality, String id, double salary, int workHours, int experienceYears, int numberOfDoctorsAdded, int numberOfNursesAdded , Department department) throws Exception {
        super(name, address, nationality, id, salary, workHours, experienceYears , department);
        this.numberOfDoctorsAdded = numberOfDoctorsAdded;
        this.numberOfNursesAdded = numberOfNursesAdded;
    }

    public void setNumberOfDoctorsAdded(int numberOfDoctorsAdded) {
        this.numberOfDoctorsAdded = numberOfDoctorsAdded;
    }

    public void setNumberOfNursesAdded(int numberOfNursesAdded) {
        this.numberOfNursesAdded = numberOfNursesAdded;
    }

    public int getNumberOfDoctorsAdded() {
        return numberOfDoctorsAdded;
    }

    public int getNumberOfNursesAdded() {
        return numberOfNursesAdded;
    }



    @Override
    public String toString() {
        return "Administrators{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", address=" + getAddress() +
                ", nationality=" + getNationality() +
                ", salary=" + getSalary() +
                ", workHours=" + getWorkHours() +
                ", experienceYears=" + getExperienceYears() +
                ", department=" + getDepartment() +
                ", numberOfDoctorsAdded=" + numberOfDoctorsAdded +
                ", numberOfNursesAdded=" + numberOfNursesAdded +
                '}';
    }




}
