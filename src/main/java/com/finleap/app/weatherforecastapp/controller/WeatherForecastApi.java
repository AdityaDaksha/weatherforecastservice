/**
 * 
 */
package com.finleap.app.weatherforecastapp.controller;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.finleap.model.ForecastDataResults;

/**
 * @author adityapratap
 *
 */
@Api(value = "weather-forecast")
@RequestMapping("/")
public interface WeatherForecastApi {

	@RequestMapping(value ="data/{city}", method = RequestMethod.GET)
	ResponseEntity<ForecastDataResults> getTempratureAndPressureByCity(@PathVariable("city") String city, HttpServletRequest request);
}
