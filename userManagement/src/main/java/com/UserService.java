package com;

import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

//View
@Path("/Users")
public class UserService
{
	 User userObj = new User();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers()
	 {
			return userObj.readUsers();
	 }
	
	//Insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUsers(
		@FormParam("name") String name,
		@FormParam("phoneNum") String phoneNum,
		@FormParam("email") String email,
		@FormParam("userName") String userName,
		@FormParam("password") String password)
	{
		String output = userObj.insertUsers(name, phoneNum, email, userName, password);
		return output;
	}
	
	//Update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUsers(String userData)
	{
		//Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		String userID = userObject.get("userID").getAsString();
		String name = userObject.get("name").getAsString();
		String phoneNum = userObject.get("phoneNum").getAsString();
		String email = userObject.get("email").getAsString();
		String userName = userObject.get("userName").getAsString();
		String password = userObject.get("password").getAsString();
		String output = userObj.updateUsers(userID, name, phoneNum, email, userName, password);
		return output;
	}
	
	//Delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUsers(String userData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		//Read the value from the element <userID>
		String userID = doc.select("userID").text();
		String output = userObj.deleteUsers(userID);
		return output;
	}
	
} 
