package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Orders;
import com.example.demo.services.OrderServices;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationServiceTests {
	@Autowired
	private OrderServices orderServices;
	
	@Test
	public void saveOrdersForCustomerId911() throws Exception {
		Orders testOrder = new Orders(911, "Apple", 0.60);
		boolean specialOffer = false;
		Orders e = orderServices.saveOrder(testOrder, specialOffer);
		assertNotNull(e);
	}
	
	@Test
	public void getOrderSummaryForCustomerId911() throws Exception {
		String res = orderServices.getOrderSummaryForCustomerId(911);
		assertNotNull(res);
	}
	
	@Test
	public void getAllOrders() throws Exception {
		List<Orders> orders = orderServices.getAllOrders();
		assertTrue(orders.isEmpty()==false);
	}
}
