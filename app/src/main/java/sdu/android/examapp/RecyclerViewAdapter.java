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

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> weatherDataList;
    //private ArrayList<String> imageList;
    private int resourceId;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<String> weatherDataList, int resourceId){ /*ArrayList<String> imageList) {*/
        this.weatherDataList = weatherDataList;
        this.resourceId = resourceId;
        //this.imageList = imageList;
        this.context = context;
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
        //Glide.with(context).asBitmap().load(imageList.get(position)).into(holder.imageView);

        holder.textView.setText(weatherDataList.get(position));
        holder.imageView.setImageResource(resourceId);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, weatherDataList.get(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherDataList.size();
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
