/**
 * 
 */
package com.finleap.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.finleap.app.weatherforecastapp.WeatherForecastApp;
import com.finleap.model.ForecastData;
import com.finleap.model.ForecastDataResults;

/**
 * @author adityapratap
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = WeatherForecastApp.class)
@AutoConfigureMockMvc
public class WeatherForecastApiControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@Test
	public void givenCity_whenNotAuthorized_then4xxClientError()
	  throws Exception {
	     
	    ForecastData data = new ForecastData();
	    data.setCityName("Mumbai");
	    ForecastDataResults results = new  ForecastDataResults();
	    results.add(data);
	    
	    mvc.perform(get("/data/Mumbai")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().is4xxClientError());
	}
	
	

}
