package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.Entity.Doctor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorRepository {



   int save(Connection connection, Doctor doctor, int departmentId , int adminId) throws SQLException;


   void update(Connection connection,int doctorId ,Doctor doctor, int departmentId ) throws SQLException;

   void deleteById(Connection connection,int doctorId) throws SQLException;



}
