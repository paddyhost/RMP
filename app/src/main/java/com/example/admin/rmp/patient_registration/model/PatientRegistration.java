package com.example.admin.rmp.patient_registration.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashwin on 26-Jan-18.
 */

public class PatientRegistration implements Parcelable
{
    private String id, registrationNo, fName,Lname, gender,dateOfRegistration,address,mobileNo,dob;

    public PatientRegistration()
    {

    }

    public PatientRegistration(String id, String registrationNo, String fName, String lname, String gender, String dateOfRegistration, String address, String mobileNo)
    {
        this.id = id;
        this.registrationNo = registrationNo;
        this.fName = fName;
        Lname = lname;
        this.gender = gender;
        this.dateOfRegistration = dateOfRegistration;
        this.address = address;
        this.mobileNo = mobileNo;
    }

    protected PatientRegistration(Parcel in)
    {
        id = in.readString();
        registrationNo = in.readString();
        fName = in.readString();
        Lname = in.readString();
        gender = in.readString();
        dateOfRegistration = in.readString();
        address = in.readString();
        mobileNo = in.readString();
    }

    public static final Creator<PatientRegistration> CREATOR = new Creator<PatientRegistration>()
    {
        @Override
        public PatientRegistration createFromParcel(Parcel in) {
            return new PatientRegistration(in);
        }

        @Override
        public PatientRegistration[] newArray(int size) {
            return new PatientRegistration[size];
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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(id);
        parcel.writeString(registrationNo);
        parcel.writeString(fName);
        parcel.writeString(Lname);
        parcel.writeString(gender);
        parcel.writeString(dateOfRegistration);
        parcel.writeString(address);
        parcel.writeString(mobileNo);
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
