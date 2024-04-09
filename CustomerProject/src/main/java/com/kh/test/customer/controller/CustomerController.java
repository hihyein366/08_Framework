package com.kh.test.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.test.customer.model.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	@PostMapping("add")
	public String addCustomer(
		@RequestParam("customerName") String customerName,
		@RequestParam("customerTel") String customerTel,
		@RequestParam("customerAddress") String customerAddress,
		Model model
		) {
		int result = service.addCustomer(customerName, customerTel, customerAddress);
		
		
		if(result > 0) {
			model.addAttribute("customerName", customerName);
		}
		return "result";
	}
	

}
