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
//Destroi as sessions
session.invalidate();
out.println("Você saiu com sucesso do sistema. Tente ver agora a página dos \"<a href='menu.jsp'>logados</a>\" ou efetue novamente <a href='index.jsp'>login</a>");
%>
</body>
</html>