package Java.mohamedproject.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {


    private static DatabaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=Hospital_System;encrypt=true;trustServerCertificate=true";
    private final String user = "sa";
    private final String password = "123";


    private DatabaseConnection()throws SQLException{
        try {

            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }




    public static DatabaseConnection getInstance() throws SQLException{
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
