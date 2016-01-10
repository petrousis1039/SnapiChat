/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.profile;

import com.dreamteam.snapichat.user.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Stratos
 */
public class UserDAOTest {
    
    @Test
    public void getUserWithID1() throws Exception {
        System.out.println("getUserWithID1");
        UserDAO userDAO = new UserDAO();
        User u = userDAO.getUser(1);
        
        assertEquals("admin", u.getUsername());
    }
    
    @Test
    public void getUserWithID2() throws Exception {
        System.out.println("getUserWithID2");
        UserDAO userDAO = new UserDAO();
        User u = userDAO.getUser(2);
        
        assertEquals("test", u.getUsername());
    }
    
    @Test
    public void getUserWithID0() throws Exception {
        System.out.println("getUserWithID0");
        UserDAO userDAO = new UserDAO();
        User u = userDAO.getUser(0);
        
        assertEquals(null, u);
    }
    
}
