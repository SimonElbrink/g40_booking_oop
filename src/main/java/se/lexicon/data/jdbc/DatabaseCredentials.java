package se.lexicon.data.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseCredentials {

    private String URL;
    private String USER;
    private String PASSWORD;

    private static final DatabaseCredentials INSTANCE = new DatabaseCredentials(Paths.get("database/mysql.properties"));


    public static DatabaseCredentials getInstance(){
        return INSTANCE;
    }

    private DatabaseCredentials(Path path){
        Properties properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(path));
            USER = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
            URL = properties.getProperty("url");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
