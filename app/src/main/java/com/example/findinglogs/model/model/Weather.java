package com.example.findinglogs.model.model;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Weather {
    private WeatherInfo main;
    private String name;
    private List<WeatherDetail> weather = new ArrayList<>();
    private String latLon;

    public String getLatLon() { return latLon; }
    public void setLatLon(String latLon) { this.latLon = latLon; }

    public Weather() {
    }

    public WeatherInfo getMain() {
        return main;
    }

    public void setMain(WeatherInfo main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeatherDetail> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDetail> weather) {
        this.weather = weather;
    }
    private Sys sys;
    public Sys getSys() { return sys; }
    public void setSys(Sys sys) { this.sys = sys; }

    @NonNull
    @Override
    public String toString() {
        return "Weather{" +
                "main=" + main +
                ", name='" + name + '\'' +
                ", weather=" + weather +
                '}';
    }
}