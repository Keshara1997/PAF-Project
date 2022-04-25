package model;

import java.sql.*;

public class Electricity
{ 
	//A common method to connect to the DB
	private Connection connect()
	 {
		 Connection con = null;
		 
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	 }
	
	//Insert
	public String insertElectricityConnectionRequests(String name, String nic, String phone, String emailadd, String address, String postalcode, String typeofservice, String usagepurpose)
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into electricityconnectionrequests (`requestId`,`fullName`,`nationalIdentityCardNo`,`phoneNumber`,`email`,`newElectricityConnectionAddress`,`postalCodeOfArea`,`serviceType`,`purpose`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, nic);
			 preparedStmt.setString(4, phone);
			 preparedStmt.setString(5, emailadd);
			 preparedStmt.setString(6, address);
			 preparedStmt.setString(7, postalcode);
			 preparedStmt.setString(8, typeofservice);
			 preparedStmt.setString(9, usagepurpose);
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the electricity connection request.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String readElectricityConnectionRequests()
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Request ID</th><th>Full Name</th><th>National Identity Card No</th>" +
					 "<th>Phone Number</th>" +
					 "<th>Email</th>" +
					 "<th>Address of the premises where electricity supply is required</th>" +
					 "<th>Postal Code of Area</th>" +
					 "<th>Type of Service</th>" +
					 "<th>Purpose of Electricity Usage</th>" +
					 "<th>Update</th><th>Remove</th></tr>";
			
			 String query = "select * from electricityconnectionrequests";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String requestId = Integer.toString(rs.getInt("requestId"));
				 String fullName = rs.getString("fullName");
				 String nationalIdentityCardNo = rs.getString("nationalIdentityCardNo");
				 String phoneNumber = rs.getString("phoneNumber");
				 String email = rs.getString("email");
				 String newElectricityConnectionAddress = rs.getString("newElectricityConnectionAddress");
				 String postalCodeOfArea = rs.getString("postalCodeOfArea");
				 String serviceType = rs.getString("serviceType");
				 String purpose = rs.getString("purpose");
				 
				 // Add into the html table
				 output += "<tr><td>" + requestId + "</td>";
				 output += "<td>" + fullName + "</td>";
				 output += "<td>" + nationalIdentityCardNo + "</td>";
				 output += "<td>" + phoneNumber + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + newElectricityConnectionAddress + "</td>";
				 output += "<td>" + postalCodeOfArea + "</td>";
				 output += "<td>" + serviceType + "</td>";
				 output += "<td>" + purpose + "</td></tr>";
				 
				 // buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='electricityrequests.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='requestId' type='hidden' value='" + requestId + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
			 output = "Error while reading the items.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	//update
	public String updateElectricityConnectionRequests(String ID, String name, String nic, String phone, String emailadd, String address, String postalcode, String typeofservice, String usagepurpose)
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE electricityconnectionrequests SET fullName=?,nationalIdentityCardNo=?,phoneNumber=?,email=?,newElectricityConnectionAddress	=?,postalCodeOfArea=?,serviceType=?,purpose=? WHERE requestId=?";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, name);
			 preparedStmt.setString(2, nic);
			 preparedStmt.setString(3, phone);
			 preparedStmt.setString(4, emailadd);
			 preparedStmt.setString(5, address);
			 preparedStmt.setString(6, postalcode);
			 preparedStmt.setString(7, typeofservice);
			 preparedStmt.setString(8, usagepurpose);
			 preparedStmt.setInt(9, Integer.parseInt(ID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the electricity connection request.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
	}
	
	// delete
	public String deleteElectricityConnectionRequests(String requestId )
	{
		 String output = "";
		 
		 try
		 {
		 
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from electricityconnectionrequests where requestId=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(requestId ));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the item.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	}