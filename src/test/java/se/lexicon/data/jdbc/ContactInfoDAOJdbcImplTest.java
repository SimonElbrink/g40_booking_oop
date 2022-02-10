package se.lexicon.data.jdbc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.H2Util;
import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.model.ContactInfo;

import static org.junit.jupiter.api.Assertions.*;

class ContactInfoDAOJdbcImplTest {

    private ContactInfoDAO testObject = new ContactInfoDAOJdbcImpl();
    private static H2Util h2Util;

    @BeforeAll
    static void beforeAll() {
        h2Util = H2Util.getINSTANCE();
    }

    @BeforeEach
    void setUp() {
        h2Util.dropAndCreateTables();
        testObject.create(new ContactInfo("9000","Test@email.com", "1234567890"));
    }

    @Test
    void findByEmail() {
        assertTrue(testObject.findByEmail("Test@email.com").isPresent());
    }

    @Test
    void FindAll() {
        assertEquals(1, testObject.findAll().size());
    }
}