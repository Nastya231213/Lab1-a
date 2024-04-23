package com.user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.GameDAO;
import com.entity.Game;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int genreId = Integer.parseInt(request.getParameter("genre"));
		int platformId = Integer.parseInt(request.getParameter("pl"));
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		String keyword = request.getParameter("keyword");

		int minPr = Integer.parseInt(minPrice);
		int maxPr = Integer.parseInt(maxPrice);

		if (minPr > maxPr) {
			minPr = 0;
			maxPr = Integer.MAX_VALUE;
		}
		GameDAO gameDAO = new GameDAO(DBConnect.getConn());
		List<Game> listOfGames;

		if (genreId == 0 && platformId == 0) {
			listOfGames = gameDAO.getGamesBySearch(keyword, minPr, maxPr);

		} else if (platformId == 0) {
			listOfGames = gameDAO.getGamesBySearchCat(keyword, genreId, minPr, maxPr);

		} else if (genreId == 0) {
			listOfGames = gameDAO.getGamesBySearchPlat(keyword, platformId, minPr, maxPr);
		} else if (keyword == null || keyword.equals("")) {
			listOfGames = gameDAO.getGamesBySearch(genreId, platformId, minPr, maxPr);
		} else {

			listOfGames = gameDAO.getGamesBySearch(keyword, genreId, platformId, minPr, maxPr);

		}
		HttpSession session = request.getSession();

		
		if (listOfGames.size() == 0) {
			session.setAttribute("listGames", null);
		} else {
			session.setAttribute("listGames", listOfGames);

		}
		response.sendRedirect("resultOfSearch.jsp");

	}

}
