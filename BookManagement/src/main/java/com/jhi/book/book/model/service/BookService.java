package com.jhi.book.book.model.service;

import java.util.List;

import com.jhi.book.book.model.dto.Book;

public interface BookService {

	/** 책 목록 조회하기
	 * @return bookList
	 */
	List<Book> selectBookList();



	/** 책 등록하기
	 * @param bookTitle
	 * @param bookWriter
	 * @param bookPrice
	 * @return
	 */
	int add(String bookTitle, String bookWriter, int bookPrice);

}
