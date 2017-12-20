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
                this.mCurrentWeather.getName(),
                this.mCurrentWeather.getMain().getTemp(),
                this.mCurrentWeather.getSys().getSunrise(),
                this.mCurrentWeather.getSys().getSunset(),
                this.mCurrentWeather.getWeather().get(0).getDescription(),
                this.mCurrentWeather.getWeather().get(0).getIcon(),
                this.mForecastWeather.getList().get(0).getTemp().getMin(),
                this.mForecastWeather.getList().get(0).getTemp().getMax(),
                this.mCurrentWeather.getWind().getSpeed(),
                this.mCurrentWeather.getWind().getDeg(),
                this.mCurrentWeather.getDt().toString(),

                this.mForecastWeather.getList().get(1).getDt(),
                this.mForecastWeather.getList().get(1).getWeather().get(0).getIcon(),
                this.mForecastWeather.getList().get(1).getWeather().get(0).getDescription(),
                this.mForecastWeather.getList().get(1).getTemp().getMin(),
                this.mForecastWeather.getList().get(1).getTemp().getMax(),

                this.mForecastWeather.getList().get(2).getDt(),
                this.mForecastWeather.getList().get(2).getWeather().get(0).getIcon(),
                this.mForecastWeather.getList().get(2).getWeather().get(0).getDescription(),
                this.mForecastWeather.getList().get(2).getTemp().getMin(),
                this.mForecastWeather.getList().get(2).getTemp().getMax(),

                this.mForecastWeather.getList().get(3).getDt(),
                this.mForecastWeather.getList().get(3).getWeather().get(0).getIcon(),
                this.mForecastWeather.getList().get(3).getWeather().get(0).getDescription(),
                this.mForecastWeather.getList().get(3).getTemp().getMin(),
                this.mForecastWeather.getList().get(3).getTemp().getMax(),

                this.mForecastWeather.getList().get(4).getDt(),
                this.mForecastWeather.getList().get(4).getWeather().get(0).getIcon(),
                this.mForecastWeather.getList().get(4).getWeather().get(0).getDescription(),
                this.mForecastWeather.getList().get(4).getTemp().getMin(),
                this.mForecastWeather.getList().get(4).getTemp().getMax()
                ));
    }

    /**
     * @param weatherData - the object inserted in database with its fields
     */
    private void SqlInject(WeatherData weatherData) {
        SqlInjector.insertData(weatherData);
    }

}
