package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue
	private int orderId;
	
	@Column(name = "customerId")
	private int customerId;
	
	@Column(name = "orderCost")
	private double orderCost;

	@Column(name = "orderItem")
	private String orderItem;
	
	public Orders() {
		
	}

	public Orders(int custId,  String orderItem, double orderCost) {
		this.customerId = custId;
		this.orderItem = orderItem;
		this.orderCost = orderCost;
	}
	
	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	private void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}
	
	public double getOrderCost() {
		return orderCost;
	}
	
	@Override
	public String toString() {
		return "Order id:"+orderId+ " Customer id:"+customerId + " Item Name:"+orderItem+ " Item Cost:"+orderCost;
	}
}
