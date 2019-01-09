**Weather forecast API**
Sample api to fetch the temperature and pressure of city for next three days. 

*Tech stack -*
Spring boot
maven
java 8
swagger
owm-japis

Please run the project as java application. The bootstrap class is **WeatherForecastApp** .

To fetch the data you can use-
1- Browser to access the JSON by hitting
localhost:8080/data/{city}
Example - localhost:8080/data/Brisbane

2- Rest client - PostMan
3-Even you can use any application in which you just need to include the jar of this app and use the rest client which is present in this app. 

I have used the swagger codegen to generate api client and models at the build time. I was planning to move the auto gen part to some library but due to time constraints leaving it for future.

Calculation of temperature and pressure is in the owm-japis lib. **DailyWeatherForecast** objects provides the dayTemp,  nightTemp and pressure. so we don't need to worry about hourly temperature and pressure.

owm-japis library internally calls https://openweathermap.org apis to get the data. And to fetch the data from openweathermap.org I have created the API key and have hardcoded at the backend for now.

In the recent update I have used **HourlyWeatherForecast** as this is free to use. Earlier was paid one. 

**Note**- if maven is not able to resolve the owm-japis dependency please download from https://search.maven.org/search?q=a:owm-japis and include it to classpath.
