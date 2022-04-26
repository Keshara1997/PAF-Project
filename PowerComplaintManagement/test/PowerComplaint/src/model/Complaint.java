package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Complaint {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electro?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertComplaint(String PerName, String PerNIC, String cArea, String cAccNo, String cAddress, String cEmal, String Comp) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into complaint(`cID`, `PerName`, `PerNIC`, `cArea`, `cAccNo`, `cAddress`, `cEmal`, `Comp`)" + " values ( ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, PerName);
			preparedStmt.setString(3, PerNIC);
			preparedStmt.setString(4, cArea);
			preparedStmt.setString(5, cAccNo);
			preparedStmt.setString(6, cAddress);
			preparedStmt.setString(7, cEmal);
			preparedStmt.setString(8, Comp);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the complaint.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readComplaint() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Complaint ID</th><th>Person Name</th><th>Complaint NIC</th><th>Area</th><th>Account No</th><th>Address</th><th>Email</th><th>Complaint</th></tr>";
			String query = "select * from complaint";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cID = Integer.toString(rs.getInt("cID"));
				String PerName = rs.getString("PerName");
				String PerNIC = rs.getString("PerNIC");
				String cArea = rs.getString("cArea");
				String cAccNo = rs.getString("cAccNo");
				String cAddress = rs.getString("cAddress");
				String cEmal = rs.getString("cEmal");
				String Comp = rs.getString("Comp");

				output += "<tr><td>" + cID + "</td>";
				output += "<td>" + PerName + "</td>";
				output += "<td>" + PerNIC + "</td>";
				output += "<td>" + cArea + "</td>";
				output += "<td>" + cAccNo + "</td>";
				output += "<td>" + cAddress + "</td>";
				output += "<td>" + cEmal + "</td>";
				output += "<td>" + Comp + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the complaint.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateComplaint(String cID, String PerName, String PerNIC, String cArea, String cAccNo, String cAddress, String cEmal, String Comp) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE complaint SET PerName=?,PerNIC=?,cArea=?,cAccNo=?,cAddress=?,cEmal=?,Comp=? WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, PerName);
			preparedStmt.setString(2, PerNIC);
			preparedStmt.setString(3, cArea);
			preparedStmt.setString(4, cAccNo);
			preparedStmt.setString(5, cAddress);
			preparedStmt.setString(6, cEmal);
			preparedStmt.setString(7, Comp);
			preparedStmt.setInt(8, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the complaint.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deleteComplaint(String cID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from complaint where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the complaint.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
