<%@ page import="java.sql.*" %>
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>

<html><body>
<b>Dados da Tabela Pessoa</b><br><br>
<table border="1">
<tr><td width="40">CÓDIGO</td><td width="100">NOME</td><td width="100">CPF</td></tr>

<%
PessoaDAO pdao = new PessoaDAO();
pdao.conexaoBD();
Pessoa[] p = pdao.consultaTodos();
for (int i = 0; i < p.length; i++)
  	{
  		out.println ("<tr><td>" + p[i].getCodigo()+ "</td><td>" + p[i].getNome() + 
  				     "</td><td>" + p[i].getCpf() + "</td></tr>");
  	}
%>

</table>

</body></html>