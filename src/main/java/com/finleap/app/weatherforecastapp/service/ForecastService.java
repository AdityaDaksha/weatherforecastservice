/**
 * 
 */
package com.finleap.app.weatherforecastapp.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.finleap.app.weatherforecastapp.exception.WeatherForecastException;
import com.finleap.model.ForecastDataResults;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.ForecastData;
import net.aksingh.owmjapis.model.param.Temp;

/**
 * @author adityapratap
 *
 */
@Service
public class ForecastService {

	private static final Logger LOOGER = Logger.getLogger(ForecastService.class);

	private static final String OWM_API_KEY = "c42cf91caf9d5bb3c3d8a676ecab0224";
	
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
			DailyWeatherForecast forecast = owm.dailyWeatherForecastByCityName(weatherCity, forecastDays);
			
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
