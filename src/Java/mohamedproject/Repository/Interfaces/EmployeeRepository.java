package Java.mohamedproject.Repository.Interfaces;


import Java.mohamedproject.DTO.EmployeeDTO;
import Java.mohamedproject.DTO.EmployeeDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {

    List<EmployeeDTO> findAllEmployees(Connection connection) throws SQLException;

    List<EmployeeDTO> findAllDoctors(Connection connection) throws SQLException;

    List<EmployeeDTO> findAllNurses(Connection connection) throws SQLException;

    List<EmployeeDTO> findAllReceptionists(Connection connection) throws SQLException;

    List<EmployeeDTO> findAllAdmins(Connection connection) throws SQLException;
}