package com.user.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserDAO dao=new UserDAO(DBConnect.getConn());
		HttpSession session=request.getSession();
		User user=dao.isExistAccount(email,password);
		RequestDispatcher dispatcher;

		if(email.equals("admin@gmail.com") && password.equals("admin")) {
			session.setAttribute("adminObj", new User());
			response.sendRedirect("admin/main");
		}
	
		else if(user!=null) {
			session.setAttribute("userObj",user);
			dispatcher=request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
		}else {
			request.setAttribute("errorMsg","Account with such a password and email doesn't exist!");
			doGet(request,response);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	

}
