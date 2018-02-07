package com.example.admin.rmp.previous_records;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.rmp.R;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.mhu_test.MhuTest;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.previous_records.apihelper.Web_PatientHistory_Helper;
import com.example.admin.rmp.previous_records.model.PatientHistory;
import com.example.admin.rmp.user_login.LoginActivity;
import com.example.admin.rmp.vaccination_record.VaccinationRecord;
import com.example.admin.rmp.vaccination_record.apihelper.Vaccination_ApiHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PreviousRecords extends Fragment /*implements TextWatcher*/ {

    private Button btn_save_prerecord;
    private Toolbar prerecord_toolbar;
    private TextInputEditText etPreviousHsopital, etDoctorName1, etDoctorName2, etDoctorName3;
    private PatientHistory patientHistory;
    private TextInputLayout prevs_hosptlTextLayout,drname1TextLayout,drname2TextLayout,drname3TextLayout;
    private PrefManager prefManager;

    public PreviousRecords()
    {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_previous_records, container, false);

        initializations(rootview);

        saveRecordClickListener();

        return rootview;
    }

    private void initializations(View view)
    {
        prerecord_toolbar = (Toolbar) view.findViewById(R.id.prerecord_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(prerecord_toolbar);
        prerecord_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        setHasOptionsMenu(true);
        prefManager=new PrefManager(getActivity());

        btn_save_prerecord = (Button) view.findViewById(R.id.btn_save_prerecord);
        etPreviousHsopital = (TextInputEditText)view.findViewById(R.id.prevs_hosptl);
        etDoctorName1 = (TextInputEditText)view.findViewById(R.id.drname1);
        etDoctorName2 = (TextInputEditText)view.findViewById(R.id.drname2);
        etDoctorName3 = (TextInputEditText)view.findViewById(R.id.drname3);
        prevs_hosptlTextLayout = (TextInputLayout) view.findViewById(R.id.prevs_hosptl_textLayout);
        drname1TextLayout = (TextInputLayout) view.findViewById(R.id.drname1_textLayout);
        drname2TextLayout = (TextInputLayout) view.findViewById(R.id.drname2_textLayout);
        drname3TextLayout = (TextInputLayout) view.findViewById(R.id.drname3_textLayout);
    }

    private void saveRecordClickListener()
    {
        btn_save_prerecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPreviousHistoryData();
                //if(checkValidation()) {

                    final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");

                    sweetAlertDialog.show();

                    Web_PatientHistory_Helper.webAddPatienHistory(getActivity(), patientHistory, new ApiResponseListener() {
                        @Override
                        public void onSuccess(String message) {
                            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText(message);
                            sweetAlertDialog.setConfirmText("Ok");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();

                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    VaccinationRecord vaccinationRecord = new VaccinationRecord();
                                    fragmentTransaction.replace(R.id.framelayout, vaccinationRecord).addToBackStack(null).commit();

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

    private void setPreviousHistoryData()
    {
        patientHistory = new PatientHistory();
        patientHistory.setPrevHospital(etPreviousHsopital.getText().toString());
        patientHistory.setDoctorName1(etDoctorName1.getText().toString());
        patientHistory.setDoctorName2(etDoctorName2.getText().toString());
        patientHistory.setDoctorName3(etDoctorName3.getText().toString());
    }

    private boolean checkValidation()
    {

        boolean response=true;

        if (etPreviousHsopital.getText().toString().trim().length() == 0)
        {
            prevs_hosptlTextLayout.setErrorEnabled(true);
            prevs_hosptlTextLayout.setErrorTextAppearance(R.style.error);
            prevs_hosptlTextLayout.setError("Enter prevoius hospital");
            response = false;
        } else {
            prevs_hosptlTextLayout.setErrorEnabled(false);
            prevs_hosptlTextLayout.setError(null);
        }


        if (etDoctorName1.getText().toString().trim().length() == 0)
        {
            drname1TextLayout.setErrorEnabled(true);
            drname1TextLayout.setErrorTextAppearance(R.style.error);
            drname1TextLayout.setError("Enter doctor name1");
            response = false;
        } else {
            drname1TextLayout.setErrorEnabled(false);
            drname1TextLayout.setError(null);
        }

        if (etDoctorName2.getText().toString().trim().length() == 0)
        {
            drname2TextLayout.setErrorEnabled(true);
            drname2TextLayout.setErrorTextAppearance(R.style.error);
            drname2TextLayout.setError("Enter doctor name2");
            response = false;
        } else {
            drname1TextLayout.setErrorEnabled(false);
            drname1TextLayout.setError(null);
        }

        if (etDoctorName3.getText().toString().trim().length() == 0)
        {
            drname3TextLayout.setErrorEnabled(true);
            drname3TextLayout.setErrorTextAppearance(R.style.error);
            drname3TextLayout.setError("Enter doctor name3");
            response = false;
        } else {
            drname3TextLayout.setErrorEnabled(false);
            drname3TextLayout.setError(null);
        }
        return response;

    }


    /*@Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (editable.length() == 0) {
            etPreviousHsopital.setError("Required");
            etDoctorName1.setVisibility(View.GONE);
        } else {
            etPreviousHsopital.setError(null);
            etDoctorName1.setVisibility(View.VISIBLE);
        }
    }*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.logout:
                prefManager.setLogOut();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
