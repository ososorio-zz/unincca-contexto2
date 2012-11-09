<%-- 
    Document   : newjsp
    Created on : Nov 8, 2012, 11:30:53 PM
    Author     : ososorio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Administrator</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css" />
        <script>
            $(function() {
                $( "#accordion" ).accordion({
                    heightStyle: "content"
                });
            });
            
            $(function() {
                $( "input[type=submit], a, button" )
                .button()
                .click(function( event ) {
                    event.preventDefault();
                });
            });
            
            $(function() {
                $( "#datepicker" ).datepicker({
                    showOn: "button",
                    buttonImage: "images/calendar.gif",
                    buttonImageOnly: true
                });
            });
        </script>
    </head>
    <body>

        <div id="accordion">
            <h3>Creacion de Votacion</h3>
            <div>
                <input type="checkbox" value="1" class="ck"/>Persona<br />
                <input type="checkbox" value="2" class="ck"/>Lista<br />
                <p><input type="text" id="datepicker" placeholder="Fecha"/></p>
                <p><input type="text"class="input apertura" placeholder="Hora Apertura"></input></p>
                <p><input type="text"class="input cierre" placeholder="Hora Cirre"></input></p>
                <p><input type="submit" id="crear" name="submit" value="Crear" class="button" /></p>

            </div>
            <h3>Inscripcion de Candidatos</h3>
            <div>


            </div>
            <h4>Inscripcion de Votantes</h4>
            <div>


            </div>
    </body>
</html>
