package Java.mohamedproject.Repository.Implementation;

import Java.mohamedproject.Entity.User;
import Java.mohamedproject.Repository.Interfaces.AuthRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAuthRepository implements AuthRepository {


    @Override public User login( Connection connection, String username, String password) throws SQLException {


        String sql = """ 
            
            SELECT
                    employee_person_id,
                    username,
                    password_hash,
                    role,
                    is_active
                    FROM dbo.login_user(?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username.trim());
            statement.setString(2, password.trim());

            System.out.println("Username = [" + username.trim() + "]");
            System.out.println("Password = [" + password.trim() + "]");

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("employee_person_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password_hash"),
                            resultSet.getString("role"),
                            resultSet.getBoolean("is_active")
                    );
                }
            }
        }

        return null;
    }

}
