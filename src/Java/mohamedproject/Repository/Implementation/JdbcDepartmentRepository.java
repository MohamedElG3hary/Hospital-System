package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.Entity.Department;
import Java.mohamedproject.Repository.Interfaces.DepartmentRepository;

import java.sql.*;
import java.util.Optional;

public class JdbcDepartmentRepository implements DepartmentRepository {

    @Override
    public Optional<Integer> findDepartmentIdByName(Connection connection, String departmentName) throws SQLException {

        String sql = """
            SELECT department_id
            FROM Department
            WHERE department_name = ?
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, departmentName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(resultSet.getInt("department_id"));
                }
            }
        }

        return Optional.empty();
    }



    @Override
    public int save(Connection connection, Department department) throws SQLException {
        String sql = """
            INSERT INTO Department (department_name)
            VALUES (?)
            """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, department.getDepartmentName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating department failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            throw new SQLException("Creating department failed, no ID obtained.");
        }
    }

    @Override
    public int findOrCreate(Connection connection, Department department) throws SQLException {
        Optional<Integer> existingDepartmentId = findDepartmentIdByName(connection, department.getDepartmentName());

        if (existingDepartmentId.isPresent()) {
            return existingDepartmentId.get();
        }

        return save(connection, department);
    }







}
