package com.example.admin.rmp.vital_info.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashwin on 26-Jan-18.
 */

public class Vital_Info implements Parcelable
{
   private String id, registrationNo, height, weight, bloodPressure, temperature, respiration,bloodPressureTo;

    public Vital_Info()
    {

    }

    public Vital_Info(String id, String registrationNo, String height, String weight, String bloodPressure, String temperature, String respiration)
    {
        this.id = id;
        this.registrationNo = registrationNo;
        this.height = height;
        this.weight = weight;
        this.bloodPressure = bloodPressure;
        this.temperature = temperature;
        this.respiration = respiration;
    }

    protected Vital_Info(Parcel in)
    {
        id = in.readString();
        registrationNo = in.readString();
        height = in.readString();
        weight = in.readString();
        bloodPressure = in.readString();
        temperature = in.readString();
        respiration = in.readString();
    }

    public static final Creator<Vital_Info> CREATOR = new Creator<Vital_Info>()
    {
        @Override
        public Vital_Info createFromParcel(Parcel in) {
            return new Vital_Info(in);
        }

        @Override
        public Vital_Info[] newArray(int size) {
            return new Vital_Info[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getRespiration() {
        return respiration;
    }

    public void setRespiration(String respiration) {
        this.respiration = respiration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(registrationNo);
        parcel.writeString(height);
        parcel.writeString(weight);
        parcel.writeString(bloodPressure);
        parcel.writeString(temperature);
        parcel.writeString(respiration);
    }

    public String getBloodPressureTo() {
        return bloodPressureTo;
    }

    public void setBloodPressureTo(String bloodPressureTo) {
        this.bloodPressureTo = bloodPressureTo;
    }
}
