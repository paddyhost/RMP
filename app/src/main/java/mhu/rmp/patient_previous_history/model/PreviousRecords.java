package mhu.rmp.patient_previous_history.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import mhu.rmp.medical_condition.model.Dose;
import mhu.rmp.mhu_test.model.MHU_Test;



public class PreviousRecords implements Parcelable{

    public  static String id, registration_no, fname, lanme, dob, gender, mobile, regitrationdate, address, state, district,
            city, area, location, unique_id, patient_category, visit_master_id, created_at,

    //vital
    pid, registrationno, height, weight, bloodpresure, tempreture, respiration, updatedate, bpto,


    //medical
    chiefcomplaints1, chiefcomplaints2, chiefcomplaints3, briefHistory1, briefHistory2, briefHistory3,
            investigation, tratementtaken, anyimprovement, diagnosys, patient_id, registrationid,


    //mhutestmaster

    bloodglucose,heamogram,creatine,urea,sgot,sgpt,adviced,ferered,remark,


    //vaccination
    dpt, bcg, measles, opv, ttt, hepatitis, other,

    //patient_history

    drname1,drname2,drname3,hospitalname;


    protected PreviousRecords(Parcel in) {
        id = in.readString();
        registration_no = in.readString();
        fname = in.readString();
        lanme = in.readString();
        dob = in.readString();
        gender = in.readString();
        mobile = in.readString();
        regitrationdate = in.readString();
        address = in.readString();
        state = in.readString();
        district = in.readString();
        city = in.readString();
        area = in.readString();
        location = in.readString();
        unique_id = in.readString();
        patient_category = in.readString();
        visit_master_id = in.readString();
        created_at = in.readString();
        pid = in.readString();
        registrationno = in.readString();
        height = in.readString();
        weight = in.readString();
        bloodpresure = in.readString();
        tempreture = in.readString();
        respiration = in.readString();
        updatedate = in.readString();
        bpto = in.readString();
        chiefcomplaints1 = in.readString();
        chiefcomplaints2 = in.readString();
        chiefcomplaints3 = in.readString();
        briefHistory1 = in.readString();
        briefHistory2 = in.readString();
        briefHistory3 = in.readString();
        investigation = in.readString();
        tratementtaken = in.readString();
        anyimprovement = in.readString();
        diagnosys = in.readString();
        patient_id = in.readString();
        registrationid = in.readString();

        dpt = in.readString();
        bcg = in.readString();
        measles = in.readString();
        opv = in.readString();
        ttt = in.readString();
        hepatitis = in.readString();
        other = in.readString();

        bloodglucose= in.readString();
        heamogram= in.readString();
        creatine= in.readString();
        urea= in.readString();
        sgot= in.readString();
        sgpt= in.readString();
        adviced= in.readString();
        ferered= in.readString();
        remark= in.readString();

        drname1= in.readString();
        drname2= in.readString();
        drname3= in.readString();
        hospitalname= in.readString();

    }

    public static final Creator<PreviousRecords> CREATOR = new Creator<PreviousRecords>() {
        @Override
        public PreviousRecords createFromParcel(Parcel in) {
            return new PreviousRecords(in);
        }

        @Override
        public PreviousRecords[] newArray(int size) {
            return new PreviousRecords[size];
        }
    };

    public ArrayList<MHU_Test> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<MHU_Test> testList) {
        this.testList = testList;
    }

    //test
    ArrayList<MHU_Test> testList;

    public static Creator<PreviousRecords> getCREATOR() {
        return CREATOR;
    }

    public ArrayList<Dose> getDoseArrayList() {
        return doseArrayList;
    }

    public void setDoseArrayList(ArrayList<Dose> doseArrayList) {
        this.doseArrayList = doseArrayList;
    }

    ArrayList<Dose> doseArrayList;

    public PreviousRecords() {
    }

    public PreviousRecords(String id, String registration_no, String fname, String lanme, String dob, String gender, String mobile, String regitrationdate, String address, String state, String district, String city, String area, String location, String unique_id, String patient_category, String visit_master_id, String created_at, String pid, String registrationno, String height, String weight, String bloodpresure, String tempreture, String respiration, String updatedate, String bpto, String chiefcomplaints1, String chiefcomplaints2, String chiefcomplaints3, String briefHistory1, String briefHistory2, String briefHistory3, String investigation, String tratementtaken, String anyimprovement, String diagnosys, String patient_id, String dpt, String bcg, String measles, String opv, String ttt, String hepatitis, String other) {
        this.id = id;
        this.registration_no = registration_no;
        this.fname = fname;
        this.lanme = lanme;
        this.dob = dob;
        this.gender = gender;
        this.mobile = mobile;
        this.regitrationdate = regitrationdate;
        this.address = address;
        this.state = state;
        this.district = district;
        this.city = city;
        this.area = area;
        this.location = location;
        this.unique_id = unique_id;
        this.patient_category = patient_category;
        this.visit_master_id = visit_master_id;
        this.created_at = created_at;
        this.pid = pid;
        this.registrationno = registrationno;
        this.height = height;
        this.weight = weight;
        this.bloodpresure = bloodpresure;
        this.tempreture = tempreture;
        this.respiration = respiration;
        this.updatedate = updatedate;
        this.bpto = bpto;
        this.chiefcomplaints1 = chiefcomplaints1;
        this.chiefcomplaints2 = chiefcomplaints2;
        this.chiefcomplaints3 = chiefcomplaints3;
        this.briefHistory1 = briefHistory1;
        this.briefHistory2 = briefHistory2;
        this.briefHistory3 = briefHistory3;
        this.investigation = investigation;
        this.tratementtaken = tratementtaken;
        this.anyimprovement = anyimprovement;
        this.diagnosys = diagnosys;
        this.patient_id = patient_id;
        this.registrationid = registrationid;

        this.dpt = dpt;
        this.bcg = bcg;
        this.measles = measles;
        this.opv = opv;
        this.ttt = ttt;
        this.hepatitis = hepatitis;
        this.other = other;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLanme() {
        return lanme;
    }

    public void setLanme(String lanme) {
        this.lanme = lanme;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRegitrationdate() {
        return regitrationdate;
    }

    public void setRegitrationdate(String regitrationdate) {
        this.regitrationdate = regitrationdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getPatient_category() {
        return patient_category;
    }

    public void setPatient_category(String patient_category) {
        this.patient_category = patient_category;
    }

    public String getVisit_master_id() {
        return visit_master_id;
    }

    public void setVisit_master_id(String visit_master_id) {
        this.visit_master_id = visit_master_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getRegistrationno() {
        return registrationno;
    }

    public void setRegistrationno(String registrationno) {
        this.registrationno = registrationno;
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

    public String getBloodpresure() {
        return bloodpresure;
    }

    public void setBloodpresure(String bloodpresure) {
        this.bloodpresure = bloodpresure;
    }

    public String getTempreture() {
        return tempreture;
    }

    public void setTempreture(String tempreture) {
        this.tempreture = tempreture;
    }

    public String getRespiration() {
        return respiration;
    }

    public void setRespiration(String respiration) {
        this.respiration = respiration;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getBpto() {
        return bpto;
    }

    public void setBpto(String bpto) {
        this.bpto = bpto;
    }

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

    public String getDiagnosys() {
        return diagnosys;
    }

    public void setDiagnosys(String diagnosys) {
        this.diagnosys = diagnosys;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getRegistrationid() {
        return registrationid;
    }

    public void setRegistrationid(String registrationid) {
        this.registrationid = registrationid;
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

    public String getTtt() {
        return ttt;
    }

    public void setTtt(String ttt) {
        this.ttt = ttt;
    }

    public String getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(String hepatitis) {
        this.hepatitis = hepatitis;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public  String getBloodglucose() {
        return bloodglucose;
    }

    public  void setBloodglucose(String bloodglucose) {
        PreviousRecords.bloodglucose = bloodglucose;
    }

    public  String getHeamogram() {
        return heamogram;
    }

    public  void setHeamogram(String heamogram) {
        PreviousRecords.heamogram = heamogram;
    }

    public  String getCreatine() {
        return creatine;
    }

    public  void setCreatine(String creatine) {
        PreviousRecords.creatine = creatine;
    }

    public  String getUrea() {
        return urea;
    }

    public  void setUrea(String urea) {
        PreviousRecords.urea = urea;
    }

    public String getSgot() {
        return sgot;
    }

    public  void setSgot(String sgot) {
        PreviousRecords.sgot = sgot;
    }

    public  String getSgpt() {
        return sgpt;
    }

    public  void setSgpt(String sgpt) {
        PreviousRecords.sgpt = sgpt;
    }

    public  String getAdviced() {
        return adviced;
    }

    public  void setAdviced(String adviced) {
        PreviousRecords.adviced = adviced;
    }

    public  String getFerered() {
        return ferered;
    }

    public  void setFerered(String ferered) {
        PreviousRecords.ferered = ferered;
    }

    public  String getRemark() {
        return remark;
    }

    public  void setRemark(String remark) {
        PreviousRecords.remark = remark;
    }


    public  String getDrname1() {
        return drname1;
    }

    public  void setDrname1(String drname1) {
        PreviousRecords.drname1 = drname1;
    }

    public  String getDrname2() {
        return drname2;
    }

    public  void setDrname2(String drname2) {
        PreviousRecords.drname2 = drname2;
    }

    public String getDrname3() {
        return drname3;
    }

    public  void setDrname3(String drname3) {
        PreviousRecords.drname3 = drname3;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        PreviousRecords.hospitalname = hospitalname;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(registration_no);
        dest.writeString(fname);
        dest.writeString(lanme);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(mobile);
        dest.writeString(regitrationdate);
        dest.writeString(address);
        dest.writeString(state);
        dest.writeString(district);
        dest.writeString(city);
        dest.writeString(area);
        dest.writeString(location);
        dest.writeString(unique_id);
        dest.writeString(patient_category);
        dest.writeString(visit_master_id);
        dest.writeString(created_at);
        dest.writeString(pid);
        dest.writeString(registrationno);
        dest.writeString(height);
        dest.writeString(weight);
        dest.writeString(bloodpresure);
        dest.writeString(tempreture);
        dest.writeString(respiration);
        dest.writeString(updatedate);
        dest.writeString(bpto);
        dest.writeString(chiefcomplaints1);
        dest.writeString(chiefcomplaints2);
        dest.writeString(chiefcomplaints3);
        dest.writeString(briefHistory1);
        dest.writeString(briefHistory2);
        dest.writeString(briefHistory3);
        dest.writeString(investigation);
        dest.writeString(tratementtaken);
        dest.writeString(anyimprovement);
        dest.writeString(diagnosys);
        dest.writeString(patient_id);
        dest.writeString(registrationid);

        dest.writeString(dpt);
        dest.writeString(bcg);
        dest.writeString(measles);
        dest.writeString(opv);
        dest.writeString(ttt);
        dest.writeString(hepatitis);
        dest.writeString(other);

        dest.writeString(bloodglucose);
        dest.writeString(heamogram);
        dest.writeString(creatine);
        dest.writeString(urea);
        dest.writeString(sgot);
        dest.writeString(sgpt);
        dest.writeString(adviced);
        dest.writeString(ferered);
        dest.writeString(remark);

        dest.writeString(drname1);
        dest.writeString(drname2);
        dest.writeString(drname3);
        dest.writeString(hospitalname);
    }
}

