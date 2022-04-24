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

@Path("/units")
public class UnitController {

	UnitModel unitModel =new UnitModel();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String json_data)
	{
		JsonObject jsp = new JsonParser().parse(json_data).getAsJsonObject();

		if(jsp.get("cus_id").getAsString()!=""&&jsp.get("cus_name").getAsString()!=""&&jsp.get("cus_phone").getAsString()!=""&&jsp.get("new_read").getAsString()!=""&&jsp.get("last_read").getAsString()!="") {

			unitModel.add_unit(Integer.parseInt(jsp.get("cus_id").getAsString()),jsp.get("cus_name").getAsString(),jsp.get("cus_phone").getAsString(),Integer.parseInt(jsp.get("new_read").getAsString()),Integer.parseInt(jsp.get("last_read").getAsString()));
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", unitModel.getRes());
			
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

		if(jsp.get("id").getAsString()!=""&&jsp.get("cus_id").getAsString()!=""&&jsp.get("cus_name").getAsString()!=""&&jsp.get("cus_phone").getAsString()!=""&&jsp.get("new_read").getAsString()!=""&&jsp.get("last_read").getAsString()!="") {

			unitModel.edit_unit(Integer.parseInt(jsp.get("id").getAsString()),Integer.parseInt(jsp.get("cus_id").getAsString()),jsp.get("cus_name").getAsString(),jsp.get("cus_phone").getAsString(),Integer.parseInt(jsp.get("new_read").getAsString()),Integer.parseInt(jsp.get("last_read").getAsString()));
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", unitModel.getRes());
	
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
	
			unitModel.delete_unit(Integer.parseInt(jsp.get("id").getAsString()));
			
			JSONObject jdata = new JSONObject();
			jdata.put("success", unitModel.getRes());
	
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
		return unitModel.get_unit();
	}
	
}
