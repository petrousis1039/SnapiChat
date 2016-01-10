/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.user.actions.story;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Despoina
 */
public class StoryTest {
    
    private Story story;
    
    @Before
    public void setUp() {
        story = new Story();
        story.setId(1);
        story.setStoryPicture("pic");
    }

    @Test
    public void testGetId() {
        System.out.println("testGetId");
        int expResult = 1;
        int result = story.getId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetId() {
        System.out.println("testSetId");
        int id = 2;
        story.setId(id);
        int testId = story.getId();
        assertEquals(id, testId);
    }

    @Test
    public void testGetStoryPicture() {
        System.out.println("testGetStoryPicture");
        String expResult = "pic";
        String result = story.getStoryPicture();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetStoryPicture() {
        System.out.println("testSetStoryPicture");
        String storyPicture = "png_pic";
        story.setStoryPicture(storyPicture);
        String resultPic = story.getStoryPicture();
        assertEquals(storyPicture, resultPic);
    }
    
}
