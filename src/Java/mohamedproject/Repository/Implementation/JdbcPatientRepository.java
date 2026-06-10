package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.DTO.PatientDTO;
import Java.mohamedproject.Entity.Patient;
import Java.mohamedproject.Repository.Interfaces.PatientRepository;

import java.sql.*;
import java.util.ArrayList;

public class JdbcPatientRepository implements PatientRepository {


    @Override
    public int save(Connection connection, Patient patient, int receptionId) throws SQLException {

        int personId = insertPerson(connection, patient);

        insertPatient(connection, patient, personId, receptionId);

        return personId;
    }


    private int insertPerson(Connection connection, Patient patient) throws SQLException {

        String sql = """
                INSERT INTO Person (name , city , nationality) VALUES (? , ? , ?) 
                """;


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getAddress());
            preparedStatement.setString(3, patient.getNationality());

            int rowEffected = preparedStatement.executeUpdate();

            if (rowEffected == 0) {
                throw new SQLException("creating patient person failed, no rows affected. ");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }

            }
            throw new SQLException("Creating patient person failed, no ID obtained.");


        }


    }

    private void insertPatient(Connection connection, Patient patient, int personId, int receptionId) throws SQLException {


        String sql = """
                INSERT INTO Patient ( person_id,disease,blood_type,registered_by_reception_id) VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, personId);
            preparedStatement.setString(2, patient.getDisease());
            preparedStatement.setString(3, patient.getBloodType());
            preparedStatement.setInt(4, receptionId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating patient failed, no rows affected.");
            }
        }


    }


    public ArrayList<PatientDTO> findAllPatients(Connection connection) throws SQLException {


        String sql = """
                
                select 
                    person_id,
                    name,
                    city,
                    nationality,
                    blood_type,
                    disease
                    from patients_details
                
                """;

        ArrayList<PatientDTO> patients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {


            while (resultSet.next()) {

                PatientDTO patient = new PatientDTO();

                patient.patientId = resultSet.getInt("person_id");
                patient.name = resultSet.getString("name");
                patient.city = resultSet.getString("city");
                patient.nationality = resultSet.getString("nationality");
                patient.bloodType = resultSet.getString("blood_type");
                patient.disease = resultSet.getString("disease");


                patients.add(patient);

            }



        }

        return patients;

    }


}
