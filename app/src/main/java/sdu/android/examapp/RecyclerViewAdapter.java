package sdu.android.examapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;

import sdu.android.examapp.ForecastEntites.CompleteWeatherForecast;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private Context context;
    private CompleteWeatherForecast forecast;
    private ArrayList<String> imageUrls;

    public RecyclerViewAdapter(Context context, CompleteWeatherForecast forecast, ArrayList<String> imageUrls) {
        this.context = context;
        this.forecast = forecast;
        this.imageUrls = imageUrls;
    }

    private String getDay(int day){
        switch (day-1){
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
            default:
                return "Not a day";
        }
    }

    private int properWeekDay(int position){
        int day = Calendar.DAY_OF_WEEK + position;
        if(day > 8){
            day = position;
        }
        return day;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //standard way of setting up the recycling view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_weather_display, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        //get images from url
        Glide.with(context).asBitmap().load(imageUrls.get(findAppropriateImage(position))).into(holder.imageView);

        holder.textView.setText(getDay(properWeekDay(position)) + ": " + forecast.getDailies().get(position).getTemp().getDay() + " \u2103 \n" +
                forecast.getDailies().get(position).getWeather().get(0).getDescription());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, forecast.getDailies().get(position).getWeather().get(0).getMain() + ": " + forecast.getDailies().get(position).getWeather().get(0).getDescription(), Toast.LENGTH_SHORT).show();

                //make intend here for new activity
                Intent intent = new Intent(context, WeatherDisplayActivity.class);
                intent.putExtra("imageUrl", imageUrls.get(findAppropriateImage(position)));
                intent.putExtra("CompleteWeatherForecast", forecast);
                intent.putExtra("clickedPosition", position);
                intent.putExtra("dayOfTheWeek", getDay(properWeekDay(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forecast == null ? 0 : forecast.getDailies().size();
    }

    private int findAppropriateImage(int position){
        switch(forecast.getDailies().get(position).getWeather().get(0).getIcon()){
            case "01d":
                return 0;
            case "02d":
                return 1;
            case "03d":
                return 2;
            case "04d":
                return 3;
            case "09d":
                return 4;
            case "10d":
                return 5;
            case "11d":
                return 6;
            case "13d":
                return 7;
            default:
                return 8;
        }
    }


    /**
     * View Holder Class
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.weather_data);
            imageView = itemView.findViewById(R.id.imageView);
            parentLayout = itemView.findViewById(R.id.parent_layout_fragment);
        }
    }
}
