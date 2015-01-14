package com.github.wkicior.helyeah.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.github.wkicior.helyeah.application.TargetURI;

@Stateless
public class ForecastService {
	
	@Inject
    @TargetURI("http://localhost:8090/forecast/12/13")
    WebTarget wwoProxy;

	/**
	 * Checks the forecast on given location and runs the notification service
	 * @param latitude
	 * @param longitude
	 */
	public void processLocation(double latitude, double longitude) {
	/*	String path = wwoProxy.getUri().getPath()
				.replace("{latitude}",	Double.toString(latitude))
				.replace("{longitude}", Double.toString(longitude));*/
		//wwoProxy.path(path);
		JsonObject resp = wwoProxy.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
		System.out.println(resp.toString());
		
	}

}
