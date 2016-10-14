package com.restaurant.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {

	private String encode;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;

		if (httprequest.getMethod().toLowerCase().equals("get")) {
			request = new HttpServletRequestWrapper(httprequest) {
				@Override
				public String getParameter(String name) {
					try {
						return new String(super.getParameter(name).getBytes(
								"iso-8859-1"), encode);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
						return null;
					}
				}
			};
		} else {
			request.setCharacterEncoding(encode);
		}

		response.setCharacterEncoding(encode);
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encode = filterConfig.getInitParameter("SetEncode");
		if (encode == null) {
			encode = "UTF-8";
		}
	}

}
