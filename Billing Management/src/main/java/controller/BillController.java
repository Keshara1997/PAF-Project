package controller;

import com.google.gson.*;
import org.json.simple.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import java.util.ArrayList;

import model.*;

@Path("/monthly_bill")
public class BillController {

	monthly_bill_Model bill_Model =new monthly_bill_Model();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_data)
	{
		JsonObject jsp = new JsonParser().parse(json_data).getAsJsonObject();

		if(jsp.get("cus_id").getAsString()!=""&&jsp.get("cus_name").getAsString()!=""&&jsp.get("cus_address").getAsString()!=""&&jsp.get("month").getAsString()!=""&&jsp.get("used_unit").getAsString()!=""&&jsp.get("unit_price").getAsString()!="") {

			bill_Model.add_monthly_bill(Integer.parseInt(jsp.get("cus_id").getAsString()),jsp.get("cus_name").getAsString(),jsp.get("cus_address").getAsString(),jsp.get("month").getAsString(),Integer.parseInt(jsp.get("used_unit").getAsString()),Double.parseDouble(jsp.get("unit_price").getAsString()));
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", bill_Model.getRes());
			
			return jdata.toString();
			
		}else {
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", "required");
			
			return jdata.toString();
			
		}
			
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String json_data)
	{
		JsonObject jsp = new JsonParser().parse(json_data).getAsJsonObject();

		if(jsp.get("id").getAsString()!=""&&jsp.get("cus_id").getAsString()!=""&&jsp.get("cus_name").getAsString()!=""&&jsp.get("cus_address").getAsString()!=""&&jsp.get("month").getAsString()!=""&&jsp.get("used_unit").getAsString()!=""&&jsp.get("unit_price").getAsString()!="") {

			bill_Model.edit_monthly_bill(Integer.parseInt(jsp.get("id").getAsString()),Integer.parseInt(jsp.get("cus_id").getAsString()),jsp.get("cus_name").getAsString(),jsp.get("cus_address").getAsString(),jsp.get("month").getAsString(),Integer.parseInt(jsp.get("used_unit").getAsString()),Double.parseDouble(jsp.get("unit_price").getAsString()));
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", bill_Model.getRes());
	
			return jdata.toString();
			
		}else {
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", "required");
			
			return jdata.toString();
			
		}
			
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String json_data)
	{
		JsonObject jsp = new JsonParser().parse(json_data).getAsJsonObject();
		if(jsp.get("id").getAsString()!="") {
	
			bill_Model.delete_monthly_bill(Integer.parseInt(jsp.get("id").getAsString()));
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", bill_Model.getRes());
	
			return jdata.toString();
			
		}else {
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", "required");
			
			return jdata.toString();
			
		}
		
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String json_data)
	{
		return bill_Model.get_monthly_bill();
	}
	
}
