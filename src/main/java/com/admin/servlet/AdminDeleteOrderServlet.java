package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.OrderDAO;

/**
 * Servlet implementation class AdminDeleteOrderServlet
 */
@WebServlet("/admin/remove_order")
public class AdminDeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId=Integer.parseInt(request.getParameter("id"));
		OrderDAO orderDAO=new OrderDAO(DBConnect.getConn());
		boolean flag=orderDAO.deleteOrder(orderId);
		HttpSession session=request.getSession();
		
	      if(flag) {
	    	  session.setAttribute("succMsg", "Successfully deleted!");
	      }else {
	    	  session.setAttribute("succMsg", "Something wrong on server..Not deleted");

	      }
	      orderDAO.closeConnection();
	      response.sendRedirect("orders.jsp");		
	}



}
