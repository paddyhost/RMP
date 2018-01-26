package com.example.admin.rmp.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.rmp.patient_registration.General_Information;
import com.example.admin.rmp.R;

public class MainActivity extends AppCompatActivity {

    public static String PATIENT_ID = "", REGISTRATION_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new General_Information());
    }

    private void loadFragment(Fragment fragment) {
        General_Information generalinformation = new General_Information();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout, generalinformation);
        transaction.commit();
    }
}
