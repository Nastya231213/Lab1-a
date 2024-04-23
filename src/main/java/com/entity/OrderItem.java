package com.entity;

public class OrderItem {
private int item_id;
private int quantity;
private int orderId;

public OrderItem() {
	
}

public int getItem_id() {
	return item_id;
}

public void setItem_id(int item_id) {
	this.item_id = item_id;
}

public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

public int getOrderId() {
	return orderId;
}
public OrderItem(int item_id, int quantity, int orderId) {
	this.item_id=item_id;
	this.quantity = quantity;
	this.orderId = orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}

}
