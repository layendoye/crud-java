package testJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Bdd {
	private Connection connection;
	private PreparedStatement prepStatement;
	private ResultSet resultSet;
	private int status;
	private void getConnection() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testJavaEntreprise", "root" , "101419");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public void initPrepar(String sql) {
		try {
			getConnection();
			prepStatement=connection.prepareStatement(sql);
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public ResultSet executeSelect() {
		try {
			resultSet=prepStatement.executeQuery();
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return resultSet;
	}
	public int execteMaj() {
		try {
			status=prepStatement.executeUpdate();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		return status;
	}
	public void closeConnection() {
		try {
			if(connection != null)
				connection.close();
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	public PreparedStatement getPrepStatement() {
		return prepStatement;
	}
}
