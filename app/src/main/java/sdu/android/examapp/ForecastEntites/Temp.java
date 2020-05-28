package sdu.android.examapp.ForecastEntites;

public class Temp {
    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;

    public Temp(double day, double min, double max, double night, double eve, double morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
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
