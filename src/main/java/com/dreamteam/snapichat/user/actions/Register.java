/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.helpers.PasswordHash;
import com.dreamteam.snapichat.user.actions.register.SnapiRegisterException;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Natasa
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    private Connection conn;

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

        PrintWriter out = response.getWriter();

        String user = request.getParameter("uname");
        String pwd = request.getParameter("pass");
        String rpwd = request.getParameter("repeat-pass");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");

        conn = DBHelper.getConnection();

        //check password
        if (!pwd.equals(rpwd)) {
            String msg = "Password and Repeat Password are not equal";
            out.write(getJSONObject(false, msg).toString());
            conn.close();
            return;
        }

        try {
            pwd = PasswordHash.createHash(pwd);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            out.write(getJSONObject(false, ex.getMessage()).toString());
            conn.close();
            return;
        }

        //check username
        boolean usernameAvailable;
        try {
            usernameAvailable = isUsernameAvailable(user);
        } catch (SnapiRegisterException ex) {
            out.write(getJSONObject(false, ex.getMessage()).toString());
            conn.close();
            return;
        }

        if (!usernameAvailable) {
            String msg = "Username not available";
            out.write(getJSONObject(false, msg).toString());
            conn.close();
            return;
        }

        //check email
        EmailValidator validator = EmailValidator.getInstance();
        boolean validEmail = validator.isValid(email);

        if (!validEmail) {
            String msg = "Invalid email address";
            out.write(getJSONObject(false, msg).toString());
            conn.close();
            return;
        }

        boolean emailAvailable;
        try {
            emailAvailable = isEmailAvailable(user);
        } catch (SnapiRegisterException ex) {
            out.write(getJSONObject(false, ex.getMessage()).toString());
            conn.close();
            return;
        }

        if (!emailAvailable) {
            String msg = "Email address already exists";
            out.write(getJSONObject(false, msg).toString());
            conn.close();
            return;
        }

        String query = "INSERT INTO user(username, passwordhash, user_email, "
                + " userfirstname, userlastname) values "
                + "(?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, user);
        st.setString(2, pwd);
        st.setString(3, email);
        st.setString(4, fname);
        st.setString(5, lname);

        int i = st.executeUpdate();

        if (i > 0) {
            out.write(getJSONObject(true, "OK").toString());
        } else {
            String msg = "An error occured, please check your details and resubmit";
            out.write(getJSONObject(false, msg).toString());
        }

        conn.close();
    }

    private boolean isUsernameAvailable(String user)
            throws SnapiRegisterException {
        try {
            return chechUsernameAvailability(user);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            throw new SnapiRegisterException("Username Error");
        }

    }

    private boolean chechUsernameAvailability(String user) throws SQLException {
        String query = "SELECT * FROM user WHERE username=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, user);

        ResultSet rs = st.executeQuery();

        boolean avail = true;

        if (rs.next()) {
            avail = false;
        }

        return avail;
    }

    private boolean isEmailAvailable(String email)
            throws SnapiRegisterException {
        try {
            return chechEmailAvailability(email);
        } catch (SQLException ex) {
            throw new SnapiRegisterException("Email Error");
        }
    }

    private boolean chechEmailAvailability(String email) throws SQLException {
        String query = "SELECT * FROM user WHERE user_email=?";
        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, email);

        ResultSet rs = st.executeQuery();

        boolean avail = true;

        if (rs.next()) {
            avail = false;
        }

        return avail;
    }

    private JSONObject getJSONObject(boolean success, String msg) {
        try {
            JSONObject jobj = new JSONObject();
            jobj.put("success", success);
            jobj.put("msg", msg);
            return jobj;
        } catch (JSONException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
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
