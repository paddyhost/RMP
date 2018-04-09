package mhu.rmp.activity.previous_record_apihelper;

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

import java.util.Hashtable;
import java.util.Map;

import mhu.rmp.activity.model.PreviousRecords;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.app.MyApplication;
import mhu.rmp.constants.WebServiceUrls;


/**
 * Created by Nikam on 08/04/2018.
 */

public class Web_GetSinglePatientRecord_ApiHelper {

    public static void webGetSinglePatientRecord(final Activity activity,final PreviousRecords previousRecords, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlGetSinglePatientRecord,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("success")) {


                            JSONArray result = responce.getJSONArray("result");

                            if(result.length()>0) {
                                for (int i = 0; i < result.length(); i++) {



                           /* {"status":"success","count":1,"type":"getSinglePatientRecord",
                           "result":[{"id":"1","registration_no":"rpm-5ac9e1a7a5e8b",
                           "fname":"Prasad ","lanme":"Gote ","dob":"1989-04-08",
                           "gender":"M","mobile":"8111111111","regitrationdate":"2018-04-08 02:32:23",
                           "address":"Aurangabad","state":"Uttar Pradesh","district":"Ghaziabad",
                           "city":"Ghaziabad","area":"Khora","location":"Near Nagar Palika office",
                           "unique_id":"123456789011","patient_category":"O","visit_master_id":"5",
                           "created_at":"2018-04-08 03:42:10"*/


                                    JSONObject jsonObjectResult = result.getJSONObject(i);

                                    previousRecords.setId(jsonObjectResult.getString("id"));
                                    previousRecords.setRegistration_no(jsonObjectResult.getString("registration_no"));
                                    previousRecords.setFname(jsonObjectResult.getString("fname"));
                                    previousRecords.setLanme(jsonObjectResult.getString("lanme"));
                                    previousRecords.setDob(jsonObjectResult.getString("dob"));
                                    previousRecords.setGender(jsonObjectResult.getString("gender"));
                                    previousRecords.setMobile(jsonObjectResult.getString("mobile"));
                                    previousRecords.setAddress(jsonObjectResult.getString("address"));
                                    previousRecords.setDistrict(jsonObjectResult.getString("district"));
                                    previousRecords.setCity(jsonObjectResult.getString("city"));
                                    previousRecords.setArea(jsonObjectResult.getString("area"));
                                    previousRecords.setLocation(jsonObjectResult.getString("location"));
                                    previousRecords.setUnique_id(jsonObjectResult.getString("unique_id"));
                                    previousRecords.setPatient_category(jsonObjectResult.getString("patient_category"));
                                    previousRecords.setVisit_master_id(jsonObjectResult.getString("visit_master_id"));
                                    previousRecords.setCreated_at(jsonObjectResult.getString("created_at"));


                                    //"vital":{"id":"5","pid":"5","registrationno":"rpm-5ac9f2027b089",
                                    // "height":"15","weight":"12","bloodpresure":"75",
                                    // "tempreture":"5","respiration":"46","updatedate":"2018-04-08 03:42:19",
                                    // "bpto":"57","visit_master_id":"5"},

                                    JSONObject jsonObjectVital = jsonObjectResult.getJSONObject("vital");

                                    previousRecords.setCreated_at(jsonObjectVital.getString("created_at"));
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

                                    JSONObject jsonObjectPrescribeDose = jsonObjectResult.getJSONObject("prescribe_dose");

                                    previousRecords.setId(jsonObjectPrescribeDose.getString("id"));
                                    previousRecords.setName(jsonObjectMedical.getString("name"));
                                    previousRecords.setFrequency(jsonObjectMedical.getString("frequency"));
                                    previousRecords.setDays(jsonObjectMedical.getString("days"));


                                    //"vaccination":{"id":"4","dpt":"Y","bcg":"Y","measles":"",
                                    // "opv":"","ttt":"","hepatitis":"","other":"zb","patient_id":"5",
                                    // "registration_no":"rpm-5ac9f2027b089","visit_master_id":"5"}

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


                                   //"test":
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


                                    apiResponseListener.onSuccess(responce.getString("message"));
                                }

                            }
                        }
                        else
                        {
                            apiResponseListener.onError(responce.getString("message"));
                        }
                    }
                    else
                    {
                        apiResponseListener.onError(responce.getString("message"));
                    }
                }
                catch (JSONException e)
                {
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
                }
                else if (error instanceof ServerError)
                {
                    apiResponseListener.onError("Server Error");
                }
                else if (error instanceof NetworkError)
                {
                    apiResponseListener.onError("Check internet connection");
                }
                else if (error instanceof ParseError)
                {
                    apiResponseListener.onError("Parse Error");
                }
                else
                {
                    apiResponseListener.onError("Unknown Error");
                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();


                //http://mobilehealthunit.in/api/V1_1/getSinglePatientRecord?visit_master_id=5&format=json

                params.put("visit_master_id",previousRecords.getVisit_master_id());
                params.put("format","json");

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }


}