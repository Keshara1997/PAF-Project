package model;


import java.sql.*;
public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");     

			
			// Provide the correct details: DBServer/DBName, username, password
			
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/powereg","root", ""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertPayment(String billID, String cardHolder, String cardNo, String cvv, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement
			String query = " insert into payment( paymentID, billID, cardHolder, cardNo, cvv, amount)"
					+ " values( ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, billID);
			preparedStmt.setString(3, cardHolder);
			preparedStmt.setInt(4, Integer.parseInt(cardNo));
			preparedStmt.setInt(5, Integer.parseInt(cvv));
			preparedStmt.setDouble(6, Double.parseDouble(amount));
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while Inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
	
	
	//reading payments
	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Bill ID</th><th>Card Holder's Name</th>" + "<th>Card No</th>"
					+ "<th>CVV</th>" + "<th>Amount</th>"+"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String billID = rs.getString("billID");
				String cardHolder = rs.getString("cardHolder");
				String cardNo = rs.getString("cardNo");
				String cvv = rs.getString("cvv");
				String amount = Double.toString(rs.getDouble("amount"));
				
				// Add into the html table
				output += "<tr><td>" + billID + "</td>";
				output += "<td>" + cardHolder + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + amount + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;	
	}
		
	//updating payments
	public String updatePayment(String paymentID, String billID, String cardHolder, String cardNo, String cvv, String amount)

	{
		String output = "";
		
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payment SET billID=?,cardHolder=?,cardNo=?,cvv=?, amount=? WHERE paymentID=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, billID);
			preparedStmt.setString(2, cardHolder);
			preparedStmt.setString(3, cardNo);
			preparedStmt.setString(4, cvv);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setInt(6, Integer.parseInt(paymentID));
			// execute the statement
			 preparedStmt.execute();
			con.close();
			
			output = "Payment updated successfully";
			
			
		} catch (Exception e) {
			
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
			
		}
		return output;
	}
	
	
	//deleting payments
			public String deletePayment(String paymentID) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for deleting.";
					}
					// create a prepared statement
					String query = "delete from payment where paymentID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(paymentID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
				} catch (Exception e) {
					output = "Error while deleting the Payment.";
					System.err.println(e.getMessage());
				}
				return output;
		
		}
		
}
