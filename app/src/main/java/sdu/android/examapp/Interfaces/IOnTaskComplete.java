package sdu.android.examapp.Interfaces;

import android.content.Context;

import java.util.ArrayList;

public interface IOnTaskComplete {
    void onTaskComplete(Context context, ArrayList<String> weatherDataList, int resourceId);
}
