package se.lexicon.data;

import org.junit.Before;
import org.junit.Test;
import se.lexicon.model.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AddressDAOIMPLTest {

    AddressDAO testObject;

    public List<Address> addresses(){
        return new ArrayList<>(
                Arrays.asList(
                        new Address("Storgatan 20", "123 45", "Växjö"),
                        new Address("VårdGatan 10", "123 45", "Växjö"),
                        new Address("Sjukhusvägen 5", "123 45", "Växjö"),
                        new Address("Sjukhusvägen 1", "543 21", "SjukaStaden")
                )
        );
    }

    @Before
    public void setUp() throws Exception {
        testObject = AddressDAOIMPL.getTestInstance(addresses());
    }

    @Test
    public void findAll_successfully() {
        List<Address> found = testObject.findAll();

        assertNotNull(found);
        assertEquals(4, found.size());
    }
}