package model;

import db.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class monthly_bill_Model {

	Connection connection;
	private String res;
	
	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public void add_monthly_bill(int cus_id,String cus_name,String cus_address,String month,int used_unit,double unit_price) {
		PreparedStatement statement;
		
		try {
			double total_price;
			connection = DBConnect.getDB();
			
			statement = connection.prepareStatement("insert into monthly_bill (cus_id,cus_name,cus_address,month,used_unit,unit_price,total_price) values (?,?,?,?,?,?,?)");
			statement.setInt(1, cus_id);
			statement.setString(2, cus_name);
			statement.setString(3, cus_address);
			statement.setString(4, month);
			statement.setInt(5, used_unit);
			statement.setDouble(6, unit_price);
			if(used_unit<=90) {
				total_price=used_unit*unit_price;
			}else {
				total_price=(unit_price*90)+((used_unit-90)*2);
			}
			statement.setDouble(7, total_price);
			statement.execute();
			statement.close();
			connection.close();
			setRes("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setRes("error");
		}
	}
	
	public String get_monthly_bill() {
		PreparedStatement statement;
		String data="";
		
		try {
			
			connection = DBConnect.getDB();
			statement = connection.prepareStatement("SELECT * FROM monthly_bill");
			
			ResultSet resultSet = statement.executeQuery();
			
			data = "<table><thead>"
		            +"<tr>"
		            +"<th style='border: 1px solid black;'>ID</th>"
	                +"<th style='border: 1px solid black;'>Customer ID</th>"
	                +"<th style='border: 1px solid black;'>Customer Name</th>"
	                +"<th style='border: 1px solid black;'>Customer Address</th>"
	                +"<th style='border: 1px solid black;'>Month</th>"
	                +"<th style='border: 1px solid black;'>Used Unit</th>"
	                +"<th style='border: 1px solid black;'>Unit price</th>"
	                +"<th style='border: 1px solid black;'>Total Price</th>"
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

	public void edit_monthly_bill(int id,int cus_id,String cus_name,String cus_address,String month,int used_unit,double unit_price) {
		PreparedStatement statement;
		
		try {
			double total_price;
			connection = DBConnect.getDB();
			
				statement = connection.prepareStatement("UPDATE monthly_bill SET cus_id=?,cus_name=?,cus_address=?,month,used_unit=?,unit_price=?,total_price=? where id=?");
				statement.setInt(1, cus_id);
				statement.setString(2, cus_name);
				statement.setString(3, cus_address);
				statement.setString(4, month);
				statement.setInt(5, used_unit);
				statement.setDouble(6, unit_price);
				if(used_unit<=90) {
					total_price=used_unit*unit_price;
				}else {
					total_price=(unit_price*90)+((used_unit-90)*2);
				}
				statement.setDouble(7, total_price);
				statement.setInt(8,id);
				statement.execute();
				statement.close();
				connection.close();
				setRes("success");
				
		
		}catch (ClassNotFoundException | SQLException  e) {
			System.out.println(e.getMessage());
			setRes("error");
		}
	}

	public void delete_monthly_bill(int id) {
		PreparedStatement statement;
		
		try {
			connection = DBConnect.getDB();
			
			statement = connection.prepareStatement("DELETE FROM monthly_bill WHERE id=?");
			statement.setInt(1, id);
			statement.execute();
			
			setRes("success");
		
		}catch (ClassNotFoundException | SQLException  e) {
			setRes("error");
		}
	}
	
}
