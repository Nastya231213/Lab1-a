package com.user.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DBConnect;


/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/ActivateAccount")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("key1");
		String hash = request.getParameter("key2");
		
		Connection conn=DBConnect.getConn();
		
		try {
			
			PreparedStatement pst = conn.prepareStatement("select email, hash, Active from user where email=? and hash=? and Active='0'");
			pst.setString(1, email);
			pst.setString(2, hash);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				PreparedStatement pst1 = conn.prepareStatement("update user set Active='1' where email=? and hash=?");
				pst1.setString(1, email);
				pst1.setString(2, hash);
				
				int i = pst1.executeUpdate();
				
				if(i == 1) {
					request.getSession().setAttribute("succMsg", "Successfull verified!Sign in");
					response.sendRedirect("login.jsp");					
				}else {
					response.sendRedirect("main.jsp");
				}
			}	
		}catch(Exception e) {
			System.out.println("Error at ActivateAccount.java: "+e);
		}
	}

	

}
