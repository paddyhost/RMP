package mhu.rmp.patient_registration;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import mhu.rmp.R;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.patient_registration.apihelper.Web_ApiHelper;
import mhu.rmp.patient_registration.model.PatientRegistration;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserVerificationFragment extends Fragment {
    public UserVerificationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_verification, container, false);
        Button btnstart= (Button) view.findViewById(R.id.btnstart);
        Button btnstartwithoutadhar= (Button) view.findViewById(R.id.btnnew);
        btnstartwithoutadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                General_Information generalinformation = General_Information.newInstance(null,null);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout, generalinformation);
                transaction.commit();

            }
        });
        final TextInputEditText uid= (TextInputEditText) view.findViewById(R.id.et_uid);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
                sweetAlertDialog.setTitleText("Please wait");
                sweetAlertDialog.show();


                if (uid.getText().toString().trim().length() == 9 || uid.getText().toString().trim().length() == 16 || uid.getText().toString().trim().length() == 20){

                    final PatientRegistration patientRegistration = new PatientRegistration();
                    Web_ApiHelper.isPatientExist(getActivity(), patientRegistration, new ApiResponseListener() {
                        @Override
                        public void onSuccess(String message) {
                            if (message.equalsIgnoreCase("PATIENT_EXISTS")) {
                                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                sweetAlertDialog.setTitleText("PATIENT_EXISTS");
                                sweetAlertDialog.setConfirmText("OK");
                                sweetAlertDialog.dismiss();

                                General_Information generalinformation = General_Information.newInstance(patientRegistration,uid.getText().toString());
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.framelayout, generalinformation);
                                transaction.commit();

                                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                    }
                                });

                            } else {
                                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                sweetAlertDialog.setTitleText("PATIENT NOT EXISTS");
                                sweetAlertDialog.setConfirmText("OK");

                              General_Information generalinformation = General_Information.newInstance(null,uid.getText().toString());
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.framelayout, generalinformation);
                                transaction.commit();
                            }

                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            });

                        }

                        @Override
                        public void onError(String message) {
                            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("Error !!");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            });
                            sweetAlertDialog.setConfirmText(message);
                        }
                    }, uid.getText().toString());
                }
                else
                {
                    sweetAlertDialog.dismiss();
                    Toast.makeText(getActivity(),"Please enter valid UID",Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;

    }


}
