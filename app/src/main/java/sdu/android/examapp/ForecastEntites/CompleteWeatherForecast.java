package sdu.android.examapp.ForecastEntites;

import java.util.List;

public class CompleteWeatherForecast {
    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private List<Daily> daily;

    public CompleteWeatherForecast(double lat, double lon, String timezone, int timezone_offset, List<Daily> daily) {
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.timezone_offset = timezone_offset;
        this.daily = daily;
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
