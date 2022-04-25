package com;

import model.Electricity;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/electricityconnectionrequests")
public class ElectricityService
{
	 Electricity electricityObj = new Electricity();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readElectricityConnectionRequests()
	 {
			return electricityObj.readElectricityConnectionRequests();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertElectricityConnectionRequests(
	@FormParam("fullName") String fullName,
	 @FormParam("nationalIdentityCardNo") String nationalIdentityCardNo,
	 @FormParam("phoneNumber") String phoneNumber,
	 @FormParam("email") String email,
	 @FormParam("newElectricityConnectionAddress") String newElectricityConnectionAddress,
	 @FormParam("postalCodeOfArea") String postalCodeOfArea,
	 @FormParam("serviceType") String serviceType,
	 @FormParam("purpose") String purpose)
	{
	 String output = electricityObj.insertElectricityConnectionRequests(fullName, nationalIdentityCardNo, phoneNumber, email, newElectricityConnectionAddress, postalCodeOfArea, serviceType, purpose);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateElectricityConnectionRequests(String electricityData)
	{
	//Convert the input string to a JSON object
	 JsonObject electricityObject = new JsonParser().parse(electricityData).getAsJsonObject();
	//Read the values from the JSON object
	 String requestId = electricityObject.get("requestId").getAsString();
	 String fullName = electricityObject.get("fullName").getAsString();
	 String nationalIdentityCardNo = electricityObject.get("nationalIdentityCardNo").getAsString();
	 String phoneNumber = electricityObject.get("phoneNumber").getAsString();
	 String email = electricityObject.get("email").getAsString();
	 String newElectricityConnectionAddress = electricityObject.get("newElectricityConnectionAddress").getAsString();
	 String postalCodeOfArea = electricityObject.get("postalCodeOfArea").getAsString();
	 String serviceType = electricityObject.get("serviceType").getAsString();
	 String purpose = electricityObject.get("purpose").getAsString();
	 String output = electricityObj.updateElectricityConnectionRequests(requestId, fullName, nationalIdentityCardNo, phoneNumber, email, newElectricityConnectionAddress, postalCodeOfArea, serviceType, purpose);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteElectricityConnectionRequests(String electricityData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(electricityData, "", Parser.xmlParser());

	//Read the value from the element <requestId>
	 String requestId = doc.select("requestId").text();
	 String output = electricityObj.deleteElectricityConnectionRequests(requestId);
	return output;
	}
	
}
