package com.github.wkicior.helyeah.service;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TimerService;

import com.github.wkicior.helyeah.model.Coordinates;

@Singleton
public class ForecastTimer {

	private final static Coordinates CHALUPY_COORDINATES = new Coordinates(
			54.741893, 18.532023);

	@Resource
	TimerService timerService;

	@EJB
	ForecastService forecastService;

	@Schedule(minute = "*/3", hour = "*")
	public void onTimeout() {
	    try {
		forecastService.processLocation(CHALUPY_COORDINATES);
	    } catch (Exception e) {
		System.out.println(e);
	    }
	}
}
