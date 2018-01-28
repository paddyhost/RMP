package com.example.admin.rmp.mhu_test;

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
import com.example.admin.rmp.vaccination_record.VaccinationRecord;

public class MhuTest extends Fragment {

    private Toolbar mhu_toolbar;
    private Button btn_save_mhu;

    public MhuTest() {
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
        View rootview = inflater.inflate(R.layout.fragment_mhu_test, container, false);
        mhu_toolbar = (Toolbar) rootview.findViewById(R.id.mhu_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mhu_toolbar);
        mhu_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        btn_save_mhu = (Button) rootview.findViewById(R.id.btn_save_mhu);
        btn_save_mhu.setOnClickListener(new View.OnClickListener() {
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
