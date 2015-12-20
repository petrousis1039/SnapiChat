<%-- 
    Document   : friends
    Created on : Nov 27, 2015, 7:50:04 PM
    Author     : Despoina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.dreamteam.snapichat.user.User" %>
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
        <title>SnapiChat | Friends</title>

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>

        <!-- font awesome -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

        <!-- bootstrap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />

        <!-- animate.css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/animate/animate.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/animate/set.css" />

        <!-- gallery -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/gallery/blueimp-gallery.min.css">

        <!-- favicon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/style.css">
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
                            <a class="navbar-brand" href="index.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo"></a>
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
                                <li class="active"><a href="friends">Friends</a></li>
                                <li ><a href="story">Story</a></li>
                                <li ><a href="chat.jsp">Snapis</a></li>
                                <li ><a href="shoutbox">ShoutBox</a></li>
                                <li ><a href="about.jsp">About</a></li>
                                <li ><a href="contact.jsp">Contact</a></li>
                            </ul>
                        </div>
                        <!-- #Nav Ends -->
                    </div>
                </div>
            </div>
        </div>

        <!-- team -->
        <div id="friends" class="container spacer ">
            <h3 class="text-center  wowload fadeInUp">Friends</h3>
            <p class="text-center wowload fadeInLeft">You have ${friends.size()} friends</p>
            <div class="row grid team  wowload fadeInUpBig">
                <c:choose>
                    <c:when test="${friends.size() > 0}">
                        <c:forEach items="${friends}" var="friend">
                            <div class=" col-sm-3 col-xs-6">
                                <figure class="effect-chico">
                                    <img src="profileImage?uid=${friend.id}" class="img-responsive" />
                                    <figcaption>
                                        <p>
                                            <b><a href="profile?id=${friend.id}">${friend.username}</a></b><br><br>
                                            <a href="#"><i class="fa fa-dribbble"></i></a> <a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i class="fa fa-twitter"></i></a>
                                        </p>
                                    </figcaption>
                                </figure>
                                <form action="deleteFriend" method="POST">
                                    <input type="hidden" name="uid" value="${friend.id}">
                                    <input class="btn btn-primary" type="submit" value="Delete">
                                </form>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h1>You have no friends!</h1>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <!-- team -->

        <div class="container contactform center">
            <h2 class="text-center  wowload fadeInUp">Make Friends</h2>

            <div class="row wowload fadeInLeftBig">      
                <div class="col-sm-6 col-sm-offset-3 col-xs-12">  
                    <form action="findFriends" method="POST">
                        <h4 class="find-friends-label">Check for other users near you:</h4>
                        <button name="find-friends" class="btn btn-warning">Find Friends</button>
                    </form>
                    <hr />
                    <form action="makeFriend" id="make-friend-frm" method="POST">
                        <input class="input-medium" placeholder="username" type="text" name="friend-name" />
                        <input class="btn btn-primary" type="submit" id="add-friend-btn" value="Add Friend" />
                    </form>
                </div>
            </div>
        </div>

        <!-- About Starts -->
        <div class="highlight-info">
            <div class="overlay spacer">
                <div class="container">
                    <div class="row text-center  wowload fadeInDownBig">
                        <div class="col-sm-3 col-xs-6">
                            <i class="fa fa-smile-o  fa-5x"></i><h4>2512 Members</h4>
                        </div>
                        <div class="col-sm-3 col-xs-6">
                            <i class="fa fa-rocket  fa-5x"></i><h4>70851 Stories</h4>
                        </div>
                        <div class="col-sm-3 col-xs-6">
                            <i class="fa fa-cloud-download  fa-5x"></i><h4>3264506 Snapis</h4>
                        </div>
                        <div class="col-sm-3 col-xs-6">
                            <i class="fa fa-map-marker fa-5x"></i><h4>32 Video Hours</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About Ends -->
        
        <!-- Footer Starts -->
        <div class="footer text-center spacer">
            <p class="wowload flipInX"><a href="#"><i class="fa fa-facebook fa-2x"></i></a> <a href="#"><i class="fa fa-instagram fa-2x"></i></a> <a href="#"><i class="fa fa-twitter fa-2x"></i></a> <a href="#"><i class="fa fa-flickr fa-2x"></i></a> </p>
            Copyright 2015-2016 SnapiChat. All rights reserved.
        </div>
        <!-- Footer Starts -->

        <!-- The Bootstrap Image Gallery lightbox, should be a child element of the document body -->
        <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
            <!-- The container for the modal slides -->
            <div class="slides"></div>
            <!-- Controls for the borderless lightbox -->
            <h3 class="title">Title</h3>
            <a class="prev"><</a>
            <a class="next">></a>
            <a class="close">x</a>
            <!-- The modal dialog, which will be used to wrap the lightbox content -->    
        </div>

        <!-- jquery -->
        <script src="${pageContext.request.contextPath}/assets/jquery.js"></script>

        <!-- wow script -->
        <script src="${pageContext.request.contextPath}/assets/wow/wow.min.js"></script>


        <!-- boostrap -->
        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js" type="text/javascript" ></script>

        <!-- jquery mobile -->
        <script src="${pageContext.request.contextPath}/assets/mobile/touchSwipe.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/respond/respond.js"></script>

        <!-- gallery -->
        <script src="${pageContext.request.contextPath}/assets/gallery/jquery.blueimp-gallery.min.js"></script>

        <!-- custom script -->
        <script src="${pageContext.request.contextPath}/assets/script.js"></script>
    </body>
</html>