package network;

import interfaces.OpenWeatherInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.URLData;

public class OpenWeatherApiClient {
    private static OpenWeatherInterface mOpenWeatherInterface;

    public static OpenWeatherInterface getOpenWeatherInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLData.BASE_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mOpenWeatherInterface = retrofit.create(OpenWeatherInterface.class);
        return mOpenWeatherInterface;
    }
}
