<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    String searchterm = request.getParameter("searchbox");
    
%>

<html>
<body>
<h2>Our Search Engine</h2>
	You searched for the word: <%=searchterm%>
</body>
</html>