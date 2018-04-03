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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;


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


                           /* {"status":"success","count":1,"type":"getPatient",
                                    "result":{"id":"241","registration_no":"jhsjhgj",
                                    "fname":"jhgadh","lanme":"sxdsxx","dob":"0000-00-00",
                                    "gender":"F","mobile":"78676677","regitrationdate":"2018-04-02 09:46:11",
                                    "address":"ghsxgda","state":"5",
                                    "district":"12","city":"5","area":"2","location":"12",
                                    "unique_id":"45525445555544","patient_category":"PW"},
                                "message":"Patient added successfully"}*/




                            JSONObject jsonObject = responce.getJSONObject("result");
                            patientRegistration.setId(jsonObject.getString("id"));
                            patientRegistration.setRegistrationNo(jsonObject.getString("registration_no"));
                            MainActivity.PATIENT_ID =jsonObject.getString("id");
                            MainActivity.REGISTRATION_ID = jsonObject.getString("registration_no");
                            patientRegistration.setfName(jsonObject.getString("fname"));
                            patientRegistration.setLname(jsonObject.getString("lanme"));
                            patientRegistration.setDob(jsonObject.getString("dob"));
                            patientRegistration.setGender(jsonObject.getString("gender"));
                            patientRegistration.setMobileNo(jsonObject.getString("mobile"));
                            patientRegistration.setDateOfRegistration(jsonObject.getString("regitrationdate"));
                            patientRegistration.setAddress(jsonObject.getString("address"));
                            patientRegistration.setState(jsonObject.getString("state"));
                            patientRegistration.setDistrict(jsonObject.getString("district"));
                            patientRegistration.setCity(jsonObject.getString("city"));
                            patientRegistration.setArea(jsonObject.getString("area"));
                            patientRegistration.setLocation(jsonObject.getString("location"));
                            patientRegistration.setUnique_id(jsonObject.getString("unique_id"));
                            patientRegistration.setPatient_category(jsonObject.getString("patient_category"));
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


                //http://mobilehealthunit.in/api/V1_1/addPatient?
                // mobile=9975294782&password=user@123&
                // registration_no=jhsjhgj&
                // fname=jhgadh&lanme=sxdsxx&dob=29-09-2017
                // &age=36&gender=F&pmobile=78676677&address=ghsxgda&
                // format=json&state=5&district=12&city=5&
                // area=2&location=12&patient_category=PW&unique_id=45525445555544


                //params.put("id",patientRegistration.getId());
                params.put("fname",patientRegistration.getfName());
                params.put("lanme",patientRegistration.getLname());
                /*params.put("age",patientRegistration.getAge());*/
                params.put("gender",patientRegistration.getGender());
                params.put("address",patientRegistration.getAddress());
                params.put("regitrationdate",patientRegistration.getDateOfRegistration());
                params.put("dob",patientRegistration.getDob());
                params.put("unique_id",patientRegistration.getUnique_id());
                params.put("registration_no",patientRegistration.getRegistrationNo());
                params.put("patient_category",patientRegistration.getPatient_category());
                params.put("state",patientRegistration.getState());
                params.put("district",patientRegistration.getDistrict());
                params.put("city",patientRegistration.getCity());
                params.put("area",patientRegistration.getArea());
                params.put("location",patientRegistration.getLocation());
                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());
                params.put("format","json");

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

    public static void isPatientExist(final Activity activity, final PatientRegistration patientRegistration, final ApiResponseListener apiResponseListener, final String uid)
    {

        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.isPatientExist,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("PATIENT_EXISTS"))
                        {
                            JSONArray jsonArray=responce.getJSONArray("result");
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            patientRegistration.setId(jsonObject.getString("id"));
                            patientRegistration.setRegistrationNo(jsonObject.getString("registration_no"));
                            MainActivity.PATIENT_ID =jsonObject.getString("id");
                            MainActivity.REGISTRATION_ID = jsonObject.getString("registration_no");
                            patientRegistration.setfName(jsonObject.getString("fname"));
                            patientRegistration.setLname(jsonObject.getString("lanme"));
                            patientRegistration.setDob(jsonObject.getString("dob"));
                            patientRegistration.setGender(jsonObject.getString("gender"));
                            patientRegistration.setMobileNo(jsonObject.getString("mobile"));
                            patientRegistration.setDateOfRegistration(jsonObject.getString("regitrationdate"));
                            patientRegistration.setAddress(jsonObject.getString("address"));
                            patientRegistration.setState(jsonObject.getString("state"));
                            patientRegistration.setDistrict(jsonObject.getString("district"));
                            patientRegistration.setCity(jsonObject.getString("city"));
                            patientRegistration.setArea(jsonObject.getString("area"));
                            patientRegistration.setLocation(jsonObject.getString("location"));
                            patientRegistration.setUnique_id(jsonObject.getString("unique_id"));
                            patientRegistration.setPatient_category(jsonObject.getString("patient_category"));
                            apiResponseListener.onSuccess(responce.getString("message"));
                        }
                        else
                        {
                            apiResponseListener.onSuccess(responce.getString("message"));
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

             /*   {
                    "status": "success",
                        "count": 1,
                        "type": "getPatient",
                        "result": {
                            "id": "233",
                            "registration_no": "jhsjhgj",
                            "fname": "jhgadh",
                            "lanme": "sxdsxx",
                            "dob": "0000-00-00",
                            "gender": "F",
                            "mobile": "78676677",
                            "regitrationdate": "2018-04-01 23:15:08",
                            "address": "ghsxgda",
                            "state": "[state]",
                            "district": "[district]",
                            "city": "[city]",
                            "area": "[area]",
                            "location": "[location]",
                            "unique_id": null,
                            "patient_category": null
                },
                    "message": "Patient added successfully"
                }*/


                params.put("format","json");
                params.put("unique_id",uid);
                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

    public static void addVisit(final Activity activity, final PatientRegistration patientRegistration,final ApiResponseListener apiResponseListener, final String uid)
    {

        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.addPatientVisit,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if(responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("VISIT_GENERATED"))
                        {
                            JSONArray jsonArray=responce.getJSONArray("result");
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            MainActivity.Visit_ID=jsonObject.getString("visit_id");

                            patientRegistration.setId(jsonObject.getString("id"));
                            patientRegistration.setRegistrationNo(jsonObject.getString("registration_no"));
                            MainActivity.PATIENT_ID =jsonObject.getString("id");
                            MainActivity.REGISTRATION_ID = jsonObject.getString("registration_no");
                            patientRegistration.setfName(jsonObject.getString("fname"));
                            patientRegistration.setLname(jsonObject.getString("lanme"));
                            patientRegistration.setDob(jsonObject.getString("dob"));
                            patientRegistration.setGender(jsonObject.getString("gender"));
                            patientRegistration.setMobileNo(jsonObject.getString("mobile"));
                            patientRegistration.setDateOfRegistration(jsonObject.getString("regitrationdate"));
                            patientRegistration.setAddress(jsonObject.getString("address"));
                            patientRegistration.setState(jsonObject.getString("state"));
                            patientRegistration.setDistrict(jsonObject.getString("district"));
                            patientRegistration.setCity(jsonObject.getString("city"));
                            patientRegistration.setArea(jsonObject.getString("area"));
                            patientRegistration.setLocation(jsonObject.getString("location"));
                            patientRegistration.setUnique_id(jsonObject.getString("unique_id"));
                            patientRegistration.setPatient_category(jsonObject.getString("patient_category"));
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
                params.put("format","json");
                params.put("unique_id",uid);
                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());
             //   http://mobilehealthunit.in/api/v1_1/addPatientVisit?format=json&unique_id=45525445555544                //returning parameters
                return params;
            }

        };
        MyApplication.getInstance().addToRequestQueue(strReq);
    }


}
