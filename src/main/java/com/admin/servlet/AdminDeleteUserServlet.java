package com.admin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;

/**
 * Servlet implementation class AdminDeleteUserServlet
 */
@WebServlet("/admin/user_remove")
public class AdminDeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO	=new UserDAO(DBConnect.getConn());
		boolean flag=userDAO.deleteUser(userId);
		HttpSession session=request.getSession();
		
	      if(flag) {
	    	  session.setAttribute("succMsg", "Successfully deleted!");
	      }else {
	    	  session.setAttribute("succMsg", "Something wrong on server..Not deleted");

	      }
	      userDAO.closeConnection();
	      response.sendRedirect("users.jsp");
	}


}
