package com.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.dao.ReviewDAO;
import com.entity.Review;
import com.entity.User;

/**
 * Servlet implementation class AdddReviewServlet
 */
@WebServlet("/add_review")
public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Object userObj=(Object)session.getAttribute("userObj");
        if(userObj==null) {
    	    response.sendRedirect("login");

        }else {
		int gameId=Integer.parseInt(request.getParameter("gId"));
	    double rating=Double.parseDouble(request.getParameter("rating"));
		User user=(User)userObj;
		int userId=user.getUser_id();
		String headline=request.getParameter("headline");
		String comment=request.getParameter("comment");
		ReviewDAO dao=new ReviewDAO(DBConnect.getConn());
		Review rev=new Review(userId,gameId,comment,rating,headline);
		dao.insertReview(rev);
	    response.sendRedirect("reviews.jsp?id="+String.valueOf(gameId));
        } 
	}


}
