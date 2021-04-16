package backend;

// Import Sql Package
import java.sql.*;

public class DbConnect {
    // Static Method for Connection
    static public Connection connection() {
        // Declaring Connection as null
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "root");
        } catch (SQLException e) {
            System.out.println("Database Connection Error");
            e.printStackTrace();
            return null;
        }
        return con;
    }

    // Return true if connection is working
    static public boolean isConnectionWorking() {
        Connection con = connection();
        return (con != null);
    }

}
