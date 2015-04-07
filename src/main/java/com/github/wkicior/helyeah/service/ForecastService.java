package com.github.wkicior.helyeah.service;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.github.wkicior.helyeah.application.TargetURI;

@Stateless
public class ForecastService {
	
	@Inject
    @TargetURI("http://wwo-proxy/forecast/{latitude}/{longitude}")
    WebTarget wwoProxy;
	
	@Inject
	@TargetURI("http://notifications/notifications")
	WebTarget notificationService;

	/**
	 * Checks the forecast on given location and runs the notification service
	 * @param latitude
	 * @param longitude
	 */
	public void processLocation(double latitude, double longitude) {
		System.out.println("processLocation");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("latitude", Double.toString(latitude));
		params.put("longitude", Double.toString(longitude));
		WebTarget resolvedWwoProxy = wwoProxy.resolveTemplates(params);
		JsonObject resp = resolvedWwoProxy.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
				System.out.println("processLocation: " + resp.toString());
		notificationService.request(MediaType.APPLICATION_JSON).post(Entity.json(resp));
		System.out.println("processLocation: OK");
	}

}
