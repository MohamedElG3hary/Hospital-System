package Java.mohamedproject.FileHospitalRepository;
import Java.mohamedproject.Department.Departments;
import Java.mohamedproject.Doctor.Doctor;
import Java.mohamedproject.HospitalRepository.HospitalRepository;
import Java.mohamedproject.Nurse.Nurse;
import Java.mohamedproject.Reception.Reception;
import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Patient.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHospitalRepository implements HospitalRepository, Serializable {
    @Serial
    private static final long serialVersionUID = 9L;
    private List<Doctor> doctors = new ArrayList<>();
    private List<Nurse> nurses = new ArrayList<>();
    private List<Reception> receptions = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();


    private final String filePath;

    public FileHospitalRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }


    @Override
    public ArrayList<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }



    @Override
    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    @Override
    public ArrayList<Nurse> getAllNurses() {
        return new ArrayList<>(nurses);
    }

    @Override
    public void addReception(Reception reception) {
        receptions.add(reception);
    }

    @Override
    public ArrayList<Reception> getAllReceptions() {
        return new ArrayList<>(receptions);
    }

    @Override
    public Department addDepartment(Departments departmentName) {
        for (Department d : departments) {
            if (d.getDepartmentName().equals(departmentName)) {
                return d;
            }
        }

        Department newDepartment = new Department(departmentName);
        departments.add(newDepartment);

        return newDepartment;
    }

    @Override
    public ArrayList<Department> getAllDepartments() {
        return new ArrayList<>(departments);
    }



    @Override
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public ArrayList<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }


    @Override
    public void save() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    @Override
    public void load() throws Exception {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            FileHospitalRepository loaded = (FileHospitalRepository) ois.readObject();
            this.doctors = loaded.doctors;
            this.nurses = loaded.nurses;
            this.receptions = loaded.receptions;
            this.departments = loaded.departments;
            this.patients = loaded.patients;

        }
    }
}