package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.GenreDAO;

/**
 * Servlet implementation class AdminRemoveGenreServlet
 */
@WebServlet("/admin/delete_genre")
public class AdminRemoveGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int genreId=Integer.parseInt(request.getParameter("id"));
		GenreDAO genreDAO=new GenreDAO(DBConnect.getConn());
		boolean flag=genreDAO.deleteGenre(genreId);
		HttpSession session=request.getSession();
	      if(flag) {
	    	  session.setAttribute("succMsg", "Successfully deleted!");
	      }else {
	    	  session.setAttribute("errorMsg", "Something wrong on server..Not deleted");

	      }
	      genreDAO.closeConnection();
	      response.sendRedirect("genre_edit.jsp");
		
	}

}
