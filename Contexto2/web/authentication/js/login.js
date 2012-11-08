/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    loginObject.init();
    loginObject.initButtons();
    loginObject.initDialogs();
    loginObject.initEffects();
});





loginObject={
    init:function(){
          //funcion solo permite digitar numeros
         $(".username").keydown(function(event) {
            // Allow: backspace, delete, tab, escape, and enter
            if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 ||
                 // Allow: Ctrl+A
                (event.keyCode == 65 && event.ctrlKey === true) ||
                 // Allow: home, end, left, right
                (event.keyCode >= 35 && event.keyCode <= 39)) {
                     // let it happen, don't do anything
                     return;
            }
            else {
                // Ensure that it is a number and stop the keypress
                if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                    event.preventDefault();
                }
            }
         });
        
    },
    initEffects:function(){
 	$(".username").focus(function() {
            $(".user-icon").css("left","-48px");
	});
	$(".username").blur(function() {
            $(".user-icon").css("left","0px");
	});
	$(".password").focus(function() {
            $(".pass-icon").css("left","-48px");
	});
	$(".password").blur(function() {
            $(".pass-icon").css("left","0px");
	});       
    },
    initDialogs:function(){
        //dialogo para informacion varia
        $( "#dialog" ).dialog({
            autoOpen: false,
            show: "blind",
            hide: "explode",
            width: 305,
            height: 480,
            modal:true,
            buttons: {
                Ok: function() {
                    $( this ).dialog( "close" );
                }
            }
        });  
        
        //crea el dialogo para cambiar el password
        $( "#dialogChangePasword" ).dialog({
            autoOpen: false,
            show: "blind",
            hide: "explode",
            width: 310,
            height: 485,
            modal:true,
            open:function(event,ui)
            {
             var context=$("#dialogChangePasword");
               $(".input.username",context).val();
               $(".input.oldpassword",context).val();
               $(".input.newpassword",context).val();
            },
            buttons: {
                "Cambiar": function() {
                    $( this ).dialog( "close" );
                    loginObject.changePassword();
                },
                "Cancelar": function(){
                    $( this ).dialog( "close" );
                    $('input[name="username"]').val("");
                    $('input[name="password"]').val("");
                }
            }
        });

       //crear el dialogo para recordar password
       $( "#dialogLostPasword" ).dialog({
            autoOpen: false,
            show: "blind",
            hide: "explode",
            width: 310,
            height: 485,
            modal:true,
            open:function(event,ui)
            {
              var context=$("#dialogLostPasword");
              $(".input.username",context).val("");
              $(".input.nombre",context).val("");
              $(".input.apellido",context).val("");
            },
            buttons: {
                "Recuperar": function() {
                    $( this ).dialog( "close" );
                    loginObject.lostPassword();
                },
                "Cancelar": function(){
                    $( this ).dialog( "close" );
                }
            }
        });
        
    },
    initButtons:function(){
       //agrega el evento para el recordar password
       $(".register").click(function(){
           $( "#dialogLostPasword" ).dialog("open")
       });
       //asigna evento a boton login
       $("#login").click(function(){
           loginObject.loginAjax();
       });
        
    },
    
    loginAjax:function(){
     var user=   $('input[name="username"]').val();
     var pass=   $('input[name="password"]').val();

            var json=  {
              "ac": 0,
              "op": 0,
              "data": {
                "cedula": user,
                "password": pass
              }
         }
         loginObject.sendAjax(json, function(response){

              if(response.login=="false")
                  {
                   $(".contentM").html(response.cause);
                   $( "#dialog" ).dialog("open");
                  }
              else
              {
                if(response.userInfo && response.userInfo.last_login == "0")
                    {
                       loginObject.type=response.userInfo.type;
                       $("#dialogChangePasword .input.username").val(user);
                       $( "#dialogChangePasword" ).dialog("open");  
                    }
                    else if(response.userInfo.type="0")
                        {
                             loginObject.redirectToUser();
                        }
                   else if(response.userInfo.type="1")
                        {
                            loginObject.redirectToAdmin();
                        } 
              }

         }, function(response){
             alert(response);
         });
    },
    lostPassword:function(){
        var context=$("#dialogLostPasword");
        var cedula=      $(".input.username",context).val();
        var nombre= $(".input.nombre",context).val();
        var apellido= $(".input.apellido",context).val();

         //verificar que no sea nulos
        var json={
          "ac": 0,
          "op": 1,
          "data": {
            "cedula": cedula,
            "nombre": nombre,
            "apellido":apellido
          }
        } 
        loginObject.sendAjax(json,function(response){
            if(response.LostPassword=="true")
              {
                 $(".contentM").html("Su contrasena es:"+response.password);
                 $( "#dialog" ).dialog("open");       
              }
            else
              {
                $(".contentM").html(response.cause);
                $( "#dialog" ).dialog("open");     
              }                

            
        },function(response){
            alert(response);
        });
    },
    changePassword:function(){
        
      var context=$("#dialogChangePasword");

      var cedula=      $(".input.username",context).val();
      var oldPassword= $(".input.oldpassword",context).val();
      var newPassword= $(".input.newpassword",context).val();

      //verificar que no sea nulos
      var json={
         "ac": 0,
         "op": 2,
         "data": {
                 "cedula": cedula,
                 "oldpassword": oldPassword,
                 "newpassword": newPassword
      }};
      loginObject.sendAjax(json,function(response){
       
          if(response.changePassword=="true")
              {
                  alert("Cambio exitoso typo usuario"+loginObject.type);
                  if(loginObject.type=="1")
                      {
                          loginObject.redirectToAdmin();
                      }
                      else
                          {
                             loginObject.redirectToUser();
                          }
              }
          else
              {
                $(".contentM").html(response.cause);
                $( "#dialog" ).dialog("open");  
              }

                  
      },function(response){
          alert(response);
      });
        
    },
    
    sendAjax:function(json,callbackDone,callbackfail){
        
        $.ajax({
              type: "POST",
              url: "../../Facade",
              data: JSON.stringify(json),
              contentType: "application/json; charset=utf-8",
              dataType: "json"

            }).done(function( response ) {
                callbackDone(response);
            })
            .fail(function(response){
                callbackfail(response);
            });  
    },
    redirectToAdmin:function(){
        alert("redirect to admin");
    },
    redirectToUser:function(){
        alert("redirect to user");
    }
};