package com.restaurant.filter;

import java.util.HashSet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LoginedCheck implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		@SuppressWarnings("unchecked")
		HashSet<HttpSession> session_set = (HashSet<HttpSession>) se
				.getSession().getServletContext().getAttribute("session_set");
		if (session_set == null) {
			session_set = new HashSet<HttpSession>();
		}
		session_set.add(se.getSession());
		se.getSession().getServletContext()
				.setAttribute("session_set", session_set);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		@SuppressWarnings("unchecked")
		HashSet<HttpSession> session_set = (HashSet<HttpSession>) se
				.getSession().getServletContext().getAttribute("session_set");
		session_set.remove(se.getSession());
		se.getSession().getServletContext()
				.setAttribute("session_set", session_set);
		
	}

}
