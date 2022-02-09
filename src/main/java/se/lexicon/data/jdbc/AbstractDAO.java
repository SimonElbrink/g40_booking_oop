package se.lexicon.data.jdbc;

import se.lexicon.model.ContactInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractDAO {

    public void closeAll(AutoCloseable...closeables) {
        if (closeables != null){
            for (AutoCloseable closable : closeables) {
                try {
                    closable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() throws SQLException{
            return DriverManager.getConnection(
                    DatabaseCredentials.getInstance().getURL(),
                    DatabaseCredentials.getInstance().getUSER(),
                    DatabaseCredentials.getInstance().getPASSWORD());
    }

    public ContactInfo mapToContactInfo(ResultSet resultSet) throws SQLException{
        return new ContactInfo(
                resultSet.getString("id"),
                resultSet.getString("email"),
                resultSet.getString("phone")
        );
    }
}
