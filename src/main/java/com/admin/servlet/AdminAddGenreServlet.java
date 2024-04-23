package com.admin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.GenreDAO;

/**
 * Servlet implementation class AdminAddGenreServlet
 */
@WebServlet("/addGenre")

public class AdminAddGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String genre = request.getParameter("genre");
		GenreDAO dao = new GenreDAO(DBConnect.getConn());
		HttpSession session = request.getSession();
		boolean flag = dao.insertGenre(genre);
		if (flag) {
			session.setAttribute("succMsg", "The genre has been added successfully");

		} else {
			session.setAttribute("errorMsg", "Something wrong on server..");

		}
		dao.closeConnection();
	
		response.sendRedirect("admin/main");

	}

}
