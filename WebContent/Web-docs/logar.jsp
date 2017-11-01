<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>
<%@ page import="API.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escritorio de Advocacia</title>
	</head>
	
	<body class="container">
		<% 
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		MainMenuAPI mm = new MainMenuAPI();
		
		
		
		if (mm.login(login,senha) == "Ok"){
			
			//descobrindo privilegios
			mm.descobrePrivilegios(login);
			
			session.setAttribute("perfil_login", mm.getPerfil().getLogin());
			session.setAttribute("privilegios", mm.getPrivilegios());
			out.println("<script>document.location.href='menu.jsp';</script>");
		
		} else if (mm.login(login,senha) == "Usuario") {
			session.setAttribute("validate-usuario", "invalid");
			out.println("<script>document.location.href='index.jsp';</script>");
		} else if (mm.login(login,senha) == "Senha") {
			session.setAttribute("validate-senha", "invalid");
			out.println("<script>document.location.href='index.jsp';</script>");
		}
			
		%>
	</body>
</html>