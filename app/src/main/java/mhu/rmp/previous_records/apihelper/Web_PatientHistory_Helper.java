package mhu.rmp.previous_records.apihelper;

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
import mhu.rmp.activity.MainActivity;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.app.MyApplication;
import mhu.rmp.constants.WebServiceUrls;
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.previous_records.model.PatientHistory;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Ashwin on 28-Jan-18.
 */

public class Web_PatientHistory_Helper
{
    public static void webAddPatienHistory(final Activity activity, final PatientHistory patientHistory,final ApiResponseListener apiResponseListener)
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

                            JSONObject jsonObjectResult =responce.getJSONObject("result");
                           // {"status":"success","count":1,"type":"addPatientHistory",
                            // "result":{"id":"6","patient_id":"6","registrationno":"66767",
                            // "drname1":"hgxajg","drname2":null,"drname3":null,
                            // "hospitalname":"lll","visit_master_id":null},
                            // "message":"PatientHistory added successfully"}
                            //Toast.makeText(activity,"PatientHistory added successfully",Toast.LENGTH_SHORT).show();
                            apiResponseListener.onSuccess(responce.getString("message"));
                        }
                        else
                        {
                            //Toast.makeText(activity,"PatientHistory add failed",Toast.LENGTH_SHORT).show();
                            apiResponseListener.onError(responce.getString("message"));
                        }
                    }
                    else
                    {
                        //Toast.makeText(activity,"PatientHistory add failed",Toast.LENGTH_SHORT).show();
                        apiResponseListener.onError(responce.getString("message"));
                    }
                }
                catch (JSONException e)
                {
                    //Toast.makeText(activity,"Exception",Toast.LENGTH_SHORT).show();
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
                    //Toast.makeText(activity,"Check internet connection",Toast.LENGTH_SHORT).show();

                }
                else if (error instanceof ServerError)
                {
                    //Toast.makeText(activity,"Server Error",Toast.LENGTH_SHORT).show();
                    apiResponseListener.onError("Server Error");
                }
                else if (error instanceof NetworkError)
                {
                    //Toast.makeText(activity,"Check internet connection",Toast.LENGTH_SHORT).show();
                    apiResponseListener.onError("Check internet connection");
                }
                else if (error instanceof ParseError)
                {
                    //Toast.makeText(activity,"Parse Error",Toast.LENGTH_SHORT).show();
                    apiResponseListener.onError("Parse Error");
                }
                else
                {
                    //Toast.makeText(activity,"Unknown Error",Toast.LENGTH_SHORT).show();
                    apiResponseListener.onError("Unknown Error");
                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
               // http://mobilehealthunit.in/api/V1_1/addPatientHistory?
                // mobile=9975294782&password=user@123&&format=json&patient_id=6
                // &registrationno=66767&drname1=hgxajg&hospitalname=lll

                try {
                    params.put("patient_id", MainActivity.PATIENT_ID);
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("registrationno",MainActivity.REGISTRATION_ID);
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("drname1",patientHistory.getDoctorName1());
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("hospitalname",patientHistory.getPrevHospital());
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("mobile",new PrefManager(activity).getMobile());
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("password",new PrefManager(activity).getPassword());
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("format","json");
                }
                catch(Exception e)
                {

                }

                try {
                    params.put("visit_master_id",MainActivity.Visit_ID);
                }
                catch(Exception e)
                {

                }

                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

}
