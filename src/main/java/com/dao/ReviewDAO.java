package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.entity.Review;

public class ReviewDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	public ReviewDAO(Connection conn){
		this.conn=conn;
		
	}

	public List<Review>getReviewsByGameId(int gameId) {
		List<Review> reviewsList=new ArrayList<Review>();
		try {
			String sql="select * from review where game_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, gameId);
			rs=pst.executeQuery();

			while(rs.next()) {
				Review rev=new Review();
				rev.setId(rs.getInt(1));
				rev.setGame_id(gameId);
				rev.setUser_id(rs.getInt(3));
				rev.setRating(rs.getDouble(4));
				rev.setComment(rs.getString(5));
				reviewsList.add(rev);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviewsList;
		
	}
	public boolean insertReview(Review rev) {
		boolean f=false;
		try {
			String sql="insert into review(game_id, user_id,rating,comment,headline,date) values(?,?,?,?,?,?)";
			Date now =new Date();
			SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formattedSateTime=formatter.format(now);
			pst=conn.prepareStatement(sql);
			pst.setInt(1,rev.getGame_id());
			pst.setInt(2, rev.getUser_id());
			pst.setDouble(3,rev.getRating());
			pst.setString(4,rev.getComment());
			pst.setString(5, rev.getHeadline());
			pst.setString(6, formattedSateTime);
			int i=pst.executeUpdate();
			if(i==1) {
				f=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public double calculateAverageRatingOfGame(int gameId) {
		double res=0;
		int quantity=0;
		try {
			String sql="select * from review where game_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, gameId);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				quantity++;
				res+=rs.getDouble(4);
				
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(quantity!=0) {
			res/=quantity;
		}
		return res;
	}
	
	public String getRatingString(int gameId) {
		String result="";
		double averageRating=calculateAverageRatingOfGame(gameId);
		int numberOfFullStars=(int)averageRating;
		
		for(int i=1;i<=numberOfFullStars;i++) {
			result+="on,";
		}
		int next=numberOfFullStars+1;
		if(averageRating>numberOfFullStars) {
			result+="half,";
			next++;
		}
		for(int j=next;j<=5;j++) {
			result+="off,";
		}
		return result.substring(0,result.length()-1);
	}
	
	public String getRatingString(double rating) {
		String result="";
		double averageRating=rating;
		int numberOfFullStars=(int)averageRating;
		
		for(int i=1;i<=numberOfFullStars;i++) {
			result+="on,";
		}
		int next=numberOfFullStars+1;
		if(averageRating>numberOfFullStars) {
			result+="half,";
			next++;
		}
		for(int j=next;j<=5;j++) {
			result+="off,";
		}
		return result.substring(0,result.length()-1);
	}
	public void closeConnection() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
