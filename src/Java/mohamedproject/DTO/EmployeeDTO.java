package Java.mohamedproject.DTO;

public class EmployeeDTO {

    public int personId;

    public String name;

    public String city;

    public String nationality;

    public String departmentName;

    public double salary;

    public int workHours;

    public int experienceYears;

    public String employeeType;


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", nationality='" + nationality + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", salary=" + salary +
                ", workHours=" + workHours +
                ", experienceYears=" + experienceYears +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }
}
