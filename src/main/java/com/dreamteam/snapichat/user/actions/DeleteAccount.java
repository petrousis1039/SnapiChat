/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.user.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * @author Despoina
 */
@WebServlet(name = "DeleteAccount", urlPatterns = {"/deleteAccount"})
public class DeleteAccount extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        Connection conn = DBHelper.getConnection();

        User u = (User) session.getAttribute("user");

        String query1 = "DELETE FROM shoutbox WHERE user_id=?";
        
        String query2 = "DELETE FROM user_friendlist WHERE userid1=? OR userid2=?";
        
        String query3 = "DELETE FROM user WHERE id=?";
        
        try {
            PreparedStatement st = conn.prepareStatement(query1);
            st.setInt(1, u.getId());
            int result = st.executeUpdate();
            
            if(result < 0) {
                out.println("Couldn't remove messages from shoutbox");
                return;
            }
            
            st = conn.prepareStatement(query2);
            st.setInt(1, u.getId());
            st.setInt(2, u.getId());
            result = st.executeUpdate();
            
            if(result < 0) {
                out.println("Couldn't delete friends");
                return;
            }
            
            st = conn.prepareStatement(query3);
            st.setInt(1, u.getId());
            result = st.executeUpdate();
            
            if (result > 0) {
                session.setAttribute("user", null);
                session.invalidate();
                out.println("Success, I know nothing, who are you? ;)");
            } else {
                out.println("Oups something went wrong! Please contact us!");
            }
        } catch (Exception e) {
            out.println("Oups something went wrong! Please contact us!");
        } finally {
            conn.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteAccount.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeleteAccount.class.getName()).log(Level.SEVERE, null, ex);
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
