package com.example.admin.rmp.app;

/**
 * Created by Ashwin on 26-Jan-18.
 */

public interface ApiResponseListener
{
    void onSuccess(String message);

    void onError(String message);
}
