package com.jhi.book.book.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jhi.book.book.model.dto.Book;

@Mapper
public interface BookMapper {

	/** 책 목록 조회
	 * @return bookList
	 */
	public List<Book> selectBookList();

	/** 책 등록하기
	 * @param book
	 * @return
	 */
	int addBook(Book book);

	public List<Book> selectAll();

	public int deleteBook(int bookNo);

}
