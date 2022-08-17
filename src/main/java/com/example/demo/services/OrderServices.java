package com.example.demo.services;

import com.example.demo.interfaces.OrderRepository;
import com.example.demo.entity.Orders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServices {
	
	@Autowired
	private OrderRepository ordersRepository;
	
	public Orders saveOrder(Orders order, boolean specialOffer) throws Exception {
		if(specialOffer) {
			
		} else {
			if(!order.getOrderItem().equals("Apple") && !order.getOrderItem().equals("Orange")) throw new Exception("Invalid order item");
			if(order.getOrderItem().equals("Apple") && order.getOrderCost() != 0.60) throw new Exception("Invalid cost for Apple");
			if(order.getOrderItem().equals("Orange") && order.getOrderCost() != 0.25) throw new Exception("Invalid cost for Orange");
		}

		return ordersRepository.save(order);
	}
	
	public String getOrderSummaryForCustomerId(int customerId) {
		List<Orders> customerOrders = ordersRepository.getOrdersByCustomerId(customerId);
		double totalCost = 0.0;
		String shoppingList = null;
		for(Orders order: customerOrders) {
			totalCost += order.getOrderCost();
			if(shoppingList != null)
				shoppingList += ", ";
			else
				shoppingList = "";
			shoppingList += order.getOrderItem();
		}
		
		BigDecimal bd = new BigDecimal(totalCost).setScale(2, RoundingMode.HALF_UP);
      	double totalCostRounded = bd.doubleValue();
		if(customerOrders.isEmpty()) return null;
	   	return "Customer id:" + Integer.toString(customerId)+ "\norder info..." + "\nShopping List:" + shoppingList + "\nTotal Cost:"+totalCostRounded ;
	}

}
