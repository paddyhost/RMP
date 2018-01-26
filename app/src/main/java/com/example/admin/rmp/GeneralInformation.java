package com.example.admin.rmp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GeneralInformation extends Fragment {

    Toolbar customertoolbar;

    public GeneralInformation() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_general_information, container, false);
        customertoolbar = (Toolbar) rootview.findViewById(R.id.customer_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(customertoolbar);
        return rootview;
    }
}
