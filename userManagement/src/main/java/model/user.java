package model;

import java.sql.*;

public class User
{ 
	//A common method to connect to the DB
	private Connection connect()
	 {
		 Connection con = null;
		 
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	 }
	
	//Insert
	public String insertUsers(String Name_u, String phone_u, String Email_u, String UserN_u, String Password_u)
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into users(`userID`,`name`,`phoneNum`,`email`,`userName`,`password`)" + " values (?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, Name_u);
			 preparedStmt.setString(3, phone_u);
			 preparedStmt.setString(4, Email_u);
			 preparedStmt.setString(5, UserN_u);
			 preparedStmt.setString(6, Password_u);
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the users.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String readUsers()
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User ID</th><th>Name</th><th>Phone number</th>" +
					 "<th>Email</th>" +
					 "<th>User name</th>" +
					 "<th>Password</th></tr>";
			
			 String query = "select * from users";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String userID = Integer.toString(rs.getInt("userID"));
				 String name = rs.getString("name");
				 String phoneNum = rs.getString("phoneNum");
				 String email = rs.getString("email");
				 String userName = rs.getString("userName");
				 String password = rs.getString("password");
				 
				 // Add into the html table
				 output += "<tr><td>" + userID + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + phoneNum + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + userName + "</td>";
				 output += "<td>" + password + "</td></tr>";
				 
				 // buttons
				 //output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='electricityrequests.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='userID' type='hidden' value='" + userID + "'>" + "</form></td></tr>";
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
	public String updateUsers(String ID, String Name_u, String phone_u, String Email_u, String UserN_u, String Password_u)
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE users SET name=?,phoneNum=?,email=?,userName=?,password=? WHERE userID=?";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, Name_u);
			 preparedStmt.setString(2, phone_u);
			 preparedStmt.setString(3, Email_u);
			 preparedStmt.setString(4, UserN_u);
			 preparedStmt.setString(5, Password_u);
			 preparedStmt.setInt(6, Integer.parseInt(ID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the user.";
			 System.err.println(e.getMessage());
		 }
		 
		 return output;
		 
	}
	
	// delete
	public String deleteUsers(String userID )
	{
		 String output = "";
		 
		 try
		 {
		 
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from users where userID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(userID ));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the user.";
			 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	}