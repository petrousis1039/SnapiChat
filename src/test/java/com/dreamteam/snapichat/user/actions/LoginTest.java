/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Natasa
 */
public class LoginTest {
    
    @Test
    public void loginAsAdmin() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("uname")).thenReturn("admin");
        when(request.getParameter("pass")).thenReturn("admin");
        
        Login login = new Login();
        login.processRequest(request, response);
        
        Mockito.verify(response).sendRedirect("edit_profile.jsp");
    }
    
    @Test
    public void loginAsUnknown() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("uname")).thenReturn("unknown");
        when(request.getParameter("pass")).thenReturn("pass");
        
        Login login = new Login();
        login.processRequest(request, response);
        
        Mockito.verify(response).sendRedirect("login.jsp");
    }
    
}
