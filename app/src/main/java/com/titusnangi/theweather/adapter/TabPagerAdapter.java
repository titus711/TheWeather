package com.titusnangi.theweather.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.titusnangi.theweather.ui.CurrentWeatherFragment;
import com.titusnangi.theweather.ui.ForecastWeatherFragment;
import com.titusnangi.theweather.utils.Constants;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {

        // passing lat, lng values through ADAPTER into Fragments
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", Constants.defaultLatLng.DEFAULT_LAT);
        bundle.putDouble("lng", Constants.defaultLatLng.DEFAULT_LNG);

        switch (i){
            case 0:
                CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
                currentWeatherFragment.setArguments(bundle);
                return currentWeatherFragment;

            case 1:
                ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment();
                forecastWeatherFragment.setArguments(bundle);
                return forecastWeatherFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
