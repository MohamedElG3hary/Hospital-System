import java.util.ArrayList;

public class Department {

    private String departmentName;
    private ArrayList<Employee>listEmployee = new ArrayList<>();
    private int numberOfEmployees;

    public Department(){
        this.departmentName = " ";

    }

    public Department(String departmentName ){

        this.departmentName = departmentName;

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
                ", listEmployee=" + listEmployee +
                ", numberOfEmployees=" + numberOfEmployees +
                '}';
    }
}
