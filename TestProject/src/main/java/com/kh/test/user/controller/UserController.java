package com.kh.test.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.test.user.model.dto.User;
import com.kh.test.user.model.service.UserService;

@Controller
public class UserController {
	
	
	@Autowired
	private UserService service;

	@PostMapping("/selectUser")
	public String selectUser(
			@RequestParam("userId") String userId, Model model
			) {
		
		User user = service.selectUser(userId);
		String path = null;
		
		if(user != null) {
			path = "/searchSuccess";
			model.addAttribute("user", user);
		} else {
			path = "/searchFail";
		}
		return path;
	}

}
