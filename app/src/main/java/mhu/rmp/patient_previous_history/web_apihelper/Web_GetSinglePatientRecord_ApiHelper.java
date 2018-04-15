package mhu.rmp.patient_previous_history.web_apihelper;

import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import mhu.rmp.patient_previous_history.model.PreviousRecords;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.app.MyApplication;
import mhu.rmp.constants.WebServiceUrls;
import mhu.rmp.medical_condition.model.Dose;
import mhu.rmp.mhu_test.model.MHU_Test;
import mhu.rmp.mhu_test.model.SubTest;


public class Web_GetSinglePatientRecord_ApiHelper {

    public static void webGetSinglePatientRecord(final Activity activity, final PreviousRecords previousRecords, final ApiResponseListener apiResponseListener, final String visit_id) {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlGetSinglePatientRecord, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success")) {
                        if (responce.getString("message").equalsIgnoreCase("success")) {

                            try {
                                JSONArray result = responce.getJSONArray("result");
                                if (result.length() > 0) {
                                    for (int i = 0; i < result.length(); i++) {
                                        JSONObject jsonObjectResult = result.getJSONObject(i);
                                        try {
                                            previousRecords.setId(jsonObjectResult.getString("id"));
                                            previousRecords.setRegistration_no(jsonObjectResult.getString("registration_no"));
                                            previousRecords.setFname(jsonObjectResult.getString("fname"));
                                            previousRecords.setLanme(jsonObjectResult.getString("lanme"));
                                            previousRecords.setDob(jsonObjectResult.getString("dob"));
                                            previousRecords.setGender(jsonObjectResult.getString("gender"));
                                            previousRecords.setMobile(jsonObjectResult.getString("mobile"));
                                            previousRecords.setRegitrationdate(jsonObjectResult.getString("regitrationdate"));
                                            previousRecords.setAddress(jsonObjectResult.getString("address"));
                                            previousRecords.setState(jsonObjectResult.getString("state"));
                                            previousRecords.setDistrict(jsonObjectResult.getString("district"));
                                            previousRecords.setCity(jsonObjectResult.getString("city"));
                                            previousRecords.setArea(jsonObjectResult.getString("area"));
                                            previousRecords.setLocation(jsonObjectResult.getString("location"));
                                            previousRecords.setUnique_id(jsonObjectResult.getString("unique_id"));
                                            previousRecords.setPatient_category(jsonObjectResult.getString("patient_category"));
                                            previousRecords.setVisit_master_id(jsonObjectResult.getString("visit_master_id"));
                                        } catch (Exception e) {
                                        }

                                        //"vital":{"id":"5","pid":"5","registrationno":"rpm-5ac9f2027b089",
                                        // "height":"15","weight":"12","bloodpresure":"75",
                                        // "tempreture":"5","respiration":"46","updatedate":"2018-04-08 03:42:19",
                                        // "bpto":"57","visit_master_id":"5"},
                                        try {
                                            JSONObject jsonObjectVital = jsonObjectResult.getJSONObject("vital");

                                            previousRecords.setPid(jsonObjectVital.getString("pid"));
                                            previousRecords.setRegistrationno(jsonObjectVital.getString("registrationno"));
                                            previousRecords.setHeight(jsonObjectVital.getString("height"));
                                            previousRecords.setWeight(jsonObjectVital.getString("weight"));
                                            previousRecords.setBloodpresure(jsonObjectVital.getString("bloodpresure"));
                                            previousRecords.setTempreture(jsonObjectVital.getString("tempreture"));
                                            previousRecords.setRespiration(jsonObjectVital.getString("respiration"));
                                            previousRecords.setUpdatedate(jsonObjectVital.getString("updatedate"));
                                            previousRecords.setBpto(jsonObjectVital.getString("bpto"));
                                            previousRecords.setVisit_master_id(jsonObjectVital.getString("visit_master_id"));
                                        } catch (Exception e) {
                                        }
                                        try {
                                            //"medical":{"id":"4","chiefcomplaints1":"svs",
                                            // "chiefcomplaints2":"sb","chiefcomplaints3":"sb",
                                            // "briefHistory1":"ag","briefHistory2":"bs","briefHistory3":"sb",
                                            // "investigation":"Y","tratementtaken":"Y","anyimprovement":"",
                                            // "diagnosys":"sgssbsh","patient_id":"5","registrationid":"rpm-5ac9f2027b089",
                                            // "visit_master_id":"5",

                                            JSONObject jsonObjectMedical = jsonObjectResult.getJSONObject("medical");

                                            previousRecords.setId(jsonObjectMedical.getString("id"));
                                            previousRecords.setChiefcomplaints1(jsonObjectMedical.getString("chiefcomplaints1"));
                                            previousRecords.setChiefcomplaints2(jsonObjectMedical.getString("chiefcomplaints2"));
                                            previousRecords.setChiefcomplaints3(jsonObjectMedical.getString("chiefcomplaints3"));
                                            previousRecords.setBriefHistory1(jsonObjectMedical.getString("briefHistory1"));
                                            previousRecords.setBriefHistory2(jsonObjectMedical.getString("briefHistory2"));
                                            previousRecords.setBriefHistory3(jsonObjectMedical.getString("briefHistory3"));
                                            previousRecords.setInvestigation(jsonObjectMedical.getString("investigation"));
                                            previousRecords.setTratementtaken(jsonObjectMedical.getString("tratementtaken"));
                                            previousRecords.setAnyimprovement(jsonObjectMedical.getString("anyimprovement"));
                                            previousRecords.setDiagnosys(jsonObjectMedical.getString("diagnosys"));
                                            previousRecords.setPatient_id(jsonObjectMedical.getString("patient_id"));
                                            previousRecords.setRegistrationid(jsonObjectMedical.getString("registrationid"));
                                            previousRecords.setVisit_master_id(jsonObjectMedical.getString("visit_master_id"));


                                            //"prescribe_dose":{"id":"3","name":"Medicine Name: \nOMECAP CAP(B15)",
                                            // "frequency":"2","days":"30","medicalconditionid":"4"}},


                                            try {
                                                ArrayList<Dose> doseArrayList = new ArrayList<>();

                                                JSONArray jsonArrayPrescribeDose = jsonObjectMedical.getJSONArray("prescribe_dose");

                                                for (int k = 0; k < jsonArrayPrescribeDose.length(); k++) {
                                                    JSONObject jsonobj = jsonArrayPrescribeDose.getJSONObject(k);
                                                    Dose dose = new Dose();

                                                    dose.setDoseName(jsonobj.getString("name"));
                                                    dose.setDoseFrequency(jsonobj.getString("frequency"));
                                                    dose.setDoseNoOfDays(jsonobj.getString("days"));
                                                    doseArrayList.add(dose);
                                                }

                                                previousRecords.setDoseArrayList(doseArrayList);
                                            } catch (Exception ex) {

                                            }
                                        } catch (Exception e) {
                                        }

                                        //"vaccination":{"id":"4","dpt":"Y","bcg":"Y","measles":"",
                                        // "opv":"","ttt":"","hepatitis":"","other":"zb","patient_id":"5",
                                        // "registration_no":"rpm-5ac9f2027b089","visit_master_id":"5"}
                                        try {
                                            JSONObject jsonObjectVaccination = jsonObjectResult.getJSONObject("vaccination");

                                            previousRecords.setId(jsonObjectVaccination.getString("id"));
                                            previousRecords.setDpt(jsonObjectVaccination.getString("dpt"));
                                            previousRecords.setBcg(jsonObjectVaccination.getString("bcg"));
                                            previousRecords.setMeasles(jsonObjectVaccination.getString("measles"));
                                            previousRecords.setOpv(jsonObjectVaccination.getString("opv"));
                                            previousRecords.setTtt(jsonObjectVaccination.getString("ttt"));
                                            previousRecords.setHepatitis(jsonObjectVaccination.getString("hepatitis"));
                                            previousRecords.setOther(jsonObjectVaccination.getString("other"));
                                            previousRecords.setPatient_id(jsonObjectVaccination.getString("patient_id"));
                                            previousRecords.setRegistration_no(jsonObjectVaccination.getString("registration_no"));
                                            previousRecords.setVisit_master_id(jsonObjectVaccination.getString("visit_master_id"));
                                        } catch (Exception e) {
                                        }

                                        //"mhutestmaster":{"id":"1","bloodglucose":null,
                                        // "heamogram":null,"creatine":null,"urea":null,
                                        // "sgot":null,"sgpt":null,"adviced":"dhdh",
                                        // "ferered":"fhdh","remark":"dhdh",
                                        // "visit_master_id":"5"},
                                        try {
                                            JSONObject jsonObjectMhuTestMaster = jsonObjectResult.getJSONObject("mhutestmaster");

                                            previousRecords.setId(jsonObjectMhuTestMaster.getString("id"));
                                            previousRecords.setBloodglucose(jsonObjectMhuTestMaster.getString("bloodglucose"));
                                            previousRecords.setHeamogram(jsonObjectMhuTestMaster.getString("heamogram"));
                                            previousRecords.setCreatine(jsonObjectMhuTestMaster.getString("creatine"));
                                            previousRecords.setUrea(jsonObjectMhuTestMaster.getString("urea"));
                                            previousRecords.setSgot(jsonObjectMhuTestMaster.getString("sgot"));
                                            previousRecords.setSgpt(jsonObjectMhuTestMaster.getString("sgpt"));
                                            previousRecords.setAdviced(jsonObjectMhuTestMaster.getString("adviced"));
                                            previousRecords.setFerered(jsonObjectMhuTestMaster.getString("ferered"));
                                            previousRecords.setRemark(jsonObjectMhuTestMaster.getString("remark"));
                                            previousRecords.setVisit_master_id(jsonObjectMhuTestMaster.getString("visit_master_id"));
                                        } catch (Exception e) {
                                        }

                                        //"patient_history":{"id":"2","patient_id":"6",
                                        // "registrationno":"66767","drname1":"sharma",
                                        // "drname2":null,"drname3":null,"hospitalname":
                                        // "hegewar","visit_master_id":"5"},
                                        try {
                                            JSONObject jsonObjectPatientHistory = jsonObjectResult.getJSONObject("patient_history");

                                            previousRecords.setId(jsonObjectPatientHistory.getString("id"));
                                            previousRecords.setPatient_id(jsonObjectPatientHistory.getString("patient_id"));
                                            previousRecords.setRegistrationno(jsonObjectPatientHistory.getString("registrationno"));
                                            previousRecords.setDrname1(jsonObjectPatientHistory.getString("drname1"));
                                            previousRecords.setDrname2(jsonObjectPatientHistory.getString("drname2"));
                                            previousRecords.setDrname3(jsonObjectPatientHistory.getString("drname3"));
                                            previousRecords.setHospitalname(jsonObjectPatientHistory.getString("hospitalname"));
                                            previousRecords.setVisit_master_id(jsonObjectPatientHistory.getString("visit_master_id"));
                                        } catch (Exception e) {
                                        }

                                        // /"test":
                                        // {"Complete Blood Count (CBC)\/ Homogram (Hgm)"
                                        // :["Hb  =  - ","TLC =  - ","DLC =  - ","PCV =  - ","RBC count  =  - ",
                                        // "Platelet count =  - ","MCV\/MCH\/MCHC =  - ","ESR =  - ","AEC =  - ",
                                        // "CBB\/ Hgm = 98 - 88"],"Liver Function Test (LFT)":["D Bil =  - ","ID Bil =  - ",
                                        // "T - Bil =  - ","T-Prot =  - ","A\/G Rat =  - ","ALT =  - ","AST =  - ",
                                        // "ALP =  - "," Comp. LFT =  - ","GGT =  - ","Alb =  - "],
                                        // "Kidney Function Test":["Urea =  - ","Creat. =  - ","Uric Acid =  - ",
                                        // "Electrolyte (Na"," K. Cl) =  - ","T Prot =  - ","A1b =  - ","A1b\/Cr Ratio =  - ",
                                        // "Micro Albumin =  - ","Comp.KFT =  - "],"Lipid Profile":["Cholesterol =  - ","TAG =  - ",
                                        // "HDL =  - "],"Glucose Profile (BSR)":["FBG =  - ","2 Hr =  - ","Random =  - ",
                                        // "GTT (0","1","2","3 Hrs) =  - ","HbA1c =  - ","Urinary Micro Albumin =  - ","Insulin F\/PP =  - "],
                                        // "WIDAL":["WIDAL =  - "],"TYPHIDOT":["TYPHIDOT =  - "],
                                        // "Malaria Serology":["Malaria Serology =  - "],"RA Factor":["RA Factor =  - "],
                                        // "HBSAG (Hepatitis B AG)":["HBSAG (Hepatitis B AG) =  - "],
                                        // "Urine Examination":["Routine & Microsocopic =  - ","Protein =  - ","Sugar =  - ",
                                        // "Ketone Bodies =  - ","Bile salt\/ bile Pigments =  - ","Urine Pregnancy Test =  - "],
                                        // "CRP":["CRP =  - "],"Anti HCV":["Anti HCV =  - "],"HIV":["HIV =  - "],
                                        // "VDRL":["VDRL =  - "],"GCT":["GCT =  - "],
                                        // "ABO RH (Blood Group)":["ABO RH (Blood Group) =  - "]}}],"message":"success"}

                                        try {
                                            JSONObject jsonTest = jsonObjectResult.getJSONObject("test");
                                            Iterator iterator = jsonTest.keys();
                                            ArrayList<MHU_Test> testlist = new ArrayList<>();
                                            while (iterator.hasNext()) {
                                                MHU_Test tests = new MHU_Test();
                                                tests.setTestName((String) iterator.next());
                                                JSONArray jsonsubtests = jsonTest.getJSONArray(tests.getTestName());
                                                ArrayList<SubTest> subTests = new ArrayList<>();
                                                for (int j = 0; j < jsonsubtests.length(); j++) {
                                                    try {
                                                        String srtsubt = (String) jsonsubtests.get(j);
                                                        SubTest subtest = new SubTest();
                                                        String[] ghshh = srtsubt.split("=");
                                                        subtest.setName(ghshh[0]);
                                                        subtest.setReding(ghshh[1].split("-")[0]);
                                                        subtest.setRefrance(ghshh[1].split("-")[1]);
                                                        subTests.add(subtest);
                                                    } catch (Exception e) {

                                                    }

                                                }
                                                tests.setSubtests(subTests);


                                                testlist.add(tests);
                                            }
                                            previousRecords.setTestList(testlist);


                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }
                                        apiResponseListener.onSuccess(responce.getString("message"));
                                    }



                                }

                            }
                            catch(Exception e)
                            {
                                apiResponseListener.onError("Exception");

                            }

                        } else {
                            apiResponseListener.onError(responce.getString("message"));
                        }
                    } else {
                        apiResponseListener.onError(responce.getString("message"));
                    }
                } catch (JSONException e) {
                    apiResponseListener.onError("Exception");
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    apiResponseListener.onError("Check internet connection");
                } else if (error instanceof ServerError) {
                    apiResponseListener.onError("Server Error");
                } else if (error instanceof NetworkError) {
                    apiResponseListener.onError("Check internet connection");
                } else if (error instanceof ParseError) {
                    apiResponseListener.onError("Parse Error");
                } else {
                    apiResponseListener.onError("Unknown Error");
                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();


                //http://mobilehealthunit.in/api/V1_1/getSinglePatientRecord?
                // visit_master_id=5&format=json

                try {

                    params.put("visit_master_id", visit_id);
                } catch (Exception e) {

                }
                try {

                    params.put("format", "json");
                } catch (Exception e) {

                }

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

}