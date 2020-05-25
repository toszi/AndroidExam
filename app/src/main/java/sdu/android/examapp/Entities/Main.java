package sdu.android.examapp.Entities;

public class Main {
    private double temp;
    private double feels_like;
    private double temp_max;
    private int pressure;
    private int humidity;

    public Main(double temp, double feels_like, double temp_max, int pressure, int humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public double getTemp() {
        return kelvinToCelsius(temp);
    }

    public double getFeels_like() {
        return kelvinToCelsius(feels_like);
    }

    public double getTemp_max() {
        return kelvinToCelsius(temp_max);
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    private double kelvinToCelsius(double degreesKelvin){
        return degreesKelvin - 273.15;
    }
}
