package mhu.rmp.vaccination_record;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mhu.rmp.R;


public class VaccinationPreviousRecordsFragment extends Fragment {

    public VaccinationPreviousRecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vaccination_previous_records, container, false);
        return view;
    }


}
