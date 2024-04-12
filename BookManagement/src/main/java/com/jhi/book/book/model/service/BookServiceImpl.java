package com.jhi.book.book.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhi.book.book.model.dto.Book;
import com.jhi.book.book.model.mapper.BookMapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper mapper;

	
	// 책 목록 조회
	@Override
	public List<Book> selectBookList() {
		return mapper.selectBookList();
	}


	// 책 등록하기
	@Override
	public int addBook(String bookTitle, String bookWriter, int bookPrice) {

		Book book = new Book();
		book.setBookTitle(bookTitle);
		book.setBookWriter(bookWriter);
		book.setBookPrice(bookPrice);
		
		return mapper.addBook(book);
	}


	@Override
	public Map<String, Object> selectAll() {
		
		List<Book> bookList = mapper.selectAll();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("bookList", bookList);

		return map;
	}


	@Override
	public int deleteBook(int bookNo) {
		return mapper.deleteBook(bookNo);
	}



}
