package com.example.admin.rmp.vital_info;

import android.content.Context;
import android.net.Uri;
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

import com.example.admin.rmp.R;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.vital_info.apihelper.WebVital_Helper;
import com.example.admin.rmp.vital_info.model.Vital_Info;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Vital_Information extends Fragment {

    Toolbar toolbarvital;
    private Button btnSaveVital;
    private TextInputEditText edtHeight,edtWeight, edtBloodPressure, edtTemperature, edtRespiration,
                            edtBloodPressTo;
    private Vital_Info vitalInfo;

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
        toolbarvital = (Toolbar) rootview.findViewById(R.id.vital_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbarvital);
        btnSaveVital = (Button)rootview.findViewById(R.id.btn_save_vital);
        edtHeight = (TextInputEditText)rootview.findViewById(R.id.edt_height);
        edtWeight = (TextInputEditText)rootview.findViewById(R.id.edt_weight);
        edtBloodPressure = (TextInputEditText)rootview.findViewById(R.id.edt_bloodpressure);
        edtTemperature = (TextInputEditText)rootview.findViewById(R.id.edt_temp);
        edtRespiration = (TextInputEditText)rootview.findViewById(R.id.edt_resp);
        edtBloodPressTo = (TextInputEditText)rootview.findViewById(R.id.edt_bloodpressure_to);
     }

    private void toolbarClickListnere()
    {
        toolbarvital.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void saveVitalClickListener()
    {
        btnSaveVital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setVitalInfo();
                final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait");

                sweetAlertDialog.show();

                WebVital_Helper.webAddVitalInfo(getActivity(), vitalInfo, new ApiResponseListener() {
                    @Override
                    public void onSuccess(String message) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText(message);
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

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
}
