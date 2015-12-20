<%-- 
    Document   : chat
    Created on : Dec 3, 2015, 12:29:32 PM
    Author     : John, Natasa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat | Chat</title>

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>

        <!-- font awesome -->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

        <!-- bootstrap -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />

        <!-- animate.css -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/animate/animate.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/animate/set.css" />

        <!-- gallery -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/gallery/blueimp-gallery.min.css">

        <!-- favicon -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">

        <link rel="stylesheet" href="assets/style.css">
        <script src="${pageContext.request.contextPath}/assets/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/assets/socketchat.js"></script>
    </head>

    <body onload="connect('${sessionScope.user.getId()}', '${sessionScope.user.getUsername()}');" onunload="disconnect();">
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
                                <li ><a href="friends">Friends</a></li>
                                <li ><a href="story">Story</a></li>
                                <li class="active"><a href="chat.jsp">Snapis</a></li>
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

        <div id="contact" class="spacer" style="padding-top:140px;">
            <div class="row" style="margin: 0; padding: 0;">
                <div class="col-sm-3 online-users">
                    <h1>Online Users</h1>
                    <ul id="online-users-list">
                        
                    </ul>
                </div>
                <div class="col-sm-8 col-xs-12">
                    <ul id="myTab" class="nav nav-tabs">
                        
                    </ul>
                    <div id="tab-content-wrap" class="tab-content">
                        
                    </div>
                    <div class="panel input-area">
                        <input id="messageInput" class="text-field" type="text" placeholder="Message"
                               onkeydown="if (event.keyCode === 13) sendMessage();" />
                        <input class="btn btn-info" type="submit" value="Send" onclick="sendMessage();" />
                        <input type="file" id="snapi-pic" name="snapi-pic" />
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

        <!-- wow script -->
        <script src="${pageContext.request.contextPath}/assets/wow/wow.min.js"></script>

        <!-- boostrap -->
        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js" type="text/javascript" ></script>

        <!-- jquery mobile -->
        <script src="${pageContext.request.contextPath}/assets/mobile/touchSwipe.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/respond/respond.js"></script>
        
        <!-- gallery -->
        <script src="${pageContext.request.contextPath}/assets/gallery/jquery.blueimp-gallery.min.js"></script>
        
        <script>
            $(function(){
                $('a[data-toggle = "tab"]').on('shown.bs.tab', function (e) {
                   // Get the name of active tab
                   var activeTab = $(e.target).text();

                   // Get the name of previous tab
                   var previousTab = $(e.relatedTarget).text();

                   $(".active-tab span").html(activeTab);
                   $(".previous-tab span").html(previousTab);
                });
            });
         </script>
    </body>
</html>
