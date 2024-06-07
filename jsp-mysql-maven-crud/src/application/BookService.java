package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookService {
	
	PreparedStatement pepStmt;
	
	public boolean deletBook(String bookId) {
		   try
	        {
			   pepStmt = getConnection().prepareStatement("delete from book where id = ? ");
			   pepStmt.setInt(1, Integer.parseInt(bookId));
			   pepStmt.executeUpdate();
	            
	        }catch (Exception e) {
	        	return false;
	        }
		   
			return true;
	}

	public boolean insertBook(Book bookObj) {
		
		String query = "insert into book values(" + bookObj.getId() + ",'" + bookObj.getName() + "','"
				+ bookObj.getType()+ "')";
		
		Connection conn = getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			System.out.println(query);
			st.executeUpdate(query);
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return true;
	}

	public void showBooks() {
		ObservableList<Book> list = getBooksList();
		System.out.print("BookData: " + list);

	}

	public ObservableList<Book> getBooksList() {
		ObservableList<Book> booksList = FXCollections.observableArrayList();
		Connection connection = getConnection();
		String query = "SELECT * FROM book ";
		Statement st;
		ResultSet rs;

		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			Book bookObj;
			while (rs.next()) {
				bookObj = new Book(rs.getLong("id"), rs.getString("name"), rs.getString("type"));
				booksList.add(bookObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booksList;
	}

	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/springbootdb", "root", "root");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
