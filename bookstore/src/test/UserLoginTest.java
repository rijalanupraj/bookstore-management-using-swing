package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.*;
class UserLoginTest {

	 @Test
	    void loginTest() {
		 	User userObj = new User("admin", "admin");
	        boolean actualOutput = userObj.checkCredentials();
	        assertEquals(true, actualOutput);
	    }


}















    
   