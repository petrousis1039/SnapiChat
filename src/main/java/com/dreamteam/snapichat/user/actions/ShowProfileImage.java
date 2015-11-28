/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.user.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.Blob;
import java.sql.Connection;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lefteris
 */
@WebServlet(name = "ShowProfileImage", urlPatterns = {"/profileImage"})
public class ShowProfileImage extends HttpServlet {

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
        Blob file;
        byte[] fileData;

        try {
            Connection conn = DBHelper.getConnection();

            String uid = request.getParameter("uid");
            
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");
            int userId = u.getId();
            
            if(uid != null) {
                userId = Integer.parseInt(uid);
            }
            
            String sqlString = "SELECT user_photo FROM user WHERE id = '" + userId + "'";
            Statement myStatement = conn.createStatement();

            ResultSet rs = myStatement.executeQuery(sqlString);

            if (rs.next()) {
                file = rs.getBlob("user_photo");
                fileData = file.getBytes(1, (int) file.length());
                if(file.length() == 0) {
                    fileData = getDefaultImage();
                }
            } else {
                out.println("file not found!");
                return;
            }

            response.setContentType("image");
            response.setHeader("Content-Disposition", "inline");
            response.setContentLength(fileData.length);

            OutputStream output = response.getOutputStream();
            output.write(fileData);

            output.flush();

        } catch (SQLException ex) {
            Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private byte[] getDefaultImage() {
        try {
            FileInputStream fileInputStream;
            String relativePath = "/images/default_profile.jpg";
            File file = new File(getServletContext().getRealPath(relativePath));
            
            byte[] bFile = new byte[(int) file.length()];
            
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            
            return bFile;
        } catch (IOException ex) {
            Logger.getLogger(ShowProfileImage.class.getName()).log(Level.SEVERE, null, ex);
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
