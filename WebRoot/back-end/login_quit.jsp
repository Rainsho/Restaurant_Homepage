<%@ page language="java" pageEncoding="UTF-8"%>
<%
	session.removeAttribute("LOGINED_USER");
	response.sendRedirect("/Restaurant/back-end/login.jsp");
%>