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
import android.widget.Toast;

import com.example.admin.rmp.R;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.user_login.LoginActivity;
import com.example.admin.rmp.vital_info.Vital_Information;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.patient_registration.model.PatientRegistration;
import com.example.admin.rmp.patient_registration.apihelper.Web_ApiHelper;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class General_Information extends Fragment {

    Toolbar customertoolbar;
    Button btnSave;
    TextInputEditText edtFName,edtLName,edtMobile,edtDob,edtAddress;
    RadioGroup genderGrp;
    RadioButton maleBtn, femaleBtn;
    private DatePickerDialog dpd;
    private int year,day,month;
    private String selected_gender = "";
    private PatientRegistration patientRegistration;
    TextInputLayout firstname_TextLayout,lname_TextLayout,mobile_TextLayout,dob_TextLayout,address_TextLayout;
    PrefManager prefManager;


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

        DOBClickListener();

        genderClickListener();

        saveClickListener();

        return rootview;
    }

    private void initializations(View view)
    {
        setHasOptionsMenu(true);
        customertoolbar = (Toolbar) view.findViewById(R.id.customer_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(customertoolbar);

        prefManager=new PrefManager(getActivity());
        edtFName = (TextInputEditText)view.findViewById(R.id.firstname);
        edtLName = (TextInputEditText)view.findViewById(R.id.lname);
        edtMobile =(TextInputEditText)view.findViewById(R.id.mobile);
        edtDob = (TextInputEditText)view.findViewById(R.id.dob);
        edtAddress = (TextInputEditText)view.findViewById(R.id.address);
        genderGrp = (RadioGroup)view.findViewById(R.id.gender);
        maleBtn = (RadioButton)view.findViewById(R.id.male);
        femaleBtn = (RadioButton)view.findViewById(R.id.female);
        btnSave = (Button)view.findViewById(R.id.btn_save);
        firstname_TextLayout=(TextInputLayout)view.findViewById(R.id.firstname_textLayout);
        lname_TextLayout=(TextInputLayout)view.findViewById(R.id.lname_textLayout);
        mobile_TextLayout=(TextInputLayout)view.findViewById(R.id.mobile_textLayout);
        dob_TextLayout=(TextInputLayout)view.findViewById(R.id.dob_textLayout);
        address_TextLayout=(TextInputLayout)view.findViewById(R.id.address_textLayout);

        edtDob.setFocusable(false);
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

    public void displayDatePicker()
    {
        Calendar mcurrentDate= Calendar.getInstance();
        year=mcurrentDate.get(Calendar.YEAR);
        month=mcurrentDate.get(Calendar.MONTH);
        day=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        dpd=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                monthOfYear=monthOfYear+1;
                edtDob.setText(year+"-"+monthOfYear+"-"+dayOfMonth);

            }
        },year,month,day);
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
                    if (checkValidation()) {
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
                    }

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

        if (edtFName.getText().toString().trim().length() == 0)
        {
            firstname_TextLayout.setErrorEnabled(true);
            firstname_TextLayout.setErrorTextAppearance(R.style.error);
            firstname_TextLayout.setError("Enter first name");
            response = false;
        } else {
            firstname_TextLayout.setErrorEnabled(false);
            firstname_TextLayout.setError(null);
        }

        if (edtLName.getText().toString().trim().length() == 0)
        {
            lname_TextLayout.setErrorEnabled(true);
            lname_TextLayout.setErrorTextAppearance(R.style.error);
            lname_TextLayout.setError("Enter last name");
            response = false;
        } else {
            lname_TextLayout.setErrorEnabled(false);
            lname_TextLayout.setError(null);
        }

        if (edtMobile.getText().toString().trim().length() == 0)
        {
            mobile_TextLayout.setErrorEnabled(true);
            mobile_TextLayout.setErrorTextAppearance(R.style.error);
            mobile_TextLayout.setError("Enter mobile number");
            response = false;
        } else {
            mobile_TextLayout.setErrorEnabled(false);
            mobile_TextLayout.setError(null);
        }

        if (edtAddress.getText().toString().trim().length() == 0)
        {
            address_TextLayout.setErrorEnabled(true);
            address_TextLayout.setErrorTextAppearance(R.style.error);
            address_TextLayout.setError("Enter address");
            response = false;
        } else {
            address_TextLayout.setErrorEnabled(false);
            address_TextLayout.setError(null);
        }

        if (edtDob.getText().toString().trim().length() == 0)
        {
            dob_TextLayout.setErrorEnabled(true);
            dob_TextLayout.setErrorTextAppearance(R.style.error);
            dob_TextLayout.setError("Select date of birth");
            response = false;
        } else {
            dob_TextLayout.setErrorEnabled(false);
            dob_TextLayout.setError(null);
        }

        if (genderGrp.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please select gender", Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        } else {
            // one of the radio buttons is checked

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

}
