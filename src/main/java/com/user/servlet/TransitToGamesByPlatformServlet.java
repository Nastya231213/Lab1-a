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


@WebServlet("/view_by_platform")
public class TransitToGamesByPlatformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            
		int genreId = Integer.parseInt(request.getParameter("genre"));
		int platformId = Integer.parseInt(request.getParameter("pl"));
		
	    GameDAO gameDAO=new GameDAO(DBConnect.getConn());
	    List<Game> listGames=gameDAO.getGamesByGenreAndPlatform(genreId,platformId);

	    
	    HttpSession session=request.getSession();
	    session.setAttribute("listGames", listGames);
	    response.sendRedirect("resultOfSearch.jsp");
	    session.setAttribute("flag", 1);	    
	}

}