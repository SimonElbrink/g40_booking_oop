package se.lexicon.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.lexicon.model.UserCredentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserCredentialsDAOIMPLTest {

    public static final String USERNAME = "Joe";
    public static final String PASSWORD = "James90";
    public static final String ROLE = "ADMIN";
    UserCredentialsDAO testObject;
    UserCredentials userCredentials;

    public List<UserCredentials> ucData = new ArrayList<>(
            Arrays.asList(
                    new UserCredentials(USERNAME, PASSWORD, ROLE),
                    new UserCredentials("Jane", "123123", "MEMBER"),
                    new UserCredentials("James", "password", "MEMBER")
            )
    );

    @BeforeEach
    void setUp() {
        testObject = UserCredentialsDAOIMPL.getTestINSTANCE(ucData);
        userCredentials = ucData.get(0);
    }

    @Test
    @DisplayName(value = "Save a UserCredentials to DAO")
    void test_create_successfully() {
        UserCredentials temp = new UserCredentials(
                "Test",
                "Not A Real Password",
                "GUEST");

        UserCredentials saved = testObject.create(temp);

        assertNotNull(saved);
        assertTrue(ucData.contains(temp));

    }


    @Test
    @DisplayName("When Create Given Null As Parameter Throw IllegalArgumentException")
    void test_create_throw_IllegalArgumentException_on_null() {
        assertThrows(
                IllegalArgumentException.class,
                () -> testObject.create(null)
        );
    }

    @Test
    @DisplayName("FindAll Should Return all Objects in DAO")
    void findAll() {
        List<UserCredentials> all = testObject.findAll();
        assertNotNull(all);
        assertEquals(ucData,all);
    }

    @Test
    @DisplayName("findByUserName Should return Object With Given Valid Parameter")
    void findByUserName_Successfully() {
        UserCredentials found = testObject.findByUserName(USERNAME);
        assertNotNull(found);
    }

    @Test
    @DisplayName("findByName Should return null when not found")
    void findByName_param_null() {
        UserCredentials found = testObject.findByUserName(null);
        assertNull(found);
    }


    @Test
    @DisplayName("findByRole Should return Object when given Valid parameter")
    void findByRole_Successfully() {
        int expectedSize = 2;
        List<UserCredentials> found = testObject.findByRole("MEMBER");
        assertNotNull(found);
        assertEquals(expectedSize, found.size());
    }

    @Test
    void delete_successfully() {

        boolean wasRemoved = testObject.delete(userCredentials.getId());

        assertTrue(wasRemoved);
        assertFalse(ucData.contains(userCredentials));

    }
}