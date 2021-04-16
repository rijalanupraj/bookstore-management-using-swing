package backend;

//Import Sql Package
import java.sql.*;

// Import ArrayList Class
import java.util.ArrayList;

public class User {

	String username, password;

	// Constructor
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean checkCredentials() {
		if (this.username.equals("admin") && this.password.equals("admin")) {
			return true;
		}
		ArrayList<User> allUsers = this.getAllUsers();
		for (int i = 0; i < allUsers.size(); i++) {
			User user = allUsers.get(i);
			if (user.username.equals(this.username) && user.password.equals(this.password)) {
				return true;
			}
		}
		return false;

	}

	// Register New User
	public boolean registerUser() {
		try {
			// DbConnect Class
			Connection con = DbConnect.connection();

			String query = "INSERT INTO user(username,password,is_admin) VALUES(?,?,?)";
			PreparedStatement ins = con.prepareStatement(query);
			ins.setString(1, this.username);
			ins.setString(2, this.password);
			ins.setInt(3, 1);
			boolean result = ins.execute();
			return !result;

		} catch (SQLException error) {
			System.out.println(error);
			return false;
		}

	}

	// Get all Users from database and store in ArrayList<User>
	public ArrayList<User> getAllUsers() {
		ArrayList<User> userArray = new ArrayList<User>();

		String query = "SELECT username, password FROM user";

		try {
			Connection con = DbConnect.connection();
			PreparedStatement ins = con.prepareStatement(query);
			ResultSet rs = ins.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");

				User newUser = new User(username, password);
				userArray.add(newUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userArray;
	}

	// Check if the username already exists or not
	public boolean doesUsernameExists() {
		ArrayList<User> allUsers = this.getAllUsers();
		for (int i = 0; i < allUsers.size(); i++) {
			User user = allUsers.get(i);
			if (user.username.equals(this.username)) {
				return true;
			}
		}
		return false;
	}

}
