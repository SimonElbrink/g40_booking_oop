package se.lexicon.data.jdbc;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.model.ContactInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactInfoDAOJdbcImpl extends AbstractDAO implements ContactInfoDAO {



    @Override
    public ContactInfo create(ContactInfo contactInfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO contact_info (id, email, phone) VALUES (?,?,?)");
            preparedStatement.setString(1, contactInfo.getId());
            preparedStatement.setString(2, contactInfo.getEmail());
            preparedStatement.setString(3, contactInfo.getPhone());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(preparedStatement, connection);
        }

        return contactInfo;
    }

    @Override
    public List<ContactInfo> findAll() {
        List<ContactInfo> contactInfoList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT * FROM contact_info");
            while (resultSet.next()){
                contactInfoList.add(
                        mapToContactInfo(resultSet)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }

        return contactInfoList;
    }

    @Override
    public Optional<ContactInfo> findById(String id) {
        ContactInfo contactInfo = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact_info WHERE id = ?");
            statement.setString(1, id);

            resultSet = statement.executeQuery();

            while(resultSet.next()){
                contactInfo = mapToContactInfo(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(resultSet, statement, connection);
        }

        return Optional.ofNullable(contactInfo);
    }

    @Override
    public Optional<ContactInfo> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean delete(String id) {

        int rowsAffected = 0;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("DELETE FROM contact_info WHERE id = ?");
            statement.setString(1,id);

            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }

        return rowsAffected > 0;
    }
}
