<%-- 
    Document   : profile
    Created on : Nov 19, 2015, 4:42:11 PM
    Author     : Petros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat | Profile</title>

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
        <link rel="stylesheet" href="assets/login.css">
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
                                <li class="active"><a href="profile.jsp">Profile</a></li>
                                <li ><a href="friends.jsp">Friends</a></li>
                                <li ><a href="story.jsp">Story</a></li>
                                <li ><a href="#">Snapis</a></li>
                                <li ><a href="help.jsp">Help</a></li>
                                <li ><a href="contact.jsp">Contact</a></li>
                            </ul>
                        </div>
                        <!-- #Nav Ends -->
                    </div>
                </div>
            </div>
        </div>

        <div id="contact" class="spacer">
            <div class="container contactform center">
                <div class="logmod__wrapper">
                    <div class="logmod__container">
                        <div class="sminputs tab-name">
                            <h4>Profile</h4>
                        </div>
                        <div class="logmod__form">
                            <form accept-charset="utf-8" action="register" class="simform">
                                <div class="sminputs">
                                    <div class="input full tall">
                                        <div class="img-wrapper">
                                            <img id="profile-img" class="profile-img" src="images/default_profile.jpg" />
                                        </div>
                                        <div class="profile-img-select">
                                            <label class="string optional" for="user-name">Username</label>
                                            <input class="string optional" maxlength="255" id="user-name" name="uname" value="${sessionScope.userid}" type="text" size="50" readonly />
                                            <label class="string optional" for="user-name">Image</label>
                                            <input type='file' id="profile-img-btn" />
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input string optional">
                                        <label class="string optional" for="user-fname">First Name</label>
                                        <input class="string optional" maxlength="255" id="user-fname" name="fname" placeholder="First Name" type="text" size="50" readonly />
                                    </div>
                                    <div class="input string optional">
                                        <label class="string optional" for="user-lname">Last Name</label>
                                        <input class="string optional" maxlength="255" id="user-lname" name="lname" placeholder="Last Name" type="text" size="50" readonly />
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input full">
                                        <label class="string optional" for="user-email">Email</label>
                                        <input class="string optional" maxlength="255" id="user-email" name="email" placeholder="Email" type="email" size="50" />
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input string optional">
                                        <label class="string optional" for="user-country">Country</label>
                                        <input class="string optional" maxlength="255" id="user-country" name="country" placeholder="Greece" type="text" size="50" />
                                    </div>
                                    <div class="input string optional">
                                        <label class="string optional" for="user-city">City</label>
                                        <input class="string optional" maxlength="255" id="user-city" name="city" placeholder="Thessaloniki" type="text" size="50" />
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input full">
                                        <label class="string optional" for="user-phone">Phone Number</label>
                                        <input class="string optional" maxlength="255" id="user-phone" name="phone" placeholder="Phone Number" type="number" size="50" />
                                    </div>
                                </div>
                                <div class="sminputs">
                                    <div class="input string optional">
                                        <label class="string optional" for="user-pw">Old Password</label>
                                        <input class="string optional" maxlength="255" id="user-pw" name="pass" placeholder="Password" type="text" size="50" />
                                    </div>
                                    <div class="input string optional">
                                        <label class="string optional" for="user-pw-repeat">New Password</label>
                                        <input class="string optional" maxlength="255" id="user-pw-repeat" placeholder="Password" type="text" size="50" />
                                    </div>
                                </div>
                                <div class="simform__actions">
                                    <input class="sumbit" name="commit" type="submit" value="Save Changes" />
                                    <span class="simform__actions-sidetext">To logout please<br><a class="special" role="link" href="logout">Click Here</a></span>
                                </div> 
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- jquery -->
        <script src="assets/jquery.js"></script>

        <script src="assets/profile.js"></script>
    </body>
</html>
