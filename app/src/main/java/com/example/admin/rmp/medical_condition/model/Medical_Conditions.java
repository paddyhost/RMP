package com.example.admin.rmp.medical_condition.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class Medical_Conditions implements Parcelable
{
    private String chiefcomplaints1, chiefcomplaints2,chiefcomplaints3,briefHistory1,
     briefHistory2,briefHistory3,investigation,tratementtaken,
     anyimprovement,diagnosysList;

    public Medical_Conditions() {
    }

    public Medical_Conditions(String chiefcomplaints1, String chiefcomplaints2, String chiefcomplaints3,
                              String briefHistory1, String briefHistory2, String briefHistory3, String investigation,
                              String tratementtaken, String anyimprovement,String diagnosysList)
    {
        this.chiefcomplaints1 = chiefcomplaints1;
        this.chiefcomplaints2 = chiefcomplaints2;
        this.chiefcomplaints3 = chiefcomplaints3;
        this.briefHistory1 = briefHistory1;
        this.briefHistory2 = briefHistory2;
        this.briefHistory3 = briefHistory3;
        this.investigation = investigation;
        this.tratementtaken = tratementtaken;
        this.anyimprovement = anyimprovement;
        this.diagnosysList = diagnosysList;
    }

    protected Medical_Conditions(Parcel in) {
        chiefcomplaints1 = in.readString();
        chiefcomplaints2 = in.readString();
        chiefcomplaints3 = in.readString();
        briefHistory1 = in.readString();
        briefHistory2 = in.readString();
        briefHistory3 = in.readString();
        investigation = in.readString();
        tratementtaken = in.readString();
        anyimprovement = in.readString();
        diagnosysList = in.readString();
    }

    public static final Creator<Medical_Conditions> CREATOR = new Creator<Medical_Conditions>() {
        @Override
        public Medical_Conditions createFromParcel(Parcel in) {
            return new Medical_Conditions(in);
        }

        @Override
        public Medical_Conditions[] newArray(int size) {
            return new Medical_Conditions[size];
        }
    };

    public String getChiefcomplaints1() {
        return chiefcomplaints1;
    }

    public void setChiefcomplaints1(String chiefcomplaints1) {
        this.chiefcomplaints1 = chiefcomplaints1;
    }

    public String getChiefcomplaints2() {
        return chiefcomplaints2;
    }

    public void setChiefcomplaints2(String chiefcomplaints2) {
        this.chiefcomplaints2 = chiefcomplaints2;
    }

    public String getChiefcomplaints3() {
        return chiefcomplaints3;
    }

    public void setChiefcomplaints3(String chiefcomplaints3) {
        this.chiefcomplaints3 = chiefcomplaints3;
    }

    public String getBriefHistory1() {
        return briefHistory1;
    }

    public void setBriefHistory1(String briefHistory1) {
        this.briefHistory1 = briefHistory1;
    }

    public String getBriefHistory2() {
        return briefHistory2;
    }

    public void setBriefHistory2(String briefHistory2) {
        this.briefHistory2 = briefHistory2;
    }

    public String getBriefHistory3() {
        return briefHistory3;
    }

    public void setBriefHistory3(String briefHistory3) {
        this.briefHistory3 = briefHistory3;
    }

    public String getInvestigation() {
        return investigation;
    }

    public void setInvestigation(String investigation) {
        this.investigation = investigation;
    }

    public String getTratementtaken() {
        return tratementtaken;
    }

    public void setTratementtaken(String tratementtaken) {
        this.tratementtaken = tratementtaken;
    }

    public String getAnyimprovement() {
        return anyimprovement;
    }

    public void setAnyimprovement(String anyimprovement) {
        this.anyimprovement = anyimprovement;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(chiefcomplaints1);
        parcel.writeString(chiefcomplaints2);
        parcel.writeString(chiefcomplaints3);
        parcel.writeString(briefHistory1);
        parcel.writeString(briefHistory2);
        parcel.writeString(briefHistory3);
        parcel.writeString(investigation);
        parcel.writeString(tratementtaken);
        parcel.writeString(anyimprovement);
        parcel.writeString(diagnosysList);
    }

    public String getDiagnosysList() {
        return diagnosysList;
    }

    public void setDiagnosysList(String diagnosysList) {
        this.diagnosysList = diagnosysList;
    }
}
