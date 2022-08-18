package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controller.OrderController;
import com.example.demo.services.OrderServices;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class DemoApplicationControllerTests {
	
	@MockBean
	private OrderServices orderServices;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void saveOrderForCustomer911() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders/saveOrder")
				.param("customerId", "911")
				.param("orderItem", "Apple")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
