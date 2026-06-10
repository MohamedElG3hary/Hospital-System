package Java.mohamedproject.Repository.Interfaces;

import Java.mohamedproject.Entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface AuthRepository {


    User login(Connection connection, String username, String password ) throws SQLException;

}
