		
$(function(){

			$(document).on("click","button#save",function(){ 
				
				 var name=$("input#name").val();
				 var surname=$("input#surname").val();
				 var phone=$("input#phone").val();
				 var recaptcha_challenge_field=$("#recaptcha_challenge_field").val();
				 var recaptcha_response_field=$("#recaptcha_response_field").val();
				 
				 var error=0;

				 
				 if(name==""){
					 
					 $("label#stateName").html("First Name field must be filled!");
					 
					 error=1; 
				 }
				 if(surname==""){
					 
					 $("label#stateSurName").html("Last Name field must be filled!");
					 
					 error=1; 
				}
				 
				 if(recaptcha_response_field==""){
					 
					 $("label#stateCaptcha").html("Captcha field must be filled!");
					 
					 error=1;
				 }

				 
				 
				
				if(error==0) {
					
				$("div#addResult").html("<img src=\"resources/images/loading.gif\" style=\"max-width:50px;max-height:50px;margin-left:250px;\"/>");

				setTimeout(
				  function() 
				  {
				  
					$.ajax({
						url:"ajaxtest.htm",
						data:{name:name,
							  surname:surname,
							  phone:phone,
							  recaptcha_challenge_field:recaptcha_challenge_field,
							  recaptcha_response_field:recaptcha_response_field},
						success:function(response){
							
							$("div#addResult").html(response);
							
							setTimeout(function(){location.reload();},2000);
							
						}
					});
				
				  }, 2000);
				
				
				
				 }
			});
			
			$(document).on("click","a.delete",function(){
				
				var id=this.id;
				
				$("a#delete").click(function(){
					
				$("span#deleteResult").html("<img src=\"resources/images/loading.gif\" style=\"max-width:50px;max-height:50px;\"/>");	

				setTimeout(
						  function() 
						  {
				$.ajax({
					url:"delete.htm",
					data:{id:id},
					success:function(response){
						$("span#deleteResult").addClass("label label-success");
						$("span#deleteResult").css("margin-left","180px");
						$("span#deleteResult").html("<i class=\"fa fa-check\"></i> "+response);
						
						setTimeout(function(){location.reload();},2000);
					}
				});//ajax
				
				},2000);
				
				
				
				});//delete confirm click
			});//delete click
			
			$(document).on("click","a.edit",function(){
				
				var id=this.id;

				$.ajax({
					url:"edit.htm",
					data:{id:id},
					success:function(response){
						//alert(response);
						$("div#editPerson").html(response); 
						
					}
				});

				
				//update person
				
				$(document).on("click","button#update",function(){
					
					 var name=$("input#ename").val();
					 var surname=$("input#esurname").val();
					 var phone=$("input#ephone").val();

					
					$("div#updateResult").html("<img src=\"resources/images/loading.gif\" style=\"max-width:50px;max-height:50px;margin-left:250px;\"/>"); 
					
					setTimeout(
							  function() 
							  {
					
					$.ajax({
						url:"update.htm",
						data:{id:id,name:name,surname:surname,phone:phone},
						success:function(response){
							//alert(response);
							$("div#updateResult").html(response);
							setTimeout(function(){location.reload();},2000);
							
						}
					});
					
					}, 2000);
					
				});
				
				
			});
			
	//phone input mask
			
	$("input#phone").mask("(999) 999?-99-99");
	
	$("input#ephone").mask("(999) 999?-99-99");

	$("input#phone,input#ephone").on("blur", function() {
			    var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

			    if( last.length == 3 ) {
			        var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
			        var lastfour = move + last;
			        var first = $(this).val().substr( 0, 9 );

			        $(this).val( first + '-' + lastfour );
			    }
			});		
			


		});