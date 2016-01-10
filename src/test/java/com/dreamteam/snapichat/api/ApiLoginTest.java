/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Despoina
 */
public class ApiLoginTest {
    
    @Test
    public void loginAsAdmin() throws Exception {
        System.out.println("loginAsAdmin");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ApiLogin apiLogin = new ApiLogin();
        apiLogin.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
        int uid = jsonResult.getInt("uid");
        String username = jsonResult.getString("username");
                
        assertEquals(success, 1);
        assertTrue(uid > 0);
        assertEquals(username, "admin");
    }
    
    @Test
    public void loginAsAdminCaseSensitiveUsername() throws Exception {
        System.out.println("loginAsAdminCaseSensitiveUsername");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("Admin");
        when(request.getParameter("password")).thenReturn("admin");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ApiLogin apiLogin = new ApiLogin();
        apiLogin.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
                
        assertEquals(success, 1);
    }
    
    @Test
    public void loginAsAdminCaseSensitivePassword() throws Exception {
        System.out.println("loginAsAdminCaseSensitivePassword");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("Admin");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ApiLogin apiLogin = new ApiLogin();
        apiLogin.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
                
        assertEquals(success, 0);
    }
    
    @Test
    public void loginAsAdminWrongPassword() throws Exception {
        System.out.println("loginAsAdminWrongPassword");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("pass");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ApiLogin apiLogin = new ApiLogin();
        apiLogin.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
        
        assertEquals(success, 0);
    }
    
    @Test
    public void loginAsUnknownUser() throws Exception {
        System.out.println("loginAsUnknownUser");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("username")).thenReturn("unknown");
        when(request.getParameter("password")).thenReturn("pass");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ApiLogin apiLogin = new ApiLogin();
        apiLogin.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
        
        assertEquals(success, 0);
    }
}
