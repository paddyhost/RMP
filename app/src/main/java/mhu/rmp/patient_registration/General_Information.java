package mhu.rmp.patient_registration;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import mhu.rmp.R;
import mhu.rmp.Utility;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.check_previous_records.CheckPreviousRecordsFragment;
import mhu.rmp.patient_previous_history.model.PreviousRecords;
import mhu.rmp.patient_registration.apihelper.Web_ApiHelper;
import mhu.rmp.patient_registration.model.PatientRegistration;
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.user_login.LoginActivity;
import mhu.rmp.utils.validation.Validations;
import mhu.rmp.vital_info.Vital_Information;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static mhu.rmp.constants.AppConstants.PATIENT_PREFIX;
import static mhu.rmp.constants.AppConstants.REGISTRATION_PREFIX;

public class General_Information extends Fragment {
    public static PatientRegistration registration;
    String adharid;
    Toolbar customertoolbar;
    Button btnSaveGeneralInformation;
    TextInputEditText edtFName,edtLName,edtMobile,edtAge,edtAddress,edtRegistrationDate;
    static TextInputEditText edtDob;
    RadioGroup genderGrp;
    static int ageYear;
    RadioButton maleBtn, femaleBtn;
    private DatePickerDialog dpd;
    private int dYear,dDay,dMonth,tYear,tDay,tMonth;
    private String selected_gender = "";
    private PatientRegistration patientRegistration;
    private Spinner patientCategorySpinner,stateSpinner,districtSpinner,citySpinner,areaSpinner,locationSpinner;
    private TextView txtPatientUniqueId,txtRegistrationNumber,txtVisitNumber,txtSelectPatientCategory;
    TextInputLayout firstname_TextLayout,lname_TextLayout,mobile_TextLayout,dob_TextLayout,address_TextLayout,age_textLayout,registartion_date_textLayout;
    private PrefManager prefManager;
    FragmentTransaction fragmentTransaction;
    Map<String, String> category = new Hashtable<String, String>();
    TextView SpinnerValue,locationSpinnerValue;
    ImageView patientCategoryImageview,location_spinner_image;


    public General_Information() {
        // Required empty public constructor
    }

    int getPatientCategoryIndex(String text)
    {
        Iterator it = category.entrySet().iterator();
        int i=0;
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getValue().toString().equalsIgnoreCase(text))
            {

                //return i;

            }
            i++;
            System.out.println(pair.getKey() + " = " + pair.getValue());

        /*String[] list = getResources().getStringArray(R.array.patient_category);
        int i = 0;
        for(String item : list)
        {
            if(item.equals(text))
            {
                return i;
                //break;
            }
            i++;*/
        }
        return  0;
    }


    private int getLocationIndex(String location)
    {
      String[] loactionlist=  getResources().getStringArray(R.array.location_array);
      for(int i=0;i<loactionlist.length;i++)
      {

          if(loactionlist[i].toString().trim().equalsIgnoreCase(location.trim()));
          {
              return i;
          }
      }


        /*String[] list = getResources().getStringArray(R.array.location_array);
        int i = 0;
        for(String item : list)
        {
            if(item.equals(location))
            {
                return i;
                //break;
            }
            i++;
        }*/
      return 0;

    }

    public static General_Information newInstance(PatientRegistration param1,String adharid) {
        General_Information fragment = new General_Information();
        Bundle args = new Bundle();
        args.putParcelable(PatientRegistration.class.getName(), param1);
        args.putString("AAdhar",adharid);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            registration = getArguments().getParcelable(PatientRegistration.class.getName());
            adharid=getArguments().getString("AAdhar");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootview = inflater.inflate(R.layout.fragment_general_information, container, false);
        category.put("Select Patient Category","");
        category.put("Pregnant Women","PW");
        category.put("Lactating Women","LW");
        category.put("Child Under-5 Years of Age","C");
        category.put("Senior Citizen-above 60 years of age","S");
        category.put("Other","O");


        initializations(rootview);

       if(registration==null)
       {
           generateUniqueId();
           generateRegistrationNumber();
           DOBClickListener();

           SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd" );
           edtRegistrationDate.setText( sdf.format( new Date() ));

           //registrationClickListener();
           genderClickListener();
       }
       else
       {
           txtPatientUniqueId.setText(registration.getUnique_id());
           txtRegistrationNumber.setText(registration.getRegistrationNo());
           edtRegistrationDate.setText(registration.getDateOfRegistration());
           //patientCategorySpinner.
           edtFName.setText(registration.getfName());
           edtFName.setFocusable(false);

           try {
               if(registration.getLname().toString().equalsIgnoreCase(" ") || edtLName.getText().toString().trim().length()<=0) {
                   edtLName.setText("NA");
               }
               else
               {
                   edtLName.setText(registration.getLname());
               }
           }
           catch (Exception e)
           {}

           edtLName.setFocusable(false);

           try {
               if(registration.getMobileNo().toString().equalsIgnoreCase(" ") || edtMobile.getText().toString().trim().length()<=0) {
                   edtMobile.setText("NA");
               }
               else
               {
                   edtMobile.setText(registration.getMobileNo());
               }
           }
           catch (Exception e){}
           edtMobile.setFocusable(false);

           try {

               if(registration.getDob().toString().equalsIgnoreCase(" ") || registration.getDob().toString().equalsIgnoreCase("0") || edtDob.getText().toString().trim().length()<=0) {
                   edtDob.setText("NA");

               }
               else
               {
                   edtDob.setText(registration.getDob());

               }
           }
           catch (Exception e)
           {}

           edtDob.setFocusable(false);


           try {

               if(registration.getAge().toString().equalsIgnoreCase(" ") || registration.getAge().toString().equalsIgnoreCase("0") || edtAge.getText().toString().trim().length()<=0) {

                   edtAge.setText("NA");
               }
               else
               {
                   edtAge.setText(String.valueOf(ageYear));
               }
           }
           catch (Exception e)
           {}


           edtAge.setFocusable(false);
           edtAddress.setText(registration.getAddress());
           edtAddress.setFocusable(false);
          // int i=getPatientCategoryIndex(registration.getPatient_category());
           //patientCategorySpinner.setSelection(i);
           patientCategorySpinner.setVisibility(View.GONE);
           patientCategoryImageview.setVisibility(View.GONE);
           SpinnerValue.setVisibility(View.VISIBLE);


           if(registration.getPatient_category().equalsIgnoreCase("PW"))
           {
               SpinnerValue.setText("Pregnant Women");
           }

           else if(registration.getPatient_category().equalsIgnoreCase("LW"))
           {
               SpinnerValue.setText("Lactating Women");
           }

           else if(registration.getPatient_category().equalsIgnoreCase("C"))
           {
               SpinnerValue.setText("Child Under-5 Years of Age");
           }

           else if(registration.getPatient_category().equalsIgnoreCase("S"))
           {
               SpinnerValue.setText("Senior Citizen-above 60 years of age");
           }

           else if(registration.getPatient_category().equalsIgnoreCase("O"))
           {
               SpinnerValue.setText("Other");

           }
          // patientCategorySpinner.setSelection(1);
           SpinnerValue.setEnabled(false);

           stateSpinner.setSelection(1);
           stateSpinner.setEnabled(false);
           districtSpinner.setSelection(1);
           districtSpinner.setEnabled(false);
           citySpinner.setSelection(1);
           citySpinner.setEnabled(false);
           areaSpinner.setSelection(1);
           areaSpinner.setEnabled(false);
           //locationSpinner.setSelection(1);
           //locationSpinner.setSelection(getLocationIndex(registration.getLocation()));
           locationSpinner.setVisibility(View.GONE);
           location_spinner_image.setVisibility(View.GONE);
           locationSpinnerValue.setVisibility(View.VISIBLE);
           locationSpinnerValue.setText(registration.getLocation());

           locationSpinnerValue.setEnabled(false);
           if(registration.getGender().equalsIgnoreCase("M"))
           {
              // patientRegistration.setGender("Female");
               maleBtn.setChecked(true);
               maleBtn.setClickable(false);
           }
           else
           {
               //patientRegistration.setGender("Male");
              femaleBtn.setChecked(true);
               maleBtn.setClickable(false);
           }
       }


        //calculateDOBFromAge(getActivity(),24,06,00);

        saveClickListener();

        return rootview;
    }

    private void initializations(View view)
    {
        setHasOptionsMenu(true);
        customertoolbar = (Toolbar) view.findViewById(R.id.customer_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(customertoolbar);
        setHasOptionsMenu(true);
        prefManager=new PrefManager(getActivity());
        location_spinner_image=(ImageView)view.findViewById(R.id.location_spinner_image);
        patientCategoryImageview=(ImageView)view.findViewById(R.id.patient_category_imageview);
        locationSpinnerValue=(TextView)view.findViewById(R.id.location_spinner_value);
        edtFName = (TextInputEditText)view.findViewById(R.id.firstname);
        edtLName = (TextInputEditText)view.findViewById(R.id.lname);
        edtMobile =(TextInputEditText)view.findViewById(R.id.mobile);
        edtDob = (TextInputEditText)view.findViewById(R.id.dob);
        edtAge= (TextInputEditText)view.findViewById(R.id.edt_age);
        edtAddress = (TextInputEditText)view.findViewById(R.id.address);
        edtRegistrationDate= (TextInputEditText)view.findViewById(R.id.registration_date);
        txtRegistrationNumber= (TextView) view.findViewById(R.id.registration_number);
        txtPatientUniqueId=(TextView) view.findViewById(R.id.patient_unique_id);
        txtVisitNumber=(TextView) view.findViewById(R.id.visit_number);
        genderGrp = (RadioGroup)view.findViewById(R.id.gender);
        maleBtn = (RadioButton)view.findViewById(R.id.male);
        femaleBtn = (RadioButton)view.findViewById(R.id.female);
        btnSaveGeneralInformation = (Button)view.findViewById(R.id.btn_save_general_information);
        firstname_TextLayout=(TextInputLayout)view.findViewById(R.id.firstname_textLayout);
        lname_TextLayout=(TextInputLayout)view.findViewById(R.id.lname_textLayout);
        mobile_TextLayout=(TextInputLayout)view.findViewById(R.id.mobile_textLayout);
        dob_TextLayout=(TextInputLayout)view.findViewById(R.id.dob_textLayout);
        address_TextLayout=(TextInputLayout)view.findViewById(R.id.address_textLayout);
        age_textLayout=(TextInputLayout)view.findViewById(R.id.age_textLayout);
        patientCategorySpinner=(Spinner)view.findViewById(R.id.patient_category_spinner);
        stateSpinner=(Spinner)view.findViewById(R.id.state_spinner);
        districtSpinner=(Spinner)view.findViewById(R.id.district_spinner);
        citySpinner=(Spinner)view.findViewById(R.id.city_spinner);
        areaSpinner=(Spinner)view.findViewById(R.id.area_spinner);
        locationSpinner=(Spinner)view.findViewById(R.id.location_spinner);
        edtDob.setFocusable(false);
        edtRegistrationDate.setFocusable(false);
        SpinnerValue=(TextView)view.findViewById(R.id.spinner_value);

    }

    private void DOBClickListener()
    {
        edtDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatePicker();
            }
        });
    }

    public static void calculateDOBFromAge(Context context, int CurrentAge, int MonthCompleted, int DaysCompleted)
    {
        //Function call like this ---- calculateDOBFromAge(MainActivity.this,25,01,15);

        Calendar calender = Calendar.getInstance();
        int mYear = calender.get(Calendar.YEAR);
        int mMonth = calender.get(Calendar.MONTH);
        int mDay = calender.get(Calendar.DAY_OF_MONTH);
        mMonth=mMonth+1;
        String AgewithZero = "0";
        String MonthswithZero = "0";
        String DaysswithZero = "0";
        String currentYearWithZero = "0";
        String currentMonthsWithZero = "0";
        String currentDaysswithZero = "0";
        if(String.valueOf(CurrentAge).length()==1)
        {
            AgewithZero = String.valueOf(AgewithZero)+String.valueOf(CurrentAge);
        }
        else
        {
            AgewithZero = String.valueOf(CurrentAge);
        }

        if(String.valueOf(MonthCompleted).length()==1)
        {
            MonthswithZero = String.valueOf(MonthswithZero)+String.valueOf(MonthCompleted);
        }
        else
        {
            MonthswithZero = String.valueOf(MonthCompleted);
        }

        if(String.valueOf(DaysCompleted).length()==1)
        {
            DaysswithZero = String.valueOf(DaysswithZero)+String.valueOf(DaysCompleted);
        }
        else
        {
            DaysswithZero = String.valueOf(DaysCompleted);
        }

        if(String.valueOf(mYear).length()==1)
        {
            currentYearWithZero = String.valueOf(currentYearWithZero)+String.valueOf(mYear);
        }
        else
        {
            currentYearWithZero = String.valueOf(mYear);
        }

        if(String.valueOf(mMonth).length()==1)
        {
            currentMonthsWithZero = String.valueOf(currentMonthsWithZero)+String.valueOf(mMonth);
        }
        else
        {
            currentMonthsWithZero = String.valueOf(mMonth);
        }

        if(String.valueOf(mDay).length()==1)
        {
            currentDaysswithZero = String.valueOf(currentDaysswithZero)+String.valueOf(mDay);
        }
        else
        {
            currentDaysswithZero = String.valueOf(mDay);
        }

        String age = AgewithZero + MonthswithZero + DaysswithZero;
        String currentDate = currentYearWithZero + currentMonthsWithZero + currentDaysswithZero;

        int calculatedDob = Integer.parseInt(age)- Integer.parseInt(currentDate);
        calculatedDob = Math.abs(calculatedDob);


        String finalDob="";
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = originalFormat.parse(String.valueOf(calculatedDob));
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
            finalDob = newFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        edtDob.setText(finalDob);
        //Toast.makeText(context,finalDob,Toast.LENGTH_SHORT).show();
    }

    public void displayDatePicker() {

        final Calendar DOBDate = Calendar.getInstance();
        dYear = DOBDate.get(Calendar.YEAR);
        dMonth = DOBDate.get(Calendar.MONTH);
        dDay = DOBDate.get(Calendar.DAY_OF_MONTH);

        dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;

                edtDob.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                edtDob.setError(null);

                final Calendar TodayDate = Calendar.getInstance();
                tYear = TodayDate.get(Calendar.YEAR);
                tMonth = TodayDate.get(Calendar.MONTH);
                tDay = TodayDate.get(Calendar.DAY_OF_MONTH);

                ageYear=tYear-year;

                edtAge.setText(String.valueOf(ageYear+"yrs"));


                edtAge.setError(null);
                edtAge.setFocusable(false);

            }
        }, dYear, dMonth, dDay);
       // dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();
    }

    private void registrationClickListener()
    {
        edtRegistrationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrationDisplayDatePicker();
            }
        });
    }

    public void registrationDisplayDatePicker() {
        final Calendar mcurrentDate = Calendar.getInstance();
        tYear = mcurrentDate.get(Calendar.YEAR);
        tMonth= mcurrentDate.get(Calendar.MONTH);
        tDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear = monthOfYear + 1;

                edtRegistrationDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                edtRegistrationDate.setError(null);

            }
        }, tYear, tMonth, tDay);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
        dpd.show();

    }

    private void genderClickListener()
    {
        genderGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if(checkedId== R.id.male)
                {
                    selected_gender="M";
                }
                else if(checkedId == R.id.female)
                {
                    selected_gender="F";
                }
            }
        });
    }


    private void saveClickListener()
    {
        try {
           btnSaveGeneralInformation.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (checkValidation()) {

                       try {
                           if (registration == null) {
                               setPatientData();
                               final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE).setTitleText("Please wait");
                               sweetAlertDialog.show();
                               Web_ApiHelper.webPatientRegistration(getActivity(), patientRegistration, new ApiResponseListener() {
                                   @Override
                                   public void onSuccess(String message) {

                                       sweetAlertDialog.dismiss();
                                       addVisit();
                                   }

                                   @Override
                                   public void onError(String message) {
                                       sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                       sweetAlertDialog.setTitleText(message);
                                       sweetAlertDialog.setConfirmText("Ok");
                                       sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                           @Override
                                           public void onClick(SweetAlertDialog sweetAlertDialog) {
                                               sweetAlertDialog.dismissWithAnimation();
                                           }
                                       });
                                   }
                               });
                           } else {
                               addVisit();
                               //"VISIT_GENERATED"
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               }
           });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private  void addVisit()
    {
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE).setTitleText("Please wait");
        sweetAlertDialog.show();
        Web_ApiHelper.addVisit(getActivity(), new ApiResponseListener() {
            @Override
            public void onSuccess(String message) {


                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog1) {
                        sweetAlertDialog.dismiss();
                        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        Vital_Information vitalInformation = new Vital_Information();
                        fragmentTransaction.replace(R.id.framelayout, vitalInformation).addToBackStack(null).commit();

                        edtFName.setText("");
                        edtLName.setText("");
                        edtAddress.setText("");
                        edtMobile.setText("");
                        edtDob.setText("");
                        maleBtn.setChecked(false);
                        femaleBtn.setChecked(false);
                    }
                });
                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("Patient Registration Successful!!");
                sweetAlertDialog.setConfirmText("Ok");

            }

            @Override
            public void onError(String message) {
                sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                sweetAlertDialog.setTitleText(message);
                sweetAlertDialog.setConfirmText("Ok");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
            }
        },registration!=null?registration.getUnique_id():patientRegistration.getUnique_id());


    }

    private void setPatientData()
    {
        patientRegistration = new PatientRegistration();
        patientRegistration.setUnique_id(txtPatientUniqueId.getText().toString());
        patientRegistration.setRegistrationNo(txtRegistrationNumber.getText().toString());
        patientRegistration.setDateOfRegistration(edtRegistrationDate.getText().toString());
        patientRegistration.setPatient_category(patientCategorySpinner.getSelectedItem().toString());
        patientRegistration.setPatient_category(category.get(patientCategorySpinner.getSelectedItem().toString()));
        patientRegistration.setfName(edtFName.getText().toString());
        patientRegistration.setLname(edtLName.getText().toString());
        patientRegistration.setMobileNo(edtMobile.getText().toString());
        patientRegistration.setDob(edtDob.getText().toString());
        patientRegistration.setAddress(edtAddress.getText().toString());
        patientRegistration.setState(stateSpinner.getSelectedItem().toString());
        patientRegistration.setDistrict(districtSpinner.getSelectedItem().toString());
        patientRegistration.setCity(citySpinner.getSelectedItem().toString());
        patientRegistration.setArea(areaSpinner.getSelectedItem().toString());
        patientRegistration.setLocation(locationSpinner.getSelectedItem().toString());
        patientRegistration.setGender(selected_gender);

    }

    private boolean checkValidation()
    {
        boolean response=true;


        if(edtRegistrationDate.getText().toString().trim().length()==0) {
            edtRegistrationDate.setError("Select Registration Date");
            response = false;
        }
        else
        {
            edtRegistrationDate.setError(null);
        }

        if (edtFName.getText().toString().trim().length() == 0)
        {
            //firstname_TextLayout.setErrorEnabled(true);
            //firstname_TextLayout.setErrorTextAppearance(R.style.error);
            edtFName.setError("Enter first name");
            response = false;
        } else {
            //firstname_TextLayout.setErrorEnabled(false);
            edtFName.setError(null);
        }

       /* if (edtLName.getText().toString().trim().length() == 0)
        {
            //lname_TextLayout.setErrorEnabled(true);
            //lname_TextLayout.setErrorTextAppearance(R.style.error);
            edtLName.setError("Enter last name");
            response = false;
        } else {
            //lname_TextLayout.setErrorEnabled(false);
            edtLName.setError(null);
        }

        if(!Validations.isValidPhoneNumber(edtMobile.getText().toString()))
        {
            //mobile_TextLayout.setErrorEnabled(true);
            //mobile_TextLayout.setErrorTextAppearance(R.style.error);
            edtMobile.setError("Enter mobile number");
            response = false;
        }
        else
        {
            //mobile_TextLayout.setErrorEnabled(false);
            edtMobile.setError(null);
        }

*/

        if (edtAddress.getText().toString().trim().length() == 0)
        {
            //address_TextLayout.setErrorEnabled(true);
            //address_TextLayout.setErrorTextAppearance(R.style.error);
            edtAddress.setError("Enter address");
            response = false;
        } else {
            //address_TextLayout.setErrorEnabled(false);
            edtAddress.setError(null);
        }

      /*  if (edtDob.getText().toString().trim().length() == 0)
        {
            //dob_TextLayout.setErrorEnabled(true);
            //dob_TextLayout.setErrorTextAppearance(R.style.error);
            edtDob.setError("Select date of birth");
            response = false;
        } else {
            //dob_TextLayout.setErrorEnabled(false);
            edtDob.setError(null);
        }*/

      /*  if (edtAge.getText().toString().trim().length() == 0)
        {
            edtAge.setError("PLease Enter age");
            response = false;
        } else {
            edtAge.setError(null);
        }*/

        if (genderGrp.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please select gender", Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        } else {
            // one of the radio buttons is checked

        }

        if (patientCategorySpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Select Patient Category")) {

            View selectedView = patientCategorySpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (patientCategorySpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select Patient Category";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }

        if (stateSpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select State")) {

            View selectedView = stateSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (stateSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select State";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }


        if (districtSpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select District")) {

            View selectedView = districtSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (districtSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select District";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }

        if (citySpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select City")) {

            View selectedView = citySpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (citySpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select City";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }



        if (areaSpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select Area")) {

            View selectedView = areaSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (areaSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select Area";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }


        if (locationSpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select Location")) {

            View selectedView = locationSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (locationSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select Location";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }
        return response;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (item.getItemId())
        {
            case R.id.logout:
                prefManager.setLogOut();
                Intent i= new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();

                break;

            case R.id.previous_record:
                if(registration!=null) {
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    CheckPreviousRecordsFragment vitalInformation = CheckPreviousRecordsFragment.getInstance(registration.getfName()+" "+registration.getLname(),registration.getUnique_id());
                    fragmentTransaction.replace(R.id.framelayout, vitalInformation).addToBackStack(null).commit();
                }


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateUniqueId()
    {
        if(adharid==null) {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
            String datetime = ft.format(dNow);
            txtPatientUniqueId.setText(PATIENT_PREFIX + datetime);
            String randomNumber = String.valueOf(Utility.generateRandomNumber(getActivity()));
            SecureRandom random = new SecureRandom();
            int num = random.nextInt(100000);
            String formatted = String.format("%05d", num);
            System.out.println(formatted);

            txtPatientUniqueId.setText(PATIENT_PREFIX + datetime+formatted);
        }
        else
        {
            txtPatientUniqueId.setText(adharid);
        }
    }

    private void generateRegistrationNumber()
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        txtRegistrationNumber.setText(REGISTRATION_PREFIX+datetime);
        String randomNumber= String.valueOf(Utility.generateRandomNumber(getActivity()));
        txtRegistrationNumber.setText(REGISTRATION_PREFIX+datetime+"_"+randomNumber);
    }
}
