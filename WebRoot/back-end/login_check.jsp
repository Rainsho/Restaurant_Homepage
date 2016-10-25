<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.restaurant.entity.User"%>
<%
	User user = (User) session.getAttribute("LOGINED_USER");
	if (user == null) {
		response.sendRedirect("/Restaurant/back-end/login.jsp");
	}
%>

