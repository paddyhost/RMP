package com.example.admin.rmp.TestAdviced;

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
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.rmp.R;
import com.example.admin.rmp.TestAdviced.apihelper.Web_TestAdviced_Helper;
import com.example.admin.rmp.TestAdviced.model.TestAdvicedModel;
import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.mhu_test.MhuTest;
import com.example.admin.rmp.mhu_test.apihelper.Web_MhuApi_Helper;
import com.example.admin.rmp.mhu_test.model.MHU_Test;
import com.example.admin.rmp.patient_registration.General_Information;
import com.example.admin.rmp.patient_registration.model.PatientRegistration;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.user_login.LoginActivity;

import com.example.admin.rmp.utils.Utility;

import com.example.admin.rmp.vaccination_record.apihelper.Vaccination_ApiHelper;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TestAdvised extends Fragment {

    public static final String MHU_TEST="mhu_test";
    private Toolbar test_toolbar;
    private TextInputEditText etRefrerredTxt,etRmarks,edtTestName;
    private Spinner advisedSpinner;
    private TestAdvicedModel testAdvicedModel;
    private Button submitBtn;
    private TextInputLayout referred_txtTextLayout,remarksTextLayout;
    private PrefManager prefManager;
    private MHU_Test mhuTest;

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
        setHasOptionsMenu(true);

        prefManager=new PrefManager(getActivity());

        test_toolbar = (Toolbar) view.findViewById(R.id.test_toolbar);
        prefManager=new PrefManager(getActivity());
        etRefrerredTxt = (TextInputEditText)view.findViewById(R.id.referred_txt);
        etRmarks = (TextInputEditText)view.findViewById(R.id.remarks);
        edtTestName= (TextInputEditText)view.findViewById(R.id.test_name_txt);
        //advisedSpinner = (Spinner)view.findViewById(R.id.advised_test);
        submitBtn = (Button) view.findViewById(R.id.submit);
        referred_txtTextLayout=(TextInputLayout)view.findViewById(R.id.referred_txt_TextLayout);
        remarksTextLayout=(TextInputLayout)view.findViewById(R.id.remarks_TextLayout);

        ((AppCompatActivity)getActivity()).setSupportActionBar(test_toolbar);
        test_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });
    }

    private void submitClickListener()
    {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdvisedData();

                //if(checkValidation()) {

                    final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");

                    sweetAlertDialog.show();

                    Web_TestAdviced_Helper.webAddMHuTest(getActivity(), testAdvicedModel, new ApiResponseListener() {
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
                                    General_Information general_information = new General_Information();
                                    fragmentTransaction.replace(R.id.framelayout, general_information).addToBackStack(null).commit();
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

     private void setAdvisedData()
    {
        testAdvicedModel.setTestName(edtTestName.getText().toString());
        testAdvicedModel.setRerered(etRefrerredTxt.getText().toString());
        testAdvicedModel.setRemark(etRmarks.getText().toString());
    }

    private boolean checkValidation()
    {
        boolean response=true;

        if (etRefrerredTxt.getText().toString().trim().length() == 0)
        {
            referred_txtTextLayout.setErrorEnabled(true);
            referred_txtTextLayout.setErrorTextAppearance(R.style.error);
            referred_txtTextLayout.setError("Enter refer");
            response = false;
        } else {
            referred_txtTextLayout.setErrorEnabled(false);
            referred_txtTextLayout.setError(null);
        }


        if (etRmarks.getText().toString().trim().length() == 0)
        {
            remarksTextLayout.setErrorEnabled(true);
            remarksTextLayout.setErrorTextAppearance(R.style.error);
            remarksTextLayout.setError("Enter remark");
            response = false;
        } else {
            remarksTextLayout.setErrorEnabled(false);
            remarksTextLayout.setError(null);
        }

      /*  View selectedView = advisedSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (advisedSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select Advised Tests";
                    selectedTextView.setError(errorString);

                } else {
                    selectedTextView.setError(null);
                }
            response = false;
        }

*/
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
}
