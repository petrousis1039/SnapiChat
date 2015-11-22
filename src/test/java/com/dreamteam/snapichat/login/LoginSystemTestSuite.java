/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.login;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Alexis
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.dreamteam.snapichat.login.RegisterTestAdmin.class,
    com.dreamteam.snapichat.login.LoginTestAdmin.class,
    com.dreamteam.snapichat.login.LogoutTestAdmin.class,
    com.dreamteam.snapichat.login.DeleteTestAdminAccount.class})
public class LoginSystemTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("--Testing Login System--");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("--Login System End--");
    }
    
}
