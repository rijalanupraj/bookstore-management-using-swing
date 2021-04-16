package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*;

class SortTest {
	private ArrayList<Book> allBooks;
	Object[][] expectedOutput = new Object[3][9];

	@BeforeEach
	void setUp() throws Exception {
		Book book1 = new Book(1, "123", "davinci", "jk rowling", "sk", "2010-10-10", 40.3, 20, 20);
		Book book2 = new Book(2, "345", "Harry", "anup", "sem", "2020-11-10", 100.5, 90, 1000);
		Book book3 = new Book(3, "789", "AngelandDemons", "anup", "sk", "2010-09-10", 200.0, 10, 20);

		// Array List of books
		allBooks = new ArrayList<Book>();
		allBooks.add(book1);
		allBooks.add(book2);
		allBooks.add(book3);

		// Expected output after Search
		ArrayList<Book> expected = new ArrayList<Book>();
		expected.add(book3);
		expected.add(book1);
		expected.add(book2);
		expectedOutput = Book.getBooksInObjects(expected);
	}

	@Test
	void sortTest() {
		Object[][] actualOutput = Book.performSortOperation(allBooks, 2);
		assertArrayEquals(expectedOutput, actualOutput);
	}
}
