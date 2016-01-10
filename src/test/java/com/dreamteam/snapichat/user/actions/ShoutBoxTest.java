/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.user.User;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Petros
 */
public class ShoutBoxTest {
    
    @Test
    public void accessShoutBoxPageAsFakeUser() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        
        // Create a fake user
        User u = new User.UserBuilder(0, "TestUser")
                .city("Thessaloniki")
                .country("Greece")
                .email("test@gmail.com")
                .firstName("Test")
                .lastName("User")
                .phone("2310000000")
                .createUser();
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(u);
        when(request.getRequestDispatcher("/shoutbox.jsp")).thenReturn(dispatcher);
        
        try {
            ShoutBox shoutbox = new ShoutBox();
            shoutbox.processRequest(request, response);
        } catch(ServletException | IOException e) {
            Assert.fail("No redirect to shoutbox.jsp found. Something went wrong!");
        }
    }
    
}
