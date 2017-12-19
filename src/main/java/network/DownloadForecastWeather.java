package network;

import interfaces.AsyncResponseCurrentWeather;
import interfaces.AsyncResponseForecastWeather;
import models.CurrentWeather;
import models.ForecastWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.URLData;

public class DownloadForecastWeather {
    private AsyncResponseForecastWeather mDelegate;

    public DownloadForecastWeather(AsyncResponseForecastWeather delegate){
        this.mDelegate = delegate;

        Call<ForecastWeather> call = OpenWeatherApiClient.getOpenWeatherInterface().getForecastStatus(
                URLData.CITY_ID,
                URLData.UNITS_TYPE,
                URLData.API_KEY
        );

        call.enqueue(new Callback<ForecastWeather>() {
            public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                mDelegate.onResponseForecastFinished(response);
            }

            public void onFailure(Call<ForecastWeather> call, Throwable throwable) {
                System.out.println(String.format("[SOMETHING WENT WRONG][ERROR][%s]", throwable.getMessage()));
            }
        });
    }
}
