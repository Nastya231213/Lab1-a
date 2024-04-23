package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.SendigEmail;
import com.entity.User;

public class UserDAO {

	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean insetNewUser(User u) {
		boolean flag = false;

		try {
			String sql = "insert into user(username,email,password,full_name,phone_number,address,registration_date,Active,hash)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getEmail());
			pst.setString(3, u.getPassword());
			pst.setString(4, u.getFullName());
			pst.setString(5, u.getPhone_number());
			pst.setString(6, u.getAddress());
			pst.setString(7, u.getRegistration_date());
			pst.setString(8, "0");
			pst.setString(9, u.getHash());
			int i = pst.executeUpdate();
			if (i == 1) {
				System.out.println("Successfully created new user");
				System.out.println("Sending Mail..");
				SendigEmail se = new SendigEmail(u.getEmail(), u.getHash());
				se.sendMail();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public List<User> getAllUsers() {
		List<User> listUsers = null;

		try {
			String sql = "select * from user";
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();
			listUsers = new ArrayList<User>();
			while (rs.next()) {

				User user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setFullName(rs.getString(5));
				user.setPhone_number(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setRegistration_date(rs.getString(8));
				listUsers.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listUsers;
	}

	public User isExistAccount(String email, String password) {
		boolean flag = false;
		User user = null;
		try {
			String sql = "select * from user where password=? and email=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, email);

			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setFullName(rs.getString(5));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setPhone_number(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setRegistration_date(rs.getString(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean isEmailExist(String email) {
		boolean flag = false;
		try {
			String sql = "select * from user where email=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public User getLastUser() {
		User user = null;
		try {
			String sql = "select * from user order by user_id DESC";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public User getUserById(int userId) {
		User user = null;
		try {
			String sql = "select * from user where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUser_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setFullName(rs.getString(5));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setPhone_number(rs.getString(6));
				user.setAddress(rs.getString(7));
				user.setRegistration_date(rs.getString(8));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public boolean updateUser(User user) {
		boolean flag = false;
		try {
			String sql = "update user set username=? , email=?, full_name=?, phone_number=?,address=? where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getFullName());
			pst.setString(4, user.getPhone_number());
			pst.setString(5, user.getAddress());
			pst.setInt(6, user.getUser_id());
			int i = pst.executeUpdate();
			if (i == 1) {
				flag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public boolean deleteUser(int userId) {
		boolean flag = false;
		try {
			String sql = "delete from user where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			
			int i=pst.executeUpdate();
			if(i==1) {
				flag=true;
			}
		} catch (Exception e) {
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
	
	public boolean updateUserInProfile(User user) {
		boolean flag=false;
		try {
			String sql="update user set username=?, email=?,password=?,full_name=?, phone_number=?,address=? where user_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPassword());
			pst.setString(4, user.getFullName());
			pst.setString(5, user.getPhone_number());
			pst.setString(6, user.getAddress());
			pst.setInt(7,user.getUser_id());
			int i=pst.executeUpdate();
			if(i==1) {
				flag=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}

}
