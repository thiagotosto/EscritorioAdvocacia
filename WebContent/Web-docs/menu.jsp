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
			<% 
			//opções do menu
			String[] opcoes_funcionario = {"Ver perfil", "Alterar senha", "Tarefas"};
			String[] opcoes_advogado = {"Processos", "Clientes"};
			String[] opcoes_gerente = {"Gerir funcionários", "Ver gerentes"};	
		
			
			//coletando privilegios
			String[] privilegios = (String[]) session.getAttribute("privilegios");
			System.out.println(privilegios);
			
			//COMPONDO OPÇÕES
			
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
			
			System.out.println(perfil.getLogin());
			
			String ocupacao = MainMenuAPI.descobreOcupacao(perfil.getLogin());
			
			//criando lista de opções
			out.println("<ul>");
			
			
			//menu popout
			out.println("<ul class='collapsible popout' data-collapsible='accordion'>");
			
			for (int i = 0; i < opcoes_perfil.length; i++) {
				
				if (opcoes_perfil[i] == "Ver perfil") {
					
					out.println("<li><div class='collapsible-header'><i class='material-icons'>face</i>" + opcoes_perfil[i] + "</div>");
					out.println("<div class='collapsible-body'><span><p>"+ perfil.getNome() +"</p><br><p>Cargo: "+ ocupacao +"</p><p>Login: "+ perfil.getLogin() +"</p><p>Matricula: "+ perfil.getMatricula() +"</p></span></div></li>");
				
				} else if (opcoes_perfil[i] == "Alterar senha") { //MUDAR SENHA!!!!
				
					//header
					out.println("<li><div class='collapsible-header'><i class='material-icons'>vpn_key</i>" + opcoes_perfil[i] + "</div>");
					
					//conteudo
					out.println("<div class='collapsible-body'><span><form action='mudar_senha.jsp'>");
						out.println("<div class='input-field'>");
							out.println("<input id='password1' name='password1' type='password'>");
							out.println("<label for='password1'>Senha nova</label>");
						out.println("</div>");
									
						out.println("<div class='input-field'>");
							if (session.getAttribute("validate-mudarsenha") == "invalid") {
								out.println("<input id='password2' name='password2' type='password' class='invalid'>");
							} else {
								out.println("<input id='password2' name='password2' type='password'>");
							}
							out.println("<label for='password2'>Repita senha</label>");
						out.println("</div>");
									
						out.println("<button class='btn waves-effect waves-light' type='submit' name='action'>alterar ");
					    		out.println("<i class='material-icons' right>send</i>");
					  	out.println("</button>");
									
					out.println("</form></span></div></li>");
				
				} else if (opcoes_perfil[i] == "Tarefas") {
					out.println("<li><div class='collapsible-header'><i class='material-icons'>event_note</i>" + opcoes_perfil[i] + "</div>");
					out.println("<div class='collapsible-body'><span>"
					+  	"<div class='collection'>"
			        +		"<a href='consultar_tarefa.jsp' class='collection-item'>Consultar tarefa</a>"
			        +		"<a href='criar_tarefa.jsp' class='collection-item'>Criar tarefa</a>"
			      	+	"</div>"

					+ "</span></div></li>");
					
				} else if (opcoes_perfil[i] == "Processos") {
					out.println("<li><div class='collapsible-header'><i class='material-icons'>description</i>" + opcoes_perfil[i] + "</div>");
					out.println("<div class='collapsible-body'><span>"
					+  	"<div class='collection'>"
					+		"<a href='consultar_processos.jsp' class='collection-item'>Consultar processo</a>"
					+		"<a href='cadastrar_processo.jsp' class='collection-item'>Cadastrar processo</a>"
				  	+	"</div>"

					
					+	"</span></div></li>");
				} else if (opcoes_perfil[i] == "Clientes") {
					out.println("<li><div class='collapsible-header'><i class='material-icons'>people</i>" + opcoes_perfil[i] + "</div>");
					out.println("<div class='collapsible-body'><span>"
					+  	"<div class='collection'>"
					+		"<a href='consultar_cliente.jsp' class='collection-item'>Consultar clientes</a>"
					+		"<a href='consultar_meus_clientes.jsp' class='collection-item'>Consultar meus clientes</a>"
					+		"<a href='cadastrar_cliente.jsp' class='collection-item'>Cadastrar cliente</a>"
					+	"</div>"
					
					+	"</span></div></li>");
				} else if (opcoes_perfil[i] == "Gerir funcionários") {
					out.println("<li><div class='collapsible-header'><i class='material-icons'>group_work</i>" + opcoes_perfil[i] + "</div>");
					out.println("<div class='collapsible-body'><span>"
							+  	"<div class='collection'>"
							+		"<a href='promover_funcionario.jsp' class='collection-item'>Promover funcionário</a>"
							+		"<a href='rebaixar_funcionario.jsp' class='collection-item'>Rebaixar funcionário</a>"
							+		"<a href='admitir_funcionario.jsp' class='collection-item'>Admitir funcionário</a>"
							+		"<a href='demitir_funcionario.jsp' class='collection-item'>Demitir funcionário</a>"
							
						  	+	"</div>"

							
							+	"</span></div></li>");
				} else if (opcoes_perfil[i] == "Ver gerentes") {
					
					//trazendo todos os gerentes
					Gerente[] gerentes= GerenteAPI.mostraTodosGerentes();
					
					out.println("<li><div class='collapsible-header'><i class='material-icons'>assignment_ind</i>" + opcoes_perfil[i] + "</div>");
					out.println("<div class='collapsible-body'><span>"
							+  	"<ul class='collection'>");
							
							for (int j = 0; j < gerentes.length; j++) {
								out.println(" <li class='collection-item avatar'>"
									     	+ 	"<img src='images/yuna.jpg' alt='' class='circle'>"
							      			+	"<span class='title'>"+ gerentes[j].getNome() +"</span>"
							      			+	"<p>"+ gerentes[j].getLogin() +"<br>"
							         		+		gerentes[j].getMatricula()
							      			+	"</p>"
							    			+	"</li>");
							}
						  	out.println("</div>"

							
							+	"</span></ul></li>");
				}
			}
			
			out.println("</ul>");
			%>
			<!-- 
			<ul class="collapsible popout" data-collapsible="accordion">
				<li>
					<div class='collapsible-header'><i class='material-icons'>filter_drama</i>First</div>
					<div class='collapsible-body'><span>Lorem ipsum dolor sit amet.</span></div>
				</li>
			    <li>
			    	<div class="collapsible-header"><i class="material-icons">place</i>Second</div>
			      	<div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
			    </li>
			    <li>
			      	<div class="collapsible-header"><i class="material-icons">whatshot</i>Third</div>
			      	<div class="collapsible-body"><span>Lorem ipsum dolor sit amet.</span></div>
			 	</li>
			 </ul>	
			-->
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
		</div>	
		<!--  Scripts-->
		<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
		<script src="../materialize/js/materialize.js"></script>
	</body>
</html>