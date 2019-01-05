/**
 * 
 */
package com.finleap.app.weatherforecastApp.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.finleap.app.weatherforecastApp.exception.WeatherForecastException;
import com.finleap.app.weatherforecastApp.model.ForecastDataResults;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import net.aksingh.owmjapis.model.param.ForecastData;
import net.aksingh.owmjapis.model.param.Temp;

/**
 * @author adityapratap
 *
 */
@Service
public class ForecastService {

	private static final Logger LOOGER = Logger.getLogger(ForecastService.class);

	public ForecastDataResults getThreeDaysWeatherForecast(String city) throws WeatherForecastException {
		String owmApiKey = "cb2fb8f4640e9f56ab8b38a6e31e56d1";
		ForecastDataResults forecastDataResults = fetchData(city, owmApiKey);
		return forecastDataResults;
	}

	private ForecastDataResults fetchData(String weatherCity, String owmApiKey) throws WeatherForecastException {
		ForecastDataResults forecastDataResults = new ForecastDataResults();
		
		byte forecastDays = 3;
		OWM owm = new OWM(owmApiKey);
		try {
			DailyWeatherForecast forecast = owm.dailyWeatherForecastByCityName(weatherCity, forecastDays);
			LOOGER.info("Weather for: " + forecast.getCityData().getName());
			List<ForecastData> dayForecast = forecast.getDataList();
			for (ForecastData forcastData : dayForecast) {
				com.finleap.app.weatherforecastApp.model.ForecastData data = new com.finleap.app.weatherforecastApp.model.ForecastData();
				
				data.setDate(forcastData.getDateTime().toString());
				data.setPressure(forcastData.getPressure());
				
				Temp temp = forcastData.getTempData();
				data.setDailyTemprature(temp.getTempDay());
				data.setCityName(weatherCity);
				data.setNightlyTemprature(temp.getTempNight());
				
				LOOGER.info("\t" + forcastData.getDateTime());
				LOOGER.info("\t" + forcastData.getPressure());
				LOOGER.info("\tTemperature: " + temp.getTempDay() + " to " + temp.getTempNight() + "\n");
				forecastDataResults.add(data);
			}
		} catch (APIException e) {
			LOOGER.error(e.getMessage(), e);
			throw new WeatherForecastException(e.getCode(), e.getMessage());
		}
		return forecastDataResults;
	}
}
