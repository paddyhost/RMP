package com.example.admin.rmp.patient_registration;

import android.app.DatePickerDialog;
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
import com.example.admin.rmp.user_login.LoginActivity;
import com.example.admin.rmp.utils.validation.Validations;
import com.example.admin.rmp.vital_info.Vital_Information;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.admin.rmp.constants.AppConstants.PATIENT_PREFIX;
import static com.example.admin.rmp.constants.AppConstants.REGISTRATION_PREFIX;

public class General_Information extends Fragment {

    Toolbar customertoolbar;
    Button btnSave;
    TextInputEditText edtFName,edtLName,edtMobile,edtDob,edtAddress,edtAge,edtRegistrationDate;
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


    public General_Information() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootview = inflater.inflate(R.layout.fragment_general_information, container, false);

        initializations(rootview);
        generateUniqueId();
        generateRegistrationNumber();
        DOBClickListener();
        registrationClickListener();
        genderClickListener();
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
        edtAge= (TextInputEditText)view.findViewById(R.id.age);
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

                final Calendar TodayDate = Calendar.getInstance();
                tYear = TodayDate.get(Calendar.YEAR);
                tMonth = TodayDate.get(Calendar.MONTH);
                tDay = TodayDate.get(Calendar.DAY_OF_MONTH);

                int age=tYear-year;
                edtAge.setText(String.valueOf(age+"yrs"));

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
                    setPatientData();

                    //if(checkValidation()) {


                        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                                .setTitleText("Please wait");

                        sweetAlertDialog.show();

                        Web_ApiHelper.webPatientRegistration(getActivity(), patientRegistration, new ApiResponseListener() {
                            @Override
                            public void onSuccess(String message) {
                                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
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
                                });
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

                    //}




            }
        });

    }

    private void setPatientData()
    {
        patientRegistration = new PatientRegistration();
        patientRegistration.setfName(edtFName.getText().toString());
        patientRegistration.setLname(edtLName.getText().toString());
        patientRegistration.setAddress(edtAddress.getText().toString());
        patientRegistration.setMobileNo(edtMobile.getText().toString());
        patientRegistration.setDob(edtDob.getText().toString());
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

        if (edtAge.getText().toString().trim().length() == 0)
        {
            edtAge.setError("PLease Enter age");
            response = false;
        } else {
            edtAge.setError(null);
        }

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
        }
        return super.onOptionsItemSelected(item);
    }


    private void generateUniqueId()
    {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String datetime = ft.format(dNow);
        txtPatientUniqueId.setText(PATIENT_PREFIX+datetime);
        String randomNumber= String.valueOf(Utility.generateRandomNumber(getActivity()));

        txtPatientUniqueId.setText(PATIENT_PREFIX+datetime+"_"+randomNumber);
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
