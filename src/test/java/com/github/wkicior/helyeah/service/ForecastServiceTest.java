package com.github.wkicior.helyeah.service;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Invocation.Builder;
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
		params.put("longitude", Double.toString(13.0));
		WebTarget resolvedWwoProxy = Mockito.mock(WebTarget.class);
		Mockito.when(wwoProxy.resolveTemplates(params)).thenReturn(resolvedWwoProxy);
		Builder builder = Mockito.mock(Builder.class);
		JsonObject wwoProxyJsonResponse = Json.createObjectBuilder().build();
		Mockito.when(builder.get(JsonObject.class)).thenReturn(wwoProxyJsonResponse);
		Mockito.when(resolvedWwoProxy.request(MediaType.APPLICATION_JSON)).thenReturn(builder);
		service.processLocation(12.0, 13.5);
	}

}
