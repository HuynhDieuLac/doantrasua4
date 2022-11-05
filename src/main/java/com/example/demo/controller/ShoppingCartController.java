package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Food;
import com.example.demo.service.FoodService;

import com.example.demo.service.OrderService;

import Utils.Utils;
import model.CartInfo;
import model.CustomerInfo;
import model.FoodInfo;

@Controller
@RequestMapping(value = "/shop")
public class ShoppingCartController {

	@Autowired
	private FoodService foodService;

	@Autowired
	private OrderService orderService;

	@RequestMapping({ "/buyFood" })
	public String listFoodHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "id", defaultValue = "") Long id) {
		Food foods = null;

		if (id != null) {
			foods = foodService.get(id);
		}

		if (foods != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			FoodInfo foodInfo = new FoodInfo(foods);
			cartInfo.addFood(foodInfo, 1);
		}
		return "redirect:/shop/shoppingCart";
	}

	// POST: Update quantity for product in cart
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
	public String shoppingCartUpdateQty(HttpServletRequest request, //
			Model model, //

			@ModelAttribute("cartForm") CartInfo cartForm)

	{
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.updateQuantity(cartForm);
		return "shoppingCart";
	}

	// GET: Show cart.
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		CartInfo myCart = Utils.getCartInSession(request);
		model.addAttribute("cartForm", myCart);
		return "shoppingCart";
	}

	@RequestMapping({ "/shoppingCartRemoveFood" })
	public String removeFoodHandler(HttpServletRequest request, Model model,
			//
			@RequestParam(value = "id", defaultValue = "") Long id) {
		Food foods = null;
		if (id != null) {
			foods = foodService.get(id);
		}
		if (foods != null) {
			CartInfo cartInfo = Utils.getCartInSession(request);
			FoodInfo foodInfo = new FoodInfo(foods);
			cartInfo.removeProduct(foodInfo);
		}
		return "redirect:/shoppingCart";
	}

	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
		}		
		model.addAttribute("customerInfo", customerInfo);
		return "shop/cart/checkOut";
	}

// POST: Save customer information.
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	public String shoppingCartCustomerSave(@ModelAttribute("customerInfo") CustomerInfo customerInfo,
			HttpServletRequest request, Model model) {
// HttpSession session = request.getSession();
		CartInfo cartInfo = Utils.getCartInSession(request);
		cartInfo.setCustomerInfo(customerInfo);
		String error = orderService.addOrder(cartInfo);
		if (error != null) {
// request.setAttribute("errorMessage", error);
			model.addAttribute("errorMessage", error);
			return "shop/cart/checkOut";
		} else {
// request.setAttribute("errorMessage", null);
		}
		return "redirect:/shop/shoppingCartConfirmation";
	}

//GET: Show information to confirm.
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (cartInfo == null || cartInfo.isEmpty()) {
			return "redirect:/shop/shoppingCart";
		}
		model.addAttribute("myCart", cartInfo);
		return "shop/cart/shoppingCartConfirmation";
	}

//POST: Submit Cart (Save)
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
	public String shoppingCartConfirmationSave(HttpServletRequest request) {
		CartInfo cartInfo = Utils.getCartInSession(request);
		if (cartInfo.isEmpty()) {
			return "redirect:/shop/shoppingCart";
		}
// Remove Cart from Session.
		Utils.removeCartInSession(request);
// Store last cart.
		Utils.storeLastOrderedCartInSession(request, cartInfo);
		return "redirect:/shop/shoppingCartFinalize";
	}

	@RequestMapping(value = { "/shoppingCartFinalize" }, method = RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request, Model model) {
		CartInfo lastOrderedCart = Utils.getLastOrderedCartInSession(request);
		if (lastOrderedCart == null) {
			return "redirect:/shop/shoppingCart";
		}
		model.addAttribute("lastOrderedCart", lastOrderedCart);
		return "shop/cart/shoppingCartFinalize";
	}
}
