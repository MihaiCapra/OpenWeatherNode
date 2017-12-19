import interfaces.AsyncResponseCurrentWeather;
import interfaces.AsyncResponseForecastWeather;
import models.CurrentWeather;
import models.ForecastWeather;
import network.DownloadCurrentWeather;
import network.DownloadForecastWeather;
import retrofit2.Response;

public class OpenWeatherMain implements
        AsyncResponseCurrentWeather,
        AsyncResponseForecastWeather{

    private CurrentWeather mCurrentWeather;
    private ForecastWeather mForecastWeather;

    public static void main(String[] args){
        OpenWeatherMain main = new OpenWeatherMain();
        main.getCurrentWeather();
        main.getForecastWeather();
    }

    /**
     * this is an instance of Downloader class for CurrentWeather
     * with Main class context as parameter
     */
    private void getCurrentWeather(){
        new DownloadCurrentWeather(this);
    }

    /**
     * this is an instance of downloader class for forecast weather with
     * Main class as parameter
     */
    private void getForecastWeather(){
        new DownloadForecastWeather(this);
    }
    /**
     * @param response is the full JSON response in format of CurrentWeather class
     */
    public void onLoadCurrentWeatherFinished(Response<CurrentWeather> response) {
        this.mCurrentWeather = response.body();
        System.out.println(String.format("[CURRENT] %s", mCurrentWeather.getName()));
    }

    /**
     *
     * @param response is the JSON response in format of ForecastWeather class
     */
    public void onResponseForecastFinished(Response<ForecastWeather> response) {
        this.mForecastWeather = response.body();
        System.out.println(String.format("[FORECAST] %s", mForecastWeather.getCity().getName()));
    }
}
