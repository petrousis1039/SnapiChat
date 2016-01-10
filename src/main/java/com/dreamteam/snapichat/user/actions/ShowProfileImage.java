/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.image.ImageTools;
import com.dreamteam.snapichat.user.User;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;

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
            int userId;
            
            if(uid == null) {
                HttpSession session = request.getSession();
                User u = (User) session.getAttribute("user");
                userId = u.getId();
            } else {
                userId = Integer.parseInt(uid);
            }
            
            String sqlString = "SELECT user_photo FROM user WHERE id = '" + userId + "'";
            Statement myStatement = conn.createStatement();

            ResultSet rs = myStatement.executeQuery(sqlString);

            if (rs.next()) {
                file = rs.getBlob("user_photo");
                try {
                    fileData = file.getBytes(1, (int) file.length());
                    if (file.length() == 0) {
                        throw new Exception("No profile image");
                    }
                } catch (Exception ex) {
                    fileData = getDefaultImage();
                }
            } else {
                out.println("file not found!");
                return;
            }

            byte[] imgByteArray;
            try {
                Image img = Toolkit.getDefaultToolkit().createImage(fileData);
                ImageTools imgTools = new ImageTools();
                Image thumb = imgTools.createThumbnail(img);
                BufferedImage bImg = ImageTools.toBufferedImage(thumb);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bImg, "jpg", baos);
                baos.flush();
                imgByteArray = baos.toByteArray();
                baos.close();
            } catch(Exception ex) {
                imgByteArray = fileData;
            }
            
            response.setContentType("image");
            response.setHeader("Content-Disposition", "inline");
            response.setContentLength(imgByteArray.length);

            OutputStream output = response.getOutputStream();
            output.write(imgByteArray);

            output.flush();

        } catch (SQLException ex) {
            Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private byte[] getDefaultImage() {
        try {
            String relativePath = "/images/default_profile.jpg";
            
            InputStream is = getServletContext().getResourceAsStream(relativePath);
            byte[] bFile = IOUtils.toByteArray(is);
            
            is.close();
            
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
