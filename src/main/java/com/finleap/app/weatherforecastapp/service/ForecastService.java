/**
 * 
 */
package com.finleap.app.weatherforecastapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.finleap.app.weatherforecastapp.exception.WeatherForecastException;
import com.finleap.model.ForecastDataResults;
import com.google.common.collect.Lists;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.Main;
import net.aksingh.owmjapis.model.param.WeatherData;

/**
 * @author adityapratap
 *
 */
@Service
public class ForecastService {

	private static final Logger LOOGER = Logger.getLogger(ForecastService.class);

	private static final String OWM_API_KEY = "c42cf91caf9d5bb3c3d8a676ecab0224";
	
	private static final DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public ForecastDataResults getThreeDaysWeatherForecast(String city) throws WeatherForecastException {
		return  fetchData(city);
	}

	private ForecastDataResults fetchData(String weatherCity) throws WeatherForecastException {
		
		ForecastDataResults forecastDataResults = new ForecastDataResults();
		
		OWM owm = new OWM(OWM_API_KEY);
		owm.setUnit(Unit.METRIC); //for temperature in Celsius.
		
		try {
			HourlyWeatherForecast hForecast = owm.hourlyWeatherForecastByCityName(weatherCity);
			
			List<WeatherData> workingData = getWorkingSet(hForecast);
			
			List<List<WeatherData>> partitionedList = Lists.partition(workingData, 8);
			int i = 1;
			for (List<WeatherData> wlist : partitionedList) {
				com.finleap.model.ForecastData data = calculateTempAndPressure(weatherCity, wlist);
				data.setDate("day-" + i++);
				forecastDataResults.add(data);
				LOOGER.info("\tdata: " + data.toString() + "\n");
			}
		} catch (APIException e) {
			LOOGER.error(e.getMessage(), e);
			throw new WeatherForecastException(e.getCode(), e.getMessage());
		}
		return forecastDataResults;
	}

	private com.finleap.model.ForecastData calculateTempAndPressure(
			String weatherCity, List<WeatherData> wlist) {
		com.finleap.model.ForecastData data = new com.finleap.model.ForecastData();
		
		List<List<WeatherData>> wdList = Lists.partition(wlist, 4);
		
		double pressure = 0.0;
		boolean day = true;
		for (List<WeatherData> dayNightList: wdList) {
			double temp = 0.0;
			for (WeatherData wdDate : dayNightList) {
				Main mainData = wdDate.getMainData();
				temp += mainData.getTemp();
				pressure += mainData.getPressure();
			}
			if (day) {
				data.setDailyTemprature(temp/4);
				day = false;
			} else {
				data.setNightlyTemprature(temp/4);
			}
		}
		data.cityName(weatherCity);
		data.setPressure(pressure/8);
		return data;
	}

	private List<WeatherData> getWorkingSet(HourlyWeatherForecast hForecast) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startDateAt6 = now.plusDays(1).toLocalDate().atTime(6, 0, 0);
		LocalDateTime endDateAt18 = now.plusDays(4).toLocalDate().atTime(3, 0, 0);
		
		List<WeatherData> workingData = new ArrayList<>();
		if("200".equals(hForecast.getRespCode())) {
			List<WeatherData> weatherDataList = hForecast.getDataList();
			for (WeatherData weatherData : weatherDataList) {
				LocalDateTime wdTime = getLocalDateTimeFromString(weatherData.getDateTimeText());
				if((wdTime.isEqual(startDateAt6) || wdTime.isAfter(startDateAt6))
						&& (wdTime.isEqual(endDateAt18) || wdTime.isBefore(endDateAt18))) {
					workingData.add(weatherData);
				}
			}
		}
		return workingData;
	}
	
	private LocalDateTime getLocalDateTimeFromString(String dateStr) {
		return LocalDateTime.parse(dateStr, FOMATTER);
	}
}
