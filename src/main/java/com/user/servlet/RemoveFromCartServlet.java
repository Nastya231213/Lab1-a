package com.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Cart;


@WebServlet("/remove_from_cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Object cart=request.getSession().getAttribute("cart");
        int id=Integer.parseInt(request.getParameter("id"));  
	    
        Cart shoppingCart =(Cart) request.getSession().getAttribute("cart");
	    shoppingCart.deleteItem(id);
	    
	    response.sendRedirect("cart.jsp");
        
	}
}
