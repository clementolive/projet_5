package com.openclassrooms.starterjwt.models;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
    void setUp() {
        user = new User("email", "lastname", "firstname", "password", true);
    }

    @Test
    public void testUserDetails() throws Exception {
        assertEquals("User(id=null, email=email, lastName=lastname, firstName=firstname, password=password," +
                " admin=true, createdAt=null, updatedAt=null)",user.toString());
        assertEquals("firstname", user.getFirstName());
        assertEquals("lastname", user.getLastName());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());
        assertTrue(user.isAdmin());
    }

    @AfterEach
    void tearDown() {
        System.out.println("User test Completed");

    }
}