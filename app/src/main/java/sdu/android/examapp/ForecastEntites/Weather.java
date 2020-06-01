package sdu.android.examapp.ForecastEntites;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable {
    private int id;
    private String main;
    private String description;
    private String icon;

    protected Weather(Parcel in) {
        id = in.readInt();
        main = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(main);
        dest.writeString(description);
        dest.writeString(icon);
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        //Makes the first letter capital
        return description.substring(0, 1).toUpperCase() + description.substring(1).toLowerCase();
    }

    public String getIcon() {
        return icon;
    }
}
