package com.example.admin.rmp.TestAdviced;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.rmp.R;


public class TestAdvicedPreviousRecordsFragment extends Fragment {

    public TestAdvicedPreviousRecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_test_adviced_previous_records, container, false);
        return view;
    }


}
