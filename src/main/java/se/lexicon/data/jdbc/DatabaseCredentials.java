package se.lexicon.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCredentials {

    private final String URL = "jdbc:mysql://localhost:3306/vaccine_booking?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Europe/Berlin";
    private final String USER = "root";
    private final String PASSWORD = "1234";

    private static final DatabaseCredentials INSTANCE = new DatabaseCredentials();

    public DatabaseCredentials() {
    }

    public static DatabaseCredentials getInstance(){
        return INSTANCE;
    }

    public Connection getConnection(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
