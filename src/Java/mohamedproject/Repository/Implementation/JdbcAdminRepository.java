package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.Entity.Administrators;
import Java.mohamedproject.Repository.Interfaces.AdminRepository;

import java.sql.*;

public class JdbcAdminRepository implements AdminRepository {

    @Override
    public int save(Connection connection, Administrators admin, int departmentId) throws SQLException {
        int personId = insertPerson(connection, admin);

        insertEmployee(connection, admin, personId, departmentId);

        insertAdmin(connection, personId);

        return personId;


    }

    private int insertPerson(Connection connection, Administrators admin) throws SQLException {
        String sql = """
                INSERT INTO Person  (name,city,nationality) VALUES (? ,  ? , ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getAddress());
            preparedStatement.setString(3, admin.getNationality());

            int rowsEffected = preparedStatement.executeUpdate();

            if (rowsEffected == 0) {
                throw new SQLException("Creating admin person failed, no rows affected.");
            }


            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }

            }

            throw new SQLException("Creating admin person failed, no ID obtained.");


        }

    }


    public void insertAdmin(Connection connection, int personId) throws SQLException {

        String sql = """
                INSERT INTO Admin  (person_id , number_of_doctors_added ,number_of_nurses_added ) VALUES
                (? , ? , ?)
                """;


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, 0);
            preparedStatement.setInt(3, 0);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Creating admin failed, no rows affected.");
            }
        }

    }


    private void insertEmployee(Connection connection, Administrators admin, int personId, int departmentId) throws SQLException {

        String sql = """
                INSERT INTO Employee (person_id , department_id , salary , work_hours , experience_years) VALUES (?,?,?,?,?) 
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, personId);
            preparedStatement.setInt(2, departmentId);
            preparedStatement.setDouble(3, admin.getSalary());
            preparedStatement.setInt(4, admin.getWorkHours());
            preparedStatement.setInt(5, admin.getExperienceYears());

            int effectedRows = preparedStatement.executeUpdate();

            if (effectedRows == 0) {
                throw new SQLException("Creating admin employee failed, no rows affected.");
            }

        }


    }

    @Override
    public void incrementDoctorsAdded(Connection connection, int adminId) throws SQLException {


        String sql = """
                UPDATE Admin
                SET number_of_doctors_added = number_of_doctors_added + 1
                WHERE person_id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, adminId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Increment doctors counter failed. Admin not found.");
            }
        }


    }

    @Override
    public void incrementNursesAdded(Connection connection, int adminId) throws SQLException {

        String sql = """
                UPDATE Admin
                SET number_of_nurses_added =   number_of_nurses_added + 1
                WHERE person_id = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);

            int effectedRows = preparedStatement.executeUpdate();
            if (effectedRows == 0) {
                throw new SQLException("Increment nurses counter failed. Admin not found.");
            }

        }


    }


    private void updatePerson(Connection connection, int adminId, Administrators admin) throws SQLException {
        String sql = """
            UPDATE Person
            SET name = ?,
                city = ?,
                nationality = ?
            WHERE person_id = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getAddress());
            preparedStatement.setString(3, admin.getNationality());
            preparedStatement.setInt(4, adminId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Updating admin person failed. Admin person not found.");
            }
        }
    }


    private void updateEmployee(Connection connection, int adminId, Administrators admin, int departmentId) throws SQLException {
        String sql = """
            UPDATE Employee
            SET department_id = ?,
                salary = ?,
                work_hours = ?,
                experience_years = ?
            WHERE person_id = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, departmentId);
            preparedStatement.setDouble(2, admin.getSalary());
            preparedStatement.setInt(3, admin.getWorkHours());
            preparedStatement.setInt(4, admin.getExperienceYears());
            preparedStatement.setInt(5, adminId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Updating admin employee failed. Admin employee not found.");
            }
        }
    }








    public void update(Connection connection, int adminId, Administrators admin, int departmentId) throws SQLException{


        updatePerson(connection, adminId, admin);

        updateEmployee(connection, adminId, admin, departmentId);


    }



    private void clearCreatedByReferences(Connection connection, int adminId) throws SQLException {
        String[] sqlStatements = {
                "UPDATE Doctor SET created_by_admin_id = NULL WHERE created_by_admin_id = ?",
                "UPDATE Nurse SET created_by_admin_id = NULL WHERE created_by_admin_id = ?",
                "UPDATE Reception SET created_by_admin_id = NULL WHERE created_by_admin_id = ?",
                "UPDATE Department SET created_by_admin_id = NULL WHERE created_by_admin_id = ?"
        };

        for (String sql : sqlStatements) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, adminId);
                preparedStatement.executeUpdate();
            }
        }
    }

    private void deleteSystemUser(Connection connection, int adminId) throws SQLException {
        String sql = """
            DELETE FROM SystemUser
            WHERE employee_person_id = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);
            preparedStatement.executeUpdate();
        }
    }



    private void deleteEmployee(Connection connection, int adminId) throws SQLException {
        String sql = """
            DELETE FROM Employee
            WHERE person_id = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Deleting admin employee failed. Employee not found.");
            }
        }
    }


    private void deleteAdmin(Connection connection, int adminId) throws SQLException {
        String sql = """
            DELETE FROM Admin
            WHERE person_id = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Deleting admin failed. Admin not found.");
            }
        }
    }



    private void deletePerson(Connection connection, int adminId) throws SQLException {
        String sql = """
            DELETE FROM Person
            WHERE person_id = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, adminId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Deleting admin person failed. Person not found.");
            }
        }
    }


    public void deleteById(Connection connection, int adminId) throws SQLException{

        clearCreatedByReferences(connection, adminId);

        deleteSystemUser(connection, adminId);

        deleteAdmin(connection, adminId);

        deleteEmployee(connection, adminId);

        deletePerson(connection, adminId);


    }



}
