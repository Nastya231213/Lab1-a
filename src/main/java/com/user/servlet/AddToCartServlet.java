package com.user.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DBConnect;
import com.dao.GameDAO;
import com.entity.Cart;
import com.entity.Game;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object cart = request.getSession().getAttribute("cart");
		String gameId = request.getParameter("gid");
		Cart shoppingCart;
		if (cart == null) {
			shoppingCart = new Cart();
			request.getSession().setAttribute("cart", shoppingCart);
		} else {
			shoppingCart = (Cart) request.getSession().getAttribute("cart");
		}

		GameDAO dao = new GameDAO(DBConnect.getConn());
		Game game = dao.getGameById(Integer.parseInt(gameId));
		shoppingCart.addItem(game);
		if(shoppingCart.getItems()!=null) {
			request.getSession().setAttribute("succMsg","The product has been added");
		}else {
			request.getSession().setAttribute("errorMsg","The product has been added");

		}
		response.sendRedirect("/GamesCollection");
		
	}

}
