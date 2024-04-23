package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.PlatformDAO;

/**
 * Servlet implementation class AdminAddPlatformGenreServlet
 */
@WebServlet("/addPlatform")
public class AdminAddPlatformGenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("platform");
		PlatformDAO dao=new PlatformDAO(DBConnect.getConn());
		boolean flag=dao.insertPlatform(name);
		HttpSession session=request.getSession();
		if(flag) {
			session.setAttribute("succMsg","The platform has been added successfully");

		}else {
			session.setAttribute("errorMsg","Something wrong on server..");

		}
		
		dao.closeConnection();
		response.sendRedirect("admin/main");

		
	}

}
