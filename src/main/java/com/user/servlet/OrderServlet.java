package com.user.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.OrderDAO;
import com.entity.Cart;
import com.entity.Order;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String fullName = request.getParameter("fullName");
		Double price = (Double) session.getAttribute("total");
		String phone = request.getParameter("phoneNum");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int userId = Integer.parseInt(request.getParameter("id"));

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date date = new Date();
		String formatDate = formatter.format(date);

		Order order = new Order(email, address, fullName, city, price, phone, formatDate, state,userId);
		OrderDAO dao = new OrderDAO(DBConnect.getConn());
		dao.insertNewOrder(order);
		Order order_=dao.getLastOrderByUserid(userId);
		
		Cart cart=(Cart) session.getAttribute("cart");
		dao.insertAllItemsFromTheCart(cart, order_.getOrderId());
		response.sendRedirect("/GamesCollection");

	}
}
