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
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
		
			<h4 class="teal-text lighten-1">Novo processo</h4>
			<% 
				Processo[] processos = ProcessoAPI.mostraProcessos(perfil);
			
				out.println("<div class='row'>"
				+		"<form class='col s12' action='cadastrar_processo_action.jsp'>"
				+	    	"<div id='div-pai' class='row'>"
				+	      		"<div class='input-field col s12'>"
				+	        		"<input  id='numero' name='numero' type='text'>"
				+	        		"<label for='numero'>Numero</label>"
				+	        	"</div>"
				+			"</div>"
				+			"<div class='row'>"
				+	        	"<div class='input-field col s12'>"
				+	          		"<input id='descricao' name='descricao' type='text' class='validate'>"
				+	          		"<label for='descricao'>Descrição</label>"
				+	        	"</div>"
				+	      	"</div>");
          	%>
				
					      	<div class="input-field col s12">
							    <select multiple id="select" name="select">
							      <%
							      	ClienteDAO cdao = new ClienteDAO();
							      	cdao.conexaoBD();
					          		
					          				
					          		Cliente[] clientes = cdao.consultaTodos();		
							      	
					          		for (int i = 0; i < clientes.length; i++) {
					          			out.println("<option value='"+ clientes[i].getId() +"'>"+ clientes[i].getNome() +"</option>");
					          		}
							      %>
							    </select>
							    <label>Clientes</label>
							  </div>
							  <button class="btn waves-effect waves-light" type="submit" name="action">Adicionar
	    							<i class="material-icons right">send</i>
	  						  </button>
				</form>
			</div>
			
		</div>
		<!--  Scripts-->
		
		<script type="text/javascript">
			$(document).ready(function() {
				console.log("READY");
			    $('select').material_select();
			});
			
			function addCliente() {
				var pai = document.getElementById("div-pai");
				
				var div = document.createElement("div");
				div.setAttribute("class", "input-field col s12");
				
				var input = document.createElement("input");
				input.setAttribute("id", );
				input.setAttribute("type", "text");
				
				var label = document.createElement("label");
				label.setAttribute("id", );
				label.setAttribute("for");
				label.innerHTML = "Cliente";
				
				pai.appendChild(div);
				div.appendChild(input);
				div.appendChild(label);
		 	}
		</script>
		
		
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>