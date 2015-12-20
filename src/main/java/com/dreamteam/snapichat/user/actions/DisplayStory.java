/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.user.User;
import com.dreamteam.snapichat.user.actions.profile.UserDAO;
import com.dreamteam.snapichat.user.actions.story.Story;
import com.dreamteam.snapichat.user.actions.story.StoryDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
 * @author Alexis
 */
@WebServlet(name = "DisplayStory", urlPatterns = {"/story/*"})
public class DisplayStory extends HttpServlet {

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
        
        String uid;
        String username;
        
        try {
            String pathInfo = request.getPathInfo();
            String[] pathParts = pathInfo.split("/");
            uid = pathParts[1];
            UserDAO userDAO = new UserDAO();
            User u = userDAO.getUser(uid);
            username = u.getUsername();
        } catch(Exception ex) {
            HttpSession session = request.getSession();
            User u = (User) session.getAttribute("user");
            uid = Integer.toString(u.getId());
            username = u.getUsername();
        }
        
        try {
            StoryDAO storyDAO = new StoryDAO();
            List<Story> stories = storyDAO.getUserPictures(uid);
            request.setAttribute("stories", stories);
            request.setAttribute("uid", uid);
            request.setAttribute("username", username);
            request.getRequestDispatcher("/story.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DisplayStory.class.getName()).log(Level.SEVERE, null, ex);
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
