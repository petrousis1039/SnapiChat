/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.shoutbox;

import com.dreamteam.snapichat.helpers.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lefteris
 */
public class ShoutDAO {
    
    public List<Shout> listShoutMessages() throws SQLException {
        List<Shout> messages = new ArrayList<>();

        Connection connection = DBHelper.getConnection();

        String query = "SELECT * from shoutbox, user WHERE shoutbox.user_id = user.id";
        PreparedStatement statement = connection.prepareStatement(query);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Shout shout = new Shout();
            shout.setUsername(resultSet.getString("username"));
            shout.setText(resultSet.getString("text"));
            messages.add(shout);
        }

        return messages;
    }
    
}
