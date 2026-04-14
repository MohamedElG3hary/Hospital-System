package Java.mohamedproject.HospitalRepository;
import Java.mohamedproject.Doctor.Doctor;
import Java.mohamedproject.Nurse.Nurse;
import Java.mohamedproject.Reception.Reception;
import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Patient.Patient;

import java.util.ArrayList;

public interface HospitalRepository  {
    void addDoctor(Doctor doctor);
    ArrayList<Doctor> getAllDoctors();

    void addNurse(Nurse nurse);
    ArrayList<Nurse> getAllNurses();

    void addReception(Reception reception);
    ArrayList<Reception> getAllReceptions();

    void addDepartment(Department department);
    ArrayList<Department> getAllDepartments();
    Department findDepartmentByName(String name);

    void addPatient(Patient patient);
    ArrayList<Patient> getAllPatients();



    void save() throws Exception;
    void load() throws Exception;
}