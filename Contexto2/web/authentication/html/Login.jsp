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
        <form name="login-form" class="login-form" action="javascript:void(0)" method="post">

                <!--HEADER-->
            <div class="header">
            <!--TITLE--><h1>Sistema de votación</h1><!--END TITLE-->
            <!--DESCRIPTION--><span>Ingrese su cedula y contrasena para poder ingresar al sistema.</span><!--END DESCRIPTION-->
            </div>
            <!--END HEADER-->

                <!--CONTENT-->
            <div class="content">
                <!--USERNAME--><input name="username" type="text" class="input username" value="Cedula" onfocus="this.value=''" placeholder="Cedula"/><!--END USERNAME-->
                <!--PASSWORD--><input name="password" type="password" class="input password" value="Password" onfocus="this.value=''" placeholder="Password"/><!--END PASSWORD-->
            </div>
            <!--END CONTENT-->

            <!--FOOTER-->
            <div class="footer">
            <!--LOGIN BUTTON--><input type="submit" id="login" name="submit" value="Ingreso" class="button" /><!--END LOGIN BUTTON-->
            <!--REGISTER BUTTON--><input type="submit" name="submit" value="Olvido su contrasena?" class="register" /><!--END REGISTER BUTTON-->
            </div>
            <!--END FOOTER-->

        </form>
        <!--END LOGIN FORM-->

        </div>
        <!--END WRAPPER-->

        <!--GRADIENT--><div class="gradient"></div><!--END GRADIENT-->

        
         
        <div id="dialog" title="Informacion">
            <p class="contentM"></p>
        </div>

         <div id="dialogChangePasword" title="Cambiar contrasena">
             <form action="javascript:void(0);" class="login-form changePassword">
                <div class="content changePassword">
                 <input type="text"     class="input username" placeholder="Cedula"></input>
                 <input type="password" class="input password oldpassword" placeholder="Password Antiguo"></input>
                 <input type="password" class="input password newpassword" placeholder="Password Nuevo"></input>
                </div>
             </form>
        </div>
        
         <div id="dialogLostPasword" title="Recuperar contrasena">
             <form action="javascript:void(0);" class="login-form lostPassword">
                <div class="content lostPassword">
                 <input type="text"     class="input username" placeholder="Cedula"></input>
                 <input type="text" class="input nombre" placeholder="Nombre"></input>
                 <input type="text" class="input apellido" placeholder="Apellido"></input>
                </div>
             </form>
        </div>       
        
        
    </body>
</html>
