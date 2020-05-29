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
        return oneDecimalDouble(kelvinToCelsius(day));
    }

    public double getNight() {
        return oneDecimalDouble(kelvinToCelsius(night));
    }

    public double getEve() {
        return oneDecimalDouble(kelvinToCelsius(eve));
    }

    public double getMorn() {
        return oneDecimalDouble(kelvinToCelsius(morn));
    }

    private double kelvinToCelsius(double degreesKelvin){
        return degreesKelvin - 273.15;
    }

    private double oneDecimalDouble(double value){
        return Math.round(value * 10) / 10.0;
    }

}
