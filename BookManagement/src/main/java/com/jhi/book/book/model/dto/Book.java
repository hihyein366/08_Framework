package com.jhi.book.book.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private int bookNo;
	private String bookTitle;
	private String bookWriter;
	private int bookPrice;
	private String regDate;
}
