package interfaces;

import models.CurrentWeather;
import models.ForecastWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherInterface {
    @GET("weather?")
    Call<CurrentWeather> getCurrentStatus(
            @Query("id") int cityId,
            @Query("units") String unitsType,
            @Query("appid") String appidKey
    );

    @GET("forecast/daily?")
    Call<ForecastWeather> getForecastStatus(
            @Query("id") int cityId,
            @Query("units") String unitsType,
            @Query("appid") String appidKey
    );
}
