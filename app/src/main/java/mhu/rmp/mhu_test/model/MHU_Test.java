package mhu.rmp.mhu_test.model;

import java.util.ArrayList;


public class MHU_Test
{
    public static final int TEST_TYPE = 0;
    public static final int SUBTEST_TYPE = 1;

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
