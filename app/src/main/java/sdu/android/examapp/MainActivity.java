package sdu.android.examapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sdu.android.examapp.ForecastEntites.CompleteWeatherForecast;
import sdu.android.examapp.Interfaces.IOnTaskComplete;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //variables
    private ArrayList<String> weatherDataList;

    //Url variables
    private final static String WEATHER_API_BASE_URL = "https://api.openweathermap.org/data/2.5/";
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

        //initCurrentWeatherInfo();
    }

    private void startAsyncTast(View v){

    }

    private void initCurrentWeatherInfo(){
        //new Async Task
        //execute async task

        final RequestHandler handler = new RequestHandler();
        try
        {
            handler.getCurrentResponseFromHttpUrl(RequestHandler.createCurrentDayURL("Odense"));
            //handler.getForecastResponseFromHttpUrl(RequestHandler.createForecastURL());
        } catch (IOException e) {
            Log.d(TAG + "-IOexception", e.toString());
        }

        weatherDataList = handler.getWeatherDataList();

        initRecyclerView();
    }

    private URL createCurrentDayURL(String city){
        Uri builtUri = Uri.parse(WEATHER_API_BASE_URL+"weather")
                .buildUpon()
                .appendQueryParameter(PARAM_QUERY, city)
                .appendQueryParameter(PARAM_APPID, API_KEY)
                .build();

        URL finalURL = null;
        try{
            finalURL = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return finalURL;
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, weatherDataList, R.drawable.ic_launcher_foreground);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //make embedded class that replaces handler that makes async
    private class AsyncWeatherDataRequest extends AsyncTask<URL, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            initRecyclerView();
        }

        @Override
        protected Void doInBackground(URL... urls) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(urls[0]).build();
            Call myCall = client.newCall(request);
            //makes async call
            myCall.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    call.cancel();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    final String callResponse = response.body().string();
                    Log.d(TAG, "Async requesting called: " + callResponse);

                    Gson gson = new Gson();
                    CompleteWeatherForecast weatherInfo = gson.fromJson(callResponse, CompleteWeatherForecast.class);
                    for(int i = 0; i < weatherInfo.getDailies().size(); i++) {
                        weatherDataList.add("Odense: " + weatherInfo.getDailies().get(i).getTemp() + " \u2103");
                    }
                }
            });
            return null;
        }
    }
}
