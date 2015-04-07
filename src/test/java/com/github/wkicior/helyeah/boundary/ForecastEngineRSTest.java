package com.github.wkicior.helyeah.boundary;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.wkicior.helyeah.service.ForecastService;

import junit.framework.TestCase;

public class ForecastEngineRSTest extends TestCase {
	
	@Mock
	ForecastService forecastService;
	
	@InjectMocks
	ForecastEngineRS forecastEngineRS;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
	}
	
	@Test
	public void test_processLocation() {
		forecastEngineRS.processLocation(1, 2);
		Mockito.verify(forecastService).processLocation(1, 2);
	}

}
