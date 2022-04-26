package com;

import model.Schedule;

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
@Path("/poweinterruptionschedules")
public class ScheduleService
{
	 Schedule scheduleObj = new Schedule();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerInterruptionSchedules()
	 {
			return scheduleObj.readPowerInterruptionSchedules();
	 }
	
	//Insert
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerInterruptionSchedules(
	@FormParam("province") String province,
	 @FormParam("area") String area,
	 @FormParam("sGroup") String sGroup,
	 @FormParam("day") String day,
	 @FormParam("month") String month,
	 @FormParam("startDay") String startDay,
	 @FormParam("endDay") String endDay,
	 @FormParam("year") String year,
	 @FormParam("startTime") String startTime,
	 @FormParam("endTime") String endTime)
	{
	 String output = scheduleObj.insertPowerInterruptionSchedules(province, area, sGroup, day, month, startDay, endDay, year, startTime, endTime);
	return output;
	}
	
	//Update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowerInterruptionSchedules(String scheduleData)
	{
	//Convert the input string to a JSON object
	 JsonObject scheduleObject = new JsonParser().parse(scheduleData).getAsJsonObject();
	//Read the values from the JSON object
	 String scheduleID = scheduleObject.get("scheduleID").getAsString();
	 String province = scheduleObject.get("province").getAsString();
	 String area = scheduleObject.get("area").getAsString();
	 String sGroup = scheduleObject.get("sGroup").getAsString();
	 String day = scheduleObject.get("day").getAsString();
	 String month = scheduleObject.get("month").getAsString();
	 String startDay = scheduleObject.get("startDay").getAsString();
	 String endDay = scheduleObject.get("endDay").getAsString();
	 String year = scheduleObject.get("year").getAsString();
	 String startTime = scheduleObject.get("startTime").getAsString();
	 String endTime = scheduleObject.get("endTime").getAsString();
	 String output = scheduleObj.updatePowerInterruptionSchedules(scheduleID, province, area, sGroup, day, month, startDay, endDay, year, startTime, endTime);
	return output;
	}
	
	//Delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerInterruptionSchedules(String scheduleData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(scheduleData, "", Parser.xmlParser());

	//Read the value from the element <scheduleID>
	 String scheduleID = doc.select("scheduleID").text();
	 String output = scheduleObj.deletePowerInterruptionSchedules(scheduleID);
	return output;
	}
	
} 

