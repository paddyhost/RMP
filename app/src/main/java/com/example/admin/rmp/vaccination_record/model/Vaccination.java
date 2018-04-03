package com.example.admin.rmp.vaccination_record.model;

/**
 * Created by Nikam on 28/01/2018.
 */

public class Vaccination {

    private String dpt,bcg,measles,opv,hepatitis,tt,other;

    public Vaccination() {
    }

    public Vaccination(String dpt, String bcg, String measles, String opv, String hepatitis, String tt, String other) {
        this.dpt = dpt;
        this.bcg = bcg;
        this.measles = measles;
        this.opv = opv;
        this.hepatitis = hepatitis;
        this.tt = tt;
        this.other = other;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getBcg() {
        return bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public String getMeasles() {
        return measles;
    }

    public void setMeasles(String measles) {
        this.measles = measles;
    }

    public String getOpv() {
        return opv;
    }

    public void setOpv(String opv) {
        this.opv = opv;
    }

    public String getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(String hepatitis) {
        this.hepatitis = hepatitis;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
