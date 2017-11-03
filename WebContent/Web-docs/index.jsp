<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escritorio Advocacia</title>
	</head>
	
	<body>
		<div class="">
			
			<div class="parallax-container">
      			<div class="parallax"><img src="images/advocacia.jpeg"></div>
    		</div>
		
			 <div class="section white container">
    			<div class="row">
					<h1 class="teal-text lighten-1" align="center">Escritorio de Advocacia</h1>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
			
					<form class="col s12" name="form1" method="post" action="logar.jsp">
					<div class="row">
						<div class="input-field col s12">
							<%
								if (session.getAttribute("validate-usuario") == "invalid") {
										out.println("<input class='invalid' name='login' type='text' id='login'>");
										out.println("<label class='active' for='senha'>Login</label>");
										session.setAttribute("validate-usuario", "valid");		
								} else {
										out.println("<input name='login' type='text' id='login'>");
										out.println("<label class='active' for='senha'>Login</label>");
								}
							%>
						</div>
					</div>
					<div class="row">
						<div class="input-field col s12">
							<%
								if (session.getAttribute("validate-senha") == "invalid") {
									
									out.println("<input id='senha' class='invalid' name='senha' type='password'>");
									out.println("<label class='active' for='senha'>Senha</label>");
									session.setAttribute("validate-senha", "valid");
								} else {
									out.println("<input name='senha' type='password' id='senha'>");
									out.println("<label class='active' for='senha'>Senha</label>");
								}
							%>
						</div>
					</div>
						
					<button class="btn waves-effect waves-light" type="submit" name="action">Logar
			    		<i class="material-icons right">send</i>
			  		</button>
					
					<p>&nbsp;</p>
					
					</form>
				</div>
			</div>
			
			<div class="parallax-container">
    			<div class="parallax"><img src="images/advocacia.jpg"></div>
  			</div>
			
			<!--  <p>&nbsp;</p> -->
			
		</div>
		<!--  Scripts-->
		<script type="text/javascript">
		$(document).ready(function(){
		      $('.parallax').parallax();
		});
		</script>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
	
 		
</html>