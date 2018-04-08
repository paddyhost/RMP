package mhu.rmp.mhu_test.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class MHU_Test  implements Parcelable
{
    private String id,test_name,attribute_id,attribute_name,map_test_attribute_id,reference_value,reading;
    private ArrayList<MHU_Test> testArrayList;

    public MHU_Test() {
    }


    protected MHU_Test(Parcel in) {
        id = in.readString();
        test_name = in.readString();
        attribute_id = in.readString();
        attribute_name = in.readString();
        map_test_attribute_id=in.readString();
        reference_value=in.readString();
        reading=in.readString();
        testArrayList = in.createTypedArrayList(MHU_Test.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(test_name);
        dest.writeString(attribute_id);
        dest.writeString(attribute_name);
        dest.writeString(map_test_attribute_id);
        dest.writeString(reference_value);
        dest.writeString(reading);
        dest.writeTypedList(testArrayList);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(String attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getAttribute_name() {
        return attribute_name;
    }

    public void setAttribute_name(String attribute_name) {
        this.attribute_name = attribute_name;
    }

    public ArrayList<MHU_Test> getTestArrayList() {
        return testArrayList;
    }

    public void setTestArrayList(ArrayList<MHU_Test> testArrayList) {
        this.testArrayList = testArrayList;
    }

    public static Creator<MHU_Test> getCREATOR() {
        return CREATOR;
    }

    public String getMap_test_attribute_id() {
        return map_test_attribute_id;
    }

    public void setMap_test_attribute_id(String map_test_attribute_id) {
        this.map_test_attribute_id = map_test_attribute_id;
    }

    public String getReference_value() {
        return reference_value;
    }

    public void setReference_value(String reference_value) {
        this.reference_value = reference_value;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }
}
