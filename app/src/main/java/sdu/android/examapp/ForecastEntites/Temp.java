package sdu.android.examapp.ForecastEntites;

import android.os.Parcel;
import android.os.Parcelable;

public class Temp implements Parcelable {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;

    protected Temp(Parcel in) {
        day = in.readDouble();
        min = in.readDouble();
        max = in.readDouble();
        night = in.readDouble();
        eve = in.readDouble();
        morn = in.readDouble();
    }

    public static final Creator<Temp> CREATOR = new Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        @Override
        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(day);
        dest.writeDouble(min);
        dest.writeDouble(max);
        dest.writeDouble(night);
        dest.writeDouble(eve);
        dest.writeDouble(morn);
    }

    public double getDay() {
        return oneDecimalDouble(kelvinToCelsius(day));
    }

    public double getMin() {
        return oneDecimalDouble(kelvinToCelsius(min));
    }

    public double getMax() {
        return oneDecimalDouble(kelvinToCelsius(max));
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
