/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.friends;

import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Stratos
 */
public class FriendDAOTest {
    
    @Test
    public void testGetAdminNewFriendsList() throws Exception {
        System.out.println("testGetAdminNewFriendsList");
        FriendDAO friendDAO = new FriendDAO();
        List<Friend> newPeople = friendDAO.listNewPeople(1);
        
        assertTrue(newPeople != null);
        assertTrue(!newPeople.isEmpty());
    }
    
    @Test
    public void testGetUnknownNewFriendsList() throws Exception {
        System.out.println("testGetUnknownNewFriendsList");
        FriendDAO friendDAO = new FriendDAO();
        List<Friend> newPeople = friendDAO.listNewPeople(4);
        
        assertTrue(newPeople != null);
    }
}
