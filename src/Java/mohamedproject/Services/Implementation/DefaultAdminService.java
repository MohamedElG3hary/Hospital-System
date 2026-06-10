package Java.mohamedproject.Services.Implementation;

import Java.mohamedproject.DTO.EmployeeDTO;
import Java.mohamedproject.DTO.EmployeeDetails;
import Java.mohamedproject.Entity.Department;
import Java.mohamedproject.Entity.Doctor;
import Java.mohamedproject.Entity.Nurse;
import Java.mohamedproject.Entity.Reception;
import Java.mohamedproject.Repository.Interfaces.*;
import Java.mohamedproject.Services.Interfaces.AdminService;
import Java.mohamedproject.util.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class DefaultAdminService implements AdminService {

    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final ReceptionRepository receptionRepository;
    private final DepartmentRepository departmentRepository;
    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;

    public DefaultAdminService(
            DoctorRepository doctorRepository,
            NurseRepository nurseRepository,
            ReceptionRepository receptionRepository,
            DepartmentRepository departmentRepository,
            AdminRepository adminRepository,
            EmployeeRepository employeeRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
        this.receptionRepository = receptionRepository;
        this.departmentRepository = departmentRepository;
        this.adminRepository = adminRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public int addDoctor(int adminId, Doctor doctor) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            Department department = doctor.getDepartment();

            int departmentId = departmentRepository.findOrCreate(connection, department);

            int doctorId = doctorRepository.save(connection, doctor, departmentId, adminId);

            adminRepository.incrementDoctorsAdded(connection, adminId);

            connection.commit();

            return doctorId;

        } catch (Exception exception) {
            connection.rollback();
            throw exception;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public int addNurse(int adminId, Nurse nurse) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            Department department = nurse.getDepartment();

            int departmentId = departmentRepository.findOrCreate(connection, department);

            int nurseId = nurseRepository.save(connection, nurse, departmentId, adminId);

            adminRepository.incrementNursesAdded(connection, adminId);

            connection.commit();

            return nurseId;

        } catch (Exception exception) {
            connection.rollback();
            throw exception;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public int addReception(int adminId, Reception reception, Department department) throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            int departmentId = departmentRepository.findOrCreate(connection, department);

            int receptionId = receptionRepository.save(connection, reception, departmentId, adminId);

            connection.commit();

            return receptionId;

        } catch (Exception exception) {
            connection.rollback();
            throw exception;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        return employeeRepository.findAllEmployees(connection);
    }

    @Override
    public List<EmployeeDTO> getAllDoctors() throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        return employeeRepository.findAllDoctors(connection);
    }

    @Override
    public List<EmployeeDTO> getAllNurses() throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        return employeeRepository.findAllNurses(connection);
    }

    @Override
    public List<EmployeeDTO> getAllReceptionists() throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        return employeeRepository.findAllReceptionists(connection);
    }

    @Override
    public List<EmployeeDTO> getAllAdmins() throws Exception {
        Connection connection = DatabaseConnection.getInstance().getConnection();

        return employeeRepository.findAllAdmins(connection);
    }
}