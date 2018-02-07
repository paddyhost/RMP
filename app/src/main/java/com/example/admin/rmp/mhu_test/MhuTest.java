package com.example.admin.rmp.mhu_test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.rmp.R;
import com.example.admin.rmp.TestAdviced.TestAdvised;
import com.example.admin.rmp.mhu_test.model.MHU_Test;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.user_login.LoginActivity;
import com.example.admin.rmp.vaccination_record.VaccinationRecord;

public class MhuTest extends Fragment {

    private Toolbar mhu_toolbar;
    private Button btn_save_mhu;
    private TextInputEditText etBloodGlucose, ethemogram, etcreatine,eturea,etsgot,etsgpt;
    private MHU_Test mhuTest;
    private TextInputLayout etblood_glucoseTextLayout,hemogramTextLayout,
            creatineTextLayout,ureaTextLayout,sgotTextLayout,sgptTextLayout;

    private PrefManager prefManager;


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

        initializations(rootview);

        saveClickListner();

        return rootview;
    }

    private void initializations(View rootview)
    {
        mhu_toolbar = (Toolbar) rootview.findViewById(R.id.mhu_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mhu_toolbar);
        mhu_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        setHasOptionsMenu(true);
        prefManager=new PrefManager(getActivity());
        etBloodGlucose = (TextInputEditText)rootview.findViewById(R.id.etblood_glucose);
        ethemogram = (TextInputEditText)rootview.findViewById(R.id.hemogram);
        etcreatine = (TextInputEditText)rootview.findViewById(R.id.creatine);
        eturea = (TextInputEditText)rootview.findViewById(R.id.urea);
        etsgot=(TextInputEditText)rootview.findViewById(R.id.sgot);
        etsgpt=(TextInputEditText)rootview.findViewById(R.id.sgpt);
        btn_save_mhu = (Button) rootview.findViewById(R.id.btn_save_mhu);
        etblood_glucoseTextLayout=(TextInputLayout)rootview.findViewById(R.id.etblood_glucose_TextLayout);
        hemogramTextLayout=(TextInputLayout)rootview.findViewById(R.id.hemogram_TextLayout);
        creatineTextLayout=(TextInputLayout)rootview.findViewById(R.id.creatine_TextLayout);
        ureaTextLayout=(TextInputLayout)rootview.findViewById(R.id.urea_TextLayout);
        sgotTextLayout=(TextInputLayout)rootview.findViewById(R.id.sgot_TextLayout);
        sgptTextLayout=(TextInputLayout)rootview.findViewById(R.id.sgpt_TextLayout);

    }

    private void saveClickListner()
    {
        btn_save_mhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setMHUTestData();
                //if(checkValidation()) {

                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                    TestAdvised testAdvised = TestAdvised.getInstance(mhuTest);
                    fragmentTransaction.replace(R.id.framelayout, testAdvised).addToBackStack(null).commit();
                //}
            }
        });
    }

    private void setMHUTestData()
    {
        mhuTest = new MHU_Test();
        mhuTest.setBloodGlucose(etBloodGlucose.getText().toString());
        mhuTest.setHemogram(ethemogram.getText().toString());
        mhuTest.setCreatine(etcreatine.getText().toString());
        mhuTest.setUrea(eturea.getText().toString());
        mhuTest.setSgot(etsgot.getText().toString());
        mhuTest.setSgpt(etsgpt.getText().toString());
    }

    private boolean checkValidation()
    {
        boolean response=true;

        if (etBloodGlucose.getText().toString().trim().length() == 0)
        {
            etblood_glucoseTextLayout.setErrorEnabled(true);
            etblood_glucoseTextLayout.setErrorTextAppearance(R.style.error);
            etblood_glucoseTextLayout.setError("Enter Blood Glucose");
            response = false;
        } else {
            etblood_glucoseTextLayout.setErrorEnabled(false);
            etblood_glucoseTextLayout.setError(null);
        }


        if (etcreatine.getText().toString().trim().length() == 0)
        {
            creatineTextLayout.setErrorEnabled(true);
            creatineTextLayout.setErrorTextAppearance(R.style.error);
            creatineTextLayout.setError("Enter creatine");
            response = false;
        } else {
            creatineTextLayout.setErrorEnabled(false);
            creatineTextLayout.setError(null);
        }


        if (ethemogram.getText().toString().trim().length() == 0)
        {
            hemogramTextLayout.setErrorEnabled(true);
            hemogramTextLayout.setErrorTextAppearance(R.style.error);
            hemogramTextLayout.setError("Enter hemogram");
            response = false;
        } else {
            hemogramTextLayout.setErrorEnabled(false);
            hemogramTextLayout.setError(null);
        }

        if (etsgot.getText().toString().trim().length() == 0)
        {
            sgotTextLayout.setErrorEnabled(true);
            sgotTextLayout.setErrorTextAppearance(R.style.error);
            sgotTextLayout.setError("Enter sgot");
            response = false;
        } else {
            sgotTextLayout.setErrorEnabled(false);
            sgotTextLayout.setError(null);
        }


        if (etsgpt.getText().toString().trim().length() == 0)
        {
            sgptTextLayout.setErrorEnabled(true);
            sgptTextLayout.setErrorTextAppearance(R.style.error);
            sgptTextLayout.setError("Enter sgpt");
            response = false;
        } else {
            sgptTextLayout.setErrorEnabled(false);
            sgptTextLayout.setError(null);
        }


        if (eturea.getText().toString().trim().length() == 0)
        {
            ureaTextLayout.setErrorEnabled(true);
            ureaTextLayout.setErrorTextAppearance(R.style.error);
            ureaTextLayout.setError("Enter refrred");
            response = false;
        } else {
            ureaTextLayout.setErrorEnabled(false);
            ureaTextLayout.setError(null);
        }

        return  response;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.logout:
                prefManager.setLogOut();
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
