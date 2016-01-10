/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Lefteris
 */
public class ListFriendsTest {
    
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
    public void getAdminFriends() throws Exception {
        System.out.println("getAdminFriends");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("uid")).thenReturn(Integer.toString(uid));
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ListFriends listFriends = new ListFriends();
        listFriends.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        JSONArray friendsJSON = jsonResult.getJSONArray("friends");
                
        assertTrue(friendsJSON != null);
    }
    
    @Test
    public void getUnknownFriends() throws Exception {
        System.out.println("getUnknownFriends");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        when(request.getParameter("uid")).thenReturn(Integer.toString(-1));
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        
        when(response.getWriter()).thenReturn(pw);
        
        ListFriends listFriends = new ListFriends();
        listFriends.processRequest(request, response);
        
        String result = sw.getBuffer().toString().trim();
        JSONObject jsonResult = new JSONObject(result);
        JSONArray friendsJSON = jsonResult.getJSONArray("friends");
        List<JSONObject> friends = new ArrayList<>();
        
        assertTrue(friendsJSON != null);
        
        for(int i=0; i<friendsJSON.length(); i++) {
            JSONObject friend = friendsJSON.getJSONObject(i);
            friends.add(friend);
        }
        
        assertTrue(friends.isEmpty());
    }
    
}
