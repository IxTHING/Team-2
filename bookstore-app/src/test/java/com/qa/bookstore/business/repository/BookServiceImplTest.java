package com.qa.bookstore.business.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.bookstore.persistence.Book;
import com.qa.bookstore.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

	@InjectMocks
	BookServiceImpl impl;

	@Mock
	private JSONUtil util;

	@Test
	public void testAddNewBook() {
		impl.setUtil(util);
		String testJsonString = "{\"author\": \"James Herbert\",\"title\": \"Rats3\",\"genre\": \"Horror\"}";
		Book aBook = new Book("James Herbert", "Rats3", "Horror");
		Mockito.when(util.getObjectForJSON(testJsonString, Book.class)).thenReturn(aBook);
		Assert.assertEquals(impl.getBookMap().size(), 1);
		String booksTitle = impl.addNewBook(testJsonString);
		Assert.assertEquals(booksTitle, "Rats3");
		Assert.assertEquals(impl.getBookMap().size(), 2);
		Mockito.verify(util).getObjectForJSON(testJsonString, Book.class);
		Mockito.verify(util, Mockito.never()).getJSONForObject(Mockito.anyString());

	}

}
