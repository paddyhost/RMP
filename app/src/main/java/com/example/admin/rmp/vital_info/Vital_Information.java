package com.example.admin.rmp.vital_info;

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

import com.example.admin.rmp.R;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.medical_condition.MedicalConditionFragment;

import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.user_login.LoginActivity;

import com.example.admin.rmp.utils.Utility;

import com.example.admin.rmp.vital_info.apihelper.WebVital_Helper;
import com.example.admin.rmp.vital_info.model.Vital_Info;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Vital_Information extends Fragment {

    Toolbar toolbarvital;
    private Button btnSaveVital;
    private TextInputEditText edtHeight,edtWeight, edtBloodPressure, edtTemperature, edtRespiration,
                            edtBloodPressTo;
    private Vital_Info vitalInfo;
    private TextInputLayout edt_heightTextLayout,edt_weightTextLayout,
    edt_bloodpressure_TextLayout,edt_bloodpressureTo_TextLayout,edt_tempTextLayout,edt_respTextLayout;
    private PrefManager prefManager;

    public Vital_Information() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_vital_information, container, false);

        initializations(rootview);
        toolbarClickListnere();
        saveVitalClickListener();

        return rootview;
    }

    private void initializations(View rootview)
    {
        setHasOptionsMenu(true);
        prefManager=new PrefManager(getActivity());
        toolbarvital = (Toolbar) rootview.findViewById(R.id.vital_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarvital);
        btnSaveVital = (Button)rootview.findViewById(R.id.btn_save_vital);
        edtHeight = (TextInputEditText)rootview.findViewById(R.id.edt_height);
        edtWeight = (TextInputEditText)rootview.findViewById(R.id.edt_weight);
        edtBloodPressure = (TextInputEditText)rootview.findViewById(R.id.edt_bloodpressure);
        edtTemperature = (TextInputEditText)rootview.findViewById(R.id.edt_temp);
        edtRespiration = (TextInputEditText)rootview.findViewById(R.id.edt_resp);
        edtBloodPressTo = (TextInputEditText)rootview.findViewById(R.id.edt_bloodpressure_to);
        edt_heightTextLayout=(TextInputLayout)rootview.findViewById(R.id.edt_height_TextLayout);
        edt_weightTextLayout=(TextInputLayout)rootview.findViewById(R.id.edt_weight_TextLayout);
        edt_bloodpressure_TextLayout=(TextInputLayout)rootview.findViewById(R.id.edt_bloodpressure_TextLayout);
        edt_bloodpressureTo_TextLayout=(TextInputLayout)rootview.findViewById(R.id.edt_bloodpressure_to_TextLayout);
        edt_tempTextLayout=(TextInputLayout)rootview.findViewById(R.id.edt_temp_TextLayout);
        edt_respTextLayout=(TextInputLayout)rootview.findViewById(R.id.edt_resp_TextLayout);
    }

    private void toolbarClickListnere()
    {
        toolbarvital.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });
    }

    private void saveVitalClickListener()
    {
        btnSaveVital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVitalInfo();

                //if(checkValidation()) {

                    final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");

                    sweetAlertDialog.show();

                    WebVital_Helper.webAddVitalInfo(getActivity(), vitalInfo, new ApiResponseListener() {
                        @Override
                        public void onSuccess(String message) {

                            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("Done !!");
                            sweetAlertDialog.setConfirmText("Ok");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    MedicalConditionFragment medicalConditionFragment = new MedicalConditionFragment();
                                    fragmentTransaction.replace(R.id.framelayout, medicalConditionFragment).addToBackStack(null).commit();

                                    edtHeight.setText("");
                                    edtWeight.setText("");
                                    edtBloodPressure.setText("");
                                    edtTemperature.setText("");
                                    edtRespiration.setText("");
                                    edtBloodPressTo.setText("");

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

    private void setVitalInfo()
    {
        vitalInfo = new Vital_Info();
        vitalInfo.setHeight(edtHeight.getText().toString());
        vitalInfo.setWeight(edtWeight.getText().toString());
        vitalInfo.setBloodPressure(edtBloodPressure.getText().toString());
        vitalInfo.setTemperature(edtTemperature.getText().toString());
        vitalInfo.setRespiration(edtRespiration.getText().toString());
        vitalInfo.setBloodPressureTo(edtBloodPressTo.getText().toString());
    }

    private boolean checkValidation()
    {
        boolean response=true;

        if (edtHeight.getText().toString().trim().length() == 0)
        {
            edt_heightTextLayout.setErrorEnabled(true);
            edt_heightTextLayout.setErrorTextAppearance(R.style.error);
            edt_heightTextLayout.setError("Enter height");
            response = false;
        } else {
            edt_heightTextLayout.setErrorEnabled(false);
            edt_heightTextLayout.setError(null);
        }


        if (edtWeight.getText().toString().trim().length() == 0)
        {
            edt_weightTextLayout.setErrorEnabled(true);
            edt_weightTextLayout.setErrorTextAppearance(R.style.error);
            edt_weightTextLayout.setError("Enter weight");
            response = false;
        } else {
            edt_weightTextLayout.setErrorEnabled(false);
            edt_weightTextLayout.setError(null);
        }

        if (edtBloodPressure.getText().toString().trim().length() == 0)
        {
            edt_bloodpressure_TextLayout.setErrorEnabled(true);
            edt_bloodpressure_TextLayout.setErrorTextAppearance(R.style.error);
            edt_bloodpressure_TextLayout.setError("Enter blood pressure");
            response = false;
        } else {
            edt_bloodpressure_TextLayout.setErrorEnabled(false);
            edt_bloodpressure_TextLayout.setError(null);
        }


        if (edtBloodPressTo.getText().toString().trim().length() == 0)
        {
            edt_bloodpressureTo_TextLayout.setErrorEnabled(true);
            edt_bloodpressureTo_TextLayout.setErrorTextAppearance(R.style.error);
            edt_bloodpressureTo_TextLayout.setError("Enter blood pressure to");
            response = false;
        } else {
            edt_bloodpressureTo_TextLayout.setErrorEnabled(false);
            edt_bloodpressureTo_TextLayout.setError(null);
        }


        if (edtTemperature.getText().toString().trim().length() == 0)
        {
            edt_tempTextLayout.setErrorEnabled(true);
            edt_tempTextLayout.setErrorTextAppearance(R.style.error);
            edt_tempTextLayout.setError("Enter temperature");
            response = false;
        } else {
            edt_tempTextLayout.setErrorEnabled(false);
            edt_tempTextLayout.setError(null);
        }

        if (edtRespiration.getText().toString().trim().length() == 0)
        {
            edt_respTextLayout.setErrorEnabled(true);
            edt_respTextLayout.setErrorTextAppearance(R.style.error);
            edt_respTextLayout.setError("Enter respiration");
            response = false;
        } else {
            edt_respTextLayout.setErrorEnabled(false);
            edt_respTextLayout.setError(null);
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
