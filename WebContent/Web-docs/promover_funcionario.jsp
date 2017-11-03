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
		<link rel="stylesheet" type="text/css" href="/Escritorio Advocacia/WebContent/materialize/css/materialize.css">
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
		
		<%
			Advogado[] advogados = AdvogadoAPI.verTodos();
			MotoBoy[] motoboys = MotoBoyAPI.verTodos();
			Secretaria[] secretarias = SecretariaAPI.verTodos();
		%>
		
		
		<div class="container">
			<p>&nbsp;</p>
			<h4 class="teal-text lighten-1">Promover funcionario</h4>
			
			<!-- COLLAPSIBLE -->
			<ul class="collapsible" data-collapsible="expandable">
				<%				
					out.println("<li>"
				+		"<div class='collapsible-header'><i class='material-icons'>filter_drama</i>Advogados</div>"
				+		"<div class='collapsible-body'><span>"
				+			"<ul class='collection'");
					//cada advogado
					for (int i = 0; i < advogados.length; i++) {	
						out.println("<li class='collection-item avatar'"
								+		"<span class='title'>"+ advogados[i].getNome() +"</span>"
								+		"<p>" + advogados[i].getLogin()
								+ 		"<br>"+ advogados[i].getMatricula() +"</p>"
								+		"<a href='' onclick='' class='secondary-content'><i class='material-icons'>group_add</i></a>"
								+	"</li>");
					}
					
					//cada motoboy
					for (int i = 0; i < motoboys.length; i++) {	
						out.println("<li class='collection-item avatar'"
								+		"<span class='title'>"+ motoboys[i].getNome() +"</span>"
								+		"<p>" + motoboys[i].getLogin()  
								+ 		"<br>"+ motoboys[i].getMatricula() +"</p>"
								+		"<a href='' onclick='' class='secondary-content'><i class='material-icons'>group_add</i></a>"
								+	"</li>");
					}
					
					//cada secretaria
					for (int i = 0; i < secretarias.length; i++) {	
						out.println("<li class='collection-item avatar'"
								+		"<span class='title'>"+ secretarias[i].getNome() +"</span>"
								+		"<p>" + secretarias[i].getLogin()
								+		"<br>"+ secretarias[i].getMatricula() +"</p>"
								+		"<a href='' onclick='' class='secondary-content'><i class='material-icons'>group_add</i></a>"+		"<a href='' onclick='' class='secondary-content'><i class='material-icons'>grade</i></a>"
								+	"</li>");
					}
						//fechando collection
						out.println("</ul>"	
				+	"</span></div>"
				+	"</li>");
				 %>
				
			</ul> <!-- fechando collapsible -->
          
		</div>	
		<!--  Scripts-->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>