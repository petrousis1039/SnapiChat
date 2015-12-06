/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.user.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Natasa
 */
@WebServlet(name = "MakeFriend", urlPatterns = {"/makeFriend"})
public class MakeFriend extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        Connection conn = DBHelper.getConnection();
        
        String friendName = request.getParameter("friend-name");
        
        //check user exists
        String query = "SELECT * FROM user WHERE username=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, friendName);
        
        ResultSet rs = st.executeQuery();
        if(!rs.next()) {
            response.sendRedirect("friends");
            return; //not user with that name
        }
        
        User u = (User) session.getAttribute("user");
        int currentUserId = u.getId();
        int friendId = rs.getInt("id");
        
        if(currentUserId == friendId) { //can't be friend with himself
            response.sendRedirect("friends");
            return;
        }
        
        //check already friends
        query = "SELECT * FROM user_friendlist WHERE userid1=? AND userid2=?";
        st = conn.prepareStatement(query);
        st.setInt(1, currentUserId);
        st.setInt(2, friendId);

        rs = st.executeQuery();
        if (rs.next()) {
            response.sendRedirect("friends");
            return; //friendship already exist
        }
        
        query = "INSERT INTO user_friendlist (userid1, userid2) VALUES (?, ?)";
        st = conn.prepareStatement(query);
        st.setInt(1, currentUserId);
        st.setInt(2, friendId);
        
        int i = st.executeUpdate();
        
        conn.close();
        response.sendRedirect("friends");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MakeFriend.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MakeFriend.class.getName()).log(Level.SEVERE, null, ex);
        }
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
