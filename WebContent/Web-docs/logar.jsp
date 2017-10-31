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

if (mm.login(login,senha)){
	session.putValue("MainMenu", mm);
	out.println("<script>document.location.href='menu.jsp';</script>");
} else {
	session.putValue("validate", "invalid");
	out.println("<script>document.location.href='index.jsp';</script>");
}
	
/*
if (p == null)
{
	out.println("<SCRIPT language='JavaScript'>");
	out.println("alert('LOGIN INEXISTNTE');");
	out.println("history.go(-1);");
	out.println("</SCRIPT>");
}
else 
{
  if (!p.getSenha().equals(senha))
  {
	  out.println("<SCRIPT language='JavaScript'>");
	  out.println("alert('SENHA INVÁLIDA');");
	  out.println("history.go(-1);");
	  out.println("</SCRIPT>");
  }
  else 
  { 
	  session.putValue("loginUsuario", login);
	  session.putValue("senhaUsuario", senha);
	  out.println("<script>document.location.href='menu.jsp';</script>");
  }
}*/
	
	
%>
</body>
</html>