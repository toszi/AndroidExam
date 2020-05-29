package sdu.android.examapp.ForecastEntites;

import android.os.Parcel;
import android.os.Parcelable;

public class FeelsLike implements Parcelable {
    private double day;
    private double night;
    private double eve;
    private double morn;

    protected FeelsLike(Parcel in) {
        day = in.readDouble();
        night = in.readDouble();
        eve = in.readDouble();
        morn = in.readDouble();
    }

    public static final Creator<FeelsLike> CREATOR = new Creator<FeelsLike>() {
        @Override
        public FeelsLike createFromParcel(Parcel in) {
            return new FeelsLike(in);
        }

        @Override
        public FeelsLike[] newArray(int size) {
            return new FeelsLike[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(day);
        dest.writeDouble(night);
        dest.writeDouble(eve);
        dest.writeDouble(morn);
    }

    public double getDay() {
        return day;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }

}
