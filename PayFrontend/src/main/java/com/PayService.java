package com;

import model.Pay;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Pays")

public class PayService {

	Pay payObj = new Pay();
     
	//user read payments 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) //respond media type
	
	public String readPay() {
		
		
		return payObj.readPay();
	}
	
	//user insert a payment
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)  //request media type
	@Produces(MediaType.TEXT_PLAIN) //respond media type
	
	 
	public String insertpay(
	 @FormParam("payDate") String payDate,
	 @FormParam("name") String name,
	 @FormParam("email") String email,
	 @FormParam("amount") String amount,
	 @FormParam("accNo") String accNo,
	 @FormParam("ccv") String ccv,
	 @FormParam("expireDate") String expireDate)
	{
	 String output = payObj.insertPay( payDate,  name,  email, amount,  accNo,  ccv, expireDate);
	return output;
	}
	
	//user update the payment using payment ID
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)  //request media type
	@Produces(MediaType.TEXT_PLAIN)  //respond media type
	public String updatePay(String PayData)
	{
	//Convert the input string to a JSON object
	 JsonObject payObject = new JsonParser().parse(PayData).getAsJsonObject();
	
	 //Read the values from the JSON object and capture the json data set
	 String payID = payObject.get("payID").getAsString();
	 String payDate = payObject.get("payDate").getAsString();
	 String name = payObject.get("name").getAsString();
	 String email= payObject.get("email").getAsString();
	 String amount = payObject.get("amount").getAsString();
	 String accNo = payObject.get("accNo").getAsString();
	 String ccv = payObject.get("ccv").getAsString();
	 String expireDate = payObject.get("expireDate").getAsString();
	  
	 String output = payObj.updatePay(payID,payDate,name,email,amount, accNo,ccv, expireDate );
	return output;
	}
	
    //user delete a payment record using payment ID
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)   //request media type
	@Produces(MediaType.TEXT_PLAIN)       //respond media type
	public String deletePay(String payData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(payData, "", Parser.xmlParser());

	//Read the value from the element <name>
	 String payID = doc.select("payID").text();
	 String output = payObj.deletePay(payID);
	return output;
	}

}
