package com.example.admin.rmp.constants;

/**
 * Created by Nikam on 19/11/2017.
 */
public class WebServiceUrls
{
    private static final String HOST = "http://mobilehealthunit.in/";
    public static final String urlUserLogin=HOST + "index.php/api/v1_1/login";
    public static final String urlAddPatient = HOST+"index.php/api/v1_1/addPatient";
    public static final String urlAddVitalInfo ="http://mobilehealthunit.in/api/V1_1/addVital";
    public static final String urlAddMedicalInfo =HOST+ "index.php/api/v1/addMedicalcondition";
    public static final String urlAddVaccination = HOST+ "index.php/api/v1_1/addvaccination";
    public static final String urlAddPatientHistory ="http://mobilehealthunit.in/api/V1_1/addPatient";
    public static final String urlAddMHuTest ="http://mobilehealthunit.in/api/v1_1/addMHUTest";
    public static final String addPriscribeDose = HOST + "index.php/api/v1_1/addPriscribeDose";
    public static final String ulGetTestMasterAttribute ="http://mobilehealthunit.in/api/v1_1/gettestmasterattribute";
    public static final String isPatientExist = HOST+"index.php/api/v1_1/isPatientExists";
    public static final String addPatientVisit = "http://mobilehealthunit.in/api/v1_1/addPatientVisit";
    public static final String urlAddTestAttributeValues="http://mobilehealthunit.in/api/v1_1/addTestAttributeValues";


}
