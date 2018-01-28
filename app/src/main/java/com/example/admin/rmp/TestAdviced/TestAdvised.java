package com.example.admin.rmp.TestAdviced;

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
import android.widget.Spinner;

import com.example.admin.rmp.R;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.mhu_test.MhuTest;
import com.example.admin.rmp.mhu_test.apihelper.Web_MhuApi_Helper;
import com.example.admin.rmp.mhu_test.model.MHU_Test;
import com.example.admin.rmp.patient_registration.General_Information;
import com.example.admin.rmp.patient_registration.model.PatientRegistration;
import com.example.admin.rmp.vaccination_record.apihelper.Vaccination_ApiHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TestAdvised extends Fragment {

    public static final String MHU_TEST="mhu_test";
    private Toolbar test_toolbar;
    private TextInputEditText etRefrerredTxt,etRmarks;
    private Spinner advisedSpinner;
    private MHU_Test mhuTest;
    private Button submitBtn;

    public TestAdvised() {
        // Required empty public constructor
    }

    public static TestAdvised getInstance(MHU_Test mhuTest) {
        TestAdvised fragment = new TestAdvised();
        Bundle args = new Bundle();
        args.putParcelable(MHU_TEST, mhuTest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mhuTest = getArguments().getParcelable(MHU_TEST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_test_advised, container, false);

        initialization(rootview);

        submitClickListener();

        return rootview;
    }

    private void initialization(View view)
    {
        test_toolbar = (Toolbar) view.findViewById(R.id.test_toolbar);
        etRefrerredTxt = (TextInputEditText)view.findViewById(R.id.referred_txt);
        etRmarks = (TextInputEditText)view.findViewById(R.id.remarks);
        advisedSpinner = (Spinner)view.findViewById(R.id.advised_test);
        submitBtn = (Button) view.findViewById(R.id.submit);

        ((AppCompatActivity)getActivity()).setSupportActionBar(test_toolbar);
        test_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void submitClickListener()
    {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdvisedData();

                final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait");

                sweetAlertDialog.show();

                Web_MhuApi_Helper.webAddMHUTest(getActivity(), mhuTest, new ApiResponseListener() {
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
                                General_Information general_information= new General_Information();
                                fragmentTransaction.replace(R.id.framelayout,general_information).addToBackStack(null).commit();
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

    private void setAdvisedData()
    {
        mhuTest.setAdvised(advisedSpinner.getSelectedItem().toString());
        mhuTest.setReferred(etRefrerredTxt.getText().toString());
        mhuTest.setRemark(etRmarks.getText().toString());
    }

}
