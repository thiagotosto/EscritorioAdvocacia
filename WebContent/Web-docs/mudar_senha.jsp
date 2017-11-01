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
			//instanciando FuncionarioDAO e conectando no banco
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.conexaoBD();
			
			//colhendo tentativas
			String senha1 = request.getParameter("password1");
			String senha2 = request.getParameter("password2");
			String senhaNova;
			
			if (senha1 != "") {
				if (senha1.equals(senha2)) {
					senhaNova = senha1;
					
					//colhendo perfil
					Funcionario perfil = fdao.consultaPorLogin((String) session.getAttribute("perfil_login"));
					
					FuncionarioAPI.mudarSenha(perfil, senhaNova);
					out.println("<script>document.location.href='menu.jsp';</script>");
					session.setAttribute("validate-mudarsenha", "valid");
				} else {
					session.setAttribute("validate-mudarsenha", "invalid");
					out.println("<script>document.location.href='menu.jsp';</script>");
				}
			} else {
				session.setAttribute("validate-mudarsenha", "invalid");
				out.println("<script>document.location.href='menu.jsp';</script>");
			}
			
			
		%>
	</body>
</html>