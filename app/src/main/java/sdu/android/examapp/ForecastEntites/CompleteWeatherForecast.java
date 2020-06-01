package sdu.android.examapp.ForecastEntites;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CompleteWeatherForecast implements Parcelable {
    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private List<Daily> daily;

    protected CompleteWeatherForecast(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
        timezone = in.readString();
        timezone_offset = in.readInt();
        daily = new ArrayList<>();
        in.readList(daily, Daily.class.getClassLoader());
    }

    public static final Creator<CompleteWeatherForecast> CREATOR = new Creator<CompleteWeatherForecast>() {
        @Override
        public CompleteWeatherForecast createFromParcel(Parcel in) {
            return new CompleteWeatherForecast(in);
        }

        @Override
        public CompleteWeatherForecast[] newArray(int size) {
            return new CompleteWeatherForecast[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeString(timezone);
        dest.writeInt(timezone_offset);
        dest.writeList(daily);
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public void setDailies(List<Daily> daily) {
        this.daily = daily;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public List<Daily> getDailies() {
        return daily;
    }




}
