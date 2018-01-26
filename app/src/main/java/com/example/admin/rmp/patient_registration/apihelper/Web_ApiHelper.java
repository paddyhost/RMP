package com.example.admin.rmp.patient_registration.apihelper;

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
import com.example.admin.rmp.activity.MainActivity;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.app.MyApplication;
import com.example.admin.rmp.constants.WebServiceUrls;
import com.example.admin.rmp.patient_registration.model.PatientRegistration;
import com.example.admin.rmp.pref_manager.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 26-Jan-18.
 */

public class Web_ApiHelper
{
    public static void webPatientRegistration(final Activity activity,final PatientRegistration patientRegistration, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddPatient,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Patient added successfully")) {

                           // {"status":"success","count":1,"type":"getPatient",
                            // "result":{"id":"7","registration_no":"rpm-5a6b06b616922","fname":"jhgadh","lanme":"sxdsxx",
                            // "dob":"0000-00-00","gender":"F","mobile":"78676677","regitrationdate":"2018-01-26 16:15:10",
                            // "address":"ghsxgda"},"message":"Patient added successfully"}

                            JSONObject jsonObject = responce.getJSONObject("result");
                            patientRegistration.setId(jsonObject.getString("id"));
                            patientRegistration.setRegistrationNo(jsonObject.getString("registration_no"));
                            MainActivity.PATIENT_ID =jsonObject.getString("id");
                            MainActivity.REGISTRATION_ID = jsonObject.getString("registration_no");

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

                //http://192.168.1.101/RMP/index.php/api/V1/addPatient?mobile=9975294782&password=user@123
                // &registration_no=jhsjhgj&
                // fname=jhgadh&lanme=sxdsxx&dob=29-09-2017&gender=F
                // &pmobile=78676677&address=ghsxgda&format=json

                params.put("fname",patientRegistration.getfName());
                params.put("lanme",patientRegistration.getLname());
                params.put("gender",patientRegistration.getGender());
                params.put("address",patientRegistration.getAddress());
                params.put("pmobile",patientRegistration.getMobileNo());
                params.put("dob",patientRegistration.getDob());
                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());

                params.put("format","json");
                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }



}
