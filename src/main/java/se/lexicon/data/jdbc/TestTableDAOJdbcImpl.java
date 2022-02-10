package se.lexicon.data.jdbc;

import se.lexicon.data.interfaces.TestTableDAO;
import se.lexicon.model.TestTableEntity;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class TestTableDAOJdbcImpl extends AbstractDAO implements TestTableDAO {

    /*
    INSERT INTO table_name (column1, column2, ....)
    VALUES (value1, value2, ....)
     */
    @Override
    public TestTableEntity create(TestTableEntity testTableEntity) {

        //Pre declare return object
        TestTableEntity result = null;

        //pre declaring connection.
        Connection connection = null;
        //pre declaring statement.
        PreparedStatement statement = null;
        //pre declaring the result from the statement, to fetch generated keys, (auto incremented)
        ResultSet keySet = null;

        try {
            //1 - getting connection
            connection = getConnection();
            //2 prepare query
            statement = connection.prepareStatement("INSERT INTO test_table (description, number) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            //3 setting the first and second parameter.
            statement.setString(1, testTableEntity.getDescription());
            statement.setInt(2, testTableEntity.getNumber());

            //4. make sure to execute the query.
            statement.execute();

            //5. Get the generated keys.
            keySet = statement.getGeneratedKeys();

            // 6. create object ot return.
            while (keySet.next()) {
                result = new TestTableEntity(
                        keySet.getInt(1),
                        testTableEntity.getDescription(),
                        testTableEntity.getNumber()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(keySet, statement, connection);

        }


        return result;
    }

    @Override
    public List<TestTableEntity> findAll() {
        return null;
    }

    @Override
    public Optional<TestTableEntity> findById(Integer id) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        TestTableEntity result = null;


        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM test_table WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                result = new TestTableEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getInt("number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(result);
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
