<%-- 
    Document   : login
    Created on : Nov 18, 2015, 3:12:11 PM
    Author     : Pasxalis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>SnapiChat | Login</title>

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
                                <li class="active"><a href="login.jsp">Login</a></li>
                                <li ><a href="profile.jsp">Profile</a></li>
                                <li ><a href="friends.jsp">Friends</a></li>
                                <li ><a href="story.jsp">Story</a></li>
                                <li ><a href="#">Snapis</a></li>
                                <li ><a href="shoutbox.jsp">ShoutBox</a></li>
                                <li ><a href="about.jsp">About</a></li>
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
                        <ul class="logmod__tabs">
                            <li data-tabtar="lgm-2"><a id="login-link" href="#">Login</a></li>
                            <li data-tabtar="lgm-1"><a id="signup-link" href="#">Sign Up</a></li>
                        </ul>
                        <div class="logmod__tab-wrapper">
                            <div class="logmod__tab lgm-1">
                                <div class="logmod__heading">
                                    <span class="logmod__heading-subtitle">Enter your personal details <strong>to create an acount</strong></span>
                                </div>
                                <div class="logmod__form">
                                    <form id="register-frm" accept-charset="utf-8" action="register" class="simform">
                                        <div id="register-response">
                                        </div>
                                        <div class="sminputs">
                                            <div class="input string optional">
                                                <label class="string optional" for="user-fname">First Name*</label>
                                                <input class="string optional" maxlength="255" id="user-fname" name="fname" placeholder="First Name" type="text" size="50" />
                                            </div>
                                            <div class="input string optional">
                                                <label class="string optional" for="user-pw-repeat">Last Name*</label>
                                                <input class="string optional" maxlength="255" id="user-lname" name="lname" placeholder="Last Name" type="text" size="50" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-name">Username*</label>
                                                <input class="string optional" maxlength="255" id="user-name" name="uname" placeholder="Username" type="text" size="50" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input string optional">
                                                <label class="string optional" for="pass">Password*</label>
                                                <input class="string optional" maxlength="255" id="user-pw" name="pass" placeholder="Password" type="text" size="50" />
                                            </div>
                                            <div class="input string optional">
                                                <label class="string optional" for="repeat-pass">Repeat password*</label>
                                                <input class="string optional" maxlength="255" id="user-pw-repeat" name="repeat-pass" placeholder="Repeat password" type="text" size="50" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-email">Email*</label>
                                                <input class="string optional" maxlength="255" id="user-email" name="email" placeholder="Email" type="email" size="50" />
                                            </div>
                                        </div>
                                        <div class="simform__actions">
                                            <input class="sumbit" id="register-btn" name="commit" type="submit" value="Create Account" />
                                            <span class="simform__actions-sidetext">By creating an account you agree to our <a class="special" href="#" target="_blank" role="link">Terms & Privacy</a></span>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="logmod__tab lgm-2">
                                <div class="logmod__heading">
                                    <span class="logmod__heading-subtitle">Enter your username and password <strong>to sign in</strong></span>
                                </div> 
                                <div class="logmod__form">
                                    <form accept-charset="utf-8" action="login" class="simform">
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-name">Username*</label>
                                                <input class="string optional" maxlength="255" id="user-name" name="uname" placeholder="Username" type="username" size="50" />
                                            </div>
                                        </div>
                                        <div class="sminputs">
                                            <div class="input full">
                                                <label class="string optional" for="user-pw">Password*</label>
                                                <input class="string optional" maxlength="255" id="user-pw" name="pass" placeholder="Password" type="password" size="50" />
                                                <span class="hide-password">Show</span>
                                            </div>
                                        </div>
                                        <div class="simform__actions">
                                            <input class="sumbit" name="commit" type="submit" value="Log In" />
                                            <span class="simform__actions-sidetext">Forgot your password?<br><a class="special" role="link" href="#">Click Here</a></span>
                                        </div> 
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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

            <script src="${pageContext.request.contextPath}/assets/index.js"></script>
            
            <script>
            $frm = $('#register-frm');
            
            $('#register-btn').click(function() {
                $.ajax({
                    url: $frm.attr('action'),
                    type: $frm.attr('method'),
                    data: $frm.serialize(),
                    success: function(data) {
                        var jobj = jQuery.parseJSON(data);
                        
                        if(!jobj.success) {
                            $('#register-response').empty();
                            $('#register-response').append('<p class="alert alert-danger">'+jobj.msg+'</p>');
                        } else {
                            $('#register-response').empty();
                            $('#register-response').append('<p class="alert alert-success">'+jobj.msg+'</p>');
                            window.location.href = 'welcome.jsp';
                        }
                    }
                });
                
                return false;
            });
            </script>
    </body>
</html>