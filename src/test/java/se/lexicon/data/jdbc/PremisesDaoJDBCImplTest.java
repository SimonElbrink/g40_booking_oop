package se.lexicon.data.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.H2Util;
import se.lexicon.data.interfaces.AddressDAO;
import se.lexicon.data.interfaces.ContactInfoDAO;
import se.lexicon.data.interfaces.PremisesDAO;
import se.lexicon.model.Address;
import se.lexicon.model.ContactInfo;
import se.lexicon.model.Premises;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PremisesDaoJDBCImplTest {

    private H2Util h2Util = H2Util.getINSTANCE();

    PremisesDAO testObject = new PremisesDaoJDBCImpl();

    @BeforeEach
    void setUp() {
        h2Util.dropAndCreateTables();

    }

    @Test
    void create() {
        Premises premises = new Premises("id1","Vårdcentral Norr",
                new Address("addresstest1", "", "", "")
        ,new ContactInfo("infotest1", "", "")
                );
        testObject.create(premises);

        Optional<Premises> id1 = testObject.findById("id1");
        assertTrue(id1.isPresent());
        System.out.println(id1);

    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }
}