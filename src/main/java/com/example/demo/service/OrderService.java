package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.boot.model.source.spi.Orderable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Food;
import com.example.demo.entities.Order;
import com.example.demo.entities.OrderDetail;
import com.example.demo.entities.OrderStatus;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.FoodRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

import model.CartInfo;
import model.CartLineInfo;
import model.CustomerInfo;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private FoodRepository foodRepository;

	public List<Order> getOrders() {
		return this.orderRepository.findAll();
	}

	public String addOrder(CartInfo cartInfo) {
		// int orderNum = this.getMaxOrderNum() + 1;
		Order order = new Order();
		Customer customer = new Customer();
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		customer.setFirstName(customerInfo.getFirstName());
		customer.setLastName(customerInfo.getLastName());
		customer.setEmail(customerInfo.getEmail());
		customer.setPhoneNumber(customerInfo.getNumberPhone());

		// chuyen het column ben address entity qua Customer entity
		customer.setStreet(customerInfo.getStreet());
		customer.setDistrict(customerInfo.getDistrict());
		customer.setCity(customerInfo.getCity());
		if (customerInfo.getFirstName() == null || customerInfo.getFirstName().isEmpty()) {
			return "Vui lòng nhập Tên!";
		}
		if (customerInfo.getLastName() == null || customerInfo.getLastName().isEmpty()) {
			return "Vui lòng nhập Họ!";
		}
		if (customerInfo.getStreet() == null || customerInfo.getStreet().isEmpty()) {
			return "Vui lòng nhập Số nhà, tên đường!";
		}
		if (customerInfo.getDistrict() == null || customerInfo.getDistrict().isEmpty()) {
			return "Nhập quận, huyện!";
		}
		if (customerInfo.getCity() == null || customerInfo.getCity().isEmpty()) {
			return "Nhập Thành phố!";
		}
		if (customerInfo.getEmail() == null || customerInfo.getEmail().isEmpty()) {
			return "Nhập Email!";
		}
		if (customerInfo.getNumberPhone() == null || customerInfo.getNumberPhone().isEmpty()) {
			return "Vui lòng nhập số điện thoại!";
		}

		customerRepository.save(customer);

		java.util.Date today = new java.util.Date();
		order.setDate(new java.sql.Date(today.getTime()));
		order.setAmount(cartInfo.getAmountTotal());
		order.setTotalQuantity(cartInfo.getQuantityTotal());
		order.setCustomer(customer);
		order.setStatus(OrderStatus.RECEIVED);
		// TODO Auto-generated method stub
		orderRepository.save(order);
		List<CartLineInfo> lines = cartInfo.getCartLines();
		List<OrderDetail> orderDetail = new ArrayList();

		for (CartLineInfo line : lines) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setAmount(line.getAmount());
			detail.setPrice(line.getFoodInfo().getFoodPrice());
			detail.setQuantity(line.getQuantity());
			orderDetail.add(detail);
			Food food = this.foodRepository.findById(line.getFoodInfo().getFoodId()).get();
			detail.setFood(food);
			food.setOrderDetail(orderDetail);
			orderDetailRepository.save(detail);
		}
		// Set OrderNum for report.
		// Set OrderNum để thông báo cho người dùng.
		cartInfo.setOrderNum(order.getId());
		return null;
	}

	public Page<Order> findAll(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Order> pageOrder = orderRepository.findAll(pageable);
		return pageOrder;
	}

	public Order findOrderById(Long id) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		return optionalOrder.get();
	}
	

	public Order receivedOrder(Long id) {
		Order order = findOrderById(id);
		order.setStatus(OrderStatus.RECEIVED);
		return orderRepository.save(order);
	}

	public Order packedOrder(Long id) {
		Order order = findOrderById(id);
		order.setStatus(OrderStatus.PACKED);
		return orderRepository.save(order);
	}

	public Order deliveriedOrder(Long id) {
		Order order = findOrderById(id);
		order.setStatus(OrderStatus.DELIVERIED);
		return orderRepository.save(order);
	}

	public Order paidOrder(Long id) {
		Order order = findOrderById(id);
		order.setStatus(OrderStatus.PAID);
		return orderRepository.save(order);
	}

	public Order cancelOrder(Long id) {
		Order order = findOrderById(id);
		order.setStatus(OrderStatus.CANCEL);
		return orderRepository.save(order);
	}
	
}
