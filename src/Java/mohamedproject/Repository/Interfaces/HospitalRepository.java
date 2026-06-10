package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.Entity.*;
import Java.mohamedproject.Enums.*;
import java.util.ArrayList;

public interface HospitalRepository  {

    void addDoctor(Doctor doctor);
    ArrayList<Doctor> getAllDoctors();

    void addNurse(Nurse nurse);
    ArrayList<Nurse> getAllNurses();

    void addReception(Reception reception);
    ArrayList<Reception> getAllReceptions();

    Department addDepartment(Departments departmentName);
    ArrayList<Department> getAllDepartments();


    void addPatient(Patient patient);
    ArrayList<Patient> getAllPatients();



    void save() throws Exception;
    void load() throws Exception;
}