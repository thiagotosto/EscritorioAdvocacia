<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<p><b><font size="5">Alteração de Dados</font></b></p>
<p>&nbsp;</p>
<% 

  int cod = Integer.parseInt(request.getParameter("codigo"));
  PessoaDAO pdao = new PessoaDAO();
  pdao.conexaoBD();
  Pessoa p = pdao.consultaPorCodigo(cod);
%>
<form method="POST" action="altera_dados.jsp?codigo=<% out.println (cod); %>"> 
 <p>Nome</p>
  <p><input type="text" name="NOME" size="67" value="<%out.println (p.getNome());%>"></p>
  <p>CPF</p>
  <p><input type="text" name="CPF" size="67" value="<%out.println (p.getCpf());%>"></p>
  <p><input type="submit" value="Salvar Alterações" name="B1">&nbsp;&nbsp;&nbsp; <input type="reset" value="Redefinir" name="B2"></p>
</form>
<p>&nbsp;</p>

</body>
</html>