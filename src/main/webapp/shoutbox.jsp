<%-- 
    Document   : shoutbox
    Created on : Nov 21, 2015, 8:14:45 PM
    Author     : Lefteris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat | ShoutBox</title>

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>

        <!-- font awesome -->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

        <!-- bootstrap -->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css" />

        <!-- animate.css -->
        <link rel="stylesheet" href="assets/animate/animate.css" />
        <link rel="stylesheet" href="assets/animate/set.css" />

        <!-- gallery -->
        <link rel="stylesheet" href="assets/gallery/blueimp-gallery.min.css">

        <!-- favicon -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">


        <link rel="stylesheet" href="assets/style.css">
    </head>

    <body>
        <div class="topbar animated fadeInLeftBig"></div>

        <!-- Header Starts -->
        <div class="navbar-wrapper">
            <div class="container">
                <div class="navbar navbar-default navbar-fixed-top" role="navigation" id="top-nav">
                    <div class="container">
                        <div class="navbar-header">
                            <!-- Logo Starts -->
                            <a class="navbar-brand" href="index.jsp"><img src="images/logo.png" alt="logo"></a>
                            <!-- #Logo Ends -->

                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>

                        <!-- Nav Starts -->
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right scroll">
                                <li ><a href="index.jsp">Home</a></li>
                                <li ><a href="login.jsp">Login</a></li>
                                <li ><a href="edit_profile.jsp">Profile</a></li>
                                <li ><a href="friends.jsp">Friends</a></li>
                                <li ><a href="story.jsp">Story</a></li>
                                <li ><a href="chat.jsp">Snapis</a></li>
                                <li class="active"><a href="shoutbox.jsp">ShoutBox</a></li>
                                <li ><a href="about.jsp">About</a></li>
                                <li ><a href="contact.jsp">Contact</a></li>
                            </ul>
                        </div>
                        <!-- #Nav Ends -->
                    </div>
                </div>
            </div>
        </div>

        <div id="contact" class="spacer" style="padding-top:140px;">
            <div class="container contactform center">
                <h2 class="text-center  wowload fadeInUp">Shout Out Loud</h2>
                <div class="row wowload fadeInLeftBig">      
                    <div class="col-sm-6 col-sm-offset-3 col-xs-12">
                        <div id="shoutbox">
                            <c:forEach items="${messages}" var="shout">
                                <div class="message">
                                    <p class="user">${shout.username}</p>
                                    <p class="text">${shout.text}</p>
                                </div>
                            </c:forEach>
                        </div>
                        <form id="message-form" action="shout" method="POST">
                            <textarea maxlength="140" rows="2" name="message" placeholder="Message"></textarea>
                            <button type="submit" id="send-msg" class="btn btn-primary"><i class="fa fa-paper-plane"></i> Send</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer Starts -->
        <div class="footer text-center spacer">
            <p class="wowload flipInX"><a href="#"><i class="fa fa-facebook fa-2x"></i></a> <a href="#"><i class="fa fa-instagram fa-2x"></i></a> <a href="#"><i class="fa fa-twitter fa-2x"></i></a> <a href="#"><i class="fa fa-flickr fa-2x"></i></a> </p>
            Copyright 2015-2016 SnapiChat. All rights reserved.
        </div>
        <!-- Footer Starts -->
        
        <!-- jquery -->
        <script src="${pageContext.request.contextPath}/assets/jquery.js"></script>

        <!-- wow script -->
        <script src="${pageContext.request.contextPath}/assets/wow/wow.min.js"></script>

        <!-- boostrap -->
        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js" type="text/javascript" ></script>

        <!-- jquery mobile -->
        <script src="${pageContext.request.contextPath}/assets/mobile/touchSwipe.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/respond/respond.js"></script>
    </body>
</html>
