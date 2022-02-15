package se.lexicon.data.jdbc;

import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.model.Premises;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PremisesDaoJDBCImpl extends AbstractDAO implements PremisesDAO {



    @Override
    public Premises create(Premises premises) {

        if (premises == null) throw new IllegalArgumentException("Entity Was Null");
        if (premises.getId() == null) throw new IllegalArgumentException("Entity id was null");
        if (premises.getAddress() != null && premises.getAddress().getId() == null) throw new IllegalArgumentException("premises.address.id was null");
        if (premises.getContactInfo() != null && premises.getContactInfo().getId() == null) throw new IllegalArgumentException("premises.contactInfo.id was null");


        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO premises (id, name, fk_contact_info, fk_address) VALUES(?, ?, ?, ?)");
            statement.setString(1, premises.getId());
            statement.setString(2, premises.getName());
            statement.setString(3, premises.getContactInfo() == null ? null : premises.getContactInfo().getId());
            statement.setString(4, premises.getAddress() == null ? null : premises.getAddress().getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(statement,connection);
        }


        return premises;
    }

    @Override
    public List<Premises> findAll() {

        List<Premises> foundMatches = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, name FROM premises");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                foundMatches.add(
                        new Premises(
                                resultSet.getString("id"),
                                resultSet.getString("name")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(resultSet, preparedStatement, connection);
        }

        return foundMatches;
    }

    @Override
    public Optional<Premises> findById(String id) {

        Premises premises = null;

        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT id, name FROM premises WHERE id = ?");
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                premises = new Premises(
                        resultSet.getString("id"),
                        resultSet.getString("name")
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(resultSet, preparedStatement, connection);
        }


        return Optional.ofNullable(premises);
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public List<Premises> findPremisesByName(String premisesName) {
        return null;
    }

    @Override
    public List<Premises> findPremisesByAddress(String addressID) {
        return null;
    }

    @Override
    public List<Premises> findPremisesByZipcode(String zipcode) {
        return null;
    }
}
