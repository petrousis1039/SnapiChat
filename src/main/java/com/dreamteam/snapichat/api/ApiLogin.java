/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.api;

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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Pasxalis
 */
@WebServlet(name = "ApiLogin", urlPatterns = {"/api/login"})
public class ApiLogin extends HttpServlet {

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
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            User u = login(username, password);
            
            JSONObject obj = new JSONObject();
            if(u != null) {
                obj.put("success", 1);
                obj.put("uid", u.getId());
                obj.put("username", u.getUsername());
            } else {
                obj.put("success", 0);
            }
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(obj.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ListFriends.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ListFriends.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(ApiLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ApiLogin.class.getName()).log(Level.SEVERE, null, ex);
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

    private User login(String uname, String pwd)
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
                
                conn.close();
                return b.createUser();
            }
        }

        conn.close();
        return null;
    }
    
}
