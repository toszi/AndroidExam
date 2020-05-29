package sdu.android.examapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

        TextView degreesTextView = findViewById(R.id.textview_degrees);
        String degrees = String.valueOf(forecast.getDailies().get(position).getTemp().getDay());
        degreesTextView.setText(degrees + " \u2103");

        TextView dayOfTheWeekTextView = findViewById(R.id.day_headline);
        dayOfTheWeekTextView.setText(intent.getStringExtra("dayOfTheWeek"));

        ImageView imageView = findViewById(R.id.display_image);
        Glide.with(this).asBitmap().load(imageUrl).into(imageView);
    }
}
