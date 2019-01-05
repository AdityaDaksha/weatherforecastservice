/**
 * 
 */
package com.finleap.app.weatherforecastApp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finleap.app.weatherforecastApp.model.ForecastDataResults;

import io.swagger.annotations.Api;

/**
 * @author adityapratap
 *
 */
@Api(value = "weather-forecast")
@RequestMapping("/")
public interface WeatherForecastApi {

	@RequestMapping(value ="data/{city}", method = RequestMethod.GET)
	ResponseEntity<ForecastDataResults> getTempratureAndPressureByCity(@PathVariable("city") String cityName, HttpServletRequest request);
}
