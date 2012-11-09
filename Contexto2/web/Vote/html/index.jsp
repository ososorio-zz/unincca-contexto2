<%-- 
    Document   : newjsp
    Created on : Nov 8, 2012, 11:30:43 PM
    Author     : ososorio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script src="../../jquery/js/jquery-1.8.0.min.js"></script>
        <script src="../../jquery/js/jquery-ui-1.8.23.custom.min.js"></script>
        <link rel="stylesheet" type="text/css" href="../../jquery/css/theme/jquery-ui-1.8.23.custom.css" media="all">
        <script type="text/javascript">
            
            var userInfo={
            "name":' <%=request.getParameter("name")%>',
            "lastname":'<%=request.getParameter("lastname")%>',
            "type":'<%=request.getParameter("type")%>',
            "last_ogin":'<%=request.getParameter("last_login")%>',
            "cedula":'<%=request.getParameter("cedula")%>'    
            };
            
            $(document).ready(function() {
                alert("Wellcome: "+userInfo.name);
            });
            
        </script>
    </head>
    <body>   
        <%@include file="../../jspf/header.jspf" %>
        <div class="content-page">
            Hello Vote
            
              <%=request.getParameter("name")%>
              <%=request.getParameter("lastname")%>
              <%=request.getParameter("type")%>
              <%=request.getParameter("last_login")%>
              <%=request.getParameter("cedula")%>
            
            
        </div>
        <%@include file="../../jspf/foother.jspf" %>
    </body>
</html>
