package sdu.android.examapp.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sdu.android.examapp.ForecastEntites.CompleteWeatherForecast;

public interface WeatherService {
    @GET("data/2.5/onecall")
    Call<CompleteWeatherForecast> getWeather(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query(value = "exclude", encoded = true) String excludeParts,
            @Query("appid") String appId
    );
}
