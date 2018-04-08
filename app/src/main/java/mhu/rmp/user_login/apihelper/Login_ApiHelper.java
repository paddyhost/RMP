package mhu.rmp.user_login.apihelper;

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
import mhu.rmp.app.MyApplication;
import mhu.rmp.constants.WebServiceUrls;
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.user_login.model.LoginUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;


public class Login_ApiHelper
{
    public static void userLoginApi(final Activity activity, final LoginUser loginUser)
    {
        StringRequest strReq = new StringRequest(Request.Method.POST, WebServiceUrls.urlUserLogin,new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try {
                    JSONObject responce = new JSONObject(response);
                    if (responce.getString("status").equalsIgnoreCase("Success"))
                    {
                        if(responce.getString("message").equalsIgnoreCase("Login successfully")) {
                            JSONArray result = responce.getJSONArray("result");
                            JSONObject jsonObject = result.getJSONObject(0);
                            PrefManager prefManager=new PrefManager(activity);
                            prefManager.setUserId(jsonObject.getString("id"));
                            prefManager.setMobile(jsonObject.getString("mobile"));
                            prefManager.setPassword(jsonObject.getString("password"));
                            prefManager.setBirthday(jsonObject.getString("dob"));

                            prefManager.createLogin(jsonObject.getString("mobile"));
                            loginUser.fireOnLoginEvent(LoginUser.LOGIN_SUCCESS);

                        }
                        else
                        {
                           loginUser.fireOnLoginEvent(LoginUser.LOGIN_FAILED);
                        }

                    }
                    else
                    {
                        loginUser.fireOnLoginEvent(LoginUser.LOGIN_RESPONSE_FAILED);
                    }
                } catch (JSONException e) {
                    loginUser.fireOnLoginEvent(LoginUser.LOGIN_JSON_ERROR);
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    loginUser.fireOnLoginEvent(LoginUser.LOGIN_NO_CONNECTION_ERROR);
                } else if (error instanceof ServerError) {
                    loginUser.fireOnLoginEvent(LoginUser.LOGIN_SERVER_ERROR);
                } else if (error instanceof NetworkError) {
                    loginUser.fireOnLoginEvent(LoginUser.LOGIN_NEWORK_ERROR);
                } else if (error instanceof ParseError) {
                    loginUser.fireOnLoginEvent(LoginUser.LOGIN_PARSE_ERROR);
                } else {
                    loginUser.fireOnLoginEvent(LoginUser.LOGIN_UNKNOWN_ERROR);
                }

            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();

                params.put("mobile", new PrefManager(activity).getUserName());
                params.put("password", new PrefManager(activity).getPassword());
                params.put("format","json");
                //returning parameters
                return params;
            }

        };

        MyApplication.getInstance().addToRequestQueue(strReq);

    }

}
