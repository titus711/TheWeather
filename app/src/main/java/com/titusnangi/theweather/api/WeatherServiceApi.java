package com.titusnangi.theweather.api;

import com.titusnangi.theweather.model.currentweather.CurrentWeatherResponse;
import com.titusnangi.theweather.model.forecastweather.ForecastWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherServiceApi {

    @GET
    Call<CurrentWeatherResponse> getCurrentWeatherResponse(@Url String endUrl);

    @GET
    Call<ForecastWeatherResponse> getForecastWeatherResponse(@Url String forecastEndUrl);

}
