package com.example.admin.rmp.TestAdviced.apihelper;

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
import com.example.admin.rmp.TestAdviced.model.TestAdvicedModel;
import com.example.admin.rmp.activity.MainActivity;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.app.MyApplication;
import com.example.admin.rmp.constants.WebServiceUrls;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.previous_records.model.PatientHistory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;


public class Web_TestAdviced_Helper
{
    public static void webAddMHuTest(final Activity activity, final TestAdvicedModel testAdvicedModel, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddMHuTest,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("MHUTest added successfully")) {

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

               // http://mobilehealthunit.in/api/V1/addMHUTest?
                // mobile=9975294782&password=user@123&&format=json&patient_id=6
                // &registrationno=66767&bloodglucose=12&heamogram=88
                // &creatine=888&urea=888&sgot=77&sgpt=7767&adviced=7878&ferered=878&remark=887
                params.put("patient_id", MainActivity.PATIENT_ID);
                params.put("registrationno",MainActivity.REGISTRATION_ID);
                params.put("bloodglucose",testAdvicedModel.getBloodglucose());
                params.put("heamogram",testAdvicedModel.getHeamogram());
                params.put("creatine",testAdvicedModel.getCreatine());
                params.put("urea",testAdvicedModel.getUrea());
                params.put("sgot",testAdvicedModel.getSgot());
                params.put("sgpt",testAdvicedModel.getSgpt());
                params.put("adviced",testAdvicedModel.getTestName());
                params.put("ferered",testAdvicedModel.getRerered());
                params.put("remark",testAdvicedModel.getRemark());
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
