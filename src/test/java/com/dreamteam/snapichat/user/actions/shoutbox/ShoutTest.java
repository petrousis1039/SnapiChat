/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.shoutbox;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Stratos
 */
public class ShoutTest {
    
    private Shout shout; 
    
    @Before
    public void setUp() {
        shout = new Shout();
        shout.setText("text");
        shout.setUsername("admin");
    }

    @Test
    public void testGetUsername() {
        System.out.println("testGetUsername");
        String expResult = "admin";
        String result = shout.getUsername();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUsername() {
        System.out.println("testSetUsername");
        String username = "user2";
        shout.setUsername(username);
        String resultUsername = shout.getUsername();
        assertEquals(username, resultUsername);
    }

    @Test
    public void testGetText() {
        System.out.println("testGetText");
        String expResult = "text";
        String result = shout.getText();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetText() {
        System.out.println("testSetText");
        String text = "lorem ipsum";
        shout.setText(text);
        String resultText = shout.getText();
        assertEquals(text, resultText);
    }
    
}
