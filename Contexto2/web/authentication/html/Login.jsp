<%-- 
    Document   : Login
    Created on : Sep 21, 2012, 5:14:24 PM
    Author     : ososorio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Basic</title>
        <script src="../../jquery/js/jquery-1.8.0.min.js"></script>
        <script src="../../jquery/js/jquery-ui-1.8.23.custom.min.js"></script>
        <script src="../js/login.js"></script>
        <link rel="stylesheet" type="text/css" href="../../jquery/css/theme/jquery-ui-1.8.23.custom.css" media="all">
        <link rel="stylesheet" type="text/css" href="../css/login.css" media="all">
    </head>
    <body>

        <!--WRAPPER-->
        <div id="wrapper">

                <!--SLIDE-IN ICONS-->
            <div class="user-icon"></div>
            <div class="pass-icon"></div>
            <!--END SLIDE-IN ICONS-->

        <!--LOGIN FORM-->
        <form name="login-form" class="login-form" >

                <!--HEADER-->
            <div class="header">
            <!--TITLE--><h1>Login Form</h1><!--END TITLE-->
            <!--DESCRIPTION--><span>Fill out the form below to login to my super awesome imaginary control panel.</span><!--END DESCRIPTION-->
            </div>
            <!--END HEADER-->

                <!--CONTENT-->
            <div class="content">
                <!--USERNAME--><input name="username" type="text" class="input username" value="Username" onfocus="this.value=''" /><!--END USERNAME-->
            <!--PASSWORD--><input name="password" type="password" class="input password" value="Password" onfocus="this.value=''" /><!--END PASSWORD-->
            </div>
            <!--END CONTENT-->

            <!--FOOTER-->
            <div class="footer">
            <!--LOGIN BUTTON--><input type="submit" name="submit" value="Login" class="button" /><!--END LOGIN BUTTON-->
            <!--REGISTER BUTTON--><input type="submit" name="submit" value="Register" class="register" /><!--END REGISTER BUTTON-->
            </div>
            <!--END FOOTER-->

        </form>
        <!--END LOGIN FORM-->

        </div>
        <!--END WRAPPER-->

        <!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

    </body>
</html>
