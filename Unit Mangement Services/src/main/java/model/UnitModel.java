package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBConnect;

public class UnitModel {

	Connection connection;
	private String res;
	
	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public void add_unit(int cus_id,String cus_name,String cus_phone,int new_read,int last_read) {
		PreparedStatement statement;
		
		try {
			connection = DBConnect.getDB();
			
			statement = connection.prepareStatement("insert into unit (cus_id,cus_name,cus_phone,new_read,last_read,used_units) values (?,?,?,?,?,?)");
			statement.setInt(1, cus_id);
			statement.setString(2, cus_name);
			statement.setString(3, cus_phone);
			statement.setInt(4, new_read);
			statement.setInt(5, last_read);
			statement.setInt(6, new_read-last_read);
			statement.execute();
			statement.close();
			connection.close();
			setRes("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setRes("error");
		}
	}
	
	public String get_unit() {
		PreparedStatement statement;
		String data="";
		
		try {
			
			connection = DBConnect.getDB();
			statement = connection.prepareStatement("SELECT * FROM unit");
			
			ResultSet resultSet = statement.executeQuery();
			
			data = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Customer ID</th>"
	                +"<th style='border: 1px solid black;'>Customer Name</th>"
	                +"<th style='border: 1px solid black;'>Customer Phone</th>"
	                +"<th style='border: 1px solid black;'>New Read</th>"
	                +"<th style='border: 1px solid black;'>Last Read</th>"
	                +"<th style='border: 1px solid black;'>Used Units</th>"
	                +"<th style='border: 1px solid black;'>Action</th>"
	                +"</tr>"
	            +"</thead><tbody>";
			
			while (resultSet.next()) {
				
				data = data+"<tr><td style='border: 1px solid black;'>"+resultSet.getString(1)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(2)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(3)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(4)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(5)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(6)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(7)+"</td>"
						+ "<td style='border: 1px solid black;'>"+resultSet.getString(8)+"</td>"
						+ "<td style='border: 1px solid black;'><button type='button' onclick=''>Delete</button></td>"
					  + "</tr>";
				
			}
			
			statement.close();
			connection.close();
			
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
		}
		
		return data+"</table>";
	}

	public void edit_unit(int id,int cus_id,String cus_name,String cus_phone,int new_read,int last_read) {
		PreparedStatement statement;
		
		try {
			connection = DBConnect.getDB();
			
				statement = connection.prepareStatement("UPDATE unit SET cus_id=?,cus_name=?,cus_phone=?,new_read=?,last_read=?,used_units=? where id=?");
				statement.setInt(1, cus_id);
				statement.setString(2, cus_name);
				statement.setString(3, cus_phone);
				statement.setInt(4, new_read);
				statement.setInt(5, last_read);
				statement.setInt(6, new_read-last_read);
				statement.setInt(7,id);
				statement.execute();
				statement.close();
				connection.close();
				setRes("success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setRes("error");
		}
	}

	public void delete_unit(int id) {
		PreparedStatement statement;
		
		try {
			connection = DBConnect.getDB();
			
			statement = connection.prepareStatement("DELETE FROM unit WHERE id=?");
			statement.setInt(1, id);
			statement.execute();
			
			setRes("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setRes("error");
		}
	}
	
}
