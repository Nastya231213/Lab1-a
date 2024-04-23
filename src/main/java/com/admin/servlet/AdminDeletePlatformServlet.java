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
import com.dao.PlatformDAO;


@WebServlet("/admin/delete_platform")
public class AdminDeletePlatformServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int platformId=Integer.parseInt(request.getParameter("id"));

		PlatformDAO platformDAO=new PlatformDAO(DBConnect.getConn());
		boolean flag=platformDAO.deletePlatform(platformId);
		HttpSession session=request.getSession();
	      if(flag) {
	    	  session.setAttribute("succMsg", "Successfully deleted!");
	      }else {
	    	  session.setAttribute("errorMsg", "Something wrong on server..Not deleted");

	      }
	      platformDAO.closeConnection();
	      response.sendRedirect("platform_edit.jsp");
	}


}
