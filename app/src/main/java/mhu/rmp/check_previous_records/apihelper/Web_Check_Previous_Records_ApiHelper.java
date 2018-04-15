package mhu.rmp.check_previous_records.apihelper;

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

import mhu.rmp.activity.MainActivity;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.app.MyApplication;
import mhu.rmp.constants.WebServiceUrls;
import mhu.rmp.patient_registration.model.PatientRegistration;

/**
 * Created by Nikam on 13/04/2018.
 */

public class Web_Check_Previous_Records_ApiHelper {


    public static void webGetVisitPatientRecord(final Activity activity, final String  patientUID, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlGetVisitPatientRecord,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("success")) {

                            try {
                                JSONArray jsonArrayResult = responce.getJSONArray("result");

                                JSONObject jsonobj = jsonArrayResult.getJSONObject(0);
                                String visitids=jsonobj.getString("visitids");

                                apiResponseListener.onSuccess(visitids);
                            }
                            catch (Exception e)
                            {
                                apiResponseListener.onError("Exception");

                            }
                            //{"status":"success","count":1,"type":"getVisitPatientRecord",
                            // "result":[{"id":"9","registration_no":"rpm-5acb0af46d35a",
                            // "fname":"kavita","lanme":"sharma","dob":"1982-04-17",
                            // "gender":"F","mobile":"9582409879",
                            // "regitrationdate":"2018-04-08 23:40:52",
                            // "address":"shiv park khora","state":"Uttar Pradesh",
                            // "district":"Ghaziabad","city":"Ghaziabad",
                            // "area":"Khora","location":"Near Kajal Printers,
                            // Vandana Vihar","unique_id":"PAT180409120836436_6",
                            // "patient_category":"","visitids":"9, ,10, ,11, "}],
                            // "message":"success"}



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


               //http://mobilehealthunit.in/api/V1_1/getVisitPatientRecord
                // ?format=json
                //&key=hh &visit_master_id=PAT180409120836436_6

                try {
                    params.put("format","json");
                }
                catch (Exception e)
                {
                }

                try {
                    params.put("uid",patientUID);
                }
                catch (Exception e)
                {
                }

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }
}
