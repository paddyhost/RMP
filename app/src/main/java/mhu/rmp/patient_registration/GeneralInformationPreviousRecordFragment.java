package mhu.rmp.patient_registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mhu.rmp.R;


public class GeneralInformationPreviousRecordFragment extends Fragment {



    public GeneralInformationPreviousRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_general_information_previous_record, container, false);


        return view;
    }

}
