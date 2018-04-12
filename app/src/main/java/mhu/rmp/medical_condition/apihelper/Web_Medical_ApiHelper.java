package mhu.rmp.medical_condition.apihelper;

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
import mhu.rmp.activity.MainActivity;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.app.MyApplication;
import mhu.rmp.constants.WebServiceUrls;
import mhu.rmp.medical_condition.model.Dose;
import mhu.rmp.medical_condition.model.Medical_Conditions;
import mhu.rmp.pref_manager.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class Web_Medical_ApiHelper
{
    public static void webAddMedicalConditions(final ArrayList<Dose> doseArrayList, final Activity activity, final Medical_Conditions medicalConditions, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddMedicalInfo,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Medicalcondition added successfully")) {


                            JSONObject result=responce.getJSONObject("result");
                            String medicalconditionid=result.getString("id");

                            if(doseArrayList!=null) {
                                for (Dose dose : doseArrayList) {
                                    addPriscribeDose(activity, dose, medicalconditionid);
                                }
                            }
                            // {"status":"success","count":1,"type":"addMedicalcondition",
                            // "result":{"id":"3","chiefcomplaints1":"ghashd","chiefcomplaints2":"bgasdhg",
                            // "chiefcomplaints3":"gsh","briefHistory1":"sghgs","briefHistory2":"sghjsgxd",
                            // "briefHistory3":"svxdhs","investigation":"Y","tratementtaken":"N",
                            // "anyimprovement":"","diagnosys":null,"patient_id":"6","registrationid":"rpm-5a6b03d57b791"},
                            // "message":"Medicalcondition added successfully"}

                            //JSONObject jsonObject = responce.getJSONObject("result");

                            //{"status":"success","count":0,"type":"addPriscribeDose","result":[],"message":"PriscribeDose added successfully"}
                            //http://localhost/RMP/index.php/api/V1/addPriscribeDose?mobile=9975294782&password=user@123&&format=json&patient_id=6&registrationno=66767&name=hdhdh&frequency=54&days=5&medicalconditionid=6


                            apiResponseListener.onSuccess(responce.getString("message"));
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

                //http://192.168.1.103/RMP/index.php/api/V1/addMedicalcondition?
                // mobile=9975294782&password=user@123&&format=json&chiefcomplaints1=ghashd&
                // chiefcomplaints2=bgasdhg&chiefcomplaints3=gsh&briefHistory1=sghgs&
                // briefHistory2=sghjsgxd&briefHistory3=svxdhs&investigation=Y&tratementtaken=N&
                // anyimprovement=ND&patient_id=6&registrationid=rpm-5a6b03d57b791

                params.put("chiefcomplaints1",medicalConditions.getChiefcomplaints1());
                params.put("chiefcomplaints2",medicalConditions.getChiefcomplaints2());
                params.put("chiefcomplaints3",medicalConditions.getChiefcomplaints3());
                params.put("briefHistory1",medicalConditions.getBriefHistory1());
                params.put("briefHistory2",medicalConditions.getBriefHistory2());
                params.put("briefHistory3",medicalConditions.getBriefHistory3());
                params.put("investigation",medicalConditions.getInvestigation());
                params.put("tratementtaken",medicalConditions.getTratementtaken());
                params.put("anyimprovement",medicalConditions.getAnyimprovement());
                params.put("diagnosys",medicalConditions.getDiagnosys());
                params.put("patient_id",MainActivity.PATIENT_ID);
                params.put("registrationid", MainActivity.REGISTRATION_ID);

                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());
                params.put("format","json");
                params.put("visit_master_id",MainActivity.Visit_ID);
                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }


    public static void addPriscribeDose(final Activity activity, final Dose dose, final String medicalconditionid)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.addPriscribeDose,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("PriscribeDose added successfully")) {

                            // {"status":"success","count":1,"type":"addMedicalcondition",
                            // "result":{"id":"3","chiefcomplaints1":"ghashd","chiefcomplaints2":"bgasdhg",
                            // "chiefcomplaints3":"gsh","briefHistory1":"sghgs","briefHistory2":"sghjsgxd",
                            // "briefHistory3":"svxdhs","investigation":"Y","tratementtaken":"N",
                            // "anyimprovement":"","diagnosys":null,"patient_id":"6","registrationid":"rpm-5a6b03d57b791"},
                            // "message":"Medicalcondition added successfully"}

                            //JSONObject jsonObject = responce.getJSONObject("result");

                            //{"status":"success","count":0,"type":"addPriscribeDose","result":[],"message":"PriscribeDose added successfully"}
                            //http://localhost/RMP/index.php/api/V1/addPriscribeDose?mobile=9975294782&password=user@123&&format=json&patient_id=6&registrationno=66767&name=hdhdh&frequency=54&days=5&medicalconditionid=6

                        }
                        else
                        {

                        }
                    }
                    else
                    {
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                }
                else if (error instanceof ServerError)
                {
                }
                else if (error instanceof NetworkError)
                {
                }
                else if (error instanceof ParseError)
                {
                }
                else
                {
                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                //http://192.168.1.103/RMP/index.php/api/V1/addMedicalcondition?
                // mobile=9975294782&password=user@123&&format=json&chiefcomplaints1=ghashd&
                // chiefcomplaints2=bgasdhg&chiefcomplaints3=gsh&briefHistory1=sghgs&
                // briefHistory2=sghjsgxd&briefHistory3=svxdhs&investigation=Y&tratementtaken=N&
                // anyimprovement=ND&patient_id=6&registrationid=rpm-5a6b03d57b791

                params.put("name",dose.getDoseName());
                params.put("frequency",dose.getDoseFrequency());
                params.put("days",dose.getDoseNoOfDays());
                params.put("medicalconditionid",medicalconditionid);

                params.put("patient_id",MainActivity.PATIENT_ID);
                params.put("registrationno", MainActivity.REGISTRATION_ID);

                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());
                params.put("format","json");
                params.put("visit_master_id",MainActivity.Visit_ID);
                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

}
