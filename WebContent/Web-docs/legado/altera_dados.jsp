<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%

Pessoa p = new Pessoa();
p.setCodigo(Integer.parseInt(request.getParameter("codigo")));
p.setNome(request.getParameter("NOME"));
p.setCpf(request.getParameter("CPF"));
PessoaDAO pdao = new PessoaDAO();
pdao.atualizar(p);

// Retorno
out.println("<SCRIPT language='JavaScript'>");
//if (res > 0) {
  out.println("alert('REGISTRO ALTERADO COM ÊXITO');");
//} else {
//  out.println("alert('FALHA NA ALTERAÇÃO');");
//}
out.println("history.go(-2);");
out.println("</SCRIPT>");

%>
</body>
</html>