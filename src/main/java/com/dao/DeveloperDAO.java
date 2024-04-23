package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.entity.Developer;
import com.entity.Game;

public class DeveloperDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
    public DeveloperDAO(Connection conn) {
    	this.conn=conn;
    	
    }
	
	public void insertDeveloper(Developer dev) {
		boolean flag=false;
		try {
			String sql="insert into developers(name,user_id) values(?,?)";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, dev.getName());
			pst.setInt(2, dev.getUserId());
			int i=pst.executeUpdate();
			if(i==1) {
				flag=true;
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
