<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>
<%@ page import="API.*" %>   
<%@ page import="utils.*" %>   
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.DateFormat" %>

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
		
		
		<!-- barra de navega��o -->
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
				//intanciando TarefaDAO e conectando ao banco
				TarefaDAO pdao = new TarefaDAO();
				pdao.conexaoBD();
				
				
				//criando e populando novo Tarefa
				Tarefa tarefa = new Tarefa();
				tarefa.setNome(request.getParameter("nome")); //pegando funcionario pelo id passado Criador
				tarefa.setCriador(fdao.consultaPorId(Integer.parseInt(request.getParameter("criador")))); //pegando funcionario pelo id passado Criador
				tarefa.setOwner(fdao.consultaPorId(Integer.parseInt(request.getParameter("select")))); //pegando funcionario pelo id passado Owner
				tarefa.setDescricao(request.getParameter("descricao"));
				tarefa.setExprData(Utils.parseDate(request.getParameter("dataExpr"))); //data de expira��o passada pelo usuario
				
				Date newDate = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				tarefa.setExpdData(dateFormat.format(newDate)); //data de expedi��o autom�tica
				
				
				TarefaAPI.cadastrarTarefa(tarefa);
				
			%>
			
			out.println("<script>document.location.href='consultar_tarefa.jsp'</script>");
		</div>	
		<!--  Scripts-->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>