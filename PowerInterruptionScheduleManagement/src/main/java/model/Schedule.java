package model;

import java.sql.*;

public class Schedule
{ 
	//A common method to connect to the DB
	private Connection connect()
	 {
		 Connection con = null;
		 
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		
		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powerinterruptionscheduledb", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 
		 return con;
	 }
	
	//Insert
	public String insertPowerInterruptionSchedules(String province_s, String area_s, String group_s, String day_s, String month_s, String startday_s, String endday_s, String year_s, String starttime_s, String endtime_s)
	 {
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for inserting."; }
			 
			 // create a prepared statement
			 String query = " insert into poweinterruptionschedules (`scheduleID`,`province`,`area`,`sGroup`,`day`,`month`,`startDay`,`endDay`,`year`,`startTime`,`endTime`)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, province_s);
			 preparedStmt.setString(3, area_s);
			 preparedStmt.setString(4, group_s);
			 preparedStmt.setString(5, day_s);
			 preparedStmt.setString(6, month_s);
			 preparedStmt.setString(7, startday_s);
			 preparedStmt.setString(8, endday_s);
			 preparedStmt.setString(9, year_s);
			 preparedStmt.setString(10, starttime_s);
			 preparedStmt.setString(11, endtime_s);
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while inserting the power interruption schedule.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String readPowerInterruptionSchedules()
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>Schedule ID</th><th>Province</th><th>Area</th><th>Group</th>" +
					 "<th>Day</th>" +
					 "<th>Month</th>" +
					 "<th>From</th>" +
					 "<th>To</th>" +
					 "<th>Year</th>" +
					 "<th>Time From</th>" +
					 "<th>Time To</th></tr>";
			
			 String query = "select * from poweinterruptionschedules";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String scheduleID = Integer.toString(rs.getInt("scheduleID"));
				 String province = rs.getString("province");
				 String area = rs.getString("area");
				 String sGroup = rs.getString("sGroup");
				 String day = rs.getString("day");
				 String month = rs.getString("month");
				 String startDay = rs.getString("startDay");
				 String endDay = rs.getString("endDay");
				 String year = rs.getString("year");
				 String startTime = rs.getString("startTime");
				 String endTime = rs.getString("endTime");
				 
				 // Add into the html table
				 output += "<tr><td>" + scheduleID + "</td>";
				 output += "<td>" + province + "</td>";
				 output += "<td>" + area + "</td>";
				 output += "<td>" + sGroup + "</td>";
				 output += "<td>" + day + "</td>";
				 output += "<td>" + month + "</td>";
				 output += "<td>" + startDay + "</td>";
				 output += "<td>" + endDay + "</td>";
				 output += "<td>" + year + "</td>";
				 output += "<td>" + startTime + "</td>";
				 output += "<td>" + endTime + "</td></tr>";
				 
				 // buttons
				 //output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='electricityrequests.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='scheduleID' type='hidden' value='" + scheduleID + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
		 }
		 catch (Exception e)
		 {
			 output = "Error while reading the power interuption schedules.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	//update
	public String updatePowerInterruptionSchedules(String ID, String province_s, String area_s, String group_s, String day_s, String month_s, String startday_s, String endday_s, String year_s, String starttime_s, String endtime_s)
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE poweinterruptionschedules SET province=?,area=?,sGroup=?,day=?,month=?,startDay=?,endDay=?,year=?,startTime=?,endTime=? WHERE scheduleID=?";
			 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, province_s);
			 preparedStmt.setString(2, area_s);
			 preparedStmt.setString(3, group_s);
			 preparedStmt.setString(4, day_s);
			 preparedStmt.setString(5, month_s);
			 preparedStmt.setString(6, startday_s);
			 preparedStmt.setString(7, endday_s);
			 preparedStmt.setString(8, year_s);
			 preparedStmt.setString(9, starttime_s);
			 preparedStmt.setString(10, endtime_s);
			 preparedStmt.setInt(11, Integer.parseInt(ID));
			 
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
	public String deletePowerInterruptionSchedules(String scheduleID )
	{
		 String output = "";
		 
		 try
		 {
		 
			 Connection con = connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from poweinterruptionschedules where scheduleID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(scheduleID ));
			 
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