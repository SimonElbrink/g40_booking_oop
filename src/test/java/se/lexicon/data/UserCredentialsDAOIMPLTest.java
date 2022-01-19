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

    UserCredentialsDAO testObject;

    public List<UserCredentials> ucData = new ArrayList<>(
            Arrays.asList(
                    new UserCredentials("Joe", "James90", "ADMIN"),
                    new UserCredentials("Jane", "123123", "MEMBER"),
                    new UserCredentials("James", "password", "MEMBER"),
                    new UserCredentials("James", "password", "MEMBER")
            )
    );

    @BeforeEach
    void setUp() {
        testObject = UserCredentialsDAOIMPL.getTestINSTANCE(ucData);
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
    @DisplayName("When Given Null As Parameter Throw IllegalArgumentException")
    void test_create_throw_IllegalArgumentException_on_null() {
        assertThrows(
                IllegalArgumentException.class,
                () -> testObject.create(null)
        );
    }
}