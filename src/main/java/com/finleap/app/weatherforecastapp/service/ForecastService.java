/**
 * 
 */
package com.finleap.app.weatherforecastapp.service;

import java.util.List;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Country;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import net.aksingh.owmjapis.model.param.ForecastData;
import net.aksingh.owmjapis.model.param.Temp;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.finleap.app.weatherforecastapp.exception.WeatherForecastException;
import com.finleap.model.ForecastDataResults;

/**
 * @author adityapratap
 *
 */
@Service
public class ForecastService {

	private static final Logger LOOGER = Logger.getLogger(ForecastService.class);

	private static final String OWM_API_KEY = "cb2fb8f4640e9f56ab8b38a6e31e56d1";
	
	public ForecastDataResults getThreeDaysWeatherForecast(String city) throws WeatherForecastException {
		return  fetchData(city);
	}

	private ForecastDataResults fetchData(String weatherCity) throws WeatherForecastException {
		
		ForecastDataResults forecastDataResults = new ForecastDataResults();
		byte forecastDays = 3;
		
		OWM owm = new OWM(OWM_API_KEY);
		//for temperature in Celsius.
		owm.setUnit(Unit.METRIC);
		try {
			Country countryCode = Country.valueOf(weatherCity.toUpperCase());
			DailyWeatherForecast forecast = owm.dailyWeatherForecastByCityName(weatherCity, countryCode, forecastDays);
			LOOGER.info("Weather for: " + forecast.getCityData().getName());
			List<ForecastData> dayForecast = forecast.getDataList();
			for (ForecastData forcastData : dayForecast) {
				com.finleap.model.ForecastData data = new com.finleap.model.ForecastData();
				
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
