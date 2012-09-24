<%-- 
    Document   : loginBasic
    Created on : Sep 21, 2012, 5:01:27 PM
    Author     : ososorio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Basic</title>
        <script src="../jquery/js/jquery-1.8.0.min.js"></script>
        <script src="../jquery/js/jquery-ui-1.8.23.custom.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../jquery/css/theme/jquery-ui-1.8.23.custom.css" media="all">
    </head>
    <body>
        
        
        <hr>
<form id="login"  > 
<h1>Log in to your <strong>website.com</strong> account!</h1>
<p class="register">Not a member? <a href="#" id="create-user">Register here!</a></p>
<div>
<label for="login_username">Username</label> 
<input type="text" name="username" id="login_username" class="field required" title="Please provide your username" />
</div>			
<div>
<label for="login_password">Password</label>
<input type="password" name="password" id="login_password" class="field required" title="Password is required" />
</div>			
<p class="forgot"><a href="#" id="forgotpass">Forgot your password?</a></p>
<div class="submit">
<button type="submit">Log in</button>   
        <label>
<input type="checkbox" name="remember" id="login_remember" value="yes" />Remember my login on this computer
</label>   
</div>
</form>	
        
        
 <hr>        
<div id="dialog-form" title="Create new user">
	<p class="validateTips">All form fields are required.</p>
	<form >
	<fieldset>
		<label for="name">Name</label>
		<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
		<label for="email">Email</label>
		<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
		<label for="password">Password</label>
		<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
 
  <hr>
 
<div id="dialog-form1" title="Forgote Password">
	<p class="validateTips">Please Enter Valid Email</p>
	<form action="../../Facade">
	<fieldset>
		<label for="email">Email</label>
		<input type="text" name="email" id="email" value="" class="text ui-widget-content ui-corner-all" />
                <button>prueba</button>
	</fieldset>
	</form>
</div>
        
        
        
    </body>
</html>
