package Java.mohamedproject.Classes;

import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Doctor.Doctor;
import Java.mohamedproject.Employee.Employee;
import Java.mohamedproject.HospitalRepository.HospitalRepository;
import Java.mohamedproject.Nurse.Nurse;
import Java.mohamedproject.Reception.Reception;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static Java.mohamedproject.SearchValues.Search.binarySearch;


public class Administrators extends Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 8L;
    private int numberOfDoctorsAdded;
    private int numberOfNursesAdded;
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Reception> receptions = new ArrayList<>();
    private ArrayList<Employee> listEmployee = new ArrayList<>();


    public Administrators() {
        super();

    }


    public void checkDepartment(Employee employee, Department dept, HospitalRepository repository) {


        if (dept != null){
            dept.addEmployee(employee);
        }
    }

    public void checkId(Employee employee, ArrayList<Employee> employees) throws Exception {

        ArrayList<Integer> employeeId = new ArrayList<>();
        for (Employee i : employees) {
            employeeId.add(Integer.parseInt(i.getId()));
        }

        if (!employees.isEmpty()) {

            int location = binarySearch(Integer.parseInt(employee.getId()), employeeId, 0, employees.size() - 1);

            if (location != -1) {
                throw new Exception("Entered Id is Repeated !! ");
            }

        }
    }

    public void addMedicalStaff(Employee employee, Department department,HospitalRepository repository) throws Exception {

        try {
            checkId(employee, listEmployee);
            Department realDepartment = repository.addDepartment(department.getDepartmentName());
            realDepartment.addEmployee(employee);

            if (employee instanceof Doctor) {
                repository.addDoctor((Doctor) employee);
                numberOfDoctorsAdded++;
            } else if (employee instanceof Nurse) {
                repository.addNurse((Nurse) employee);
                numberOfNursesAdded++;
            }

            repository.save();

           // setListEmployee(repository);

        }catch (Exception e){
            System.out.println(e.getMessage()+" ");
        }

        // write admin file in Version 2



    }




    public void addReception(Reception reception, HospitalRepository repository) {
        repository.addReception(reception);
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


    public ArrayList<Employee> KnowAllDoctors() {
        return department.getListEmployee();
    }

    public ArrayList<Department> knowAllDepartment() {
        return departments;
    }

    public ArrayList<Reception> getReceptions() {
        return receptions;
    }

    public void setListEmployee(HospitalRepository repository) {
        ArrayList<Doctor> doctors =  repository.getAllDoctors();
        ArrayList<Nurse> nurses =  repository.getAllNurses();
        listEmployee.addAll(doctors);
        listEmployee.addAll(nurses);


    }

    public ArrayList<Employee> getListEmployee() {
        return listEmployee;
    }

    @Override
    public String toString() {
        return "Administrators{" +
                "Medical Staff Added =" + getListEmployee() +
                "Number of Doctors Added = " + getNumberOfDoctors() +
                "Number of Nurses Added = " + getNumberOfNursesAdded() +
                '}';
    }
}
