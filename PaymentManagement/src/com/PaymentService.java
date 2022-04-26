package com;

import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Path("/Payments")
public class PaymentService {

	Payment itemObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readPayment();
	}
	
	// insert payment API
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)

		public String insertPayment(@FormParam("billID") String billID, @FormParam("cardHolder") String cardHolder,
				@FormParam("cardNo") String cardNo, @FormParam("cvv") String cvv, @FormParam("amount") String amount) {
			String output = itemObj.insertPayment(billID, cardHolder, cardNo, cvv, amount);
			return output;
		}
		
		// API for update payment
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)

		public String updatePayment(String itemData) {
			// Convert the input string to a JSON object
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
			// Read the values from the JSON object
			String paymentID = itemObject.get("paymentID").getAsString();
			String billID = itemObject.get("billID").getAsString();
			String cardHolder = itemObject.get("cardHolder").getAsString();
			String cardNo = itemObject.get("cardNo").getAsString();
			String cvv = itemObject.get("cvv").getAsString();
			String amount = itemObject.get("amount").getAsString();

			String output = itemObj.updatePayment(paymentID, billID, cardHolder, cardNo, cvv, amount);
			
			
			return output;
		}
		
		
			//API for Delete payments
				@DELETE
				@Path("/")
				@Consumes(MediaType.APPLICATION_XML)
				@Produces(MediaType.TEXT_PLAIN)
				
				public String deletePayment(String paymentData)
				{
					//Convert the input string to an XML document
					 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
					 
					//Read the value from the element <paymentID>
					 String paymentID = doc.select("paymentID").text(); 
					
					 String output = itemObj.deletePayment(paymentID); 
					
					 return output; 
				}

		
}

