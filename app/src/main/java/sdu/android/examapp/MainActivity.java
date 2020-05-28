package sdu.android.examapp;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sdu.android.examapp.ForecastEntites.CompleteWeatherForecast;
import sdu.android.examapp.Interfaces.WeatherService;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //Weather data variables
    Retrofit retrofit;
    WeatherService weatherService;
    Thread webApiCallThread;
    volatile boolean apiCallRunning = true;

    //variables
    private ArrayList<String> weatherDataList;

    //Url variables
    private final static String WEATHER_API_BASE_URL = "https://api.openweathermap.org/";
    private final static String API_KEY = "7674b889ee154b8410ac0be7b9dcd5e9";
    private final static String PARAM_APPID = "appid";
    //parameters for current day request
    private final static String PARAM_QUERY = "q";
    //parameters for forecast request
    private final static String PARAM_LON = "lon";
    private final static String PARAM_LAT = "lat";
    private final static String PARAM_EXCLUDE = "exclude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Created");

        //instantiate retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //create retrofit instance of WeatherService
        weatherService = retrofit.create(WeatherService.class);

        //create new thread
        webApiCallThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Thread running");
                Call<CompleteWeatherForecast> forecast = weatherService.getWeather(55.4, 10.39, "current,minutely,hourly", API_KEY);
                try {
                    final CompleteWeatherForecast forecastResponse = forecast.execute().body();
                    Log.d(TAG, "run: daily size is " + forecastResponse.getDailies().size());
                    for (int i = 0; i < forecastResponse.getDailies().size(); i++){
                        Log.d(TAG, "run: daily weather = " + forecastResponse.getDailies().get(i).getWeather().get(0).getMain());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //start the thread
        webApiCallThread.start();
    }

    @Override
    protected void onDestroy() {
        apiCallRunning = false;
        super.onDestroy();
        try{
            webApiCallThread.join();
            Log.d(TAG, "onDestroy: Thread stopped");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, weatherDataList, R.drawable.ic_launcher_foreground);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
