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
import com.entity.Order;

/**
 * Servlet implementation class AdminEditOrderServlet
 */
@WebServlet("/admin/edit_order")
public class AdminEditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         String name=request.getParameter("fullName");
	         String email=request.getParameter("email");
	         String phoneNum=request.getParameter("phoneNum");
	         String address=request.getParameter("address");
	         String city=request.getParameter("city");
	         String state=request.getParameter("state");
	         String status=request.getParameter("status");
	         int orderId=Integer.parseInt(request.getParameter("id"));
	         
	         OrderDAO orderDAO=new OrderDAO(DBConnect.getConn());
	         Order order=orderDAO.getOrderById(orderId);
	         order.setFullName(name);
	         order.setEmail(email);
	         order.setPhone(phoneNum);
	         order.setAddress(address);
	         order.setCity(city);
	         order.setState(state);
	         order.setStatus(status);
	         boolean flag=orderDAO.EditOrder(order);
	         HttpSession session=request.getSession();
	         if(flag) {
	        	 session.setAttribute("succMsg", "The order was updated!");
	         }else {
	        	 session.setAttribute("errorMsg", "Something wrong on server .. The order wasn't updated");
	         }
	         orderDAO.closeConnection();
	         response.sendRedirect("orders.jsp");
	         
	         
	}

}
