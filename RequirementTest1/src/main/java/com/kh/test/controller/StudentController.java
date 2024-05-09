package com.kh.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.test.model.dto.Student;
import com.kh.test.model.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("stu")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService service;
	
//	@GetMapping("index")
//	public String stuMain() {
//		return "stu/index";
//	}
	
	@ResponseBody
	@GetMapping("selectAll")
	public List<Student> selectAll() {
		List<Student> stuList = service.selectAll();
		
		return stuList;
	}

}
