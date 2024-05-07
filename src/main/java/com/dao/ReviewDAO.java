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

public class ReviewDAO extends DatabaseDAO {

    public ReviewDAO(Connection conn) {
        super(conn);
    }

    public List<Review> getReviewsByGameId(int gameId) {
        List<Review> reviewsList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM review WHERE game_id=?";
            ResultSet rs = select("review", new String[]{"*"}, "game_id=?", new Object[]{gameId});
            while (rs.next()) {
                Review rev = new Review();
                rev.setId(rs.getInt(1));
                rev.setGame_id(gameId);
                rev.setUser_id(rs.getInt(3));
                rev.setRating(rs.getDouble(4));
                rev.setComment(rs.getString(5));
                reviewsList.add(rev);
            }
            rs.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewsList;
    }

    public boolean insertReview(Review rev) {
        boolean f = false;
        try {
            String sql = "INSERT INTO review (game_id, user_id, rating, comment, headline, date) VALUES (?, ?, ?, ?, ?, ?)";
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedSateTime = formatter.format(now);
            Object[] values = {rev.getGame_id(), rev.getUser_id(), rev.getRating(), rev.getComment(), rev.getHeadline(), formattedSateTime};
            f = insert("review", new String[]{"game_id", "user_id", "rating", "comment", "headline", "date"}, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
	
    public double calculateAverageRatingOfGame(int gameId) {
        double res = 0;
        int quantity = 0;
        try {
            ResultSet rs = select("review", new String[]{"rating"}, "game_id=?", new Object[]{gameId});
            while (rs.next()) {
                quantity++;
                res += rs.getDouble("rating");
            }
            rs.close(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (quantity != 0) {
            res /= quantity;
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

}
