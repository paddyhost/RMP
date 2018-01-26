package com.example.admin.rmp.constants;

/**
 * Created by Nikam on 19/11/2017.
 */
public class WebServiceUrls
{
    private static final String HOST = "http://192.168.1.101/RMP/";
    public static final String urlUserLogin=HOST + "index.php/api/v1/login";
    public static final String urlAddPatient = HOST+"index.php/api/V1/addPatient";
    public static final String urlAddVitalInfo = HOST+ "index.php/api/V1/addVital";
}
