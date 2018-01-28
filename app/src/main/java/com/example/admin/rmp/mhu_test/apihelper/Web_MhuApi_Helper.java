package com.example.admin.rmp.mhu_test.apihelper;

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
import com.example.admin.rmp.mhu_test.model.MHU_Test;
import com.example.admin.rmp.pref_manager.PrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class Web_MhuApi_Helper
{
    public static void webAddMHUTest(final Activity activity, final MHU_Test mhuTest, final ApiResponseListener apiResponseListener)
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

                params.put("patient_id", MainActivity.PATIENT_ID);
                params.put("registration_no",MainActivity.REGISTRATION_ID);
                params.put("bloodglucose",mhuTest.getBloodGlucose());
                params.put("heamogram",mhuTest.getHemogram());
                params.put("creatine",mhuTest.getCreatine());
                params.put("urea",mhuTest.getUrea());
                params.put("sgot",mhuTest.getSgot());
                params.put("sgpt",mhuTest.getSgpt());
                params.put("adviced",mhuTest.getAdvised());
                params.put("ferered",mhuTest.getAdvised());
                params.put("remark",mhuTest.getAdvised());

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
