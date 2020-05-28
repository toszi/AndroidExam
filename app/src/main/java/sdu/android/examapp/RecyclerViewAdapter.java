package sdu.android.examapp;

import android.content.Context;
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

        int imageId = 0;

        //get images from url
        Glide.with(context).asBitmap().load(imageUrls.get(imageId)).into(holder.imageView);

        holder.textView.setText("Odense " + forecast.getDailies().get(position).getTemp().getDay() + " \u2103");

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Odense", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return forecast == null ? 0 : forecast.getDailies().size();
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
