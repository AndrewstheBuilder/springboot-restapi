package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc; 
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.Orders;
import com.example.demo.services.OrderServices;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	private OrderServices orderServices;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
	
//	@Test
//	void contextLoads() {
//	}
	
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
	public void saveBatchOrderForCustomerId911() {
		mockMvc.perform(post("/api/v1/orders/batchOrder?customerId=2&orderItems=Apple&orderItems=Apple&orderItems=Apple&orderItems=Orange&orderItems=Orange&orderItems=Orange"))
	}
}
