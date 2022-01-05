package se.lexicon.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCredentialsTest {

   private UserCredentials testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new UserCredentials("ABC123", "testtestsson1", "ThisASecret", "Admin");
    }

    @Test
    public void setUsername_successfully() {
        // Arrange
        String expected = "simonelbrink";
        String actual = "";

        // Act
        testObject.setUsername(expected);
        actual = testObject.getUsername();

        // Assert
        assertEquals(expected,actual);

    }

    @Test
    public void setUsername_should_throw_IllegalArgumentException_when_null() {
       assertThrows(IllegalArgumentException.class, () -> testObject.setUsername(null));
    }

    @Test
    public void setPassword_successfully() {
        //Arrange
        String newPassword = "eksod";

        //ACT
        testObject.setPassword(newPassword);

        //Assert
        String actual = testObject.getPassword();
        assertEquals(newPassword, actual);
    }


    @Test
    public void setPassword_should_throw_IllegalArgumentException_when_null() {
        assertThrows(IllegalArgumentException.class, () -> testObject.setPassword(null));
    }
}