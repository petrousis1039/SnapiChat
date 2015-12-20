/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.profile;

import com.dreamteam.snapichat.helpers.DBHelper;
import com.dreamteam.snapichat.user.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Natasa
 */
public class UserDAO {
    
    public User getUser(int id) throws SQLException {
        return getUser(Integer.toString(id));
    }
    
    public User getUser(String id) throws SQLException {
        User user = null;
        
        Connection connection = DBHelper.getConnection();

        String query = "SELECT * FROM user"
                + " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            User.UserBuilder builder = new User.UserBuilder(
                    resultSet.getInt("id"), resultSet.getString("username"));
            builder.firstName(resultSet.getString("userfirstname"));
            builder.lastName(resultSet.getString("userlastname"));
            builder.email(resultSet.getString("user_email"));
            builder.country(resultSet.getString("user_country"));
            builder.city(resultSet.getString("user_city"));
            builder.phone(resultSet.getString("user_phone_num"));
            
            user = builder.createUser();
        }

        connection.close();
        return user;
    }
    
}
