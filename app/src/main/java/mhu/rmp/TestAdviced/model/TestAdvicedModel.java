package mhu.rmp.TestAdviced.model;

/**
 * Created by Nikam on 02/04/2018.
 */

public class TestAdvicedModel {


    private String id, bloodglucose, heamogram, creatine,urea,sgot,sgpt,test_name,ferered,remark;

    public TestAdvicedModel() {
    }

    public TestAdvicedModel(String id, String bloodglucose, String heamogram, String creatine, String urea, String sgot, String sgpt, String test_name, String ferered, String remark) {
        this.id = id;
        this.bloodglucose = bloodglucose;
        this.heamogram = heamogram;
        this.creatine = creatine;
        this.urea = urea;
        this.sgot = sgot;
        this.sgpt = sgpt;
        this.test_name = test_name;
        this.ferered = ferered;
        this.remark = remark;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBloodglucose() {
        return bloodglucose;
    }

    public void setBloodglucose(String bloodglucose) {
        this.bloodglucose = bloodglucose;
    }

    public String getHeamogram() {
        return heamogram;
    }

    public void setHeamogram(String heamogram) {
        this.heamogram = heamogram;
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

    public String getTestName() {
        return test_name;
    }

    public void setTestName(String test_name) {
        this.test_name = test_name;
    }

    public String getRerered() {
        return ferered;
    }

    public void setRerered(String ferered) {
        this.ferered = ferered;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
