package com.qa.bookstore.business.repository;

public interface BookService {
	String getAllBooks();

	String addNewBook(String bookJson);

	String deleteBook(Integer bookId);

}
