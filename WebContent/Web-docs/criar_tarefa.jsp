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
		<title>Escritório de Advocacia</title>
	</head>
	<body>
		
		
		<!-- barra de navegação -->
		<nav class="teal lighten-1">
		   	<div class="container nav-wrapper">
		      	<a href="menu.jsp" class="brand-logo">Escritório de Advocacia</a>
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
			<h4 class="teal-text lighten-1">Nova tarefa</h4>
			<% 			
				out.println("<div class='row'>"
				+		"<form class='col s12' action='criar_tarefa_action.jsp'>"
				+			"<input name='criador' type='hidden' value='"+ perfil.getId() +"'>"
				+	    	"<div id='div-pai' class='row'>"
				+	      		"<div class='input-field col s12'>"
				+	        		"<input  id='nome' name='nome' type='text'>"
				+	        		"<label for='nome'>Nome</label>"
				+	        	"</div>"
				+			"</div>"
				+			"<div class='row'>"
				+	        	"<div class='input-field col s12'>"
				+	          		"<input id='dataExpr' class='datepicker' name='dataExpr' type='text'>"
				+	          		"<label for='dataExpr'>Data Expiração</label>"
				+	        	"</div>"
				+	      	"</div>"			
				+			"<div class='row'>"
				+	        	"<div class='input-field col s12'>"
				+	          		"<textarea id='descricao' name='descricao' class='materialize-textarea'></textarea>"
				+	          		"<label for='descricao'>Descrição</label>"
				+	        	"</div>"
				+	      	"</div>");
          	%>
          	
					      	<div class="input-field col s12">
							    <select id="select" name="select">
							      <%				          		
					          				
					          		Funcionario[] funcionarios = fdao.consultaTodos();		
							      	
					          		for (int i = 0; i < funcionarios.length; i++) {
					          			out.println("<option value='"+ funcionarios[i].getId() +"'>"+ funcionarios[i].getNome() +"</option>");
					          		}
							      %>
							    </select>
						      	<label>Responsável</label>
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
			    $('select').material_select();
			    
			    $('.datepicker').pickadate({
			        selectMonths: true, // Creates a dropdown to control month
			        selectYears: 15, // Creates a dropdown of 15 years to control year,
			        today: 'Today',
			        clear: 'Clear',
			        close: 'Ok',
			        closeOnSelect: false // Close upon selecting a date,
			      });
			});
		</script>
		
		
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>