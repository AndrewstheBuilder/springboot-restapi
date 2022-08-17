package com.example.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.Integer;
import java.util.List;

import com.example.demo.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
	public final static String GET_ORDERS_BY_CUSTOMERID = "SELECT o FROM Orders o WHERE customer_id = :customerId";

	@Query(GET_ORDERS_BY_CUSTOMERID)
	List<Orders> getOrdersByCustomerId(@Param("customerId") int customerId);
}
