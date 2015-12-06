package com.dreamteam.snapichat.user.actions;

import com.dreamteam.snapichat.user.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;

@WebServlet(name = "Chat", urlPatterns = {"/chat.do"})
public class Chat extends HttpServlet {

    // message map, mapping user UID with a message list
    private static Map<String, List<String>> chatMap = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        
        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        if(u == null) {
            return;
        }
        // send message
        if ("send".equals(action)) {
            // get param with UTF-8 enconding
            String msg = new String(req.getParameter("msg").getBytes("ISO-8859-1"), "UTF-8");
            String uid = u.getUsername();
            for (String s : chatMap.keySet()) {
                if (!s.equals(uid)) {
                    synchronized (chatMap.get(s)) {
                        // put message to any other user's msg list
                        chatMap.get(s).add("<div class=\"message other\"><p class=\"user\">" + uid + "</p><p class=\"text\">" + msg + "</p></div>");
                    }
                }
            }
        } else if ("get".equals(action)) { // get message
            String uid = u.getUsername();
            if (uid == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
            List<String> l = chatMap.get(uid);
            if (l == null) {
                chatMap.put(uid, new ArrayList<String>());
                l = chatMap.get(uid);
            }
            synchronized (l) {
                if (l.size() > 0) {
                    // for UTF-8 chars
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter out = resp.getWriter();
                    JSONArray jsna = new JSONArray();
                    // add all msg to json array and clear list
                    while (l.size() > 0) {
                        jsna.put(l.remove(0));
                    }

                    out.println(jsna);
                    out.close();
                }
            }
        }
    }

    public static Map<String, List<String>> getChatMap() {
        return chatMap;
    }
}
