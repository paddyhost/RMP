package mhu.rmp.vaccination_record.apihelper;

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
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.vaccination_record.model.Vaccination;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Nikam on 28/01/2018.
 */

public class Vaccination_ApiHelper {

    public static void webAddVaccination(final Activity activity, final Vaccination vaccination, final ApiResponseListener apiResponseListener)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlAddVaccination,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("vaccinationmaster added successfully")) {



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
                params.put("dpt",vaccination.getDpt());
                params.put("bcg",vaccination.getBcg());
                params.put("measles",vaccination.getMeasles());
                params.put("opv",vaccination.getOpv());
                params.put("ttt",vaccination.getTt());
                params.put("hepatitis",vaccination.getHepatitis());
                params.put("other",vaccination.getOther());
                params.put("mobile",new PrefManager(activity).getMobile());
                params.put("password",new PrefManager(activity).getPassword());
                params.put("visit_master_id",MainActivity.Visit_ID);
                params.put("format","json");
                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }
}
