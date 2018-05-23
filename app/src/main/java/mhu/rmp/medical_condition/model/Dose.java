package mhu.rmp.medical_condition.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Dose implements Parcelable
{
    private String doseName, doseFrequency, doseNoOfDays,time;

//    ArrayList<String> nameOfMedicine;


    public Dose() {
    }

    public Dose(String doseName, String doseFrequency, String doseNoOfDays,String time) {
        this.doseName = doseName;
        this.doseFrequency = doseFrequency;
        this.doseNoOfDays = doseNoOfDays;
        this.time=time;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected Dose(Parcel in) {
        doseName = in.readString();
        doseFrequency = in.readString();
        doseNoOfDays = in.readString();
        time = in.readString();
    }

    public static final Creator<Dose> CREATOR = new Creator<Dose>() {
        @Override
        public Dose createFromParcel(Parcel in) {
            return new Dose(in);
        }

        @Override
        public Dose[] newArray(int size) {
            return new Dose[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doseName);
        dest.writeString(doseFrequency);
        dest.writeString(doseNoOfDays);
        dest.writeString(time);
    }


    public String getDoseName() {
        return doseName;
    }

    public void setDoseName(String doseName) {
        this.doseName = doseName;
    }

    public String getDoseFrequency() {
        return doseFrequency;
    }

    public void setDoseFrequency(String doseFrequency) {
        this.doseFrequency = doseFrequency;
    }

    public String getDoseNoOfDays() {
        return doseNoOfDays;
    }

    public void setDoseNoOfDays(String doseNoOfDays) {
        this.doseNoOfDays = doseNoOfDays;
    }
}
