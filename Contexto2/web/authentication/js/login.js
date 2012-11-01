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
        
        
        $("#login").click(function(){
             $.ajax({
                              type: "POST",
                              url: "../../Facade",
                              data: $(".login-form").val()
                           
                              
                            }).done(function( msg ) {
                             // alert( "Data Saved: " + msg );
                              //console.info(msg);
                              try{
                              console.info(msg);
                              }catch(e)
                              {
                               console.info(msg);    
                              }
                            });  
            
        });
        
        
});