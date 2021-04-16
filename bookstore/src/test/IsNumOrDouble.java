package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.*;
class IsNumOrDouble {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		String value1 = "20";
		String value2 = "40.2";
		String value3 = "2Ram";
		
		assertEquals(true, Utils.isNumberOrDouble(value1));
		assertEquals(true, Utils.isNumberOrDouble(value2));
		assertEquals(false, Utils.isNumberOrDouble(value3));
	}

}
