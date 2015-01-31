package com.github.wkicior.helyeah.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import model.Coordinates;

import com.github.wkicior.helyeah.application.TargetURI;

@Stateless
public class ForecastService {

	@Inject
	@TargetURI("http://wwo-proxy/forecast/{latitude}/{longitude}")
	WebTarget wwoProxy;

	/**
	 * Checks the forecast on given location and runs the notification service
	 * 
	 * @param latitude
	 * @param longitude
	 */
	public void processLocation(final Coordinates coordinates) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("latitude", Double.toString(coordinates.getLatitude()));
		params.put("longitude", Double.toString(coordinates.getLongitude()));
		WebTarget resolvedWwoProxy = wwoProxy.resolveTemplates(params);
		JsonObject resp = resolvedWwoProxy.request(MediaType.APPLICATION_JSON)
				.get(JsonObject.class);
		System.out.println(resp.toString());

	}

}
