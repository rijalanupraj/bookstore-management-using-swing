package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import backend.*;

class IsValidateDateTest {

	@Test
	void test() {
		String date1 = "2020-01-02";
		String date2 = "2020-2-2";
		String date3 = "2020/2/2";
		
		assertEquals(true, Utils.isValidDate(date1));
		assertEquals(true, Utils.isValidDate(date2));
		assertEquals(false, Utils.isValidDate(date3));
		
	
	}

}
