import java.util.ArrayList;

public class Administrators  extends Employee{

    private Department department;
    private Doctor doctor;
    private Nurse nurse;
    private int numberOfDoctorsAdded;
    private int numberOfNursesAdded;
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Reception> receptions = new ArrayList<>();
    private ArrayList<Employee> listEmployee = new ArrayList<>();


    public Administrators(){
        super();
    }

    public Administrators(String name , String address , String nationality, String id, double salary , int workHours , int experienceYears){
        super(name,address,nationality,id,salary,workHours,experienceYears);
    }


    private Department findDepartmentByName(String name) {
        for (Department d : departments) {
            if (d.getDepartmentName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }
    public void addMedicalStaff(Employee employee){
        Department dept = findDepartmentByName(employee.getDepartment().getDepartmentName());
        if(dept==null){
            dept = employee.getDepartment();
            departments.add(dept);
        }else {
            dept.addEmployee(employee);
        }
        listEmployee.add(employee);

        if (employee instanceof Doctor) {
            numberOfDoctorsAdded++;
        } else if (employee instanceof Nurse) {
            numberOfNursesAdded++;
        }


    }



    public void addDepartment(Department department )throws Exception{
        Department dept = findDepartmentByName(department.getDepartmentName());
        if (dept == null){
            departments.add(department);
        }else {
            throw new Exception("Department Was Exist !! ");
        }

    }
    public void addReception(Reception reception){
        receptions.add(reception);
    }

    public ArrayList<Employee> getDoctors() {
        return listEmployee;
    }

    public int getNumberOfDoctors() {
        return numberOfDoctorsAdded;
    }

    public int getNumberOfNursesAdded() {
        return numberOfNursesAdded;
    }


    public ArrayList<Employee> KnowAllDoctors(){
        return department.getListEmployee();
    }
    public ArrayList<Department>knowAllDepartment(){
        return departments;
    }
    public ArrayList<Reception>getReceptions(){
        return receptions;
    }
    public ArrayList<Employee> getListEmployee() {
        return listEmployee;
    }



    @Override
    public String toString() {
        return "Administrators{" +
//                super.toString() +
                "Medical Staff Added =" + listEmployee  +
                "Number of Doctors Added = " +numberOfDoctorsAdded +
                "Number of Nurses Added = "+ numberOfNursesAdded+
                '}';
    }
}
