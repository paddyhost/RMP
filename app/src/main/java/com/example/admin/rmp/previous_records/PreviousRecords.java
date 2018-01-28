package com.example.admin.rmp.previous_records;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.rmp.R;

public class PreviousRecords extends Fragment {

    private Toolbar prerecord_toolbar;

    public PreviousRecords() {
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
        View rootview = inflater.inflate(R.layout.fragment_previous_records, container, false);
        prerecord_toolbar = (Toolbar) rootview.findViewById(R.id.prerecord_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(prerecord_toolbar);
        prerecord_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return rootview;
    }

}
