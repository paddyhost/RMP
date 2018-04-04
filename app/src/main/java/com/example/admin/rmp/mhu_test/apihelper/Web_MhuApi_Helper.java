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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;


public class Web_MhuApi_Helper
{
/*
    public static void webGetTestMasterAttribute(final Activity activity, final ArrayList<MHU_Test> mhuTestArrayList, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.ulGetTestMasterAttribute,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    String message=responce.getString("message");

                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("MHUTest added successfully")) {

                            JSONArray result=responce.getJSONArray("result");

                            if(result.length()>0) {

                                for (int i = 0; i < result.length(); i++) {

                                    MHU_Test mhu_test = new MHU_Test();


                                    JSONObject jsonObjectResult = result.getJSONObject(i);

                                    mhu_test.setId(jsonObjectResult.getString("id"));
                                    mhu_test.setTest_name(jsonObjectResult.getString("test_name"));

                                    JSONArray testArray = jsonObjectResult.getJSONArray("attributes");
                                    ArrayList<MHU_Test> testArrayList =new ArrayList<>();

                                    for (int j = 0; j < testArray.length(); j++) {

                                        JSONObject jsonObjectTeam=testArray.getJSONObject(j);

                                        mhu_test.setAttribute_id(jsonObjectTeam.getString("id"));
                                        mhu_test.setAttribute_name(jsonObjectTeam.getString("attribute_name"));

                                        testArrayList.add(mhu_test);
                                    }

                                    mhuTestArrayList.add(mhu_test);
                                }
                            }
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

                params.put("all","Y");
                params.put("format","json");

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }
*/

    public static void webAddTestAttributeValues(final Activity activity, final ApiResponseListener apiResponseListener, final JSONArray testobj)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddTestAttributeValues,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    String message=responce.getString("message");

                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("VALUES_ADDED")) {

                            JSONArray result=responce.getJSONArray("result");

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

                params.put("patient_id",MainActivity.PATIENT_ID);
                params.put("registeration_id",MainActivity.REGISTRATION_ID);
                params.put("visit_master_id",MainActivity.Visit_ID);

                params.put("format","json");
                params.put("testobj",testobj+"");



                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

}
