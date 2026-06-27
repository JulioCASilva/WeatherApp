package com.example.findinglogs.viewmodel;


import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.findinglogs.model.model.Weather;
import com.example.findinglogs.model.repo.Repository;
import com.example.findinglogs.model.repo.remote.api.WeatherCallback;
import com.example.findinglogs.model.util.Logger;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private static final int FETCH_INTERVAL = 120_000;
    private final Repository mRepository;
    private final MutableLiveData<List<Weather>> _weatherList = new MutableLiveData<>(new ArrayList<>());
    private final LiveData<List<Weather>> weatherList = _weatherList;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable fetchRunnable = this::fetchAllForecasts;
    private final LinkedHashSet<String> cities = new LinkedHashSet<>();
    private static final String KEY_CITIES = "monitored_cities";

    public MainViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
        loadCities();
        startFetching();
    }

    public LiveData<List<Weather>> getWeatherList() {
        return weatherList;
    }

    private void startFetching() {
        fetchAllForecasts();
        handler.postDelayed(fetchRunnable, FETCH_INTERVAL);
    }
    public void refresh() {
        if (Logger.ISLOGABLE) Logger.d(TAG, "refresh()");
        handler.removeCallbacks(fetchRunnable);
        fetchAllForecasts();
    }

    public void addCity(String latLon) {
        if (Logger.ISLOGABLE) Logger.d(TAG, "addCity: " + latLon);
        cities.add(latLon);
        saveCities();
        refresh();
    }

    public void removeCity(String latLon) {
        if (Logger.ISLOGABLE) Logger.d(TAG, "removeCity: " + latLon);
        cities.remove(latLon);
        saveCities();
        refresh();
    }
    private void saveCities() {
        String joined = cities.isEmpty()
                ? "EMPTY"
                : android.text.TextUtils.join(";", cities);
        mRepository.saveString(KEY_CITIES, joined);
    }

    private void loadCities() {
        String saved = mRepository.readString(KEY_CITIES);
        if ("EMPTY".equals(saved)) {
        } else if (saved != null && !saved.isEmpty()) {
            cities.addAll(java.util.Arrays.asList(saved.split(";")));
        } else {
            cities.addAll(mRepository.getLocalizations().values());
            saveCities();
        }
    }

    private void fetchAllForecasts() {
        if (Logger.ISLOGABLE) Logger.d(TAG, "fetchAllForecasts()");

        List<String> localizations = new ArrayList<>(cities);
        int total = localizations.size();

        if (total == 0) {
            _weatherList.postValue(new ArrayList<>());
            return;
        }

        Weather[] results = new Weather[total];
        AtomicInteger finished = new AtomicInteger(0);

        for (int i = 0; i < total; i++) {
            final int index = i;
            String latlon = localizations.get(i);

            mRepository.retrieveForecast(latlon, new WeatherCallback() {
                @Override
                public void onSuccess(Weather result) {
                    result.setLatLon(latlon);
                    results[index] = result;
                    onDone();
                }

                @Override
                public void onFailure(String error) {
                    if (Logger.ISLOGABLE) Logger.d(TAG, "onFailure: " + error);
                    onDone();
                }

                private void onDone() {
                    if (finished.incrementAndGet() == total) {
                        List<Weather> ordered = new ArrayList<>();
                        for (Weather w : results) {
                            if (w != null) ordered.add(w);
                        }
                        _weatherList.postValue(ordered);
                        handler.postDelayed(fetchRunnable, FETCH_INTERVAL);
                    }
                }
            });
        }
    }

    @Override
    protected void onCleared() {
        handler.removeCallbacks(fetchRunnable);
        super.onCleared();
    }
}