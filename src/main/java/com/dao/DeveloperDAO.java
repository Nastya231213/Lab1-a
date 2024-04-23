package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.entity.Developer;
import com.entity.Game;

public class DeveloperDAO {
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;

	public DeveloperDAO(Connection conn) {
		this.conn = conn;

	}

	public void insertDeveloper(Developer dev) {
		boolean flag = false;
		try {
			String sql = "insert into developers(name) values(?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, dev.getName());
			int i = pst.executeUpdate();
			if (i == 1) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Developer getLast() {
	    Developer developer = null;
	    try {
	        String sql = "SELECT * FROM developers ORDER BY id DESC LIMIT 1";
	        pst = conn.prepareStatement(sql);
	        rs = pst.executeQuery();
	        if (rs.next()) {
	            developer = new Developer(rs.getString("name"), rs.getInt("id"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return developer;
	}
}
