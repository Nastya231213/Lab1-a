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
import com.entity.Review;
import com.entity.User;

public class OrderDAO extends DatabaseDAO implements GenericDAO<Order>{
  
	public OrderDAO(Connection conn) {
        super(conn);
	}
	 public boolean insert(Order order) {
	        String tableName = "orderofuser";
	        String[] columns = {"email", "address", "total_price", "phone", "full_name", "city", "date", "state", "user_id"};
	        Object[] values = {order.getEmail(), order.getAddress(), order.getPrice(), order.getPhone(), order.getFullName(), order.getCity(), order.getDate(), order.getState(), order.getUserId()};
	        return insert(tableName, columns, values);
	    }
	    public boolean delete(int orderId) {
	        String tableName = "orderofuser";
	        String condition = "order_id=?";
	        Object[] values = {orderId};
	        return delete(tableName, condition, values);
	    }
	    public Order getLastOrderByUserId(int userId) {
	        Order order = null;
	        String tableName = "orderofuser";
	        String[] columns = {"order_id", "email", "address", "total_price", "phone", "full_name", "city", "date", "state", "user_id", "status"};
	        String condition = "user_id = ?";
	        Object[] values = {userId};

	        ResultSet rs = select(tableName, columns, condition, values);
	        try {
	            if (rs.next()) {
	                order = mapResultSetToOrder(rs);
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
	        return order;
	    }
	    public Order mapResultSetToOrder(ResultSet rs) throws SQLException {
	        Order order = new Order();
	        order.setOrderId(rs.getInt("order_id"));
	        order.setEmail(rs.getString("email"));
	        order.setAddress(rs.getString("address"));
	        order.setPrice(rs.getDouble("total_price"));
	        order.setPhone(rs.getString("phone"));
	        order.setFullName(rs.getString("full_name"));
	        order.setCity(rs.getString("city"));
	        order.setDate(rs.getString("date"));
	        order.setState(rs.getString("state"));
	        order.setUserId(rs.getInt("user_id"));
	        order.setStatus(rs.getString("status"));
	        return order;
	    }
	    public Order getById(int orderId) {
	        Order order = null;
	        String tableName = "orderofuser";
	        String[] columns = {"order_id", "email", "address", "total_price", "phone", "full_name", "city", "date", "state", "status"};
	        String condition = "order_id = ?";
	        Object[] values = {orderId};

	        ResultSet rs = select(tableName, columns, condition, values);
	        try {
	            if (rs.next()) {
	                order = mapResultSetToOrder(rs);
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
	        return order;
	    }

	    public List<Order> getAllOrdersByUserId(int userId) {
	        List<Order> orderList = new ArrayList<>();
	        String tableName = "orderofuser";
	        String condition = "user_id = ?";
	        Object[] values = {userId};

	        ResultSet rs = select(tableName, null, condition, values);
	        try {
	            while (rs.next()) {
	                Order order = mapResultSetToOrder(rs);
	                orderList.add(order);
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
	        return orderList;
	    }
	    public List<OrderItem> getOrderItems(int orderId) {
	        List<OrderItem> orderItemList = new ArrayList<>();
	        String tableName = "order_item";
	        String[] columns = {"order_id", "item_id", "quantity"};
	        String condition = "order_id = ?";
	        Object[] values = {orderId};

	        ResultSet rs = select(tableName, columns, condition, values);
	        try {
	            while (rs.next()) {
	                OrderItem orderItem = new OrderItem();
	                orderItem.setOrderId(rs.getInt("order_id"));
	                orderItem.setItem_id(rs.getInt("item_id"));
	                orderItem.setQuantity(rs.getInt("quantity"));
	                orderItemList.add(orderItem);
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
	        return orderItemList;
	    }
	
	    public List<Order> getAll() {
	        List<Order> orderList = new ArrayList<>();
	        String tableName = "orderofuser";

	        ResultSet rs = select(tableName,null, null, null);
	        try {
	            while (rs.next()) {
	                Order order = mapResultSetToOrder(rs);
	                orderList.add(order);
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
	        return orderList;
	    }
	
	    public Order getLastOrder() {
	        Order order = null;
	        String sql = "SELECT * FROM orderofuser ORDER BY order_id DESC LIMIT 1";
	        ResultSet rs = executeQuery(sql, null);
	        try {
	            if (rs.next()) {
	                order = mapResultSetToOrder(rs);
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
	        return order;
	    }


	    public boolean update(Order order) {
	        String tableName = "orderofuser";
	        String[] columns = {"email", "address", "phone", "full_name", "city", "state", "status"};
	        Object[] values = {order.getEmail(), order.getAddress(), order.getPhone(), order.getFullName(), order.getCity(), order.getState(), order.getStatus()};
	        String condition = "order_id = ?";
	        Object[] conditionValues = {order.getOrderId()};
	        return update(tableName, columns, values, condition, conditionValues);
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
	    String tableName = "order_item";
	    String[] columns = {"order_id", "item_id", "quantity"};
	    Object[] values = {item.getOrderId(), item.getItem_id(), item.getQuantity()};
	    insert(tableName, columns, values);
	}

	
}
