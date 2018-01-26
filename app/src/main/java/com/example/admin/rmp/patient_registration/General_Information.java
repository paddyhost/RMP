package com.example.admin.rmp.patient_registration;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import com.example.admin.rmp.R;
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
    private DatePickerDialog dpd;
    private int year,day,month;
    private String selected_gender = "";
    private PatientRegistration patientRegistration;


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
        customertoolbar = (Toolbar) view.findViewById(R.id.customer_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(customertoolbar);

        edtFName = (TextInputEditText)view.findViewById(R.id.firstname);
        edtLName = (TextInputEditText)view.findViewById(R.id.lname);
        edtMobile =(TextInputEditText)view.findViewById(R.id.mobile);
        edtDob = (TextInputEditText)view.findViewById(R.id.dob);
        edtAddress = (TextInputEditText)view.findViewById(R.id.address);
        genderGrp = (RadioGroup)view.findViewById(R.id.gender);
        btnSave = (Button)view.findViewById(R.id.btn_save);
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
                final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait");

                sweetAlertDialog.show();

                Web_ApiHelper.webPatientRegistration(getActivity(), patientRegistration, new ApiResponseListener() {
                    @Override
                    public void onSuccess(String message)
                    {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText(message);
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                Vital_Information vitalInformation= new Vital_Information();
                                fragmentTransaction.replace(R.id.framelayout,vitalInformation).addToBackStack(null).commit();

                            }
                        });
                    }

                    @Override
                    public void onError(String message)
                    {
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


}
