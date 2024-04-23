package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.entity.Cart;
import com.entity.Game;
import com.entity.Order;
import com.entity.OrderItem;
import com.entity.User;

public class OrderDAO {
    Connection conn=null;
	PreparedStatement pst;
    ResultSet rs=null;
	
	public OrderDAO(Connection conn) {
		this.conn=conn;
	}
	public boolean  insertNewOrder(Order order) {
		boolean flag=false;
		
		try {
			String sql="insert into orderofuser(email,address,total_price,phone,full_name,city,date,state,user_id) values(?,?,?,?,?,?,?,?,?)";
	        pst=conn.prepareStatement(sql);
	        pst.setString(1, order.getEmail());
	        pst.setString(2, order.getAddress());
			pst.setDouble(3, order.getPrice());
			pst.setString(4, order.getPhone());
			pst.setString(5, order.getFullName());
			pst.setString(6,order.getCity());
			pst.setString(7, order.getDate());
			pst.setString(8,order.getState());
			pst.setInt(9, order.getUserId());
			int f=pst.executeUpdate();
			if(f==1) {
				flag=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}       
		
		return flag;
	}
	public boolean  deleteOrder(int orderId) {
		boolean flag=false;
		
		try {
			String sql="delete from orderofuser where order_id=?";
	        pst=conn.prepareStatement(sql);
	        pst.setInt(1, orderId);
	
			int f=pst.executeUpdate();
			if(f==1) {
				flag=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}       
		
		return flag;
	}
	public Order getLastOrderByUserid(int user_id) {
		Order order=null;
		try {
			
			String sql="select * from orderofuser where user_id=? order by order_id DESC";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				order=new Order();
		        order.setOrderId(rs.getInt(1));
		        order.setEmail(rs.getString(2));
		        order.setAddress(rs.getString(3));

		        order.setPrice(rs.getDouble(4));

		        order.setPhone(rs.getString(5));

		        order.setFullName(rs.getString(6));

		        order.setCity(rs.getString(7));
		        order.setDate(rs.getString(8));
		        order.setState(rs.getString(9));
		        order.setUserId(rs.getInt(10));
		        order.setStatus(rs.getString(11));


			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return order;
		
	}
	public Order getOrderById(int order_id) {
		Order order=null;
		try {
			
			String sql="select * from orderofuser where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, order_id);
			rs=pst.executeQuery();
			if(rs.next()) {
				order=new Order();
		        order.setOrderId(rs.getInt(1));
		        order.setEmail(rs.getString(2));
		        order.setAddress(rs.getString(3));

		        order.setPrice(rs.getDouble(4));

		        order.setPhone(rs.getString(5));

		        order.setFullName(rs.getString(6));

		        order.setCity(rs.getString(7));
		        order.setDate(rs.getString(8));
		        order.setState(rs.getString(9));
		        order.setUserId(rs.getInt(10));
		        order.setStatus(rs.getString(11));


			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return order;
		
	}
	public List<Order> getAllOrderByUserid(int user_id) {
		List<Order> orderList=new ArrayList<Order>();
		try {
			
			String sql="select * from orderofuser where user_id=? order by order_id DESC";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs=pst.executeQuery();
			while(rs.next()) {
				Order order=new Order();
		        order.setOrderId(rs.getInt(1));
		        order.setEmail(rs.getString(2));
		        order.setAddress(rs.getString(3));

		        order.setPrice(rs.getDouble(4));

		        order.setPhone(rs.getString(5));

		        order.setFullName(rs.getString(6));

		        order.setCity(rs.getString(7));
		        order.setDate(rs.getString(8));
		        order.setState(rs.getString(9));
		        order.setUserId(rs.getInt(10));
		        order.setStatus(rs.getString(11));

		        orderList.add(order);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return orderList;
		
	}
	public List<OrderItem> getOrderItems(int orderId) {
		List<OrderItem> orderList=new ArrayList<OrderItem>();
		try {
			String sql="select * from order_item where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, orderId);
			rs=pst.executeQuery();
			while(rs.next()) {
				OrderItem orderItem=new OrderItem();
				orderItem.setOrderId(rs.getInt(1));
				orderItem.setItem_id(rs.getInt(2));
				orderItem.setQuantity(rs.getInt(3));
				orderList.add(orderItem);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	public List<Order> getAllOrders() {
		List<Order> orderList=new ArrayList<Order>();
		try {
			
			String sql="select * from orderofuser";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
				Order order=new Order();
		        order.setOrderId(rs.getInt(1));
		        order.setEmail(rs.getString(2));
		        order.setAddress(rs.getString(3));

		        order.setPrice(rs.getDouble(4));

		        order.setPhone(rs.getString(5));

		        order.setFullName(rs.getString(6));

		        order.setCity(rs.getString(7));
		        order.setDate(rs.getString(8));
		        order.setState(rs.getString(9));
		        order.setUserId(rs.getInt(10));
		        order.setStatus(rs.getString(11));
		        
		        orderList.add(order);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return orderList;
		
	}
	public boolean EditOrder(Order order) {
		boolean flag=false;
		
		try {
			String sql="update orderofuser set email=?, address=?,phone=?,full_name=?, city=?,state=?,status=? where order_id=?";
	        pst=conn.prepareStatement(sql);
	       pst.setString(1, order.getEmail());
	       pst.setString(2,order.getAddress());
	       pst.setString(3, order.getPhone());
	       pst.setString(4, order.getFullName());
	       pst.setString(5, order.getCity());
	       pst.setString(6,order.getState());
	       pst.setString(7,order.getStatus());
	       pst.setInt(8, order.getOrderId());
			int f=pst.executeUpdate();
			if(f==1) {
				flag=true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}       
		
		return flag;
		
	}
	
	public void insertAllItemsFromTheCart(Cart cart,int orderId) {
		boolean flag=false;
		HashMap<Game,Integer> ourItems=cart.getItems();
		Iterator<Map.Entry<Game,Integer>> iterator=ourItems.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<Game, Integer>entry=iterator.next();
			Game game=entry.getKey();
			int quantity=entry.getValue();
			
			OrderItem item=new OrderItem(game.getId(),quantity,orderId);
			insertOrderItem(item);
			
		}
	}
	
	public void insertOrderItem(OrderItem item) {
		try {
			
			String sql="insert into order_item values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, item.getOrderId());
			pst.setInt(2, item.getItem_id());
			pst.setInt(3, item.getQuantity());
			pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
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
