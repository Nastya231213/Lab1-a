package com.admin.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.DB.DBConnect;
import com.dao.GameDAO;
import com.entity.Game;

/**
 * Servlet implementation class AdminAddBookServlet
 */
@WebServlet("/admin/addGame")
@MultipartConfig

public class AdminAddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		int platform = Integer.parseInt(request.getParameter("platform"));
		int genre = Integer.parseInt(request.getParameter("genre"));
        String year=request.getParameter("year");
		Double price = Double.parseDouble(request.getParameter("price"));
		String description = request.getParameter("description");
        String developer=request.getParameter("developer");
		Part part = request.getPart("bimg");
		String fileName = part.getSubmittedFileName();

		Game game = new Game(title, price, platform, fileName, genre, description,year,developer);
		GameDAO dao = new GameDAO(DBConnect.getConn());
        
		
		
		boolean flag = dao.insertGame(game);
		HttpSession session = request.getSession();
		if (flag) {
			String path = "C:\\Users\\olegp\\eclipse-workspace\\Games Collection\\src\\main\\webapp\\image";
			File file = new File(path);
			part.write(path + File.separator + fileName);

			session.setAttribute("succMsg", "The game has been added successfully");

		} else {
			session.setAttribute("errorMsg", "Something wrong on server..");

		}
	    dao.closeConnection();
		response.sendRedirect("main");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("form_add_game.jsp");
		dispatcher.forward(req, resp);
		
	}

}
