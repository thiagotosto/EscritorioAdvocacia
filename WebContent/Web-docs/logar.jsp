<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
String login = request.getParameter("login");
String senha = request.getParameter("senha");
PessoaDAO pdao = new PessoaDAO();
pdao.conexaoBD();
Pessoa p = pdao.consultaPorLogin(login);

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
}
	
	
%>
</body>
</html>