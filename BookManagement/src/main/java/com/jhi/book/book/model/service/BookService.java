package com.jhi.book.book.model.service;

import java.util.List;
import java.util.Map;

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
	int addBook(String bookTitle, String bookWriter, int bookPrice);



	/** 검조수하는 곳에서 조회
	 * @return
	 */
	Map<String, Object> selectAll();



	/** 삭제하기
	 * @param bookNo
	 * @return
	 */
	int deleteBook(int bookNo);

}
