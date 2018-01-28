package com.example.admin.rmp.medical_condition;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.rmp.R;
import com.example.admin.rmp.previous_records.PreviousRecords;

public class MedicalCondition extends Fragment {

    Toolbar medical_toolbar;
    private Button btnsavemedical;
    public MedicalCondition() {
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
        View rootview = inflater.inflate(R.layout.fragment_medical_condition, container, false);
        medical_toolbar = (Toolbar) rootview.findViewById(R.id.medical_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(medical_toolbar);
        medical_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        btnsavemedical = (Button) rootview.findViewById(R.id.btn_save_medical);
        btnsavemedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                PreviousRecords previousRecords= new PreviousRecords();
                fragmentTransaction.replace(R.id.framelayout,previousRecords).addToBackStack(null).commit();
            }
        });
        return rootview;
    }
}
