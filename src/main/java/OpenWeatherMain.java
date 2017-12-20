import interfaces.AsyncResponseCurrentWeather;
import interfaces.AsyncResponseForecastWeather;
import models.CurrentWeather;
import models.ForecastWeather;
import network.DownloadCurrentWeather;
import network.DownloadForecastWeather;
import retrofit2.Response;
import sql.SqlInjector;
import sql.WeatherData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OpenWeatherMain implements
        AsyncResponseCurrentWeather,
        AsyncResponseForecastWeather {

    private CurrentWeather mCurrentWeather;
    private ForecastWeather mForecastWeather;

    public static void main(String[] args) {
        final OpenWeatherMain main = new OpenWeatherMain();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("[DOWNLOAD TASK]");
                main.getCurrentWeather();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    /**
     * this is an instance of Downloader class for CurrentWeather
     * with Main class context as parameter
     */
    private void getCurrentWeather() {
        new DownloadCurrentWeather(this);
    }

    /**
     * this is an instance of downloader class for forecast weather with
     * Main class as parameter
     */
    private void getForecastWeather() {
        new DownloadForecastWeather(this);
    }

    /**
     * @param response is the full JSON response in format of CurrentWeather class
     */
    public void onLoadCurrentWeatherFinished(Response<CurrentWeather> response) {
        this.mCurrentWeather = response.body();
        getForecastWeather();
        System.out.println(String.format("[CURRENT] %s", mCurrentWeather.getName()));
    }

    /**
     * @param response is the JSON response in format of ForecastWeather class
     */
    public void onResponseForecastFinished(Response<ForecastWeather> response) {
        this.mForecastWeather = response.body();
        System.out.println(String.format("[FORECAST] %s", mForecastWeather.getCity().getName()));

        //insert in database
        SqlInject(new WeatherData(
                mCurrentWeather.getName(),
                mCurrentWeather.getMain().getTemp().doubleValue(),
                mCurrentWeather.getSys().getSunrise(),
                mCurrentWeather.getSys().getSunset(),
                mCurrentWeather.getWeather().get(0).getDescription(),
                mCurrentWeather.getWeather().get(0).getIcon(),
                mCurrentWeather.getMain().getTempMin().doubleValue(),
                mCurrentWeather.getMain().getTempMax().doubleValue(),
                mCurrentWeather.getWind().getSpeed(),
                mCurrentWeather.getWind().getDeg(),
                mCurrentWeather.getDt().toString(),

                mForecastWeather.getList().get(0).getDt(),
                mForecastWeather.getList().get(0).getWeather().get(0).getIcon(),
                mForecastWeather.getList().get(0).getWeather().get(0).getDescription(),
                mForecastWeather.getList().get(0).getTemp().getMin(),
                mForecastWeather.getList().get(0).getTemp().getMax(),

                mForecastWeather.getList().get(1).getDt(),
                mForecastWeather.getList().get(1).getWeather().get(0).getIcon(),
                mForecastWeather.getList().get(1).getWeather().get(0).getDescription(),
                mForecastWeather.getList().get(1).getTemp().getMin(),
                mForecastWeather.getList().get(1).getTemp().getMax(),

                mForecastWeather.getList().get(2).getDt(),
                mForecastWeather.getList().get(2).getWeather().get(0).getIcon(),
                mForecastWeather.getList().get(2).getWeather().get(0).getDescription(),
                mForecastWeather.getList().get(2).getTemp().getMin(),
                mForecastWeather.getList().get(2).getTemp().getMax(),

                mForecastWeather.getList().get(3).getDt(),
                mForecastWeather.getList().get(3).getWeather().get(0).getIcon(),
                mForecastWeather.getList().get(3).getWeather().get(0).getDescription(),
                mForecastWeather.getList().get(3).getTemp().getMin(),
                mForecastWeather.getList().get(3).getTemp().getMax()
                ));
    }

    /**
     * @param weatherData - the object inserted in database with its fields
     */
    private void SqlInject(WeatherData weatherData) {
        SqlInjector.insertData(weatherData);
    }

}
