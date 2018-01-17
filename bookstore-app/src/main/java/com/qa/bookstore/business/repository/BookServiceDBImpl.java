package com.qa.bookstore.business.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.bookstore.persistence.Book;
import com.qa.bookstore.util.JSONUtil;

@Transactional(SUPPORTS)
public class BookServiceDBImpl implements BookService{

	private static final Logger LOGGER = Logger.getLogger(BookServiceDBImpl.class);

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;


	public String getAllBooks() {
		LOGGER.info("In get all books method from DB");
		Query query = em.createQuery("SELECT e FROM Book e");
		Collection<Book> books = (Collection<Book>) query.getResultList();
		return util.getJSONForObject(books);
	}


	@Transactional(REQUIRED)
	public String addNewBook(String bookJson) {
		Book newBook = util.getObjectForJSON(bookJson, Book.class);
		em.persist(newBook);
		return bookJson;
	}


	@Transactional(REQUIRED)
	public String deleteBook(Integer bookId) {
		Book book = findBook(new Long(bookId));
		if (book != null) {
			em.remove(book);
		}
		return "{\"message\": \"book sucessfully removed\"}";
	}

	private Book findBook(Long id) {
		return em.find(Book.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public JSONUtil getUtil() {
		return util;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
