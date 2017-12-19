package interfaces;

import models.ForecastWeather;
import retrofit2.Response;

public interface AsyncResponseForecastWeather {
    void onResponseForecastFinished(Response<ForecastWeather> response);
}
