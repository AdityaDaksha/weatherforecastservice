/**
 * 
 */
package com.finleap.app.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.finleap.app.weatherforecastapp.exception.WeatherForecastException;
import com.finleap.app.weatherforecastapp.service.ForecastService;
import com.finleap.model.ForecastDataResults;

/**
 * @author adityapratap
 *
 */
@RunWith(SpringRunner.class)
public class ForecastServiceTest {
	
	@InjectMocks
	private ForecastService forecastService;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(ForecastServiceTest.class);
	    forecastService = new ForecastService();
	}
	
	@Test
	public void testThreeDaysWeatherForecast() throws WeatherForecastException {
		String city = "Mumbai";
		ForecastDataResults  forecastDataResults = forecastService.getThreeDaysWeatherForecast(city);
		Assert.assertEquals(3, forecastDataResults.size());
	}
	
	@Test(expected = WeatherForecastException.class)
	public void testThreeDaysWeatherForecastWithWrongCityName() throws WeatherForecastException {
		String city = "xyz";
		ForecastDataResults  forecastDataResults = forecastService.getThreeDaysWeatherForecast(city);
		Assert.assertEquals(0, forecastDataResults.size());
	}

}
