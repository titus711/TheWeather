package com.titusnangi.theweather.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.titusnangi.theweather.R;
import com.titusnangi.theweather.adapter.ForecastRVAdapter;
import com.titusnangi.theweather.api.RetrofitClient;
import com.titusnangi.theweather.api.WeatherServiceApi;
import com.titusnangi.theweather.model.forecastweather.ForecastWeatherResponse;
import com.titusnangi.theweather.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForecastWeatherFragment extends Fragment {

    private RecyclerView weatherListRV;
    private TextView cityFTV;
    private ForecastRVAdapter adapter;
    private String forecast_weather_url;

    // Required empty public constructor
    public ForecastWeatherFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast_weather, container, false);


        weatherListRV = view.findViewById(R.id.weatherListRV);
        cityFTV = view.findViewById(R.id.cityFTV);

        WeatherServiceApi weatherServiceAPI = RetrofitClient.getClient(Constants.baseUrl.WEATHER_BASE_URL).create(WeatherServiceApi.class);
        double latitude = getArguments().getDouble("lat");
        double longitude = getArguments().getDouble("lng");

        forecast_weather_url = String.format("forecast?lat=%f&lon=%f&units=metric&appid=%s", latitude, longitude, Constants.apiKeys.WEATHER_API);

        weatherServiceAPI.getForecastWeatherResponse(forecast_weather_url)
                .enqueue(new Callback<ForecastWeatherResponse>() {
                    @Override
                    public void onResponse(Call<ForecastWeatherResponse> call, Response<ForecastWeatherResponse> response) {
                        if (response.isSuccessful()) {
                            ForecastWeatherResponse forecastWeatherResponse = response.body();

                            List<com.titusnangi.theweather.model.forecastweather.List> weatherLists = forecastWeatherResponse.getList();

                            String userCity = forecastWeatherResponse.getCity().getName() + ", ";
                            String userCountry = forecastWeatherResponse.getCity().getCountry();

                            if (userCity.contains(getString(R.string.hatfield))) {
                                String pretoria_country = "Weather in " + getString(R.string.pretoria) + ", " + userCountry;
                                cityFTV.setText(pretoria_country);
                            } else {
                                String city_country = "Weather in " + userCity + userCountry;
                                cityFTV.setText(city_country);
                            }

                            if (adapter == null) {
                                adapter = new ForecastRVAdapter(weatherLists, getContext());

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                weatherListRV.setLayoutManager(linearLayoutManager);
                                weatherListRV.setAdapter(adapter);
                            } else {
                                adapter.updateCollection(weatherLists);

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastWeatherResponse> call, Throwable t) {
                        Toast.makeText(getContext(), " Failed to load" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("ForecastFailed: ", t.getMessage() + "\n" + t.getLocalizedMessage());
                    }
                });


        return view;
    } // ending onCreateView

}