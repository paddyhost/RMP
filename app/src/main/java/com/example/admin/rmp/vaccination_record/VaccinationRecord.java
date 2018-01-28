package com.example.admin.rmp.vaccination_record;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.rmp.R;
import com.example.admin.rmp.mhu_test.MhuTest;

public class VaccinationRecord extends Fragment {

    private Toolbar vaccination_toolbar;
    private Button btn_save_vaccination;

    public VaccinationRecord() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_vaccination_record, container, false);
        vaccination_toolbar = (Toolbar) rootview.findViewById(R.id.vaccination_toolbar);
        vaccination_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        btn_save_vaccination = (Button) rootview.findViewById(R.id.btn_save_vaccination);
        btn_save_vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                MhuTest mhuTest= new MhuTest();
                fragmentTransaction.replace(R.id.framelayout,mhuTest).addToBackStack(null).commit();
            }
        });
        return rootview;
    }
}
