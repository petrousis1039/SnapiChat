/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.helpers.PasswordHash;
import com.dreamteam.snapichat.user.User;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
 * @author Petros
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    private HttpSession session;

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
        session = request.getSession();

        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pass");

        boolean loggedIn = false;
        try {
            loggedIn = login(uname, pwd);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (loggedIn) {
            response.sendRedirect("edit_profile.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private boolean login(String uname, String pwd)
            throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        Connection conn = DBHelper.getConnection();

        String query = "SELECT * FROM user WHERE username=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, uname);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            String pass = rs.getString("passwordhash");

            if (PasswordHash.validatePassword(pwd, pass)) {
                int id = rs.getInt("id");
                String email = rs.getString("user_email");
                String firstName = rs.getString("userfirstname");
                String lastName = rs.getString("userlastname");
                String country = rs.getString("user_country");
                String city = rs.getString("user_city");
                String phone = rs.getString("user_phone_num");

                User.UserBuilder b = new User.UserBuilder(id, uname)
                        .email(email)
                        .firstName(firstName)
                        .lastName(lastName)
                        .country(country)
                        .city(city)
                        .phone(phone);
                session.setAttribute("user", b.createUser());
                return true;
            }
        }

        conn.close();
        return false; //invalid password
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
