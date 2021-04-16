package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import backend.*;

class LinearSearchTest {
	private ArrayList<Book> allBooks;;
	private ArrayList<Book> expectedOutput;

	@BeforeEach
	void setUp() throws Exception {
		Book book1 = new Book(1, "123", "harry", "jk rowling", "sk", "2010-10-10", 40.3, 20, 20);
		Book book2 = new Book(2, "345", "Harry", "anup", "sem", "2020-11-10", 100.5, 90, 1000);
		Book book3 = new Book(3, "789", "AngelandDemons", "anup", "sk", "2010-09-10", 200.0, 10, 20);

		// Array List of books
		allBooks = new ArrayList<Book>();
		allBooks.add(book1);
		allBooks.add(book2);
		allBooks.add(book3);

		// Expected output after Search
		expectedOutput = new ArrayList<Book>();
		expectedOutput.add(book1);
		expectedOutput.add(book2);
	}

	@Test
	void linearSearch() {
		ArrayList<Book> actualOutput = Book.linearSearch(allBooks, "harry", "title");
		assertEquals(expectedOutput, actualOutput);
	}

}
