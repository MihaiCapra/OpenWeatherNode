import interfaces.AsyncResponseCurrentWeather;
import models.CurrentWeather;
import network.DownloadCurrentWeather;
import retrofit2.Response;

public class OpenWeatherMain implements AsyncResponseCurrentWeather{
    public static void main(String[] args){
        OpenWeatherMain main = new OpenWeatherMain();
        main.getCurrentWeather();
    }

    private void getCurrentWeather(){
        new DownloadCurrentWeather(this);
    }

    public void onLoadCurrentWeatherFinished(Response<CurrentWeather> response) {
        System.out.println(response.body().getMain().getHumidity());
    }
}
