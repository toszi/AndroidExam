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
import android.widget.TextView;

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

    //variables
    private CompleteWeatherForecast completeWeatherForecast;
    private ArrayList<String> imageUrls = new ArrayList<>();

    //Url variables
    private final static String WEATHER_API_BASE_URL = "https://api.openweathermap.org/";
    private final static String API_KEY = "7674b889ee154b8410ac0be7b9dcd5e9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Created");

        RetrofitAsyncTask retrofitAsyncTask = new RetrofitAsyncTask();
        retrofitAsyncTask.execute();

        TextView cityTextView = findViewById(R.id.cityTextView);
        String city = "Odense";
        cityTextView.setText(city);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initImageUrls(){
        Log.d(TAG, "initImageBitmaps: Getting all weather images");

        //clear sky
        imageUrls.add("https://openweathermap.org/img/wn/01d@4x.png");
        //few clouds
        imageUrls.add("https://openweathermap.org/img/wn/02d@4x.png");
        //scattered clouds
        imageUrls.add("https://openweathermap.org/img/wn/03d@4x.png");
        //broken clouds
        imageUrls.add("https://openweathermap.org/img/wn/04d@4x.png");
        //shower rain
        imageUrls.add("https://openweathermap.org/img/wn/09d@4x.png");
        //rain
        imageUrls.add("https://openweathermap.org/img/wn/10d@4x.png");
        //thunderstorm
        imageUrls.add("https://openweathermap.org/img/wn/11d@4x.png");
        //snow
        imageUrls.add("https://openweathermap.org/img/wn/13d@4x.png");
        //mist
        imageUrls.add("https://openweathermap.org/img/wn/50d@4x.png");

        //initiate recycler view
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, completeWeatherForecast, imageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    /* Async task class */
    private class RetrofitAsyncTask extends AsyncTask<Void, Void, CompleteWeatherForecast>{

        private static final String ASYNC_TAG = "RetrofitAsyncTask";

        @Override
        protected void onPreExecute() {
            //get all images from openweathermap for the different weather types
            initImageUrls();

            //initialize RecyclerView
            //initRecyclerView();
        }

        @Override
        protected CompleteWeatherForecast doInBackground(Void... voids) {
            CompleteWeatherForecast weatherForecast = null;

            //instantiate retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(WEATHER_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //create retrofit instance of WeatherService
            weatherService = retrofit.create(WeatherService.class);

            Log.d(ASYNC_TAG, "Retrieving weather data");
            Call<CompleteWeatherForecast> forecast = weatherService.getWeather(55.403756, 10.40270, "current,minutely,hourly", API_KEY);
            try {
                weatherForecast = forecast.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d(ASYNC_TAG, "Weather data retrieved and available");
            return weatherForecast;
        }

        @Override
        protected void onPostExecute(CompleteWeatherForecast weatherForecast) {
            super.onPostExecute(weatherForecast);
            completeWeatherForecast = weatherForecast;
            initImageUrls();
        }
    }
}
