/**
 * 
 */
package com.finleap.app.weatherforecastapp;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author adityapratap
 *
 */
@SpringBootApplication
public class WeatherForecastApp {
	
	private static final Logger LOOGER = Logger.getLogger(WeatherForecastApp.class);
	
	private static ConfigurableApplicationContext context;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOOGER.info("Starting weather forecast service...");
		try {
			context = new SpringApplicationBuilder(WeatherForecastApp.class).headless(false).run(args);
		} catch (Exception e) {
			LOOGER.error(e.getMessage(), e);
			if (context != null) {
				LOOGER.info("Closing context...");
				context.close();
			}
			LOOGER.info("Shutting down system...");
			System.exit(1);
		}
	}

}
