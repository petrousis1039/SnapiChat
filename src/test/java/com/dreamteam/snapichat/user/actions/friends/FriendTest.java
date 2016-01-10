/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.friends;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natasa
 */
public class FriendTest {
    
    private Friend friend;
    
    public FriendTest() {
    }
    
    @Before
    public void setUp() {
        friend = new Friend();
        friend.setId(2);
        friend.setUsername("someone");
    }

    @Test
    public void testGetId() {
        System.out.println("testGetId");
        int expResult = 2;
        int result = friend.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        System.out.println("testSetId");
        int id = 3;
        friend.setId(id);
        int resultId = friend.getId();
        assertEquals(id, resultId);
    }

    @Test
    public void testGetUsername() {
        System.out.println("testGetUsername");
        String expResult = "someone";
        String result = friend.getUsername();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUsername() {
        System.out.println("testSetUsername");
        String username = "bob";
        friend.setUsername(username);
        String resultUsername = friend.getUsername();
        assertEquals(username, resultUsername);
    }
    
}
