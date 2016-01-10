/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.story;

import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Despoina
 */
public class StoryDAOTest {
    
    @Test
    public void getStoryWithUID1() throws Exception {
        System.out.println("getStoryWithUID1");
        StoryDAO storyDAO = new StoryDAO();
        List<Story> u = storyDAO.getUserPictures("1");
        
        assertTrue(u != null);
    }
    
    @Test
    public void getStoryWithUID0() throws Exception {
        System.out.println("getStoryWithUID1");
        StoryDAO storyDAO = new StoryDAO();
        List<Story> u = storyDAO.getUserPictures("0");
        
        assertTrue(u.isEmpty());
    }
    
}
