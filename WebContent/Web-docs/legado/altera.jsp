<%@ page import="java.sql.*" %>
<%@ page import="DAO.*" %>
<%@ page import="modelo.*" %>

<html><body>
<b>Dados da Tabela Pessoa</b><br><br>
<table border="1">
<tr><td width="40">CÓDIGO</td><td width="100">NOME</td><td width="100">CPF</td><td width="80">ESCOLHER</td></tr>

<%
PessoaDAO pdao = new PessoaDAO();
pdao.conexaoBD();
Pessoa[] p = pdao.consultaTodos();
for (int i = 0; i < p.length; i++)
  	{
  		out.println ("<tr><td>" + p[i].getCodigo()+ "</td><td>" + p[i].getNome() + "</td><td>" + p[i].getCpf() +   
  				     "</td><td><a href='altera_form.jsp?codigo=" + p[i].getCodigo() + "'>Alterar</a>&nbsp;&nbsp;" +
  				     "OU&nbsp;&nbsp;<a href='exclui.jsp?codigo=" + p[i].getCodigo() + "'>Excluir</a></td></tr>");
  	}
%>

</table>

</body></html>