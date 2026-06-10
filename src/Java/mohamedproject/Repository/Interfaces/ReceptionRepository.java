package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.Entity.Reception;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

public interface ReceptionRepository {



    public int save(Connection connection , Reception reception , int departmentId, int adminId ) throws SQLException;
}
