/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.api;

import com.dreamteam.snapichat.helpers.DBHelper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * @author Stratos
 */
@WebServlet(name = "UpdateLocation", urlPatterns = {"/api/location"})
public class UpdateLocation extends HttpServlet {

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
        JSONObject jsonResponse = new JSONObject();
        
        try {
            String uid = request.getParameter("uid");
            String longitude = request.getParameter("longitude");
            String latitude = request.getParameter("latitude");
            
            if(uid == null || longitude == null || latitude == null) {
                jsonResponse.put("success", "0");
                jsonResponse.put("cause", "insufficient parameters");
                return;
            }
            
            Connection conn = DBHelper.getConnection();
            PreparedStatement st = conn.prepareStatement("INSERT INTO user_location "
                    + "(uid, longitude, latitude) VALUES (?, ?, ?) "
                    + "ON DUPLICATE KEY UPDATE "
                    + "longitude = VALUES(longitude),"
                    + "latitude = VALUES(latitude)");
            st.setString(1, uid);
            st.setString(2, longitude);
            st.setString(3, latitude);
            
            st.executeUpdate();
            
            jsonResponse.put("success", "1");
            
            conn.close();
        } catch (Exception ex) {
            try {
                jsonResponse.put("success", "0");
                jsonResponse.put("cause", ex.toString());
                Logger.getLogger(UpdateLocation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex1) {
                Logger.getLogger(UpdateLocation.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());
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
