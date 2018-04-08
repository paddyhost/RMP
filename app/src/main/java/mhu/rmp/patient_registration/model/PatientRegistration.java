package mhu.rmp.patient_registration.model;

import android.os.Parcel;
import android.os.Parcelable;


public class PatientRegistration implements Parcelable
{
    private String id, registrationNo, fName,Lname,gender,dateOfRegistration,address,
            mobileNo,dob,unique_id,patient_category,state,district,city,area,location,visit_id,visit_master_id;


    public PatientRegistration()
    {
    }

    public PatientRegistration(String id, String registrationNo, String fName, String lname,String age, String gender, String dateOfRegistration, String address, String mobileNo, String dob, String state, String district, String city, String area, String location, String unique_id, String patient_category,String visit_id,String visit_master_id) {
        this.id = id;
        this.registrationNo = registrationNo;
        this.fName = fName;
        Lname = lname;
        this.gender = gender;
        this.dateOfRegistration = dateOfRegistration;
        this.address = address;
        this.mobileNo = mobileNo;
        this.dob = dob;
        this.state = state;
        this.district = district;
        this.city = city;
        this.area = area;
        this.location = location;
        this.unique_id = unique_id;
        this.patient_category = patient_category;
        this.visit_id=visit_id;
        this.visit_master_id=visit_master_id;
    }

    protected PatientRegistration(Parcel in) {
        id = in.readString();
        registrationNo = in.readString();
        fName = in.readString();
        Lname = in.readString();
        gender = in.readString();
        dateOfRegistration = in.readString();
        address = in.readString();
        mobileNo = in.readString();
        dob = in.readString();
        state = in.readString();
        district = in.readString();
        city = in.readString();
        area = in.readString();
        location = in.readString();
        unique_id = in.readString();
        patient_category = in.readString();
        visit_id=in.readString();
        visit_master_id=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(registrationNo);
        dest.writeString(fName);
        dest.writeString(Lname);
        dest.writeString(gender);
        dest.writeString(dateOfRegistration);
        dest.writeString(address);
        dest.writeString(mobileNo);
        dest.writeString(dob);
        dest.writeString(unique_id);
        dest.writeString(patient_category);
        dest.writeString(state);
        dest.writeString(district);
        dest.writeString(city);
        dest.writeString(area);
        dest.writeString(location);
        dest.writeString(visit_id);
        dest.writeString(visit_master_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientRegistration> CREATOR = new Creator<PatientRegistration>() {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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

    public String getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(String visit_id) {
        this.visit_id = visit_id;
    }

    public String getVisit_master_id() {
        return visit_master_id;
    }

    public void setVisit_master_id(String visit_master_id) {
        this.visit_master_id = visit_master_id;
    }
}
