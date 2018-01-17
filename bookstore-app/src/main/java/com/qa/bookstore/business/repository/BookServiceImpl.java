package com.qa.bookstore.business.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.bookstore.persistence.Book;
import com.qa.bookstore.util.JSONUtil;

@ApplicationScoped
@Alternative
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = Logger.getLogger(BookServiceImpl.class);
	private final int INITIAL_COUNT = 1;
	private Map<Integer, Book> bookMap;
	private int ID;

	@Inject
	private JSONUtil util;

	public BookServiceImpl() {
		this.bookMap = new HashMap<Integer, Book>();
		ID = INITIAL_COUNT;
		initBookStore();
	}

	public String getAllBooks() {
		return util.getJSONForObject(bookMap.values());
	}

	public String addNewBook(String bookJson) {
		ID++;
		Book newBook = util.getObjectForJSON(bookJson, Book.class);
		LOGGER.info("In add book method about to add to map");
		bookMap.put(ID, newBook);
		LOGGER.info("In add book method book added to map");
		return newBook.getTitle();
	}

	public String deleteBook(Integer bookId) {
		LOGGER.info("In delete book method about to remove book");
		bookMap.remove(bookId);
		LOGGER.info("In delete book method book removed");
		return "{\"message\": \"book sucessfully removed\"}";
	}

	private void initBookStore() {
		Book aBook = new Book("James Herbert", "Rats", "Horror");
		bookMap.put(1, aBook);
	}

	public Map<Integer, Book> getBookMap() {
		return bookMap;
	}

	public void setBookMap(Map<Integer, Book> bookMap) {
		this.bookMap = bookMap;
	}

	public JSONUtil getUtil() {
		return util;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
