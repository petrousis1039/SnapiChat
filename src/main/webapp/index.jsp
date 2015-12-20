<%-- 
    Document   : index
    Created on : Nov 16, 2015, 11:24:10 AM
    Author     : Stratos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat</title>

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
                                <li class="active"><a href="index.jsp">Home</a></li>
                                <li ><a href="login.jsp">Login</a></li>
                                <li ><a href="edit_profile.jsp">Profile</a></li>
                                <li ><a href="friends">Friends</a></li>
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

        <!-- works -->
        <div id="works" class="clearfix grid"> 
            <figure class="effect-oscar  wowload fadeIn">
                <img src="${pageContext.request.contextPath}/images/portfolio/1.jpg" alt="img01"/>
                <figcaption>
                    <h2>Nature</h2>
                    <p>Discover the beauty of nature<br>
                        <a href="${pageContext.request.contextPath}/images/portfolio/1.jpg" title="1" data-gallery>View more</a></p>            
                </figcaption>
            </figure>
            <figure class="effect-oscar  wowload fadeInUp">
                <img src="${pageContext.request.contextPath}/images/portfolio/2.jpg" alt="img01"/>
                <figcaption>
                    <h2>Events</h2>
                    <p>Invite everyone with the most awesome way<br>
                        <a href="${pageContext.request.contextPath}/images/portfolio/2.jpg" title="1" data-gallery>View more</a></p>            
                </figcaption>
            </figure>
            <figure class="effect-oscar  wowload fadeInUp">
                <img src="${pageContext.request.contextPath}/images/portfolio/3.jpg" alt="img01"/>
                <figcaption>
                    <h2>Music</h2>
                    <p>Share every moment with the loved ones<br>
                        <a href="${pageContext.request.contextPath}/images/portfolio/3.jpg" title="1" data-gallery>View more</a></p>            
                </figcaption>
            </figure>
        </div>
        <!-- works -->

        <!-- Cirlce Starts -->
        <div id="about"	class="container spacer about">		
            <h2 class="text-center wowload fadeInUp">SnapiChat</h2>  
            <div class="row">
                <div class="col-sm-6 wowload fadeInLeft">
                    <h4><i class="fa fa-paint-brush"></i> Design</h4>
                    <p>The most elegant and creative way to communicate and share your intrests, with the people you care about. Expand your social life, make new friends and reunite with the old ones. Join our community now and discover the full potential of your creative part.</p>
                </div>
                <div class="col-sm-6 wowload fadeInRight">
                    <h4><i class="fa fa-code"></i> Frontend & Backend Development</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                </div>
            </div>
        </div>
        <!-- #Cirlce Ends -->

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