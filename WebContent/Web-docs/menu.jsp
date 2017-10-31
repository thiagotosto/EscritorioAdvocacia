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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escritorio Advocacia</title>
	</head>
	<body>
		<% 
		String[] opcoes_funcionario = {"Ver perfil", "Mudar senha", "Tarefas"};
		String[] opcoes_advogado = {"Processos", "Clientes"};
		String[] opcoes_gerente = {"Gerir funcionários", "Ver gerentes"};	
		String[] sair = {"Sair"};
		
		//coletando privilegios
		String[] privilegios = (String[]) session.getAttribute("privilegios");
		System.out.println(privilegios);
		
		//opções do perfil
		String[] opcoes_perfil;
		
		//adicionando opções básicas de funcionarios
		opcoes_perfil = opcoes_funcionario;
		
		//adicionando opçóes de advogado se for advogado
		if (privilegios[1] == "Advogado") {
			opcoes_perfil = Utils.joinArray(opcoes_perfil, opcoes_advogado);
		}
		
		//adicionando opções de gerente se for gerente
		if (privilegios[0] == "Gerente") {
			opcoes_perfil = Utils.joinArray(opcoes_perfil, opcoes_gerente);
		}
		
		//adicionando a opçao sair
		opcoes_perfil = Utils.joinArray(opcoes_perfil, sair);
		
		
		//criando lista de opções
		out.println("<ul>");
		
		
		for (int i = 0; i < opcoes_perfil.length; i++) {
			out.println("<li>"+ opcoes_perfil[i] +" </li>");
		}
		
		out.println("</ul>");
		%>
		
		
		
		<!-- 
		<p align="center"><b><font size="4"><u>Exemplo de uso com o JSP</u></font></b>
		<p>&nbsp;</p>
		<p><font color="#FF0000"><b>Escolha a opção:</b></font></p>
		<p>&nbsp;</p>
		
		<ol>
			<li><a href="consulta.jsp">Consultar</a></li>
			<li><a href="inclui.jsp">Incluir</a></li>
			<li><a href="altera.jsp">Alterar ou Excluir</a></li>
			<li><a href="logoff.jsp">Sair</a></li>
		</ol>
		
		<p>&nbsp;</p>
		-->
		
		<!--  Scripts-->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>