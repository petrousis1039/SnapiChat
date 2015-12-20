/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.story;

import com.dreamteam.snapichat.helpers.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexis
 */
public class StoryDAO {
    
    public List<Story> getUserPictures(String uid) throws SQLException {
        List<Story> stories = new ArrayList<>();

        Connection connection = DBHelper.getConnection();
        
        String query = "SELECT * FROM user_story_pictures WHERE id_user=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, uid);
        
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Story story = new Story();
            story.setId(resultSet.getInt("idpictures"));
            story.setStoryPicture(resultSet.getString("story_picture"));
            stories.add(story);
        }
        
        connection.close();
        return stories;
    }
    
}
