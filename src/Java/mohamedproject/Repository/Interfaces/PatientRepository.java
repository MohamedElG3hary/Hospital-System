package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.DTO.PatientDTO;
import Java.mohamedproject.Entity.Patient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientRepository {



    public int save(Connection connection, Patient patient, int receptionId) throws SQLException;


    ArrayList<PatientDTO> findAllPatients(Connection connection) throws SQLException;





}
