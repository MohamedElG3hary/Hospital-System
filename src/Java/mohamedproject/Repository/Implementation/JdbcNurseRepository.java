package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.Entity.Nurse;
import Java.mohamedproject.Repository.Interfaces.NurseRepository;

import java.sql.*;

public class JdbcNurseRepository implements NurseRepository {

    @Override
    public int save(Connection connection, Nurse nurse, int departmentId , int adminId) throws SQLException {

        int personId = insertPerson(connection, nurse);

        insertEmployee(connection, nurse, personId, departmentId);

        insertNurse(connection, personId ,  adminId);

        return personId;
    }

    private int insertPerson(Connection connection, Nurse nurse) throws SQLException {
        String sql = """
                INSERT INTO Person (name, city, nationality)
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, nurse.getName());
            statement.setString(2, nurse.getAddress());
            statement.setString(3, nurse.getNationality());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating nurse person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            throw new SQLException("Creating nurse person failed, no ID obtained.");
        }
    }

    private void insertEmployee(Connection connection, Nurse nurse, int personId, int departmentId) throws SQLException {

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

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, departmentId);
            preparedStatement.setDouble(3, nurse.getSalary());
            preparedStatement.setInt(4, nurse.getWorkHours());
            preparedStatement.setInt(5, nurse.getExperienceYears());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating nurse employee failed, no rows affected.");
            }
        }
    }

    private void insertNurse(Connection connection, int personId , int adminId) throws SQLException {

        String sql = """
                INSERT INTO Nurse (person_id , created_by_admin_id)
                VALUES (? , ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, personId);
            statement.setInt(2, adminId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating nurse failed, no rows affected.");
            }
        }
    }



    private void updatePerson(Connection connection , int nurseId ,Nurse nurse)throws  SQLException{

        String sql = """
                UPDATE Person
                SET name = ?,                
                    city = ? ,
                    nationality = ? 
                WHERE   person_id = ?
                """;


        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,nurse.getName());
            statement.setString(2,nurse.getAddress());
            statement.setString(3,nurse.getNationality());
            statement.setInt(4 , nurseId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Updating nurse person failed. Nurse person not found.");
            }



        }



    }


    private void updateEmployee(Connection connection , int nurseId , Nurse nurse , int departmentId) throws SQLException {

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
            statement.setDouble(2,nurse.getSalary());
            statement.setInt(3,nurse.getWorkHours());
            statement.setInt(4,nurse.getExperienceYears());
            statement.setInt(5,nurseId);

            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Updating nurse employee failed. Nurse employee not found.");
            }


        }

    }


    @Override
    public void  update(Connection connection, int nurseId , Nurse nurse, int departmentId ) throws SQLException{


        updatePerson(connection , nurseId ,nurse);

        updateEmployee(connection , nurseId , nurse, departmentId);

    }



    private void deleteNurse(Connection connection , int nurseId)throws SQLException{

        String sql = """
                
                DELETE FROM Nurse
                WHERE person_id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,nurseId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows ==0 ){
                throw new SQLException("Deleting nurse failed. Nurse not found.");
            }

        }

    }

    private void deleteEmployee(Connection connection , int nurseId) throws SQLException{

        String sql = """
                DELETE FROM Employee
                WHERE person_id = ?
                """;



        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1 , nurseId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0 ){
                throw new SQLException("Deleting nurse employee failed. Employee not found.");
            }


        }

    }


    private void deletePerson(Connection connection , int nurseId) throws SQLException{

        String sql = """
                
                DELETE FROM Person
                WHERE person_id = ?
                
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1 , nurseId);

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Deleting nurse person failed. Person not found.");
            }

        }


    }






    @Override
    public void deleteById(Connection connection,int nurseId) throws SQLException {

        deleteNurse(connection , nurseId);

        deleteEmployee(connection,nurseId);

        deletePerson(connection , nurseId);

    }







}