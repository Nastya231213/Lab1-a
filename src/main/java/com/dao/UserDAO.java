package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;

public class UserDAO extends DatabaseDAO implements GenericDAO<User> {


	public UserDAO(Connection conn) {
		super(conn);
	}

	 @Override
	    public boolean insert(User u) {
	        String sql = "INSERT INTO user(username,email,password,full_name,phone_number,address,registration_date,Active,hash)"
	                + " VALUES(?,?,?,?,?,?,?,?,?)";
	        Object[] values = { u.getUsername(), u.getEmail(), u.getPassword(), u.getFullName(), u.getPhone_number(),
	                u.getAddress(), u.getRegistration_date(), "0", u.getHash() };
	        return insert("user",
	                new String[] { "username", "email", "password", "full_name", "phone_number", "address",
	                        "registration_date", "Active", "hash" },
	                values);
	    }

	 @Override
	    public List<User> getAll() {
	        List<User> listUsers = new ArrayList<>();

	        ResultSet rs = select("user", new String[] { "*" }, null,null);
	        try {
	            while (rs.next()) {
	                User user = new User();
	                user.setUser_id(rs.getInt("user_id"));
	                user.setUsername(rs.getString("username"));
	                user.setEmail(rs.getString("email"));
	                user.setPassword(rs.getString("password"));
	                user.setFullName(rs.getString("full_name"));
	                user.setPhone_number(rs.getString("phone_number"));
	                user.setAddress(rs.getString("address"));
	                user.setRegistration_date(rs.getString("registration_date"));
	                listUsers.add(user);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return listUsers;
	    }
	 public User getLastUser() {
		    User user = null;
		    ResultSet rs = null;
		    try {
		        rs = select("user", new String[]{"*"}, null, null);
		        if (rs.last()) {
		            user = new User();
		            user.setUser_id(rs.getInt("user_id"));
		            user.setUsername(rs.getString("username"));
		            user.setEmail(rs.getString("email"));
		            user.setPassword(rs.getString("password"));
		            user.setFullName(rs.getString("full_name"));
		            user.setPhone_number(rs.getString("phone_number"));
		            user.setAddress(rs.getString("address"));
		            user.setRegistration_date(rs.getString("registration_date"));
		            user.setHash(rs.getString("hash"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) {
		                rs.close();
		       


	@Override
	public User getById(int id) {
	    User user = null;
	    ResultSet rs = null;
	    try {
	        String[] columns = {"user_id", "username", "email", "password", "full_name", "phone_number", "address", "registration_date"};
	        String condition = "user_id=?";
	        Object[] values = {id};
	        rs = select("user", columns, condition, values);
	        if (rs.next()) {
	            user = new User();
	            user.setUser_id(rs.getInt("user_id"));
	            user.setUsername(rs.getString("username"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setFullName(rs.getString("full_name"));
	            user.setPhone_number(rs.getString("phone_number"));
	            user.setAddress(rs.getString("address"));
	            user.setRegistration_date(rs.getString("registration_date"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return user;
	}


	  @Override
	    public boolean update(User user) {
	        String[] columns = {"username", "email", "full_name", "phone_number", "address"};
	        Object[] values = {user.getUsername(), user.getEmail(), user.getFullName(), user.getPhone_number(), user.getAddress()};
	        String condition = "user_id=" + user.getUser_id();
	        return update("user", columns, values, condition);
	    }

	  public boolean isExistAccount(String email, String password) {
		    boolean flag = false;
		    ResultSet rs = null;
		    try {
		        String[] columns = {"user_id"};
		        String condition = "email=? AND password=?";
		        Object[] values = {email, password};
		        rs = select("user", columns, condition, values);
		        if (rs.next()) {
		            flag = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) {
		                rs.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    return flag;
		}

    public boolean isEmailExist(String email) {
        boolean flag = false;
        ResultSet rs = null;
        try {
            String[] columns = {"user_id"};
            String condition = "email=?";
            Object[] values = {email};
            rs = select("user", columns, condition, values);
            
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return flag;
    }

    @Override
    public boolean delete(int userId) {
        String condition = "user_id=" + userId;
        return delete("user", condition);
    }



    public boolean updateUserInProfile(User user) {
        String[] columns = {"username", "email", "password", "full_name", "phone_number", "address"};
        Object[] values = {user.getUsername(), user.getEmail(), user.getPassword(), user.getFullName(), user.getPhone_number(), user.getAddress()};
        String condition = "user_id=" + user.getUser_id();
        return update("user", columns, values, condition);
    }


}
