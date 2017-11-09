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
			    		//instanciando GerenteDAO e AdvogadoDAO e conectando no banco
						GerenteDAO gdao = new GerenteDAO();
			    		AdvogadoDAO adao = new AdvogadoDAO();
						gdao.conexaoBD();
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
			<h4 class="teal-text lighten-1">demitir funcionário</h4>
			
			<form id="demitir-funcionario" action="demitir_funcionario_action.jsp"> 
				<input id="funcionario-demitido" name="funcionario-demitido" type="hidden">
			
				<!-- COLLAPSIBLE -->
				<ul class="collapsible" data-collapsible="expandable">
					
							
						<%				
							out.println("<li>"
						+		"<!-- ADVOGADOS -->"
						+		"<div class='collapsible-header'><i class='material-icons'>gavel</i>Advogados</div>"
						+		"<div class='collapsible-body'><span>"
						+			"<ul class='collection'>");
							//cada advogado
							for (int i = 0; i < advogados.length; i++) {	
								out.println("<li class='collection-item avatar'>"
										+		"<span class='title'>"+ advogados[i].getNome() +"</span>"
										+		"<p>" + advogados[i].getLogin()
										+ 		"<br>"+ advogados[i].getMatricula() +"</p>");
										if (gdao.consultaPorLogin(advogados[i].getLogin()) != null) {
											out.println("<a href='javascript:{}' onclick='passademitido(\""+ advogados[i].getLogin() +"\");' class='secondary-content'><i class='material-icons'>highlight_off</i></a>");
										}
										out.println("</li>");
							}
							out.println("</ul>"
							+		"</span></div>"
							+	"</li>");
						
						out.println("<li>"
						+		"<!-- MOTOBOYS -->"
						+		"<div class='collapsible-header'><i class='material-icons'>motorcycle</i>Motoboys</div>"
						+		"<div class='collapsible-body'><span>"
						+			"<ul class='collection'>");
							//cada motoboy
							for (int i = 0; i < motoboys.length; i++) {	
								out.println("<li class='collection-item avatar'>"
										+		"<span class='title'>"+ motoboys[i].getNome() +"</span>"
										+		"<p>" + motoboys[i].getLogin()  
										+ 		"<br>"+ motoboys[i].getMatricula() +"</p>");
										if (gdao.consultaPorLogin(motoboys[i].getLogin()) != null) {							
											out.println("<a href='javascript:{}' onclick='passademitido(\""+ motoboys[i].getLogin() +"\");' class='secondary-content'><i class='material-icons'>highlight_off</i></a>");
										}
										out.println("</li>");
							}
							out.println("</ul>"
							+		"</span></div>"
							+	"</li>");
							
						
						out.println("<li>"
								+		"<!-- SECRETARIAS -->"
								+		"<div class='collapsible-header'><i class='material-icons'>phone</i>Secretarias</div>"
								+		"<div class='collapsible-body'><span>"
								+			"<ul class='collection'>");
							//cada secretaria
							for (int i = 0; i < secretarias.length; i++) {	
								out.println("<li class='collection-item avatar'>"
										+		"<span class='title'>"+ secretarias[i].getNome() +"</span>"
										+		"<p>" + secretarias[i].getLogin()
										+		"<br>"+ secretarias[i].getMatricula() +"</p>");
										if (gdao.consultaPorLogin(secretarias[i].getLogin()) != null) {
											out.println("<a href='javascript:{}' onclick='passademitido(\""+ secretarias[i].getLogin() +"\");' class='secondary-content'><i class='material-icons'>highlight_off</i></a>");
										}
										out.println("</li>");
							}
							out.println("</ul>"
							+		"</span></div>"
							+	"</li>");
						
						 %>
				
				</ul> <!-- fechando collapsible -->
          	</form>
		</div>	
		<!--  Scripts-->
		<script>
			function passademitido(logindemitido){
				Materialize.toast('Funcionario demitido', 4000);
				var input = document.getElementById("funcionario-demitido");
				var form = document.getElementById("demitir-funcionario");
				
				input.value = logindemitido;
				form.submit();
			}
		</script>
		
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	</body>
</html>