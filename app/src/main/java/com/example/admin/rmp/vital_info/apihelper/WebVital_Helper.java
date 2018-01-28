package com.example.admin.rmp.vital_info.apihelper;

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
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.vital_info.model.Vital_Info;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 26-Jan-18.
 */

public class WebVital_Helper
{
    public static void webAddVitalInfo(final Activity activity, final Vital_Info vitalInfo, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddVitalInfo,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Vital added successfully")) {

                            // {"status":"success","count":1,"type":"addVital",
                            // "result":{"id":"3","pid":"1","registrationno":"rpm-5a6b03d57b791",
                            // "height":"23","weight":"23","bloodpresure":"2","tempreture":"2","respiration":"9"},
                            // "message":"Vital added successfully"}

                            //JSONObject jsonObject = responce.getJSONObject("result");

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

                //http://192.168.1.101/RMP/index.php/api/V1/addVital?
                // mobile=9975294782&password=user@123&&format=json&
                // pid=1&registrationno=rpm-5a6b03d57b791&height=23&
                // weight=23&bloodpresure=2&tempreture=2&respiration=9

                params.put("pid", MainActivity.PATIENT_ID);
                params.put("registrationno",MainActivity.REGISTRATION_ID);
                params.put("height",vitalInfo.getHeight());
                params.put("weight",vitalInfo.getWeight());
                params.put("bloodpresure",vitalInfo.getBloodPressure());
                params.put("tempreture",vitalInfo.getTemperature());
                params.put("respiration",vitalInfo.getRespiration());
                params.put("bpto",vitalInfo.getBloodPressureTo());
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
