package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	private static Connection conn = null;
	public DBConnect (){
		
	}

	public static Connection getConn() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/gamescollectionshop?serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "root", "123123qqq");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	

}
