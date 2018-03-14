package com.example.admin.rmp.mhu_test;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admin.rmp.R;
import com.example.admin.rmp.TestAdviced.TestAdvised;
import com.example.admin.rmp.mhu_test.model.MHU_Test;
import com.example.admin.rmp.pref_manager.PrefManager;
import com.example.admin.rmp.user_login.LoginActivity;
import com.example.admin.rmp.utils.Utility;

import org.w3c.dom.Text;

public class MhuTest extends Fragment {

    private Toolbar mhu_toolbar;
    private Spinner completeBloodCountHomogramSpinner,liverFunctionSpinner,kidneyFunctionTestSpinner,
            lipidProfileSpinner,glucoseProfileSpinner,urineExaminationSpinner;
    private TextInputEditText edtBloodReferenceValue,edtBloodReading,edtLiverFunctionReferenceValue,edtKidneyFunctionTestReferenceValue,
            edtKidneyFunctionTestReading,edtLiverFunctionReading,edtLipidProfileReferenceValue,edtLipidProfileReading,edtGlucoseProfileReferenceValue
            ,edtGlucoseProfileReading,edtWidalReferenceValue,edtWidalReading,edtTyphidotReferenceValue,edtTyphidotReading,
            edtMalariaSerologyReferenceValue,edtMalariaSerologyReading,edtRafactorReferenceValue,
            edtRaFactorReading,edtHbsagReferenceValue,edtHbsagReading,edtUrineExaminationReferenceValue,
            edtUrineExaminationReading,edtCrpReferenceValue,edtCrpReading,edtAntiHcvReferenceValue,
            edtAntiHcvReading,edtHivReferenceValue,edtHivReading,edtVdrlReferenceValue,edtVdrlReading,
            edtGctReferenceValue,edtGctReading,edtAboRhReferenceValue,edtAboRhReading;

    private TextView txtWidal,txtTyphidot,txtMalariaSerology,txtRaFactor,txtHbsag,txtCrp,
            txtAntiHcv,txtHiv,txtVdrl,txtGct,txtAborh;

    private Button btnSaveMhu;
    private MHU_Test mhuTest;

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

    private void initializations(View view)
    {
        mhu_toolbar = (Toolbar) view.findViewById(R.id.mhu_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mhu_toolbar);
        mhu_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });

        setHasOptionsMenu(true);

        prefManager=new PrefManager(getActivity());

        completeBloodCountHomogramSpinner=(Spinner)view.findViewById(R.id.complete_blood_count_homogram_spinner);
        edtBloodReferenceValue=(TextInputEditText)view.findViewById(R.id.blood_reference_value);
        edtBloodReading=(TextInputEditText)view.findViewById(R.id.blood_reading);

        liverFunctionSpinner=(Spinner)view.findViewById(R.id.liver_function_spinner);
        edtLiverFunctionReferenceValue=(TextInputEditText)view.findViewById(R.id.liver_function_reference_value);
        edtLiverFunctionReading=(TextInputEditText)view.findViewById(R.id.liver_function_reading);

        kidneyFunctionTestSpinner=(Spinner)view.findViewById(R.id.kidney_function_test_spinner);
        edtKidneyFunctionTestReferenceValue=(TextInputEditText)view.findViewById(R.id.kidney_function_test_reference_value);
        edtKidneyFunctionTestReading=(TextInputEditText)view.findViewById(R.id.kidney_function_test_reading);

        lipidProfileSpinner=(Spinner)view.findViewById(R.id.lipid_profile_spinner);
        edtLipidProfileReferenceValue=(TextInputEditText)view.findViewById(R.id.lipid_profile_reference_value);
        edtLipidProfileReading=(TextInputEditText)view.findViewById(R.id.lipid_profile_reading);

        glucoseProfileSpinner=(Spinner)view.findViewById(R.id.glucose_profile_spinner);
        edtGlucoseProfileReferenceValue=(TextInputEditText)view.findViewById(R.id.glucose_profile_reference_value);
        edtGlucoseProfileReading=(TextInputEditText)view.findViewById(R.id.glucose_profile_reading);

        txtWidal=(TextView)view.findViewById(R.id.widal);
        edtWidalReferenceValue=(TextInputEditText)view.findViewById(R.id.widal_reference_value);
        edtWidalReading=(TextInputEditText)view.findViewById(R.id.widal_reading);

        txtTyphidot=(TextView)view.findViewById(R.id.typhidot);
        edtTyphidotReferenceValue=(TextInputEditText)view.findViewById(R.id.typhidot_reference_value);
        edtTyphidotReading=(TextInputEditText)view.findViewById(R.id.typhidot_reading);

        txtMalariaSerology=(TextView)view.findViewById(R.id.malaria_serology);
        edtMalariaSerologyReferenceValue=(TextInputEditText)view.findViewById(R.id.malaria_serology_reference_value);
        edtMalariaSerologyReading=(TextInputEditText)view.findViewById(R.id.malaria_serology_reading);

        txtRaFactor=(TextView)view.findViewById(R.id.ra_factor);
        edtRafactorReferenceValue=(TextInputEditText)view.findViewById(R.id.ra_factor_reference_value);
        edtRaFactorReading=(TextInputEditText)view.findViewById(R.id.ra_factor_reading);

        txtHbsag=(TextView)view.findViewById(R.id.hbsag);
        edtHbsagReferenceValue=(TextInputEditText)view.findViewById(R.id.hbsag_reference_value);
        edtHbsagReading=(TextInputEditText)view.findViewById(R.id.hbsag_reading);

        urineExaminationSpinner=(Spinner)view.findViewById(R.id.urine_examination_spinner);
        edtUrineExaminationReferenceValue=(TextInputEditText)view.findViewById(R.id.urine_examination_reference_value);
        edtUrineExaminationReading=(TextInputEditText)view.findViewById(R.id.urine_examination_reading);

        txtCrp=(TextView)view.findViewById(R.id.crp);
        edtCrpReferenceValue=(TextInputEditText)view.findViewById(R.id.crp_reference_value);
        edtCrpReading=(TextInputEditText)view.findViewById(R.id.crp_reading);

        txtAntiHcv=(TextView)view.findViewById(R.id.anti_hcv);
        edtAntiHcvReferenceValue=(TextInputEditText)view.findViewById(R.id.anti_hcv_reference_value);
        edtAntiHcvReading=(TextInputEditText)view.findViewById(R.id.anti_hcv_reading);

        txtHiv=(TextView)view.findViewById(R.id.hiv);
        edtHivReferenceValue=(TextInputEditText)view.findViewById(R.id.hiv_reference_value);
        edtHivReading=(TextInputEditText)view.findViewById(R.id.hiv_reading);

        txtVdrl=(TextView)view.findViewById(R.id.vdrl);
        edtVdrlReferenceValue=(TextInputEditText)view.findViewById(R.id.vdrl_reference_value);
        edtVdrlReading=(TextInputEditText)view.findViewById(R.id.vdrl_reading);

        txtGct=(TextView)view.findViewById(R.id.gct);
        edtGctReferenceValue=(TextInputEditText)view.findViewById(R.id.gct_reference_value);
        edtGctReading=(TextInputEditText)view.findViewById(R.id.gct_reading);

        txtAborh=(TextView)view.findViewById(R.id.aborh);
        edtAboRhReferenceValue=(TextInputEditText)view.findViewById(R.id.abo_rh_reference_value);
        edtAboRhReading=(TextInputEditText)view.findViewById(R.id.abo_rh_reading);

        btnSaveMhu=(Button)view.findViewById(R.id.btn_save);
    }

    private void saveClickListner()
    {
        btnSaveMhu.setOnClickListener(new View.OnClickListener() {
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
       /* mhuTest.setBloodGlucose(etBloodGlucose.getText().toString());
        mhuTest.setHemogram(ethemogram.getText().toString());
        mhuTest.setCreatine(etcreatine.getText().toString());
        mhuTest.setUrea(eturea.getText().toString());
        mhuTest.setSgot(etsgot.getText().toString());
        mhuTest.setSgpt(etsgpt.getText().toString());*/
    }

    /*private boolean checkValidation()
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
*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (item.getItemId())
        {
            case R.id.logout:
                prefManager.setLogOut();
                Intent i= new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
