package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.Entity.Doctor;
import Java.mohamedproject.Repository.Interfaces.DoctorRepository;

import java.sql.*;

public class JdbcDoctorRepository implements DoctorRepository {

    @Override
    public int save(Connection connection, Doctor doctor, int departmentId , int adminId) throws SQLException {
        int personId = insertPerson(connection, doctor);

        insertEmployee(connection, doctor, personId, departmentId);

        insertDoctor(connection, personId,  adminId);

        return personId;
    }

    private int insertPerson(Connection connection, Doctor doctor) throws SQLException {
        String sql = """
                INSERT INTO Person (name, city, nationality)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getAddress());
            statement.setString(3, doctor.getNationality());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating doctor person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            throw new SQLException("Creating doctor person failed, no ID obtained.");
        }
    }

    private void insertEmployee(Connection connection, Doctor doctor, int personId, int departmentId) throws SQLException {

        String sql = """
                INSERT INTO Employee (
                    person_id,
                    department_id,
                    salary,
                    work_hours,
                    experience_years
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, personId);
            statement.setInt(2, departmentId);
            statement.setDouble(3, doctor.getSalary());
            statement.setInt(4, doctor.getWorkHours());
            statement.setInt(5, doctor.getExperienceYears());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating doctor employee failed, no rows affected.");
            }
        }
    }

    private void insertDoctor(Connection connection, int personId ,int adminId) throws SQLException {
        String sql = """
                INSERT INTO Doctor (person_id , created_by_admin_id)
                VALUES (?,?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, personId);
            statement.setInt(2, adminId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating doctor failed, no rows affected.");
            }
        }
    }


    private void updatePerson(Connection connection, int doctorId ,Doctor doctor) throws SQLException{

        String sql = """
                
                UPDATE Person
                   SET  name = ?,
                   city = ? ,
                   nationality = ? 
                   WHERE   person_id = ?
                
                """;


        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,doctor.getName());
            statement.setString(2,doctor.getAddress());
            statement.setString(3,doctor.getNationality());
            statement.setInt(4 , doctorId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Updating doctor person failed. Doctor person not found.");
            }



        }

    }


    private void updateEmployee(Connection connection, int doctorId ,Doctor doctor, int departmentId ) throws SQLException{

        String sql= """
                
                    UPDATE Employee 
                    SET department_id = ? ,
                        salary = ? ,
                        work_hours = ? ,
                        experience_years = ? 
                    WHERE person_id = ?
                
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1,departmentId);
            statement.setDouble(2,doctor.getSalary());
            statement.setInt(3,doctor.getWorkHours());
            statement.setInt(4,doctor.getExperienceYears());
            statement.setInt(5,doctorId);

            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Updating doctor employee failed. Doctor employee not found.");
            }


        }

    }


    @Override
    public void update(Connection connection,int doctorId ,Doctor doctor, int departmentId ) throws SQLException{

            updatePerson(connection,doctorId,doctor);

            updateEmployee(connection,doctorId , doctor , departmentId);

    }



    private void  deleteDoctor(Connection connection , int doctorId)throws SQLException{

        String sql = """
                DELETE FROM Doctor
                WHERE person_id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,doctorId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows ==0 ){
                throw new SQLException("Deleting doctor failed. Doctor not found.");
            }

        }


    }

    private void deleteEmployee(Connection connection , int doctorId) throws SQLException{

        String sql = """
                DELETE FROM Employee
                WHERE person_id = ?
                """;



        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1 , doctorId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0 ){
                throw new SQLException("Deleting doctor employee failed. Employee not found.");
            }


        }
    }

    private void deletePerson(Connection connection , int doctorId) throws SQLException {

        String sql = """
                
                DELETE FROM Person
                WHERE person_id = ?
                
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1 , doctorId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Deleting doctor person failed. Person not found.");
            }

        }




    }



    @Override
    public void deleteById(Connection connection,int doctorId )throws SQLException{

        deleteDoctor(connection,doctorId);

        deleteEmployee(connection,doctorId);

        deletePerson(connection, doctorId) ;




    }
















}
