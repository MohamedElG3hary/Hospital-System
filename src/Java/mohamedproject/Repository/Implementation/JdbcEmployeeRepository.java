package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.DTO.EmployeeDTO;
import Java.mohamedproject.DTO.EmployeeDetails;
import Java.mohamedproject.Repository.Interfaces.EmployeeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcEmployeeRepository implements EmployeeRepository {

    @Override
    public List<EmployeeDTO> findAllEmployees(Connection connection) throws SQLException {
        String sql = """
                SELECT
                    e.person_id,
                    p.name,
                    p.city,
                    p.nationality,
                    d.department_name,
                    e.salary,
                    e.work_hours,
                    e.experience_years,
                    CASE
                        WHEN doc.person_id IS NOT NULL THEN 'DOCTOR'
                        WHEN n.person_id IS NOT NULL THEN 'NURSE'
                        WHEN a.person_id IS NOT NULL THEN 'ADMIN'
                        WHEN r.person_id IS NOT NULL THEN 'RECEPTION'
                        ELSE 'EMPLOYEE'
                    END AS employee_type
                FROM Employee e
                JOIN Person p
                    ON e.person_id = p.person_id
                LEFT JOIN Department d
                    ON e.department_id = d.department_id
                LEFT JOIN Doctor doc
                    ON e.person_id = doc.person_id
                LEFT JOIN Nurse n
                    ON e.person_id = n.person_id
                LEFT JOIN Admin a
                    ON e.person_id = a.person_id
                LEFT JOIN Reception r
                    ON e.person_id = r.person_id
                ORDER BY e.person_id
                """;

        return mapEmployeeDetails(connection, sql);
    }

    @Override
    public List<EmployeeDTO> findAllDoctors(Connection connection) throws SQLException {
        String sql = """
                SELECT
                    e.person_id,
                    p.name,
                    p.city,
                    p.nationality,
                    d.department_name,
                    e.salary,
                    e.work_hours,
                    e.experience_years,
                    'DOCTOR' AS employee_type
                FROM Doctor doc
                JOIN Employee e
                    ON doc.person_id = e.person_id
                JOIN Person p
                    ON e.person_id = p.person_id
                LEFT JOIN Department d
                    ON e.department_id = d.department_id
                ORDER BY e.person_id
                """;

        return mapEmployeeDetails(connection, sql);
    }

    @Override
    public List<EmployeeDTO> findAllNurses(Connection connection) throws SQLException {
        String sql = """
                SELECT
                    e.person_id,
                    p.name,
                    p.city,
                    p.nationality,
                    d.department_name,
                    e.salary,
                    e.work_hours,
                    e.experience_years,
                    'NURSE' AS employee_type
                FROM Nurse n
                JOIN Employee e
                    ON n.person_id = e.person_id
                JOIN Person p
                    ON e.person_id = p.person_id
                LEFT JOIN Department d
                    ON e.department_id = d.department_id
                ORDER BY e.person_id
                """;

        return mapEmployeeDetails(connection, sql);
    }

    @Override
    public List<EmployeeDTO> findAllReceptionists(Connection connection) throws SQLException {
        String sql = """
                SELECT
                    e.person_id,
                    p.name,
                    p.city,
                    p.nationality,
                    d.department_name,
                    e.salary,
                    e.work_hours,
                    e.experience_years,
                    'RECEPTION' AS employee_type
                FROM Reception r
                JOIN Employee e
                    ON r.person_id = e.person_id
                JOIN Person p
                    ON e.person_id = p.person_id
                LEFT JOIN Department d
                    ON e.department_id = d.department_id
                ORDER BY e.person_id
                """;

        return mapEmployeeDetails(connection, sql);
    }

    @Override
    public List<EmployeeDTO> findAllAdmins(Connection connection) throws SQLException {
        String sql = """
                SELECT
                    e.person_id,
                    p.name,
                    p.city,
                    p.nationality,
                    d.department_name,
                    e.salary,
                    e.work_hours,
                    e.experience_years,
                    'ADMIN' AS employee_type
                FROM Admin a
                JOIN Employee e
                    ON a.person_id = e.person_id
                JOIN Person p
                    ON e.person_id = p.person_id
                LEFT JOIN Department d
                    ON e.department_id = d.department_id
                ORDER BY e.person_id
                """;

        return mapEmployeeDetails(connection, sql);
    }

    private List<EmployeeDTO> mapEmployeeDetails(Connection connection, String sql) throws SQLException {

        ArrayList<EmployeeDTO> employees = new ArrayList<>();


        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                EmployeeDTO employeeDTO = new EmployeeDTO();
                employeeDTO.personId = resultSet.getInt("person_id");
                employeeDTO.name = resultSet.getString("name");
                employeeDTO.city = resultSet.getString("city");
                employeeDTO.nationality = resultSet.getString("nationality");
                employeeDTO.departmentName = resultSet.getString("department_name");
                employeeDTO.salary = resultSet.getDouble("salary");
                employeeDTO.workHours = resultSet.getInt("work_hours");
                employeeDTO.experienceYears = resultSet.getInt("experience_years");
                employeeDTO.employeeType = resultSet.getString("employee_type");


                employees.add(employeeDTO);
            }
        }

        return employees;
    }


}
