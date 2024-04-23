package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Platform;

public class PlatformDAO {
Connection conn=null;
PreparedStatement pst;
ResultSet rs;	
	public PlatformDAO(Connection conn) {
		this.conn=conn;
	}
	
	
	public boolean insertPlatform(String namePlatform) {
		boolean f=false;
		
		try {
			String sql="insert into platforms(name) values(?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,namePlatform);
			int i=pst.executeUpdate();
			
			if(i==1) {
				f=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	public List<Platform> getAllPlatforms() {
		List<Platform> list=new ArrayList<Platform>();
		try {
			String sql="select * from platforms";
			pst=conn.prepareStatement(sql);
			
			rs=pst.executeQuery();
			while(rs.next()) {
				Platform pl=new Platform();
				pl.setId(rs.getInt(1));
				pl.setName(rs.getString(2));
				list.add(pl);
			}
			
		}catch(Exception e) {
	         e.printStackTrace();
		}
		return list;
	}
	
	public Platform getPlatformById(int id) {
		Platform platform=null;
		
		try {
			String sql="select * from platforms where id=?";
	        pst=conn.prepareStatement(sql);
	        pst.setInt(1, id);
	        rs=pst.executeQuery();
	        
	        while(rs.next()) {
	        	platform=new Platform();
	        	platform.setId(id);
	        	platform.setName(rs.getString(2));
	        }
	        
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return platform;
	}
	
	public boolean deletePlatform(int id) {
		boolean flag=false;
		
		try {
			String sql="delete from platforms where id=?";
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
