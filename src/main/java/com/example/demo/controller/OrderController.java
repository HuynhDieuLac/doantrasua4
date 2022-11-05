package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Food;
import com.example.demo.entities.Order;
import com.example.demo.service.OrderService;

@Controller
@RequestMapping(value="/admin")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order")
	public String showOrderList(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
			@RequestParam(name="sortField", required=false, defaultValue = "id") String sortField,
			@RequestParam(name="sortDir", required=false, defaultValue="asc" ) String sortDir,
			Model model) {
		int pageSize = 12;
		Page<Order> orderFood = orderService.findAll(pageNo, pageSize, sortField, sortDir);
		List<Order> orders = orderFood.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", orderFood.getTotalPages());
		model.addAttribute("totalItems", orderFood.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("orders", orders);
		return "order";
	}
	
	@GetMapping("/vieworder")
	public String showUserInfo(@RequestParam(name = "orderId") Long id, Model model) {
		Order order = orderService.findOrderById(id);		
		model.addAttribute("order", order);
		return "view-order";
	}
		
	@GetMapping("/packedOrder")
	public String packedOrder(@RequestParam(name="orderId") Long id, Model model)
	{
		orderService.packedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "view-order";
	}
	
	@GetMapping("/receivedOrder")
	public String receivedOrder(@RequestParam(name="orderId") Long id, Model model)
	{
		orderService.receivedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "view-order";
	}
	
	@GetMapping("/deliveriedOrder")
	public String deliveriedOrder(@RequestParam(name="orderId") Long id, Model model)
	{
		orderService.deliveriedOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "view-order";
	}
	
	@GetMapping("/paidOrder")
	public String paidOrder(@RequestParam(name="orderId") Long id, Model model)
	{
		orderService.paidOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "view-order";
	}
	
	@GetMapping("/cancelOrder")
	public String cancelOrder(@RequestParam(name="orderId") Long id, Model model)
	{
		orderService.cancelOrder(id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "view-order";
	}
}
