package se.lexicon.data.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.H2Util;
import se.lexicon.model.TestTableEntity;

import static org.junit.jupiter.api.Assertions.*;

class TestTableDAOJdbcImplTest {

    private static H2Util h2Util = null;

    TestTableDAOJdbcImpl testObject = new TestTableDAOJdbcImpl();

    @BeforeAll
    static void beforeAll() {
        h2Util = H2Util.getINSTANCE();
    }

    @BeforeEach
    void setUp() {
        h2Util.dropAndCreateTables();
    }

    @Test
    void create() {
        String description = "desc";
        int number = 1337;
        TestTableEntity tte = new TestTableEntity(description,number);

        TestTableEntity returned = testObject.create(tte);

        assertTrue(testObject.findById(returned.getId()).isPresent());
    }
}