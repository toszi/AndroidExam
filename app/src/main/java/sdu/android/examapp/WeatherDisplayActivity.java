package sdu.android.examapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import sdu.android.examapp.ForecastEntites.CompleteWeatherForecast;

public class WeatherDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_display);

        Intent intent = getIntent();
        CompleteWeatherForecast forecast = intent.getParcelableExtra("CompleteWeatherForecast");
        String imageUrl = intent.getStringExtra("imageUrl");
        int position = intent.getExtras().getInt("clickedPosition");

        //Back button on title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Headline
        TextView dayOfTheWeekTextView = findViewById(R.id.day_headline);
        dayOfTheWeekTextView.setText(intent.getStringExtra("dayOfTheWeek"));

        //Weather image
        ImageView imageView = findViewById(R.id.display_image);
        Glide.with(this).asBitmap().load(imageUrl).into(imageView);

        //Degrees beside the image
        TextView degreesTextView = findViewById(R.id.textview_degrees);
        String degrees = String.valueOf(forecast.getDailies().get(position).getTemp().getDay());
        degreesTextView.setText(degrees + "\u2103");

        //Min / max degrees
        TextView minMaxDegrees = findViewById(R.id.max_min_degrees);
        minMaxDegrees.setText("Highest: " + forecast.getDailies().get(position).getTemp().getMax() + "\u2103"
                + " / Lowest: " + forecast.getDailies().get(position).getTemp().getMin() + "\u2103");

        //Weather description
        TextView weatherDescription = findViewById(R.id.weather_description);
        weatherDescription.setText(forecast.getDailies().get(position).getWeather().get(0).getDescription());

        //Humidity
        TextView humidityTextView = findViewById(R.id.humidityTextView);
        humidityTextView.setText("Humidity: \n" +
                forecast.getDailies().get(position).getHumidity() + "%");

        //Feels like and UV index
        TextView feelsLikeAndUVITextView = findViewById(R.id.feelsLikeAndUVTextView);
        feelsLikeAndUVITextView.setText("Feels like: " + forecast.getDailies().get(position).getFeels_like().getDay() + "\u2103" + "\n"
        + "UV Index: " + forecast.getDailies().get(position).getUvi());

        //Wind speed
        TextView windSpeedTextView = findViewById(R.id.windSpeed);
        windSpeedTextView.setText("Windspeed\n"+
                forecast.getDailies().get(position).getWind_speed() + "m/s \n\n"
        + "Wind direction \n" + calculateDirectionOfWind(forecast.getDailies().get(position).getWind_deg()));

        //Expected rain
        TextView expectedRainTextView = findViewById(R.id.expectedRain);
        expectedRainTextView.setText("Expected amount of rain: \n" +
                forecast.getDailies().get(position).getRain() + " mm");
    }

    private String calculateDirectionOfWind(int degrees){
        int maxDegrees = 360;
        int oneSixTeenthOfMax = maxDegrees / 16;
        int oneEighthOfMax = maxDegrees / 8;
        int north = 0;
        int east = 90;
        int south = 180;
        int west = 270;
        int northEast = north + oneEighthOfMax;
        int southEast = east + oneEighthOfMax;
        int southWest = south + oneEighthOfMax;
        int northWest = west + oneEighthOfMax;

        if(degrees > maxDegrees - oneSixTeenthOfMax || degrees < oneSixTeenthOfMax){
            return "North";
        }else if (degrees < northEast - oneSixTeenthOfMax || degrees < northEast + oneSixTeenthOfMax){
            return "North East";
        }else if (degrees < east - oneSixTeenthOfMax || degrees < east + oneSixTeenthOfMax){
            return "East";
        }else if (degrees < southEast - oneSixTeenthOfMax || degrees < southEast + oneSixTeenthOfMax){
            return "South East";
        }else if (degrees < south - oneSixTeenthOfMax || degrees < south + oneSixTeenthOfMax){
            return "South";
        }else if (degrees < southWest - oneSixTeenthOfMax || degrees < southWest + oneSixTeenthOfMax){
            return "South West";
        }else if (degrees < west - oneSixTeenthOfMax || degrees < west + oneSixTeenthOfMax){
            return "West";
        }else if (degrees < northWest - oneSixTeenthOfMax || degrees < northWest + oneSixTeenthOfMax){
            return "North West";
        }
        return "Unknown";
    }
}
