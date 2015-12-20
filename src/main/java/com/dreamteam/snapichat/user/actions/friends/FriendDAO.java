/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.friends;

import com.dreamteam.snapichat.helpers.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Stratos
 */
public class FriendDAO {
    
    public static enum Action {
        FIND_NEW(0), LIST_FRIENDS(1);
        
        private final int value;

        private Action(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    public List<Friend> list(int id, int action) throws SQLException {
        Action a;
        switch (action) {
            case 0:
                a = Action.FIND_NEW;
                break;
            case 1:
                a = Action.LIST_FRIENDS;
                break;
            default:
                return Collections.EMPTY_LIST;
        }
        
        return list(id, a);
    }
    
    public List<Friend> list(int id, Action action) throws SQLException {
        if(action == Action.FIND_NEW) {
            return listNewPeople(id);
        } else if(action == Action.LIST_FRIENDS) {
            return listUserFriends(id);
        }
        
        return Collections.EMPTY_LIST;
    }
    
    public List<Friend> listNewPeople(int id) throws SQLException {
        List<Friend> friends = new ArrayList<>();

        Connection connection = DBHelper.getConnection();
        
        String query = "SELECT id,username FROM user"
                + " WHERE id <> ? AND id NOT IN "
                    + "(SELECT userid2 FROM user_friendlist WHERE userid1 = ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.setInt(2, id);
        
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Friend friend = new Friend();
            friend.setId(resultSet.getInt("id"));
            friend.setUsername(resultSet.getString("username"));
            friends.add(friend);
        }

        return friends;
    }
    
    public List<Friend> listUserFriends(int id) throws SQLException {
        List<Friend> friends = new ArrayList<>();

        Connection connection = DBHelper.getConnection();

        String query = "SELECT id,username FROM user,user_friendlist "
                + "WHERE user.id = user_friendlist.userid2 AND userid1 = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Friend friend = new Friend();
            friend.setId(resultSet.getInt("id"));
            friend.setUsername(resultSet.getString("username"));
            friends.add(friend);
        }

        return friends;
    }
    
}
