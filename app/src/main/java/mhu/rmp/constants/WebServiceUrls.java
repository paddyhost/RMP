package mhu.rmp.constants;

/**
 * Created by Nikam on 19/11/2017.
 */
public class WebServiceUrls
{
    private static final String HOST = "http://mobilehealthunit.in/";
    public static final String urlUserLogin=HOST + "api/v1_1/login";
    public static final String urlAddPatient = HOST+"/api/v1_1/addPatient";
    public static final String urlAddVitalInfo =HOST + "api/V1_1/addVital";
    public static final String urlAddMedicalInfo =HOST+ "api/V1_1/addMedicalcondition";
    public static final String urlAddVaccination = HOST+ "api/v1_1/addvaccination";
    public static final String urlAddPatientHistory =HOST+ "api/V1_1/addPatient";
    public static final String urlAddMHuTest =HOST+ "api/v1_1/addMHUTest";
    public static final String addPriscribeDose = HOST + "api/v1_1/addPriscribeDose";
    public static final String ulGetTestMasterAttribute =HOST+ "api/v1_1/gettestmasterattribute";
    public static final String isPatientExist = HOST+ "api/v1_1/isPatientExists";
    public static final String addPatientVisit = HOST+ "api/v1_1/addPatientVisit";
    public static final String urlAddTestAttributeValues=HOST+ "api/v1_1/addTestAttributeValues";
    public static final String urlGetSinglePatientRecord=HOST+ "api/v1_1/getSinglePatientRecord";


}
