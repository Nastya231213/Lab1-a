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
import com.entity.User;

@WebServlet("/admin/edit_user")
public class AdminEditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          int userId=Integer.parseInt(request.getParameter("id"));
          UserDAO userDAO=new UserDAO(DBConnect.getConn());
	      User user=userDAO.getUserById(userId);
	      String username=request.getParameter("username");
	      String email=request.getParameter("email");
	      String fullName=request.getParameter("fname");
	      String phone=request.getParameter("phno");
	      String address=request.getParameter("address");
	      user.setUsername(username);
	      user.setEmail(email);
	      user.setFullName(fullName);
	      user.setPhone_number(phone);
	      user.setAddress(address);
	      boolean flag=userDAO.updateUser(user);
	      HttpSession session =request.getSession();
	      if(flag) {
	    	  session.setAttribute("succMsg", "Successfully updated!");
	      }else {
	    	  session.setAttribute("errorMsg", "Something wrong on server..Not Updated");

	      }
	      userDAO.closeConnection();
	      response.sendRedirect("admin.jsp");
	}

}
