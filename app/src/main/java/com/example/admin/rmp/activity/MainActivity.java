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

import com.example.admin.rmp.patient_registration.General_Information;
import com.example.admin.rmp.R;

public class MainActivity extends AppCompatActivity {

    public static String PATIENT_ID = "", REGISTRATION_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
    }

    private void loadFragment()
    {
        General_Information generalinformation = new General_Information();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout, generalinformation);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {

        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alert_exit, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

        // this is set the view from XML inside AlertDialog
        alert.setTitle("Exit");
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
               finish();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();



    }
}
