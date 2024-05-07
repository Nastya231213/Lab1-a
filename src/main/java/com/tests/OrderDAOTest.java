package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.DB.DBConnect;
import com.dao.GameDAO;
import com.dao.OrderDAO;
import com.entity.Order;

public class OrderDAOTest {
	private Connection conn;
	private OrderDAO orderDAO;

	@Before
	public void setUp() throws Exception {

		conn = DBConnect.getConn();
		orderDAO = new OrderDAO(conn);

	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

	@Test
	public void testInsertNewOrder() {
		Order order = new Order("test@email.com", "123 Test St", "John Doe", "Test City", 100.00, "1234567890",
				"2024-04-24", "Test State", 1);
		assertTrue(orderDAO.insert(order));
	}

    @Test
    public void testGetOrderById() {
        Order order = orderDAO.getLastOrder();
        Order receivedOrder=orderDAO.getById(order.getOrderId());
        assertNotNull(receivedOrder);
        assertEquals(order.getOrderId(), receivedOrder.getOrderId());
    }
    @Test
    public void testDeleteOrder() {
        Order order = orderDAO.getLastOrder();
        orderDAO.delete(order.getOrderId());
        assertNull(orderDAO.getById(order.getOrderId()));

    }
    @Test
    public void testEditOrder() {
  
        Order retrievedOrder = orderDAO.getLastOrder();

        retrievedOrder.setAddress("New Address");
        retrievedOrder.setCity("New City");
        retrievedOrder.setState("New State");

        assertTrue(orderDAO.update(retrievedOrder));

        Order updatedOrder = orderDAO.getById(retrievedOrder.getOrderId());
        assertNotNull(updatedOrder);
        assertEquals(retrievedOrder.getAddress(), updatedOrder.getAddress());
        assertEquals(retrievedOrder.getCity(), updatedOrder.getCity());
        assertEquals(retrievedOrder.getState(), updatedOrder.getState());
    }
}
