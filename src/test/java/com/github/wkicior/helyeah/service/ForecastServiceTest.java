package com.github.wkicior.helyeah.service;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ForecastServiceTest {
	
	@Mock
	WebTarget wwoProxy;
	
	@Mock
	WebTarget notificationService;
	
	
	@InjectMocks
	ForecastService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}

	@Test
	public void testProcessLocation() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("latitude", Double.toString(12.0));
		params.put("longitude", Double.toString(13.5));
		WebTarget resolvedWwoProxy = Mockito.mock(WebTarget.class);
		Mockito.when(wwoProxy.resolveTemplates(params)).thenReturn(resolvedWwoProxy);
		Builder builderWwoProxy = Mockito.mock(Builder.class);
		Builder builderNotificationService = Mockito.mock(Builder.class);
		JsonObject wwoProxyJsonResponse = Json.createObjectBuilder().build();
		Mockito.when(builderWwoProxy.get(JsonObject.class)).thenReturn(wwoProxyJsonResponse);
		Mockito.when(resolvedWwoProxy.request(MediaType.APPLICATION_JSON)).thenReturn(builderWwoProxy);
		Mockito.when(notificationService.request(MediaType.APPLICATION_JSON)).thenReturn(builderNotificationService);
		
		service.processLocation(12.0, 13.5);
		Mockito.verify(builderNotificationService).post(Mockito.any(Entity.class));
	}

}
