package com.example.admin.rmp.previous_records.apihelper;

import android.app.Activity;
import android.widget.Toast;

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
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.previous_records.model.PatientHistory;
import com.example.admin.rmp.vaccination_record.model.Vaccination;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class Web_PatientHistory_Helper
{
    public static void webAddPatienHistory(final Activity activity, final PatientHistory patientHistory)
            /* final ApiResponseListener apiResponseListener*/
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddPatientHistory,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("PatientHistory added successfully")) {


                            //Toast.makeText(activity,"PatientHistory added successfully",Toast.LENGTH_SHORT).show();
                            //apiResponseListener.onSuccess(responce.getString("message"));
                        }
                        else
                        {
                            Toast.makeText(activity,"PatientHistory add failed",Toast.LENGTH_SHORT).show();
                            //apiResponseListener.onError(responce.getString("message"));
                        }
                    }
                    else
                    {
                        Toast.makeText(activity,"PatientHistory add failed",Toast.LENGTH_SHORT).show();
                        //apiResponseListener.onError(responce.getString("message"));
                    }
                }
                catch (JSONException e)
                {
                    Toast.makeText(activity,"Exception",Toast.LENGTH_SHORT).show();
                    //apiResponseListener.onError("Exception");
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //apiResponseListener.onError("Check internet connection");
                    Toast.makeText(activity,"Check internet connection",Toast.LENGTH_SHORT).show();

                }
                else if (error instanceof ServerError)
                {
                    Toast.makeText(activity,"Server Error",Toast.LENGTH_SHORT).show();
                    //apiResponseListener.onError("Server Error");
                }
                else if (error instanceof NetworkError)
                {
                    Toast.makeText(activity,"Check internet connection",Toast.LENGTH_SHORT).show();
                    //apiResponseListener.onError("Check internet connection");
                }
                else if (error instanceof ParseError)
                {
                    Toast.makeText(activity,"Parse Error",Toast.LENGTH_SHORT).show();
                    //apiResponseListener.onError("Parse Error");
                }
                else
                {
                    Toast.makeText(activity,"Unknown Error",Toast.LENGTH_SHORT).show();
                    //apiResponseListener.onError("Unknown Error");
                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                params.put("patient_id", MainActivity.PATIENT_ID);
                params.put("registrationno",MainActivity.REGISTRATION_ID);
                params.put("drname1",patientHistory.getDoctorName1());
                params.put("drname2",patientHistory.getDoctorName2());
                params.put("drname3",patientHistory.getDoctorName3());
                params.put("hospitalname",patientHistory.getPrevHospital());
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
