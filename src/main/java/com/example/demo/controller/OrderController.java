package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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
		boolean specialOffer = false;
		return ResponseEntity.ok(orderServices.saveOrder(newOrder, specialOffer));
	}
	
	@GetMapping("/orders/getOrderByOrderId")
	public ResponseEntity<String> getOrderByOrderId(@RequestParam int orderId){
		return ResponseEntity.ok(orderServices.getOrderByOrderId(orderId));
	}
	
	@GetMapping("/orders/getAllOrders")
	public ResponseEntity<List<Orders>> getAllOrders(){
		return ResponseEntity.ok(orderServices.getAllOrders());
	}
	
	@PostMapping("/orders/batchOrder")
	public List<ResponseEntity<Orders>> saveBatchOrder(@RequestParam("customerId") int customerId, @RequestParam List<String> orderItems) throws Exception{
		int appleCounter = 0;
		int orangeCounter = 0;
		boolean specialOffer = false;
		List<ResponseEntity<Orders>> res = new ArrayList<>();
		for(String orderName : orderItems) {
			if(!orderName.equals("Apple") && !orderName.equals("Orange"))
				throw new Exception("Invalid order items");
			else if(orderName.equals("Apple")) {
				appleCounter += 1;
				if(appleCounter == 2) {
					appleCounter = 0;//reset counter on 2nd apple
					Orders newOrder = new Orders(customerId, orderName, 0.0);
					specialOffer = true;
					res.add(ResponseEntity.ok(orderServices.saveOrder(newOrder, specialOffer)));
				} else {
					Orders newOrder = new Orders(customerId, orderName, 0.60);
					specialOffer = false;
					res.add(ResponseEntity.ok(orderServices.saveOrder(newOrder, specialOffer)));
				}
			}
			else if(orderName.equals("Orange"))
			{
				orangeCounter += 1;
				if(orangeCounter == 3) {
					orangeCounter = 0; //reset counter on third orange
					Orders newOrder = new Orders(customerId, orderName, 0.0);
					specialOffer = true;
					res.add(ResponseEntity.ok(orderServices.saveOrder(newOrder, specialOffer)));
				} else {
					Orders newOrder = new Orders(customerId, orderName, 0.25);
					specialOffer = false;
					res.add(ResponseEntity.ok(orderServices.saveOrder(newOrder, specialOffer)));
				}
			}				
		}
		return res;
	}
	
}
