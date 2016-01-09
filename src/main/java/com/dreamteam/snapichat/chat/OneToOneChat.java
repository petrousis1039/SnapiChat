/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.chat;

import com.dreamteam.snapichat.user.User;
import com.dreamteam.snapichat.user.actions.profile.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Lefteris
 */
@ServerEndpoint("/chatApp/{uid}")
public class OneToOneChat {

    //store the online users with their session id and user id
    private static final HashMap<String, List<String>> USERS_MAP = new HashMap<>();
    //store the session parameters of all connected clients  
    private static final Set<OneToOneChat> CONNECTIONS = new CopyOnWriteArraySet<>();
    
    private String uid;
    private User user;
    private Session session;

    public OneToOneChat() {
        uid = null;
        user = null;
    }

    @OnOpen
    public void start(Session session, @PathParam("uid") String userID) {
        this.session = session;
        session.setMaxBinaryMessageBufferSize(19999999);
        session.setMaxTextMessageBufferSize(19999999);
        
        //add this session to connections set
        CONNECTIONS.add(this);
        UserDAO userDAO = new UserDAO();
        try {
            this.uid = userID;
            this.user = userDAO.getUser(userID);
        } catch (SQLException ex) {
            Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //add username and session id in hashmap
        addUserInMap(userID, session.getId());
        
        //broadcast new user joined notification to all connected clients
        newJoinUpdateAllJSON(uid);
        
        broadcast(getOnlineUsersListJSON());
    }

    @OnClose
    public void end(Session session) {
        //removing the session from connections set 
        CONNECTIONS.remove(this);
        
        //removing user from hashmap  
        removeUserFromMap(session.getId());
        
        //broadcasting user closed notification to all connected clients 
        closeUpdateAllJSON(uid);
        
        //inform others for my exit
        broadcast(getOnlineUsersListJSON());
    }

    @OnMessage
    public void incoming(Session session, String message) {
        try {
            JSONObject data = new JSONObject(message);
            String action = (String) data.get("action");
            
            if(action.equals("MESSAGE")) {
                String msgTo = data.getString("to");
                String msgContent = data.getString("message");
                String msgImg = data.getString("img");
                
                sendMessageToUser(msgTo, uid, msgContent, msgImg);
            } else if(action.equals("LIST")) {
                String usersList = getOnlineUsersListJSON();
                broadcast(usersList, session.getId());
            }
        } catch (JSONException ex) {
            Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMessageToUser(String to, String from, String actualMessage, String img) {
        //get sessionid of recipient and sender
        List<String> toSessionIDs = getSessionIDsByUser(to);
        List<String> fromSessionIDs = getSessionIDsByUser(from);
        
        //prepare proper format of msg
        String messageToSend = prepareMessageJSON(to, from, user.getUsername(), actualMessage, img);
        
        //send the message to recipient and inform sender
        for(int i=0; i<fromSessionIDs.size(); i++) {
            broadcast(messageToSend, fromSessionIDs.get(i));
        }
        for(int j=0; j<toSessionIDs.size(); j++) {
            broadcast(messageToSend, toSessionIDs.get(j));
        }
    }

    private void broadcast(String messageToSend, String toSessionId) {
        for (OneToOneChat client : CONNECTIONS) {
            try {
                synchronized (client) {
                    //comparing the session id  
                    if (client.session.getId().equals(toSessionId)) {
                        client.session.getBasicRemote().sendText(messageToSend); //send message to the user  
                    }
                }
            } catch (IOException e) {
                CONNECTIONS.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                }
                String message = String.format("* %s %s",
                        client.user.getUsername(), "has been disconnected.");
                broadcast(message);
            }
        }
    }

    private static void broadcast(String msg) {
        for (OneToOneChat client : CONNECTIONS) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg); //broadcasting to all connected clients  
                }
            } catch (IOException e) {
                CONNECTIONS.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                }
                String message = String.format("* %s %s",
                        client.user.getUsername(), "has been disconnected.");
                broadcast(message);
            }
        }
    }
    
    private String prepareMessageJSON(String to, String from, String fromUsername, String actualMessage, String img) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("action", "MESSAGE");
            obj.put("from", from);
            obj.put("from_username", fromUsername);
            obj.put("to", to);
            obj.put("message", actualMessage);
            obj.put("img", img);
            
            return obj.toString();
        } catch (JSONException ex) {
            Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    private void newJoinUpdateAllJSON(String user) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("action", "JOIN");
            obj.put("user", user);
            broadcast(obj.toString());
        } catch (JSONException ex) {
            Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeUpdateAllJSON(String user) {
        try {
            JSONObject obj = new JSONObject();
            obj.put("action", "CLOSE");
            obj.put("user", user);
            broadcast(obj.toString());
        } catch (JSONException ex) {
            Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //add session id and user id to hashmap
    private void addUserInMap(String uid, String sid) {
        if(USERS_MAP.containsKey(uid)) {
            USERS_MAP.get(uid).add(sid);
        } else {
            List<String> sessionIDs = new ArrayList<>();
            sessionIDs.add(sid);
            USERS_MAP.put(uid, sessionIDs);
        }
    }

    //remove user from hashmap by session id
    private void removeUserFromMap(String sid) {
        for(String uid : USERS_MAP.keySet()) {
            List<String> sessions = USERS_MAP.get(uid);
            if(sessions.contains(sid)) {
                sessions.remove(sid);
                if(sessions.isEmpty()) {
                    USERS_MAP.remove(uid);
                }
            }
        }
    }

    //get list of online users from hashmap
    private String getOnlineUsersListJSON() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("action", "LIST");
            JSONArray users = new JSONArray();
            
            for(Entry<String, List<String>> m : USERS_MAP.entrySet()) {
                String userID = m.getKey();
                User u;
                
                UserDAO userDAO = new UserDAO();
                try {
                    u = userDAO.getUser(userID);
                } catch (SQLException ex) {
                    Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }

                JSONObject jsonUser = new JSONObject();
                jsonUser.put("uid", userID);
                jsonUser.put("username", u.getUsername());
                jsonUser.put("longitude", u.getLongitude());
                jsonUser.put("latitude", u.getLatitude());

                users.put(jsonUser);
            }
            
            obj.put("users", users);
            
            return obj.toString();
        } catch (JSONException ex) {
            Logger.getLogger(OneToOneChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    private List<String> getSessionIDsByUser(String uid) {
        if(USERS_MAP.containsKey(uid)) {
            return USERS_MAP.get(uid);
        }
        
        return null;
    }

    @OnError
    public void onError(Throwable t) {
    }
}
