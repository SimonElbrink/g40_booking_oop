package se.lexicon;

import se.lexicon.data.jdbc.DatabaseCredentials;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class H2Util {

    private static H2Util INSTANCE;
    private String sql;


    static {
        BufferedReader reader= null;
        try{
            DatabaseCredentials.initialize("database/h2.properties");
            reader = Files.newBufferedReader(Paths.get("database/vaccine_booking Table Creating.sql"));
           String sql = reader.lines().collect(Collectors.joining());
           INSTANCE = new H2Util(sql);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static H2Util getINSTANCE(){
        return INSTANCE;
    }

    public H2Util(String sql) {
        this.sql = sql;
    }

    public void dropAndCreateTables(){
        try (
                Connection connection = DriverManager.getConnection(DatabaseCredentials.getInstance().getURL(), DatabaseCredentials.getInstance().getUSER(), DatabaseCredentials.getInstance().getPASSWORD());
                Statement statement = connection.createStatement()
        ){
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
