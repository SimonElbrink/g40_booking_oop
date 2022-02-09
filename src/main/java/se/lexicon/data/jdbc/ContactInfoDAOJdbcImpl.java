package se.lexicon.data.jdbc;

import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.model.ContactInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactInfoDAOJdbcImpl implements ContactInfoDAO {



    @Override
    public ContactInfo create(ContactInfo contactInfo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseCredentials.getInstance().getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO contact_info (id, email, phone) VALUES (?,?,?)");
            preparedStatement.setString(1, contactInfo.getId());
            preparedStatement.setString(2, contactInfo.getEmail());
            preparedStatement.setString(3, contactInfo.getPhone());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            connection = DatabaseCredentials.getInstance().getConnection();
            statement = connection.createStatement();
           resultSet = statement.executeQuery("SELECT * FROM contact_info");
            while (resultSet.next()){
                contactInfoList.add(
                        new ContactInfo(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3)
                        ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
            connection = DatabaseCredentials.getInstance().getConnection();
            statement = connection.prepareStatement("SELECT * FROM contact_info WHERE id = ?");
            statement.setString(1, id);

            resultSet = statement.executeQuery();

            while(resultSet.next()){
                contactInfo = new ContactInfo(
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Optional.ofNullable(contactInfo);
    }

    @Override
    public Optional<ContactInfo> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
