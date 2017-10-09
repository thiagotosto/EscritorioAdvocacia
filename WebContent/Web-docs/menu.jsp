<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
if(session.getValue("loginUsuario") != null || session.getValue("senhaUsuario") != null)
{
   out.println("Você está logado com sucesso no sistema, por isso consegue ver está pagina. Seu login é: " + session.getValue("loginUsuario") + " e sua senha: " + session.getValue("senhaUsuario") + ". Clique <a href='logoff.jsp'>aqui</a> para sair do sistema");
} else {
   out.println("Você não está logado no sistema. Clique <a href='index.jsp'>aqui</a> para logar-se");
}
%>
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
</body>
</html>