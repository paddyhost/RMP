package com.example.admin.rmp.previous_records.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class PatientHistory implements Parcelable
{
    private String prevHospital, doctorName1, doctorName2, doctorName3;

    public PatientHistory(String prevHospital, String doctorName1, String doctorName2, String doctorName3) {
        this.prevHospital = prevHospital;
        this.doctorName1 = doctorName1;
        this.doctorName2 = doctorName2;
        this.doctorName3 = doctorName3;
    }

    protected PatientHistory(Parcel in) {
        prevHospital = in.readString();
        doctorName1 = in.readString();
        doctorName2 = in.readString();
        doctorName3 = in.readString();
    }

    public static final Creator<PatientHistory> CREATOR = new Creator<PatientHistory>() {
        @Override
        public PatientHistory createFromParcel(Parcel in) {
            return new PatientHistory(in);
        }

        @Override
        public PatientHistory[] newArray(int size) {
            return new PatientHistory[size];
        }
    };

    public PatientHistory() {

    }

    public String getPrevHospital() {
        return prevHospital;
    }

    public void setPrevHospital(String prevHospital) {
        this.prevHospital = prevHospital;
    }

    public String getDoctorName1() {
        return doctorName1;
    }

    public void setDoctorName1(String doctorName1) {
        this.doctorName1 = doctorName1;
    }

    public String getDoctorName2() {
        return doctorName2;
    }

    public void setDoctorName2(String doctorName2) {
        this.doctorName2 = doctorName2;
    }

    public String getDoctorName3() {
        return doctorName3;
    }

    public void setDoctorName3(String doctorName3) {
        this.doctorName3 = doctorName3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(prevHospital);
        parcel.writeString(doctorName1);
        parcel.writeString(doctorName2);
        parcel.writeString(doctorName3);
    }
}
