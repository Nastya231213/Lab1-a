package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterCart
 */
@WebFilter("/cart.jsp")
public class FilterCart extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request_=(HttpServletRequest) request;
		HttpServletResponse response_=(HttpServletResponse)response;
        HttpSession session=request_.getSession();
		Object cart=(Object)session.getAttribute("cart");
		Object user=(Object)request_.getSession().getAttribute("userObj");

		if(cart==null) {
			String page="/GamesCollection/";
			session.setAttribute("errorMsg", "The cart is empty!");
			response_.sendRedirect(page);
		}else if(user==null) {
			String page="/GamesCollection/login";
			response_.sendRedirect(page);
		}else {
		chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
