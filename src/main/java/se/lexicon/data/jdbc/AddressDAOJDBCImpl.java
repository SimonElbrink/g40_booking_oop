package se.lexicon.data.jdbc;

import se.lexicon.data.interfaces.AddressDAO;
import se.lexicon.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AddressDAOJDBCImpl extends AbstractDAO implements AddressDAO {
    @Override
    public Address create(String streetAddress, String zipCode, String city) {
        return null;
    }

    @Override
    public Address create(Address address) {
        if(address == null) throw new IllegalArgumentException("Entity was null");
        if(address.getId() == null) throw new IllegalArgumentException("Entity id was null");
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("INSERT INTO address (id, street, zip_code, city) VALUES (?,?,?,?)");
            statement.setString(1, address.getId());
            statement.setString(2, address.getStreetAddress());
            statement.setString(3, address.getZipCode());
            statement.setString(4, address.getCity());
            statement.execute();
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeAll(statement, connection);
        }
        return address;
    }

    @Override
    public Optional<Address> findById(String id) {

        Address address = null;

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM address WHERE id = ?");
            statement.setString(1, id);

            resultSet = statement.executeQuery();

            while(resultSet.next()){
                address = new Address(
                        resultSet.getString("id"),
                        resultSet.getString("street"),
                        resultSet.getString("zip_code"),
                        resultSet.getString("city")
                );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(resultSet,statement,connection);
        }

        return Optional.ofNullable(address);

    }

    @Override
    public List<Address> findAll() {
        return null;
    }

    @Override
    public List<Address> findAddressByZipcode(String zipCode) {
        return null;
    }

    @Override
    public List<Address> findAddressByCity(String city) {
        return null;
    }

    @Override
    public Address update(String id, Address updated) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
