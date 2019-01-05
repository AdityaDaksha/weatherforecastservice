/*
 * Weather forecast
 * This is a weather forecast api.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: contacttoaditya09@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.finleap.app.weatherforecastApp.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * ForecastData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-01-05T19:46:50.278+05:30")
public class ForecastData {
  @JsonProperty("date")
  private String date = null;

  @JsonProperty("cityName")
  private String cityName = null;

  @JsonProperty("dailyTemprature")
  private Double dailyTemprature = null;

  @JsonProperty("nightlyTemprature")
  private Double nightlyTemprature = null;

  @JsonProperty("pressure")
  private Double pressure = null;

  public ForecastData date(String date) {
    this.date = date;
    return this;
  }

   /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(value = "")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public ForecastData cityName(String cityName) {
    this.cityName = cityName;
    return this;
  }

   /**
   * Get cityName
   * @return cityName
  **/
  @ApiModelProperty(example = "Brisbane,AU", required = true, value = "")
  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public ForecastData dailyTemprature(Double dailyTemprature) {
    this.dailyTemprature = dailyTemprature;
    return this;
  }

   /**
   * Get dailyTemprature
   * @return dailyTemprature
  **/
  @ApiModelProperty(value = "")
  public Double getDailyTemprature() {
    return dailyTemprature;
  }

  public void setDailyTemprature(Double dailyTemprature) {
    this.dailyTemprature = dailyTemprature;
  }

  public ForecastData nightlyTemprature(Double nightlyTemprature) {
    this.nightlyTemprature = nightlyTemprature;
    return this;
  }

   /**
   * Get nightlyTemprature
   * @return nightlyTemprature
  **/
  @ApiModelProperty(value = "")
  public Double getNightlyTemprature() {
    return nightlyTemprature;
  }

  public void setNightlyTemprature(Double nightlyTemprature) {
    this.nightlyTemprature = nightlyTemprature;
  }

  public ForecastData pressure(Double pressure) {
    this.pressure = pressure;
    return this;
  }

   /**
   * Get pressure
   * @return pressure
  **/
  @ApiModelProperty(value = "")
  public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ForecastData forecastData = (ForecastData) o;
    return Objects.equals(this.date, forecastData.date) &&
        Objects.equals(this.cityName, forecastData.cityName) &&
        Objects.equals(this.dailyTemprature, forecastData.dailyTemprature) &&
        Objects.equals(this.nightlyTemprature, forecastData.nightlyTemprature) &&
        Objects.equals(this.pressure, forecastData.pressure);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, cityName, dailyTemprature, nightlyTemprature, pressure);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ForecastData {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    cityName: ").append(toIndentedString(cityName)).append("\n");
    sb.append("    dailyTemprature: ").append(toIndentedString(dailyTemprature)).append("\n");
    sb.append("    nightlyTemprature: ").append(toIndentedString(nightlyTemprature)).append("\n");
    sb.append("    pressure: ").append(toIndentedString(pressure)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}
