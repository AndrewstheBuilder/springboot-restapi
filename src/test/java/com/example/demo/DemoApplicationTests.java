package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Orders;
import com.example.demo.services.OrderServices;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private OrderServices orderServices;
	
//	@Test
//	void contextLoads() {
//	}
	
	@Test
	public void saveOrdersForCustomerId911() throws Exception {
		Orders testOrder = new Orders(911, "Apple", 0.60);
		Orders e = orderServices.saveOrder(testOrder);
		assertNotNull(e);
	}
	
	@Test
	public void getOrderSummaryForCustomerId911() throws Exception {
		String res = orderServices.getOrderSummaryForCustomerId(911);
		assertNotNull(res);
	}

}
