package com.example.admin.rmp.activity;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.util.Util;
import com.example.admin.rmp.patient_registration.General_Information;
import com.example.admin.rmp.R;
import com.example.admin.rmp.patient_registration.UserVerificationFragment;
import com.example.admin.rmp.utils.Utility;

public class MainActivity extends AppCompatActivity {

    public static String PATIENT_ID = "", REGISTRATION_ID = "",Visit_ID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
    }

    private void loadFragment()
    {
        UserVerificationFragment generalinformation = new UserVerificationFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout, generalinformation);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Utility.closeAppDialog(MainActivity.this);

    }
}
