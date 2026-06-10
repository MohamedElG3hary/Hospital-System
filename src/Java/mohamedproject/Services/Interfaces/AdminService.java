package Java.mohamedproject.Services.Interfaces;

import Java.mohamedproject.DTO.EmployeeDTO;
import Java.mohamedproject.DTO.EmployeeDetails;
import Java.mohamedproject.Entity.Department;
import Java.mohamedproject.Entity.Doctor;
import Java.mohamedproject.Entity.Nurse;
import Java.mohamedproject.Entity.Reception;

import java.util.List;

public interface AdminService {

    int addDoctor(int adminId, Doctor doctor) throws Exception;

    int addNurse(int adminId, Nurse nurse) throws Exception;

    int addReception(int adminId, Reception reception, Department department) throws Exception;

    List<EmployeeDTO> getAllEmployees() throws Exception;

    List<EmployeeDTO> getAllDoctors() throws Exception;

    List<EmployeeDTO> getAllNurses() throws Exception;

    List<EmployeeDTO> getAllReceptionists() throws Exception;

    List<EmployeeDTO> getAllAdmins() throws Exception;
}