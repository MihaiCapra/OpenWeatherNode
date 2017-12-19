package interfaces;

import models.CurrentWeather;
import retrofit2.Response;

public interface AsyncResponseCurrentWeather {
    void onLoadCurrentWeatherFinished(Response<CurrentWeather> response);
}
