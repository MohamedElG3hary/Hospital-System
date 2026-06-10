package Java.mohamedproject.Entity;



import java.io.Serial;
import java.io.Serializable;
import Java.mohamedproject.Enums.Departments;
import java.util.ArrayList;


public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = 7L;
    private Departments departmentName;
    private ArrayList<Employee> listEmployee = new ArrayList<>();

    public Department(Departments departmentName) {
        this.departmentName = departmentName;
    }

    public void addEmployee(Employee employee) {
        listEmployee.add(employee);
    }

    public String getDepartmentName() {
        return departmentName.name();
    }

    public ArrayList<Employee> getListEmployee() {
        return listEmployee;
    }

    public int getNumberOfEmployees() {
        return listEmployee.size();
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName=" + departmentName +
                ", numberOfEmployees=" + getNumberOfEmployees() +
                '}';
    }
}
