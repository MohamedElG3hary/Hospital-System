package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.Entity.Nurse;

import java.sql.Connection;
import java.sql.SQLException;

public interface NurseRepository {


    public int save(Connection connection, Nurse nurse, int departmentId, int adminId) throws SQLException;

    void update(Connection connection,int nurseId ,Nurse nurse, int departmentId ) throws SQLException;

    void deleteById(Connection connection,int nurseId) throws SQLException;


}
