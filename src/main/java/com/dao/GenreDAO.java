package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Genre;

public class GenreDAO {

	Connection conn=null;
	PreparedStatement pst;
    ResultSet rs;
	public GenreDAO(Connection conn) {
		this.conn=conn;
	}
	
	
	public boolean insertGenre(String name) {
		boolean f = false;
        try {
        	String sql="insert into genre (name) values(?)";
        	pst=conn.prepareStatement(sql);
        	pst.setString(1, name);
        	int i=pst.executeUpdate();
        	if(i==1) {
        		f=true;
        	}
        	
        }catch(Exception e) {
        	e.printStackTrace();    	
        }
		
		
		return f;
	}
	
	public List<Genre> getAllGenre(){
		List<Genre> listGenre=new ArrayList<Genre>();
		
		try {
			String sql="select * from genre";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				Genre genre=new Genre();
				genre.setId(rs.getInt(1));
				genre.setName(rs.getString(2));
				listGenre.add(genre);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listGenre;
	}
	
	public Genre characteristics(int id) {
		Genre g=null;
		
		try {
			String sql="select * from  genre where genre_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,id);
			rs=pst.executeQuery();
			while(rs.next()) {
				g=new Genre();
				g.setId(id);
				g.setName(rs.getString(2));
			}
		     
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return g;
	}
	public Genre getGenreById(int id){
		Genre genre=new Genre();
		try {
			String sql="select * from genre where genre_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				genre.setId(rs.getInt(1));
				genre.setName(rs.getString(2));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return genre;
	}
	public boolean deleteGenre(int id){
		boolean flag=false;
		try {
			String sql="delete from genre where genre_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			int f=pst.executeUpdate();
            if(f==1) {
            	flag=true;
            }
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
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
