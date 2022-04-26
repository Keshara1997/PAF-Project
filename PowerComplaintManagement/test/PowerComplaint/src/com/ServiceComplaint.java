package com;

import model.Complaint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Complaint")
public class ServiceComplaint {
	Complaint ComplaintObj = new Complaint();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplaint() {
		return ComplaintObj.readComplaint();
	}

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(@FormParam("PerName") String PerName, 
			@FormParam("PerNIC") String PerNIC,
			@FormParam("cArea") String cArea,
			@FormParam("cAccNo") String cAccNo,
			@FormParam("cAddress") String cAddress,
			@FormParam("cEmal") String cEmal,
			@FormParam("Comp") String Comp) {
		String output = ComplaintObj.insertComplaint(PerName, PerNIC, cArea, cAccNo, cAddress, cEmal,Comp);
		return output;

	}

	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateComplaint(String complaintData) {
		// Convert the input string to a JSON object
		JsonObject ProObject = new JsonParser().parse(complaintData).getAsJsonObject();

		// Read the values from the JSON object
		String cID = ProObject.get("cID").getAsString();
		String PerName = ProObject.get("PerName").getAsString();
		String PerNIC = ProObject.get("PerNIC").getAsString();
		String cArea = ProObject.get("cArea").getAsString();
		String cAccNo = ProObject.get("cAccNo").getAsString();
		String cAddress = ProObject.get("cAddress").getAsString();
		String cEmal = ProObject.get("cEmal").getAsString();
		String Comp = ProObject.get("Comp").getAsString();

		String output = ComplaintObj.updateComplaint(cID, PerName, PerNIC, cArea, cAccNo, cAddress, cEmal, Comp);
		return output;
	}

	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteComplaint(String complaintData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(complaintData, "", Parser.xmlParser());

		// Read the value from the element <Cid>
		String cID = doc.select("cID").text();
		String output = ComplaintObj.deleteComplaint(cID);
		return output;
	}
}
