package com.example.admin.rmp.patient_registration;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.rmp.R;
import com.example.admin.rmp.Utility;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.patient_registration.apihelper.Web_ApiHelper;
import com.example.admin.rmp.patient_registration.model.PatientRegistration;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.previous_records_history.GeneralInformationPreviousRecordFragment;
import com.example.admin.rmp.previous_records_history.PreviousRecordsActivity;
import com.example.admin.rmp.user_login.LoginActivity;
import com.example.admin.rmp.utils.validation.Validations;
import com.example.admin.rmp.vital_info.Vital_Information;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.admin.rmp.constants.AppConstants.PATIENT_PREFIX;
import static com.example.admin.rmp.constants.AppConstants.REGISTRATION_PREFIX;

public class General_Information extends Fragment {
    PatientRegistration registration;
    String adharid;
    Toolbar customertoolbar;
    Button btnSave;
    TextInputEditText edtFName,edtLName,edtMobile,edtAge,edtAddress,edtRegistrationDate;
    static TextInputEditText edtDob;
    RadioGroup genderGrp;
    RadioButton maleBtn, femaleBtn;
    private DatePickerDialog dpd;
    private int dYear,dDay,dMonth,tYear,tDay,tMonth;
    private String selected_gender = "";
    private PatientRegistration patientRegistration;
    private Spinner patientCategorySpinner,stateSpinner,districtSpinner,citySpinner,areaSpinner,locationSpinner;
    private TextView txtPatientUniqueId,txtRegistrationNumber,txtVisitNumber;
    TextInputLayout firstname_TextLayout,lname_TextLayout,mobile_TextLayout,dob_TextLayout,address_TextLayout,age_textLayout,registartion_date_textLayout;
    private PrefManager prefManager;
    FragmentTransaction fragmentTransaction;


    public General_Information() {
        // Required empty public constructor
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
        initializations(rootview);
       if(registration==null)
       {
           generateUniqueId();
           generateRegistrationNumber();
           DOBClickListener();
           registrationClickListener();
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
           edtLName.setText(registration.getLname());
           edtLName.setFocusable(false);
           edtMobile.setText(registration.getMobileNo());
           edtMobile.setFocusable(false);
           edtDob.setText(registration.getDob());
           edtDob.setFocusable(false);
           edtAge.setFocusable(false);
           edtAddress.setText(registration.getAddress());
           edtAddress.setFocusable(false);
           patientCategorySpinner.setSelection(1);
           patientCategorySpinner.setEnabled(false);
           stateSpinner.setSelection(1);
           stateSpinner.setEnabled(false);
           districtSpinner.setSelection(1);
           districtSpinner.setEnabled(false);
           citySpinner.setSelection(1);
           citySpinner.setEnabled(false);
           areaSpinner.setSelection(1);
           areaSpinner.setEnabled(false);
           locationSpinner.setSelection(1);
           locationSpinner.setEnabled(false);
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
        btnSave = (Button)view.findViewById(R.id.btn_save);
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

                int ageYear=tYear-year;

                edtAge.setText(String.valueOf(ageYear+"yrs"));
                edtAge.setError(null);
                edtAge.setFocusable(false);

            }
        }, dYear, dMonth, dDay);
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(checkValidation())
                    {
                    if (registration == null) {
                        setPatientData();
                         final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE).setTitleText("Please wait");
                            sweetAlertDialog.show();
                            Web_ApiHelper.webPatientRegistration(getActivity(), patientRegistration, new ApiResponseListener() {
                                @Override
                                public void onSuccess(String message) {
                            /*sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("Done !!");
                            sweetAlertDialog.setConfirmText("Ok");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
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
                            });*/
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
                        }
                        else
                            {
                            addVisit();
                            //"VISIT_GENERATED"
                        }
                }
            }
        });


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
                sweetAlertDialog.setTitleText("Done !!");
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

        if (edtLName.getText().toString().trim().length() == 0)
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

        if (edtDob.getText().toString().trim().length() == 0)
        {
            //dob_TextLayout.setErrorEnabled(true);
            //dob_TextLayout.setErrorTextAppearance(R.style.error);
            edtDob.setError("Select date of birth");
            response = false;
        } else {
            //dob_TextLayout.setErrorEnabled(false);
            edtDob.setError(null);
        }

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
                Intent intent=new Intent(getActivity(), PreviousRecordsActivity.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void generateUniqueId()
    {
        if(adharid==null) {
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
            String datetime = ft.format(dNow);
            txtPatientUniqueId.setText(PATIENT_PREFIX + datetime);
            String randomNumber = String.valueOf(Utility.generateRandomNumber(getActivity()));

            txtPatientUniqueId.setText(PATIENT_PREFIX + datetime + "_" + randomNumber);
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
