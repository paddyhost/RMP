package com.example.admin.rmp.mhu_test.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class MHU_Test  implements Parcelable
{
    private String BloodGlucose, hemogram, creatine,urea,sgot,sgpt,advised,referred,remark;

    public MHU_Test() {
    }

    public MHU_Test(String bloodGlucose, String hemogram, String creatine, String urea, String sgot, String sgpt) {
        BloodGlucose = bloodGlucose;
        this.hemogram = hemogram;
        this.creatine = creatine;
        this.urea = urea;
        this.sgot = sgot;
        this.sgpt = sgpt;
    }

    protected MHU_Test(Parcel in) {
        BloodGlucose = in.readString();
        hemogram = in.readString();
        creatine = in.readString();
        urea = in.readString();
        sgot = in.readString();
        sgpt = in.readString();
        remark = in.readString();
        referred = in.readString();
        advised = in.readString();

    }

    public static final Creator<MHU_Test> CREATOR = new Creator<MHU_Test>() {
        @Override
        public MHU_Test createFromParcel(Parcel in) {
            return new MHU_Test(in);
        }

        @Override
        public MHU_Test[] newArray(int size) {
            return new MHU_Test[size];
        }
    };

    public String getBloodGlucose() {
        return BloodGlucose;
    }

    public void setBloodGlucose(String bloodGlucose) {
        BloodGlucose = bloodGlucose;
    }

    public String getHemogram() {
        return hemogram;
    }

    public void setHemogram(String hemogram) {
        this.hemogram = hemogram;
    }

    public String getCreatine() {
        return creatine;
    }

    public void setCreatine(String creatine) {
        this.creatine = creatine;
    }

    public String getUrea() {
        return urea;
    }

    public void setUrea(String urea) {
        this.urea = urea;
    }

    public String getSgot() {
        return sgot;
    }

    public void setSgot(String sgot) {
        this.sgot = sgot;
    }

    public String getSgpt() {
        return sgpt;
    }

    public void setSgpt(String sgpt) {
        this.sgpt = sgpt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(BloodGlucose);
        parcel.writeString(hemogram);
        parcel.writeString(creatine);
        parcel.writeString(urea);
        parcel.writeString(sgot);
        parcel.writeString(sgpt);
        parcel.writeString(remark);
        parcel.writeString(referred);
        parcel.writeString(advised);
    }

    public String getAdvised() {
        return advised;
    }

    public void setAdvised(String advised) {
        this.advised = advised;
    }

    public String getReferred() {
        return referred;
    }

    public void setReferred(String referred) {
        this.referred = referred;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
