package expensetracker;
import java.util.Stack;
import java.sql.*;
import javax.swing.*;

//This section of code connects the database.
public class DisplayExpenses {
	public static Connection ConnectDB() {
		
		try
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Suashi\\ghubstuff\\githubtest\\expensetracker\\expensedata.db");
			JOptionPane.showMessageDialog(null, "Connection Made");
			return conn;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connection error");
			return null;
		}
	}
}
