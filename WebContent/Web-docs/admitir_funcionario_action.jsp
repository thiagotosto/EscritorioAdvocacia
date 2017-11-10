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
			
			String cargo = request.getParameter("cargo");
			System.out.println(cargo);
			
			
			if (cargo.equals("advogado")) {
				
				//instanciando advogado novo
				Advogado advogado = new Advogado();
				
				//populando advogado novo
				advogado.setNome(request.getParameter("nome"));
				advogado.setLogin(request.getParameter("login"));
				advogado.setSenha(request.getParameter("senha"));
				advogado.setOab(request.getParameter("oab"));
				advogado.setMatricula(Utils.geraMatricula());
				
				//persistindo no banco
				AdvogadoAPI.admitirAdvogado(advogado);
				
			} else if (cargo.equals("motoboy")) {
				System.out.println("Passei aqui em motoboy");
				
				//instanciando novo motoboy
				MotoBoy motoboy = new MotoBoy();
				
				//populando motoboy novo 
				motoboy.setNome(request.getParameter("nome"));
				motoboy.setLogin(request.getParameter("login"));
				motoboy.setSenha(request.getParameter("senha"));
				motoboy.setMatricula(Utils.geraMatricula());
				
				//persisitindo no banco
				MotoBoyAPI.admitirMotoBoy(motoboy);
				
			} else if (cargo.equals("secretaria")) {
				
				//instanciando novo secretaria
				Secretaria secretaria = new Secretaria();
				
				//populando motoboy novo 
				secretaria.setNome(request.getParameter("nome"));
				secretaria.setLogin(request.getParameter("login"));
				secretaria.setSenha(request.getParameter("senha"));
				secretaria.setMatricula(Utils.geraMatricula());
				
				//persisitindo no banco
				SecretariaAPI.admitirSecretaria(secretaria);
				
			}
			
			%>
			
			out.println("<script>document.location.href='promover_funcionario.jsp'</script>");
		</div>	
		<!--  Scripts-->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>