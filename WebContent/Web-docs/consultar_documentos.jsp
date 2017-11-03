<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>
<%@ page import="API.*" %>   
<%@ page import="utils.*" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
		<link rel="stylesheet" type="text/css" href="../css/style.css">
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escritorio de Advocacia</title>
	</head>
	<body>
		
		
		<!-- barra de navegação -->
		<nav class="teal lighten-1">
		   	<div class="container nav-wrapper">
		      	<a href="menu.jsp" class="brand-logo">Escritorio de Advocacia</a>
		      	<ul id="nav-mobile" class="right hide-on-med-and-down">
		        	<%
			    		//instanciando FuncionarioDAO e conectando no banco
						FuncionarioDAO fdao = new FuncionarioDAO();
						fdao.conexaoBD();
		        	
						Funcionario perfil = fdao.consultaPorLogin((String) session.getAttribute("perfil_login"));
						
		        		out.println("<li>"+ perfil.getNome() +"</li>");
		        	%>
		        	<li><a href="logout.jsp">Logout</a></li>
		    	</ul>
			</div>
		</nav>
		
		<div class="container">
			<% 			
			//instanciando ProcessoDAO e conectando no banco
			ProcessoDAO pdao = new ProcessoDAO();
			pdao.conexaoBD();
			
			System.out.println(request.getParameter("processo"));
			
			//trazendo caminho de documentos
			String[][] documentos = pdao.consultaPorNumero(Integer.parseInt(request.getParameter("processo"))).getDocumentos();
			
			//printando header da colletion
			out.println("<p>&nbsp;</p><h4 id='documentos-header' class='teal-text lighten-1'>Documentos</h4>"
			+			"<style> #documentos-header{margin-bottom: 15px;}</style>"
			+			"<div class='collection with-header'>");
			
			for (int i = 0; i < documentos.length; i++) {
				out.println("<a class='collection-item' href='"+ documentos[i][1] +"'>"+ documentos[i][2] +"</a>");
			}
			
			out.println("</div>");
			 
		        
		
			%>
			
		</div>	
		<!--  Scripts-->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>