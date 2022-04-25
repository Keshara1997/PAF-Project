package model;
import java.sql.* ;

public class user
{

public Connection connect()
{
         Connection con = null;
          try
          {
             Class.forName("com.mysql.jdbc.Driver");
             con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/usermanagement",
                                              "root", "");
//For testing
          System.out.print("Successfully connected");
          }
          catch(Exception e)
          {
             e.printStackTrace();
          }
return con;
}

//Insert
public String insertUser(String Name, String phone, String Email, String UserN,String Password)
{
String output = "";
try
{
    Connection con = connect();
    if (con == null)
    {
      return "Error while connecting to the database";
    }
    
    // create a prepared statement
    String query = " insert into users (`userID`,`name`,`phoneNum`,`email`,`userName`,`password`)"
   		  + " values ( ?, ?, ?, ?, ?,?)";
    PreparedStatement preparedStmt = con.prepareStatement(query);
    
    // binding values
    preparedStmt.setInt(1, 0);
    preparedStmt.setString(2, Name);
    preparedStmt.setInt(3, Integer.parseInt(phone));
    preparedStmt.setString(4, Email);
    preparedStmt.setString(5, UserN);
    preparedStmt.setString(6, Password);
   
    //execute the statement
    preparedStmt.execute();
    con.close();
    
    output = "Inserted successfully";
 }
 catch (Exception e)
 {
    output = "Error while inserting";
    System.err.println(e.getMessage());
 }
    return output;
    }

//read
public String readItems()
{
        String output = "";
        try
        {
            Connection con = connect();
            if (con == null)
            {
                 return "Error while connecting to the database for reading.";
            }
        // Prepare the html table to be displayed
        output = "<table border='1'><tr><th>Name</th>"
               +"<th>Phone Number</th><th>Email</th>"
               + "<th>User Name</th>"
               + "<th>Password</th>"
               + "<th>Update</th><th>Remove</th></tr>";
        String query = "select * from users";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
       // iterate through the rows in the result set
       while (rs.next())
      {
               String userID = Integer.toString(rs.getInt("userID"));
               String name = rs.getString("name");
               String phoneNum = Integer.toString(rs.getInt("phoneNum"));
               String email = rs.getString("email");
               String userName = rs.getString("userName");
               String password = rs.getString("password");
               
       // Add a row into the html table
       output += "<tr><td>" + name + "</td>";
       output += "<td>" + phoneNum + "</td>";
       output += "<td>" + email + "</td>";
       output += "<td>" + userName + "</td>";
       output += "<td>" + password + "</td>";
      
       //buttons
       output += "<td><input name='btnUpdate' "
              + " type='button' value='Update'></td>"
              + "<td><form method='post' action='users.jsp'>"
              + "<input name='btnRemove' "
              + " type='submit' value='Remove'>"
              + "<input name='userID' type='hidden' "
              + " value='" + userID + "'>" + "</form></td></tr>";
        }
        con.close();
        //Complete the html table
        output += "</table>";
       }
       catch (Exception e)
        {
           output = "Error while reading the users.";
           System.err.println(e.getMessage());
        }
        return output;
}
//Update
public String updateUser(String ID, String name, String phone, String Email, String UserN,String Password)
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
	 preparedStmt.setString(1, name); 
	 preparedStmt.setInt(2,Integer.parseInt (phone)); 
	 preparedStmt.setString(3,Email); 
	 preparedStmt.setString(4, UserN); 
	 preparedStmt.setString(5, Password); 
	 preparedStmt.setInt(6,Integer.parseInt (ID));
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the item."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
}