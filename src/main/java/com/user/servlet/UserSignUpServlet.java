package com.user.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.DeveloperDAO;
import com.dao.UserDAO;
import com.entity.Developer;
import com.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/sign_up")
public class UserSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassoword = request.getParameter("confirmPassword");
		String fullName = request.getParameter("fname");
		String phone = request.getParameter("phno");
		String address = request.getParameter("address");
		String check = request.getParameter("check");
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat DateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		
		String formatDate = DateFormat.format(calendar.getTime());
    
        
		User u = new User(userName, email, password, fullName, phone, address, formatDate);
		UserDAO dao = new UserDAO(DBConnect.getConn());
		HttpSession session = request.getSession();
		boolean checkEmail = dao.isEmailExist(email);
		
		Random theRandom=new Random();
		theRandom.nextInt(999999);
		String myHash=DigestUtils.md5Hex(""+theRandom);
		u.setHash(myHash);
		if (checkEmail) {
			session.setAttribute("passAlreadyExistMsg", "This password has already existed!");
		} else {
			if (check != null) {
				if (password.equals(confirmPassoword)) {
					if (dao.insetNewUser(u)) {
                         
				        response.sendRedirect("verify.jsp");
				        
				        
					} else {
						session.setAttribute("errorMsg", "Something wrong on server");
						doGet(request, response);

					}
				} else {
					session.setAttribute("pasMsg", "Passwords don't match");
					doGet(request, response);

				}

			} else {
				session.setAttribute("failMsg", "Agree with terms & Condition");
				doGet(request, response);

			}
			
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

}
