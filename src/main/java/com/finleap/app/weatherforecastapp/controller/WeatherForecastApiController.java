/**
 * 
 */
package com.finleap.app.weatherforecastapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.weatherforecastapp.exception.WeatherForecastException;
import com.finleap.app.weatherforecastapp.service.ForecastService;
import com.finleap.model.ForecastDataResults;

/**
 * @author adityapratap
 *
 */
@RestController
public class WeatherForecastApiController implements WeatherForecastApi {
	
	private static final Logger LOOGER = Logger.getLogger(WeatherForecastApiController.class);

	@Autowired
	private ForecastService forecastService;
	
	@Override
	public ResponseEntity<ForecastDataResults> getTempratureAndPressureByCity(@PathVariable String city,
			HttpServletRequest request) {
		LOOGER.info("Fetching data for city: " + city);
		ForecastDataResults result = new ForecastDataResults();
		try {
			result = forecastService.getThreeDaysWeatherForecast(city);
		} catch (WeatherForecastException e) {
			LOOGER.error(e.getMessage(), e);
			return new ResponseEntity<>(result, HttpStatus.valueOf(e.getErrorCode()));
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
