package Java.mohamedproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {


    private static DataBaseConnection instance;
    private Connection connection;
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=Hospital_System;encrypt=true;trustServerCertificate=true";

    private final String user = "sa";
    private final String password = "123";


    private DataBaseConnection(){
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static DataBaseConnection getInstance() {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}
