package com.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DBConnect;
import com.dao.UserDAO;
import com.entity.User;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/edit_theuser")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("username");
		String email=request.getParameter("email");
		String oldPassword=request.getParameter("password");
		String newPassword=request.getParameter("newPassword");
		String fullName=request.getParameter("fname");
		String phoneNo=request.getParameter("phno");
		String address=request.getParameter("address");
		User user=(User)request.getSession().getAttribute("userObj");
		if(!oldPassword.equals(user.getPassword())) {
			request.getSession().setAttribute("errorMsg", "Passwords don't match");
			response.sendRedirect("settings.jsp");
	
		}else {
			
			user.setUsername(userName);
			user.setEmail(email);
			user.setPassword(newPassword);
			user.setFullName(fullName);
			user.setPhone_number(phoneNo);
			user.setAddress(address);
			UserDAO userDAO=new UserDAO(DBConnect.getConn());
			boolean flag=userDAO.updateUserInProfile(user);
			if(flag) {
				request.setAttribute("succMsg", "Successfully updated!");
			}else {
				request.setAttribute("errorMsg","Something wrong on server.. Didn't manage to edit the user profile");
			}
			response.sendRedirect("settings.jsp");
		}
	}

}
