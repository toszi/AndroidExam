package sdu.android.examapp.ForecastEntites;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Daily implements Parcelable {
    private int dt;
    private int sunrise;
    private int sunset;
    private Temp temp;
    private FeelsLike feels_like;
    private int pressure;
    private int humidity;
    private double dew_point;
    private double wind_speed;
    private int wind_deg;
    private List<Weather> weather;
    private int clouds;
    private double rain;
    private double uvi;

    protected Daily(Parcel in) {
        dt = in.readInt();
        sunrise = in.readInt();
        sunset = in.readInt();
        temp = (Temp) in.readValue(Temp.class.getClassLoader());
        feels_like = (FeelsLike) in.readValue(FeelsLike.class.getClassLoader());
        pressure = in.readInt();
        humidity = in.readInt();
        dew_point = in.readDouble();
        wind_speed = in.readDouble();
        wind_deg = in.readInt();
        /* weather */
        weather = new ArrayList<>();
        in.readTypedList(weather, Weather.CREATOR);
        /*         */
        clouds = in.readInt();
        rain = in.readDouble();
        uvi = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dt);
        dest.writeInt(sunrise);
        dest.writeInt(sunset);
        dest.writeValue(temp);
        dest.writeValue(feels_like);
        dest.writeInt(pressure);
        dest.writeInt(humidity);
        dest.writeDouble(dew_point);
        dest.writeDouble(wind_speed);
        dest.writeInt(wind_deg);
        dest.writeList(weather);
        dest.writeInt(clouds);
        dest.writeDouble(rain);
        dest.writeDouble(uvi);
    }

    public static final Parcelable.Creator<Daily> CREATOR = new Parcelable.Creator<Daily>() {
        @Override
        public Daily createFromParcel(Parcel in) {
            return new Daily(in);
        }

        @Override
        public Daily[] newArray(int size) {
            return new Daily[size];
        }
    };

    public int getDt() {
        return dt;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public Temp getTemp() {
        return temp;
    }

    public FeelsLike getFeels_like() {
        return feels_like;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getDew_point() {
        return dew_point;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public int getClouds() {
        return clouds;
    }

    public double getRain() {
        return rain;
    }

    public double getUvi() {
        return uvi;
    }


}
