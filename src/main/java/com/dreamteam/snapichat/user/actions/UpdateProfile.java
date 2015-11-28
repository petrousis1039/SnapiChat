/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.user.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Alexis
 */
@WebServlet(name = "UpdateProfile", urlPatterns = {"/updateProfile"})
public class UpdateProfile extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        InputStream inputStream = null;
        
        Part filePart = (Part) request.getPart("user-photo");
        String email = (String) request.getParameter("user-email");
        String country = (String) request.getParameter("user-country");
        String city = (String) request.getParameter("user-city");
        String phone = (String) request.getParameter("user-phone");
//        String oldPwd = (String) request.getParameter("user-pwd");
//        String newPwd = (String) request.getParameter("new-user-pwd");
        
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            inputStream = filePart.getInputStream();
        }

        try {
            Connection conn = DBHelper.getConnection();
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");
            
            String sql = "UPDATE user SET ";
            
            int index = 1;
            if(filePart.getSize() != 0) {
                sql += " user_photo=?,";
            }
            
            sql += " user_email=?, user_country=?, user_city=?, user_phone_num=? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);

            if (filePart.getSize() != 0) {
                statement.setBinaryStream(index++, inputStream, (int) filePart.getSize());
            }
            
            statement.setString(index++, email);
            statement.setString(index++, country);
            statement.setString(index++, city);
            statement.setString(index++, phone);
            statement.setInt(index++, u.getId());

            int row = statement.executeUpdate();
            if (row > 0) {
                out.println("File uploaded!!!");
                u.refreshInfo();
                
                conn.close();

                RequestDispatcher rs = request.getRequestDispatcher("profile.jsp");
                rs.include(request, response);
            } else {
                out.println("Couldn't upload your file!!!");

                conn.close();

                RequestDispatcher rs = request.getRequestDispatcher("profile.jsp");
                rs.include(request, response);
            }

        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
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
