package com.vehicle.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.vehicle.dao.*;


/**
 * This is the root path for our restful api service
 * In the web.xml file, we specified that /api/* need to be in the URL to
 * get to this class.
 * 
 * We are versioning the class in the URL path.  This is the first version v1.
 * Example how to get to the root of this api resource:
 * http://localhost:7001/com.vehicle.rest/api/v1/status
 * 
 * @author samiulsaki
 *
 */


@Path("/v1/status") // route to java class
public class V1_status {
	
	private static final String api_version = "00.01.00"; //version of the api
	
	/**
	 * This method sits at the root of the api.  It will return the name
	 * of this api.
	 * 
	 * @return String - Title of the api
	 */
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version
	 * into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.version.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	
	@Path("/version") // route to specific method
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: </p>" + api_version;
	}
	
	@Path("/database") // Route to database
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			
			conn = OracleVehicle.OracleVehicleConn().getConnection(); // Get database connection and establish connection
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME " + 
							"from sys.dual"); // Write the SQL query and have java pre-compile it. Its faster and safer this way
			
			ResultSet rs = query.executeQuery(); // Send pre-compiled SQL to Oracle for data
			
			while (rs.next()) {
				// /*DEBUG*/ System.out.println(rs.getString("DATETIME"));
				myString = rs.getString("DATETIME");
			}
			
			query.close(); // Close connection
			
			returnString = "<p>Database Status</p> " + 
					"<p>Database Date/Time return: " + myString + "</p>";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally { // Always executes when try block closes. Make sure any error in try block the finally clause will always run
			if (conn != null) conn.close(); // No matter what happen we close the connection here
		}
		
		return returnString;
	}
}

