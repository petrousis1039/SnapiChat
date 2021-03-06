/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.api;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.user.actions.friends.Friend;
import com.dreamteam.snapichat.user.actions.friends.FriendDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Stratos
 */
@WebServlet(name = "ListFriends", urlPatterns = {"/listFriends"})
public class ListFriends extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));
            FriendDAO friendDAO = new FriendDAO();
            FriendDAO.Action action = FriendDAO.Action.LIST_FRIENDS;
            List<Friend> friends = friendDAO.list(uid, action);
            JSONObject obj = new JSONObject();
            JSONArray friendsJSON = new JSONArray();
            
            Connection conn = DBHelper.getConnection();
            
            for(Friend f : friends) {
                String longitude = "0";
                String latitude = "0";
                String query = "SELECT * FROM user_location WHERE uid=?";
                PreparedStatement st = conn.prepareStatement(query);
                st.setInt(1, f.getId());
                ResultSet rs = st.executeQuery();
                if(rs.next()) {
                    longitude = rs.getString("longitude");
                    latitude = rs.getString("latitude");
                }
                JSONObject fJSON = new JSONObject();
                fJSON.put("id", f.getId());
                fJSON.put("username", f.getUsername());
                fJSON.put("longitude", longitude);
                fJSON.put("latitue", latitude);
                friendsJSON.put(fJSON);
            }
            obj.put("friends", friendsJSON);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(obj.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ListFriends.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ListFriends.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
