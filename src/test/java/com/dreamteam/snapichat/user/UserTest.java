/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author John
 */
public class UserTest {
    
    private User testUser;
    
    @Before
    public void setUpClass() {
        testUser = new User.UserBuilder(0, "TestUser")
                .city("Thessaloniki")
                .country("Greece")
                .email("test@gmail.com")
                .firstName("Test")
                .lastName("User")
                .phone("2310000000")
                .createUser();
    }

    @Test
    public void testGetId() {
        System.out.println("testGetId");
        int expResult = 0;
        int result = testUser.getId();
        assertEquals("ID as int", expResult, result);
    }
    
    @Test
    public void testGetFalseId() {
        System.out.println("testGetFalseId");
        int expResult = 1;
        int result = testUser.getId();
        assertNotEquals("ID as int", expResult, result);
    }

    @Test
    public void testGetIdAsString() {
        System.out.println("testGetIdAsString");
        String expResult = "0";
        String result = testUser.getIdAsString();
        assertEquals("ID as String", expResult, result);
    }
    
    @Test
    public void testGetFalseIdAsString() {
        System.out.println("testGetFalseIdAsString");
        String expResult = "1";
        String result = testUser.getIdAsString();
        assertNotEquals("ID as String", expResult, result);
    }
    
    @Test
    public void testGetEmail() {
        System.out.println("testGetEmail");
        String expResult = "test@gmail.com";
        String result = testUser.getEmail();
        assertEquals("Email address", expResult, result);
    }
    
    @Test
    public void testGetFalseEmail() {
        System.out.println("testGetFalseEmail");
        String expResult = "test@test.com";
        String result = testUser.getEmail();
        assertNotEquals("Email address", expResult, result);
    }

    @Test
    public void testGetUsername() {
        System.out.println("testGetUsername");
        String expResult = "TestUser";
        String result = testUser.getUsername();
        assertEquals("Username", expResult, result);
    }
    
    @Test
    public void testGetFalseUsername() {
        System.out.println("testGetFalseUsername");
        String expResult = "someone";
        String result = testUser.getUsername();
        assertNotEquals("Username", expResult, result);
    }

    @Test
    public void testGetFirstName() {
        System.out.println("testGetFirstName");
        String expResult = "Test";
        String result = testUser.getFirstName();
        assertEquals("First Name", expResult, result);
    }
    
    @Test
    public void testGetFalseFirstName() {
        System.out.println("testGetFalseFirstName");
        String expResult = "Tom";
        String result = testUser.getFirstName();
        assertNotEquals("First Name", expResult, result);
    }
    
    @Test
    public void testGetLastName() {
        System.out.println("testGetLastName");
        String expResult = "User";
        String result = testUser.getLastName();
        assertEquals("Last Name", expResult, result);
    }
    
    @Test
    public void testGetFalseLastName() {
        System.out.println("testGetFalseLastName");
        String expResult = "Papadopoulos";
        String result = testUser.getLastName();
        assertNotEquals("Last Name", expResult, result);
    }

    @Test
    public void testGetCountry() {
        System.out.println("testGetCountry");
        String expResult = "Greece";
        String result = testUser.getCountry();
        assertEquals("Country", expResult, result);
    }
    
    @Test
    public void testGetFalseCountry() {
        System.out.println("testGetFalseCountry");
        String expResult = "USA";
        String result = testUser.getCountry();
        assertNotEquals("Country", expResult, result);
    }

    @Test
    public void testGetCity() {
        System.out.println("testGetCity");
        String expResult = "Thessaloniki";
        String result = testUser.getCity();
        assertEquals("City", expResult, result);
    }
    
    @Test
    public void testGetFalseCity() {
        System.out.println("testGetFalseCity");
        String expResult = "Athens";
        String result = testUser.getCity();
        assertNotEquals("City", expResult, result);
    }

    @Test
    public void testGetPhone() {
        System.out.println("testGetPhone");
        String expResult = "2310000000";
        String result = testUser.getPhone();
        assertEquals("Phone", expResult, result);
    }
    
    @Test
    public void testGetFalsePhone() {
        System.out.println("testGetFalsePhone");
        String expResult = "2310000001";
        String result = testUser.getPhone();
        assertNotEquals("Phone", expResult, result);
    }
    
}
