package com.jhi.book.book.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhi.book.book.model.dto.Book;
import com.jhi.book.book.model.mapper.BookMapper;

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
	public int add(String bookTitle, String bookWriter, int bookPrice) {

		Book book = new Book();
		book.setBookTitle(bookTitle);
		book.setBookWriter(bookWriter);
		book.setBookPrice(bookPrice);
		
		return mapper.addBook(book);
	}



}
