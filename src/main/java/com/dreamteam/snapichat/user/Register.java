/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.helpers.PasswordHash;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Natasa
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    private Statement st;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String user = request.getParameter("uname");
        String pwd = request.getParameter("pass");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");

        st = DBHelper.connect();

        String query = String.format("SELECT * FROM user WHERE username='%s'", user);
        ResultSet rs = st.executeQuery(query);

        boolean avail = isUsernameAvailable(user);
        
        if (!avail) {
            response.sendRedirect("login.jsp"); //username already exists
        } else {
            boolean registered = registerUser(user, pwd, email, fname, lname);
            if(registered) {
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
    
    private boolean isUsernameAvailable(String user) {
        String query = String.format("SELECT * FROM user WHERE username='%s'", user);
        ResultSet rs;
        try {
            rs = st.executeQuery(query);
            
            if (rs.next()) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        return true;
    }
    
    private boolean registerUser(String user, String pwd, String email, String fname, String lname)
            throws SQLException {
        try {
            pwd = PasswordHash.createHash(pwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query;
        query = String.format(
                "INSERT INTO user(username, passwordhash, user_email, "
                        + " userfirstname, userlastname) values "
                        + "('%s', '%s', '%s', '%s', '%s')",
                user, pwd, email, fname, lname);
        int i = st.executeUpdate(query);

        return i > 0;
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
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
