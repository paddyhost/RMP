package mhu.rmp.patient_previous_history;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mhu.rmp.R;
import mhu.rmp.patient_registration.UserVerificationFragment;


public class CheckPreviousRecordsFragment extends Fragment {

    private Button btnVerifyPatientHistory;
    public CheckPreviousRecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_check_previous_records, container, false);

        btnVerifyPatientHistory=(Button)view.findViewById(R.id.btn_verify_patient_history);


        btnVerifyPatientHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getActivity(),PreviousRecordsActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;
    }


}
