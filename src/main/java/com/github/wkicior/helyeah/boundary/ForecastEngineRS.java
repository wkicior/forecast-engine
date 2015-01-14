package com.github.wkicior.helyeah.boundary;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.github.wkicior.helyeah.service.ForecastService;

@Path("/forecast-engine")
public class ForecastEngineRS {
	
	@EJB
	ForecastService service;
	
	@GET
	@Path("/{latitude}/{longitude}")
	public Response processLocation(@PathParam("latitude") double latitude, @PathParam("longitude") double longitude) { 
		service.processLocation(latitude, longitude); 
		return Response.status(200).entity("OK").build();
 
	}
}
