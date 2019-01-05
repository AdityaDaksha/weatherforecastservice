/**
 * 
 */
package com.finleap.app.weatherforecastApp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.finleap.app.weatherforecastApp.exception.WeatherForecastException;
import com.finleap.app.weatherforecastApp.model.ForecastDataResults;
import com.finleap.app.weatherforecastApp.service.ForecastService;

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
	public ResponseEntity<ForecastDataResults> getTempratureAndPressureByCity(String cityName,
			HttpServletRequest request) {
		LOOGER.info("Fetching data for city: " + cityName);
		ForecastDataResults result = new ForecastDataResults();
		try {
			result = forecastService.getThreeDaysWeatherForecast(cityName);
		} catch (WeatherForecastException e) {
			LOOGER.error(e.getMessage(), e);
			return new ResponseEntity<ForecastDataResults>(result, HttpStatus.resolve(e.getErrorCode()));
		}
		return new ResponseEntity<ForecastDataResults>(result, HttpStatus.OK);
	}

}
