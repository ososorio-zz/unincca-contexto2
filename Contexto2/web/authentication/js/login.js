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
                                   $(".contentM").html("Welcome "+msg.userInfo.name);
                                   $( "#dialog" ).dialog("open");
                                  }
                              
                              
                              }catch(e)
                              {
                               console.info(msg);    
                              }
                            });  
            
        });
        
        
});