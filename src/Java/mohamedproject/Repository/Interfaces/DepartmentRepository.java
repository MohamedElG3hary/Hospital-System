package Java.mohamedproject.Repository.Interfaces;


import Java.mohamedproject.Entity.Department;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface DepartmentRepository {


    Optional<Integer> findDepartmentIdByName(Connection connection, String departmentName) throws SQLException;

    int save(Connection connection, Department department) throws SQLException;

    int findOrCreate(Connection connection, Department department) throws SQLException;




}
