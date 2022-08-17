package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Orders;
import com.example.demo.services.OrderServices;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	@Autowired
	private OrderServices orderServices;
	
	@GetMapping("/orders/orderSummary")
	public ResponseEntity<String> getOrderSummaryForCustomerId(@RequestParam("customerId") int customerId) {
		return ResponseEntity.ok(orderServices.getOrderSummaryForCustomerId(customerId));
	}
	
	@PostMapping("/orders/saveOrder")
	public ResponseEntity<Orders> saveOrder(@RequestParam("customerId") int customerId, @RequestParam String orderItem) throws Exception{
		double orderCost = 0.0;
		if(orderItem.equals("Apple")) {
			orderCost = 0.60;
		} else if(orderItem.equals("Orange")) {
			orderCost = 0.25;
		} else {
			throw new Exception("Invalid order item");
		}
		Orders newOrder = new Orders(customerId, orderItem, orderCost);
		return ResponseEntity.ok(orderServices.saveOrder(newOrder));
	}
}
