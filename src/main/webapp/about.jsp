<%-- 
    Document   : about
    Created on : Nov 21, 2015, 11:16:55 PM
    Author     : Despoina
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat | About</title>

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
                                <li ><a href="friends">Friends</a></li>
                                <li ><a href="story">Story</a></li>
                                <li ><a href="chat.jsp">Snapis</a></li>
                                <li ><a href="shoutbox">ShoutBox</a></li>
                                <li class="active"><a href="about.jsp">About</a></li>
                                <li ><a href="contact.jsp">Contact</a></li>
                            </ul>
                        </div>
                        <!-- #Nav Ends -->
                    </div>
                </div>
            </div>
        </div>
        
        <div id="partners" class="container spacer ">
            <h2 class="text-center  wowload fadeInUp">Some of our happy clients</h2>
            <div class="clearfix">
                <div class="col-sm-6 partners  wowload fadeInLeft">
                    <img src="images/partners/1.jpg" alt="partners">
                    <img src="images/partners/2.jpg" alt="partners">
                    <img src="images/partners/3.jpg" alt="partners">
                    <img src="images/partners/4.jpg" alt="partners">
                </div>

                <div class="col-sm-6">
                    <div id="carousel-testimonials" class="carousel slide testimonails  wowload fadeInRight" data-ride="carousel">
                        <div class="carousel-inner">  
                            <div class="item active animated bounceInRight row">
                                <div class="animated slideInLeft col-xs-2">
                                    <img alt="portfolio" src="images/team/1.jpg" width="100" class="img-circle img-responsive">
                                </div>
                                <div  class="col-xs-10">
                                    <p> I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.</p>      
                                    <span>Angel Smith - <b>eshop Canada</b></span>
                                </div>
                            </div>
                            <div class="item  animated bounceInRight row">
                                <div class="animated slideInLeft col-xs-2">
                                    <img alt="portfolio" src="images/team/2.jpg" width="100" class="img-circle img-responsive">
                                </div>
                                <div  class="col-xs-10">
                                    <p>No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful.</p>
                                    <span>John Partic - <b>Crazy Pixel</b></span>
                                </div>
                            </div>
                            <div class="item  animated bounceInRight row">
                                <div class="animated slideInLeft  col-xs-2">
                                    <img alt="portfolio" src="images/team/3.jpg" width="100" class="img-circle img-responsive">
                                </div>
                                <div  class="col-xs-10">
                                    <p>On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue.</p>
                                    <span>Harris David - <b>Jet London</b></span>
                                </div>
                            </div>
                        </div>

                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-testimonials" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-testimonials" data-slide-to="1"></li>
                            <li data-target="#carousel-testimonials" data-slide-to="2"></li>
                        </ol>
                        <!-- Indicators -->
                    </div>
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