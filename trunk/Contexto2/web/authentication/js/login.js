/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
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


        //evitar el uso de letras
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


        //crea el obeto dialog
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


           //crea el obeto dialog
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
                    changePassword();
                },
                "Cancelar": function(){
                    $( this ).dialog( "close" );
                    $('input[name="username"]').val("");
                    $('input[name="password"]').val("");
                }
            }
        });

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
                    lostPassword();
                },
                "Cancelar": function(){
                    $( this ).dialog( "close" );
                }
            }
        });
        
       $(".register").click(function(){
           $( "#dialogLostPasword" ).dialog("open")
       });
        

        //envia json cuando se realiza click sobre login
       $("#login").click(function(){

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
             $.ajax({
                              type: "POST",
                              url: "../../Facade",
                              data: JSON.stringify(json),
                              contentType: "application/json; charset=utf-8",
                              dataType: "json"

                            }).done(function( msg ) {

                              try{
                              console.info(msg);

                              if(msg.login=="false")
                                  {
                                   $(".contentM").html(msg.cause);
                                   $( "#dialog" ).dialog("open");
                                  }
                              else
                                  {
                                    if(msg.userInfo && msg.userInfo.last_login == "0")
                                        {
                                           $("#dialogChangePasword .input.username").val(user);
                                           $( "#dialogChangePasword" ).dialog("open");  
                                            
                                        }
                                        else if(msg.userInfo.type="0")
                                            {
                                                alert("redirect to user");
                                                //type
                                            }
                                       else if(msg.userInfo.type="1")
                                            {
                                                alert("redirect to admin");
                                                //type
                                            } 
                                           
                              
                                  }


                              }catch(e)
                              {
                               console.info(msg);
                              }
                            });

        });


   function changePassword(){
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

          $.ajax({
                  type: "POST",
                  url: "../../Facade",
                  data: JSON.stringify(json),
                  contentType: "application/json; charset=utf-8",
                  dataType: "json"

                }).done(function( response ) {

                  try{
                  console.info(response);
                  
                  if(response.changePassword=="true")
                      {
                          alert("Cambio exitoso");
                          
                      }
                  else
                      {
                        $(".contentM").html(response.cause);
                         $( "#dialog" ).dialog("open");  
                          
                      }
                 


                  }catch(e)
                  {
                   console.info(response);
                  }
                });


   }
   
   
   function lostPassword()
   {
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

          $.ajax({
                  type: "POST",
                  url: "../../Facade",
                  data: JSON.stringify(json),
                  contentType: "application/json; charset=utf-8",
                  dataType: "json"

                }).done(function( response ) {

                  try{
                  console.info(response);
                  
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

                  }catch(e)
                  {
                   console.info(response);
                  }
                });
       
   }





});



