<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escritorio Advocacia</title>
	</head>
	
	<body class="container">
		<p align="center"><b><font size="4"><u>Escritorio de Advocacia</u></font></b>
		<p>&nbsp;</p>
		<p><font color="#FF0000"><b>Digite o login:</b></font></p>
		<p>&nbsp;</p>
		
		<form class="col s12" name="form1" method="post" action="logar.jsp">
		<div class="row">
			<div class="input-field col s12">
				<input name="login" type="text" id="login">
				<label for="login">Login</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<%
					if (session.getAttribute("validate") == "invalid") {
						out.println("<input id='senha' class='invalid' name='senha' type='password' id='senha'>");
						out.println("<label class='active' for='senha'>Senha</label>");
					} else {
						out.println("<input name='senha' type='password' id='senha'>");
						out.println("<label class='active' for='senha'>Senha</label>");
					}
				%>
			</div>
		</div>
			
		<input type="submit" name="Submit" value="Logar">
		</form>
		
		<p>&nbsp;</p>
	
		<!--  Scripts-->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
	
 		
</html>