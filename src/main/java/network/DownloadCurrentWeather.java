package network;

import interfaces.AsyncResponseCurrentWeather;
import models.CurrentWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.URLData;

public class DownloadCurrentWeather {
    private AsyncResponseCurrentWeather mDelegate;

    public DownloadCurrentWeather(AsyncResponseCurrentWeather delegate){
        this.mDelegate = delegate;

        Call<CurrentWeather> call = OpenWeatherApiClient.getOpenWeatherInterface().getCurrentStatus(
                URLData.CITY_ID,
                URLData.UNITS_TYPE,
                URLData.API_KEY
        );

        call.enqueue(new Callback<CurrentWeather>() {
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                mDelegate.onLoadCurrentWeatherFinished(response);
            }

            public void onFailure(Call<CurrentWeather> call, Throwable throwable) {
                System.out.println(String.format("[SOMETHING WENT WRONG][ERROR][%s]", throwable.getMessage()));
            }
        });
    }
}
