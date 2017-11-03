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
		
		
		<!-- barra de navega��o -->
		<nav class="teal lighten-1">
		   	<div class="container nav-wrapper">
		      	<a href="menu.jsp" class="brand-logo">Escritorio de Advocacia</a>
		      	<ul id="nav-mobile" class="right hide-on-med-and-down">
		        	<%
			    		//instanciando FuncionarioDAO e conectando no banco
						AdvogadoDAO adao = new AdvogadoDAO();
						adao.conexaoBD();
		        	
						Advogado perfil = adao.consultaPorLogin((String) session.getAttribute("perfil_login"));
						
		        		out.println("<li>"+ perfil.getNome() +"</li>");
		        	%>
		        	<li><a href="logout.jsp">Logout</a></li>
		    	</ul>
			</div>
		</nav>
		
		<div class="container">
			<p>&nbsp;</p>
			<h4 class="teal-text lighten-1">Clientes</h4>
			<% 
				Cliente[] clientes = ClienteAPI.consultarClientes(perfil);
				
				out.println("<ul class='collapsible' data-collapsible='accordion'>");
			
				for (int i = 0; i < clientes.length; i++) {
					out.println("<li>"
				    +			  	"<div class='collapsible-header'><i class='material-icons'>person</i>"+ clientes[i].getNome() +"</div>"
				    +  				"<div class='collapsible-body'><span><p>Cpf: "+ clientes[i].getCpf() +"</p>");
				    out.println(		"</span></div>"
				    +					"<div class='collapsible-body'><span>"
				    +					"<div class='row'>"
					+						"<div class='col s1'>"						
				    +							"<form id='editar-cliente"+ clientes[i].getCpf() +"' action='edita_cliente.jsp'>"
					+								"<input id='cliente' name='cliente' type='hidden' value='"+ clientes[i].getCpf() +"'>"
					+								"<a class='teal-text lighten-1' href='javascript:{}' onclick=\"document.getElementById('editar-cliente"+ clientes[i].getCpf() +"').submit();\"><i class='material-icons'>edit</i></a>"
					+							"</form>"
					+						"</div>"
					+						"<div class='col s11'>"
					+							"<form id='deletar-cliente"+ clientes[i].getCpf() +"' action='deleta_cliente.jsp'>"
					+								"<input id='cliente-deletado' name='cliente-deletado' type='hidden' value='"+ clientes[i].getCpf() +"'>"
					+								"<a class='teal-text lighten-1' href='javascript:{}' onclick=\"document.getElementById('deletar-cliente"+ clientes[i].getCpf() +"').submit();\"><i class='material-icons'>delete</i></a>"
					+							"</form>"
					+						"</div>"
					+					"</div>"
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