<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p align="center"><b><font size="4"><u>Exemplo de uso com o JSP</u></font></b>
<p>&nbsp;</p>
<p><font color="#FF0000"><b>Digite o login:</b></font></p>
<p>&nbsp;</p>

<form name="form1" method="post" action="logar.jsp">
Login:
<input name="login" type="text" id="login">
Senha:
<input name="senha" type="password" id="senha">
<input type="submit" name="Submit" value="Logar">
</form>

<p>&nbsp;</p>
</body>
</html>