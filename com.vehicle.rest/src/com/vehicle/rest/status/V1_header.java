package com.vehicle.rest.status;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.vehicle.util.ParseHttpHeader;

@Path("/v1/header")
public class V1_header {

	/**
	 * This resource is a example of how to look at the http header.
	 * 
	 * @param hsr
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllVehicleRegs(@Context HttpServletRequest hsr) throws Exception {
		
		Response rb = null;	
		JSONObject json = new JSONObject();
		
		try {
			//call method to parse header into json object
			json = ParseHttpHeader.parseHSR(hsr);
			System.out.println("json header: " + json);
			
			//once its in a json object, we can search for data we want to use
			String browser = json.optString("User-Agent", "NOT-FOUND");
			
			//return data to the user
			rb = Response.ok( browser ).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rb;
	}
	
}