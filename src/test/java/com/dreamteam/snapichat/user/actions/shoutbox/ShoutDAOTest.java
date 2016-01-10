/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.shoutbox;

import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Stratos
 */
public class ShoutDAOTest {
    
    @Test
    public void testListShoutMessages() throws Exception {
        System.out.println("testListShoutMessages");
        ShoutDAO shoutDAO = new ShoutDAO();
        List<Shout> u = shoutDAO.listShoutMessages();
        
        assertTrue(u != null);
    }
    
}
