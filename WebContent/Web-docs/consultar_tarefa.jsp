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
		<link rel="stylesheet" type="text/css" href="../materialize/css/style.css">
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
			<p>&nbsp;</p>
			<h4 class="teal-text lighten-1">Tarefas</h4>
			<% 
				Tarefa[] tarefas = TarefaAPI.consultarTarefas(perfil);
				
				out.println("<ul class='collapsible' data-collapsible='accordion'>");
			
				for (int i = 0; i < tarefas.length; i++) {
					out.println("<li>"
				    +			  	"<div class='collapsible-header'><i class='material-icons'>event</i>"+ tarefas[i].getDescricao() +"</div>"
				    +  				"<div class='collapsible-body'><span><p>Data de Expiração: "+ tarefas[i].getExprData() +"</p><br><p>Data de Expedição: "+ tarefas[i].getExpdData() +"</p></span></div>"
				    +				"<div class='collapsible-body'><span>"
				    +					"<form id='consumir-tarefa' action='consumir_tarefa.jsp'>"
				    +						"<input id='tarefa' name='tarefa' type='hidden' value='"+ tarefas[i].getId() +"'>"
				    +						"<a class='teal-text lighten-1' href='javascript:{}' onclick=\"document.getElementById('consumir-tarefa').submit();\"I><i class='material-icons'>done</i></a>"
				    +					"</form>"
				    +				"</span></div>"
				    + 			"</li>");
				}
			
				out.println("</ul>");
			%>
          
		</div>	
		<!--  Scripts-->
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>