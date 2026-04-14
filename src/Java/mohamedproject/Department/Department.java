package Java.mohamedproject.Department;

import Java.mohamedproject.Employee.Employee;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Java.mohamedproject.Person.Person.isValidValue;

public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = 7L;
    private String departmentName;
    private ArrayList<Employee>listEmployee = new ArrayList<>();
    private int numberOfEmployees;

    public Department(){
        this.departmentName = " ";

    }

    public Department(String departmentName )throws Exception{

        if(isValidValue(Departments.class,departmentName)){
            this.departmentName = departmentName;

        }else {
            throw new Exception("Invalid Department !! ");
        }

    }

    public void addEmployee(Employee employee){
        listEmployee.add(employee);
        numberOfEmployees++;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public ArrayList<Employee> getListEmployee() {
        return listEmployee;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setListEmployee(ArrayList<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }
}
