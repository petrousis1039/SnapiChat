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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Lefteris
 */
public class UpdateLocationTest {
    
    private static int uid; //admin uid
    
    @BeforeClass
    public static void setUpClass() throws Exception {
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
        
        uid = jsonResult.getInt("uid");
    }
    
    @Test
    public void updateAdminLocation() throws Exception {
        System.out.println("UpdateAdminLocation");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("uid")).thenReturn(Integer.toString(uid));
        when(request.getParameter("longitude")).thenReturn("22.5");
        when(request.getParameter("latitude")).thenReturn("40.5");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        UpdateLocation updateLocation = new UpdateLocation();
        updateLocation.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
                
        assertEquals(success, 1);
    }
    
    @Test
    public void updateAdminNoLocation() throws Exception {
        System.out.println("UpdateAdminNoLocation");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("uid")).thenReturn(Integer.toString(uid));
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        UpdateLocation updateLocation = new UpdateLocation();
        updateLocation.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
        
        assertEquals(success, 0);
        
        String cause = jsonResult.getString("cause");
        assertTrue(cause != null);
        assertTrue(!cause.equals(""));
    }
    
    @Test
    public void updateUnknownLocation() throws Exception {
        System.out.println("UpdateUnknownLocation");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("uid")).thenReturn(Integer.toString(-1));
        when(request.getParameter("longitude")).thenReturn("22.5");
        when(request.getParameter("latitude")).thenReturn("40.5");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        UpdateLocation updateLocation = new UpdateLocation();
        updateLocation.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
        
        assertEquals(success, 0);
        
        String cause = jsonResult.getString("cause");
        assertTrue(cause != null);
        assertTrue(!cause.equals(""));
    }
    
    @Test
    public void updateLocationWithoutUID() throws Exception {
        System.out.println("UpdateLocationWithoutUID");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("longitude")).thenReturn("22.5");
        when(request.getParameter("latitude")).thenReturn("40.5");
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        UpdateLocation updateLocation = new UpdateLocation();
        updateLocation.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        int success = jsonResult.getInt("success");
        
        assertEquals(success, 0);
        
        String cause = jsonResult.getString("cause");
        assertTrue(cause != null);
        assertTrue(!cause.equals(""));
    }
    
}
