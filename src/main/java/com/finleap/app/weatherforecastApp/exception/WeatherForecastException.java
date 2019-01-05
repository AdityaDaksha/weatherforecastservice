/**
 * 
 */
package com.finleap.app.weatherforecastApp.exception;

/**
 * @author adityapratap
 *
 */
@SuppressWarnings("serial")
public class WeatherForecastException extends Exception {

	
	private final Integer errorCode;
	
	public WeatherForecastException(Integer errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public WeatherForecastException(Integer errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}
