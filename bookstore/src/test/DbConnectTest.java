package test;

import backend.*;

//Import Sql Package
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbConnectTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void connectionTest() {
		Connection actualOutput = DbConnect.connection();
		 assertNotNull(actualOutput);
	}

}
