package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.Entity.Administrators;

import java.sql.Connection;
import java.sql.SQLException;

public interface AdminRepository {



     int save(Connection connection, Administrators admin, int departmentId) throws SQLException;

     void incrementDoctorsAdded(Connection connection, int adminId) throws SQLException;

     void incrementNursesAdded(Connection connection, int adminId) throws SQLException;

     void update(Connection connection, int adminId, Administrators admin, int departmentId) throws SQLException;

     void deleteById(Connection connection, int adminId) throws SQLException;














}





