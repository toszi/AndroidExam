package sdu.android.examapp.ForecastEntites;

import java.util.List;

public class CompleteWeatherForecast {
    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private List<Daily> dailies;

    public CompleteWeatherForecast(double lat, double lon, String timezone, int timezone_offset, List<Daily> dailies) {
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.timezone_offset = timezone_offset;
        this.dailies = dailies;
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
        return dailies;
    }
}
