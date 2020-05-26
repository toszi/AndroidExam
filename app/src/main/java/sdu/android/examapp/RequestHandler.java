package sdu.android.examapp;

import android.net.Uri;
import android.util.Log;

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
import sdu.android.examapp.Entities.CompleteWeatherInfo;

public class RequestHandler {

    private static final String TAG = "RequestHandler";

    private final static String WEATHER_API_BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private final static String API_KEY = "7674b889ee154b8410ac0be7b9dcd5e9";
    private final static String PARAM_APPID = "appid";
    //parameters for current day request
    private final static String PARAM_QUERY = "q";

    //parameters for forecast request
    private final static String PARAM_LON = "lon";
    private final static String PARAM_LAT = "lat";
    private final static String PARAM_EXCLUDE = "exclude";

    //variables
    private ArrayList<String> weatherDataList = new ArrayList<>();
    private ArrayList<String> imageUrlList = new ArrayList<>();

    static URL createCurrentDayURL(String city){
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

    void getCurrentResponseFromHttpUrl(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
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
                Log.d(TAG + "-getCurrentResponseFromHttpUrl-onResponse", callResponse);

                Gson gson = new Gson();
                CompleteWeatherInfo weatherInfo = gson.fromJson(callResponse, CompleteWeatherInfo.class);
                weatherDataList.add("Current Weather:\n" + weatherInfo.getName() + ": " + weatherInfo.getMain().getTemp() + " \u2103");
            }
        });
    }
/*
    static URL createForecastURL(){
        Uri builtUri = Uri.parse(WEATHER_API_BASE_URL+"onecall")
                .buildUpon()
                .appendQueryParameter(PARAM_LAT, "55.4")
                .appendQueryParameter(PARAM_LON, "10.39")
                .appendQueryParameter(PARAM_EXCLUDE, "current,minutely,hourly")
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

    void getForecastResponseFromHttpUrl(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
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
                Log.d(TAG + "-getForecastResponseFromHttpUrl-onResponse", callResponse);

                Gson gson = new Gson();
                CompleteWeatherInfo weatherInfo = gson.fromJson(callResponse, CompleteWeatherInfo.class);
                //weatherDataList.add("Current Weather:\n" + weatherInfo.getName() + ": " + weatherInfo.getMain().getTemp() + " \u2103");
            }
        });
    }*/

    public ArrayList<String> getWeatherDataList() {
        return weatherDataList;
    }
}
