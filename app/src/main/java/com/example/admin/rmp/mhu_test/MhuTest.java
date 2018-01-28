package com.example.admin.rmp.mhu_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.admin.rmp.R;
import com.example.admin.rmp.TestAdviced.TestAdvised;
import com.example.admin.rmp.mhu_test.model.MHU_Test;
import com.example.admin.rmp.vaccination_record.VaccinationRecord;

public class MhuTest extends Fragment {

    private Toolbar mhu_toolbar;
    private Button btn_save_mhu;
    private TextInputEditText etBloodGlucose, ethemogram, etcreatine,eturea,etsgot,etsgpt;
    private MHU_Test mhuTest;


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
        etBloodGlucose = (TextInputEditText)rootview.findViewById(R.id.etblood_glucose);
        ethemogram = (TextInputEditText)rootview.findViewById(R.id.hemogram);
        etcreatine = (TextInputEditText)rootview.findViewById(R.id.creatine);
        eturea = (TextInputEditText)rootview.findViewById(R.id.urea);
        etsgot=(TextInputEditText)rootview.findViewById(R.id.sgot);
        etsgpt=(TextInputEditText)rootview.findViewById(R.id.sgpt);
        btn_save_mhu = (Button) rootview.findViewById(R.id.btn_save_mhu);

    }

    private void saveClickListner()
    {
        btn_save_mhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setMHUTestData();

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                TestAdvised testAdvised= TestAdvised.getInstance(mhuTest);
                fragmentTransaction.replace(R.id.framelayout,testAdvised).addToBackStack(null).commit();
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
}
