package com.jhi.book.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jhi.book.book.model.dto.Book;
import com.jhi.book.book.model.service.BookService;

@Controller
@RequestMapping("book")
public class BookController {
	
	@Autowired
	private BookService service;

	
	
	/** 책 목록 조회
	 * @return
	 */
	@ResponseBody
	@GetMapping("selectBookList")
	public List<Book> selectBookList() {
		
		return service.selectBookList();
	}
	
	@ResponseBody
	@GetMapping("searchBookList")
	public List<Book> searchBookList() {
		
		return service.selectBookList();
	}
	
	
	/** 책 등록 페이지 이동
	 * @return
	 */
	@GetMapping("add")
	public String addBook() {
		return "book/book-add";
	}
	
	/** 책 등록 하기
	 * @param book
	 * @return result
	 */
	@PostMapping("add")
	public String addBook(
		@RequestParam("bookTitle") String bookTitle,
		@RequestParam("bookWriter") String bookWriter,
		@RequestParam("bookPrice") int bookPrice,
		RedirectAttributes ra
		) {
		int result = service.addBook(bookTitle, bookWriter, bookPrice);
		
		String path = null;
		String message = null;
		if(result > 0) {
			message = "등록 성공";
			path = "/";
		} else {
			message = "등록 실패";
			path = "add";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	@GetMapping("update")
	public String updateBook() {
		return "book/book-update";
	}
	
	@GetMapping("delete")
	public int deleteBook(@RequestBody int bookNo) {
		return service.deleteBook(bookNo);
	}

	
}
