package com.titusnangi.theweather.model.forecastweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {
    @SerializedName("pod")
    @Expose
    String pod = null;
}
