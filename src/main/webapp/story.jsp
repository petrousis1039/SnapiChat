<%-- 
    Document   : story
    Created on : Dec 18, 2015, 11:18:56 AM
    Author     : Pasxalis
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat | Story</title>

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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/timeline.css">
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
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><img src="${pageContext.request.contextPath}/images/logo.png" alt="logo"></a>
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
                                <li ><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                                <li ><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                                <li ><a href="${pageContext.request.contextPath}/edit_profile.jsp">Profile</a></li>
                                <li ><a href="${pageContext.request.contextPath}/friends">Friends</a></li>
                                <li class="active"><a href="${pageContext.request.contextPath}/story">Story</a></li>
                                <li ><a href="${pageContext.request.contextPath}/chat.jsp">Snapis</a></li>
                                <li ><a href="${pageContext.request.contextPath}/shoutbox">ShoutBox</a></li>
                                <li ><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
                                <li ><a href="${pageContext.request.contextPath}/contact.jsp">Contact</a></li>
                            </ul>
                        </div>
                        <!-- #Nav Ends -->
                    </div>
                </div>
            </div>
        </div>
        <div id="works"  class=" clearfix grid">
            <div class="page-header">
                <h1 id="timeline">${username}'s Story</h1>
            </div>
            <ul class="timeline">

                <c:forEach items="${stories}" var="story" varStatus="loop">
                    <c:choose>
                        <c:when test="${loop.index % 2 == 1}" >
                            <li class="timeline-inverted">
                                <div class="timeline-badge warning"><i class="glyphicon glyphicon-bookmark"></i></div>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <div class="timeline-badge warning"><i class="glyphicon glyphicon-asterisk"></i></div>
                        </c:otherwise>
                    </c:choose>
                        <div class="timeline-panel">
                            <div class="timeline-heading">
                                <h4 class="timeline-title">Pic #${loop.index} <c:if test="${uid == sessionScope.user.getId()}"><a href="deleteStoryPic?sid=${story.getId()}">Delete</a></c:if></h4>
                            </div>
                            <div class="timeline-body">
                                <figure class="effect-roxy wowload fadeIn animated" style="visibility: visible; animation-name: fadeIn;">
                                    <img src="${story.getStoryPicture()}">
                                    <figcaption>
                                        <p>Image Title</p>
                                        <p><a href="${story.getStoryPicture()}" data-gallery="">View</a></p>            
                                    </figcaption>
                                </figure>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
	</div>
	
	<!-- Footer Starts -->
	<div class="footer text-center spacer">
		<p class="wowload flipInX"><a href="#"><i class="fa fa-facebook fa-2x"></i></a> <a href="#"><i class="fa fa-instagram fa-2x"></i></a> <a href="#"><i class="fa fa-twitter fa-2x"></i></a> <a href="#"><i class="fa fa-flickr fa-2x"></i></a> </p>
		Copyright 2015-2016 SnapiChat. All rights reserved.
	</div>
	
	<!-- The Bootstrap Image Gallery lightbox, should be a child element of the document body -->
	<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls">
	<!-- The container for the modal slides -->
	<div class="slides"></div>
	<!-- Controls for the borderless lightbox -->
		<h3 class="title">Title</h3>
		<a class="prev">‹</a>
		<a class="next">›</a>
		<a class="close">×</a>
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

