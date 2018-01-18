package com.qa.team2.business.repository;

public interface BookService {
	String getAllBooks();

	String addNewBook(String bookJson);

	String deleteBook(Integer bookId);

}
