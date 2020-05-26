package sdu.android.examapp.ForecastEntites;

import java.util.List;

public class Daily {
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

    public Daily(int dt, int sunrise, int sunset, Temp temp, FeelsLike feels_like,
                 int pressure, int humidity, double dew_point, double wind_speed, int wind_deg,
                 List<Weather> weather, int clouds, double rain, double uvi) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dew_point = dew_point;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weather = weather;
        this.clouds = clouds;
        this.rain = rain;
        this.uvi = uvi;
    }

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
