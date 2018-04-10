package mhu.rmp.mhu_test.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class MHU_Test
{
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public ArrayList<SubTest> getSubtests() {
        return subtests;
    }

    public void setSubtests(ArrayList<SubTest> subtests) {
        this.subtests = subtests;
    }

    String testName;
    ArrayList<SubTest> subtests;




}
