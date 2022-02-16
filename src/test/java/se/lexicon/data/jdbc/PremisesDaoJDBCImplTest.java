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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PremisesDaoJDBCImplTest {

    private H2Util h2Util = H2Util.getINSTANCE();
    PremisesDAO testObject = new PremisesDaoJDBCImpl();
    Premises premises = null;

    @BeforeEach
    void setUp() {
        h2Util.dropAndCreateTables();

        premises = new Premises("Vårdcentral Norr",
                new Address("addresstest1", "", "", "")
                ,new ContactInfo("infotest1", "", "")
        );
        testObject.create(premises);



    }

    @Test
    void create() {
        Premises premises = new Premises("id1","Vårdcentral Norr",
                new Address("addresstest1", "", "", ""),
                new ContactInfo("infotest1", "", "")
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

    @Test
    void findByName() {

        String nameToFind = "vårdcentral norr";
        System.out.println("nameToFind = " + nameToFind);

        assertEquals(1, testObject.findPremisesByName(nameToFind).size());
    }

    @Test
    void findByZipcode() {

        List<Premises> premisesByZipcode = testObject.findPremisesByZipcode("123 45");
        premisesByZipcode.forEach(System.out::println);
        assertEquals(1, premisesByZipcode.size());


    }

    @Test
    void update() {

       Premises toBeUpdated =  testObject.findById(premises.getId()).orElseThrow(RuntimeException::new);

       toBeUpdated.setName("Sjukhuset Centrum");
       toBeUpdated.setAddress(new Address("Sjukhuset 1", "","", ""));
       toBeUpdated.setContactInfo(new ContactInfo("SjukhusInfo", "", ""));

       testObject.update(toBeUpdated);

        Optional<Premises> found = testObject.findById(premises.getId());
        assertTrue(found.isPresent());
        System.out.println(found.get());

    }

    @Test
    void delete() {
        assertTrue(testObject.findById(premises.getId()).isPresent());
        boolean wasDeleted = testObject.delete(premises.getId());
        assertTrue(wasDeleted);
        assertFalse(testObject.findById(premises.getId()).isPresent());
    }
}