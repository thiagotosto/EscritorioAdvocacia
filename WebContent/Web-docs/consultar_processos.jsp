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
			<h4 class="teal-text lighten-1">Processos</h4>
			<% 
				Processo[] processos = ProcessoAPI.mostraProcessos(perfil);
				
				out.println("<ul class='collapsible' data-collapsible='accordion'>");
			
				for (int i = 0; i < processos.length; i++) {
					out.println("<li>"
				    +			  	"<div class='collapsible-header'><i class='material-icons'>description</i>"+ processos[i].getDescricao() +"</div>"
				    +  				"<div class='collapsible-body'><span><p>Numero: "+ processos[i].getNumero() +"</p>"
				    +				"<br><p>Clientes: ");
				    for (int j = 0; j < processos[i].getClientes().length; j++) {
				    	if (j < processos[i].getClientes().length - 1) {
				    		out.println(processos[i].getClientes()[j].getNome() + ", ");
				    	} else {
				    		out.println(processos[i].getClientes()[j].getNome() + "</p>");
				    	}
					}		    
				    out.println(		"</span></div>"
				    +					"<div class='collapsible-body'><span>"
				    +					"<div class='row'>"
					+						"<div class='col s1'>"						
				    +							"<form id='consultar-documentos"+ processos[i].getNumero() +"' action='consultar_documentos.jsp'>"
					+								"<input id='processo' name='processo' type='hidden' value='"+ processos[i].getNumero() +"'>"
					+								"<a style='display:block;width:10px;' class='teal-text lighten-1' href='javascript:{}' onclick=\"document.getElementById('consultar-documentos"+ processos[i].getNumero() +"').submit();\"><i class='material-icons'>folder</i></a>"
					+							"</form>"
					+						"</div>"
					+						"<div class='col s11'>"
					+							"<form id='deletar-processo"+ processos[i].getNumero() +"' action='deleta_processo.jsp'>"
					+								"<input id='processo-deletado' name='processo-deletado' type='hidden' value='"+ processos[i].getNumero() +"'>"
					+								"<a style='display:block;width:10px;' class='teal-text lighten-1' href='javascript:{}' onclick=\"document.getElementById('deletar-processo"+ processos[i].getNumero() +"').submit();\"><i class='material-icons'>delete</i></a>"
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