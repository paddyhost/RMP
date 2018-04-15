package mhu.rmp.medical_condition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.patient_previous_history.model.PreviousRecords;
import mhu.rmp.medical_condition.adapter.DoseList_Adapter;

public class MedicalConditionPrevoiusRecordsFragment extends Fragment {

    private TextView txtCheifComplaint1,txtCheifComplaint2,txtCheifComplaint3,txtBriefHistory1,
            txtBriefHistory2,txtBriefHistory3,txtDoctorName1,txtDoctorName2,txtDoctorName3,txtHosptialName,txtPreviousInvestigation,
            txtTreatmentTaken,txtDiagnosys;
    RecyclerView dose_list;

    private PreviousRecords previousRecords;

    public MedicalConditionPrevoiusRecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medication_condition_prevoius_records, container, false);
        previousRecords = PreviousRecordsActivity.previousRecords;
        initializations(view);
        setData();


        return view;
    }


    private  void initializations(View view)
    {
        txtCheifComplaint1=(TextView)view.findViewById(R.id.txt_cheif_complaint1);
        txtCheifComplaint2=(TextView)view.findViewById(R.id.txt_cheif_complaint2);
        txtCheifComplaint3=(TextView)view.findViewById(R.id.txt_cheif_complaint3);
        txtBriefHistory1=(TextView)view.findViewById(R.id.txt_brief_history1);
        txtBriefHistory2=(TextView)view.findViewById(R.id.txt_brief_history2);
        txtBriefHistory3=(TextView)view.findViewById(R.id.txt_brief_history3);
        txtDoctorName1=(TextView)view.findViewById(R.id.txt_doctor_name1);
        txtDoctorName2=(TextView)view.findViewById(R.id.txt_doctor_name2);
        txtDoctorName3=(TextView)view.findViewById(R.id.txt_doctor_name3);
        txtPreviousInvestigation=(TextView)view.findViewById(R.id.txt_previous_investigation);
        txtTreatmentTaken=(TextView)view.findViewById(R.id.txt_treatment_taken);
        txtDiagnosys=(TextView)view.findViewById(R.id.txt_diagnosys);
        dose_list=(RecyclerView) view.findViewById(R.id.dose_list);

        txtHosptialName=(TextView)view.findViewById(R.id.txt_previous_hospital_name);
    }

    private void setData()
    {

        if(previousRecords.getDrname1().equalsIgnoreCase("") && txtDoctorName1.getText().toString().trim().length()<=0)
        {
            txtDoctorName1.setText("NA");
        }
        else
        {
            txtDoctorName1.setText(String.valueOf(previousRecords.getDrname1()));
        }

        if(previousRecords.getDrname2().equalsIgnoreCase("") && txtDoctorName2.getText().toString().trim().length()<=0)
        {
            txtDoctorName2.setText("NA");
        }

        else {
            txtDoctorName2.setText(String.valueOf(previousRecords.getDrname2()));
        }


        if(previousRecords.getDrname3().equalsIgnoreCase("") && txtDoctorName3.getText().toString().trim().length()<=0)
        {
            txtDoctorName3.setText("NA");
        }
        else {
            txtDoctorName3.setText(String.valueOf(previousRecords.getDrname3()));
        }



        if(previousRecords.getHospitalname().equalsIgnoreCase("") && txtHosptialName.getText().toString().trim().length()<=0)
        {
            txtHosptialName.setText("NA");
        }
        else
        {
            txtHosptialName.setText(String.valueOf(previousRecords.getHospitalname()));
        }

        if(previousRecords.getChiefcomplaints1().equalsIgnoreCase("") && txtCheifComplaint1.getText().toString().trim().length()<=0)
        {
            txtCheifComplaint1.setText("NA");
        }
        else
        {
            txtCheifComplaint1.setText(String.valueOf(previousRecords.getChiefcomplaints1()));
        }


        if(previousRecords.getChiefcomplaints2().equalsIgnoreCase("") && txtCheifComplaint2.getText().toString().trim().length()<=0)
        {
            txtCheifComplaint2.setText("NA");
        }
        else
        {
            txtCheifComplaint2.setText(String.valueOf(previousRecords.getChiefcomplaints2()));
        }


        if(previousRecords.getChiefcomplaints3().equalsIgnoreCase("") && txtCheifComplaint3.getText().toString().trim().length()<=0)
        {
            txtCheifComplaint3.setText("NA");
        }
        else
        {
            txtCheifComplaint3.setText(String.valueOf(previousRecords.getChiefcomplaints3()));
        }



        if(previousRecords.getBriefHistory1().equalsIgnoreCase("") && txtBriefHistory1.getText().toString().trim().length()<=0)
        {
            txtBriefHistory1.setText("NA");
        }
        else
        {
            txtBriefHistory1.setText(String.valueOf(previousRecords.getBriefHistory1()));
        }

        if(previousRecords.getBriefHistory2().equalsIgnoreCase("") && txtBriefHistory2.getText().toString().trim().length()<=0)
        {
            txtBriefHistory2.setText("NA");
        }
        else
        {
            txtBriefHistory2.setText(String.valueOf(previousRecords.getBriefHistory2()));
        }


        if(previousRecords.getBriefHistory3().equalsIgnoreCase("") && txtBriefHistory3.getText().toString().trim().length()<=0)
        {
            txtBriefHistory3.setText("NA");
        }
        else
        {
            txtBriefHistory3.setText(String.valueOf(previousRecords.getBriefHistory3()));
        }


        if(previousRecords.getInvestigation().equalsIgnoreCase("Y")) {
            txtPreviousInvestigation.setText("Yes");
        } else if(previousRecords.getInvestigation().equalsIgnoreCase("N"))
        {
            txtPreviousInvestigation.setText("No");
        }else if(previousRecords.getInvestigation().equalsIgnoreCase("D"))
        {
            txtPreviousInvestigation.setText("Don't Know");
        }else
        {
            txtPreviousInvestigation.setText("NA");
        }


        if(previousRecords.getTratementtaken().equalsIgnoreCase("Y")) {
            txtTreatmentTaken.setText("Yes");
        }
        else if(previousRecords.getTratementtaken().equalsIgnoreCase("N"))
        {
            txtTreatmentTaken.setText("No");
        }
        else if(previousRecords.getTratementtaken().equalsIgnoreCase("D"))
        {
            txtTreatmentTaken.setText("Don't Know");
        }else
        {
            txtTreatmentTaken.setText("NA");
        }



        if(previousRecords.getDiagnosys().equalsIgnoreCase("") && txtDiagnosys.getText().toString().trim().length()<=0)
        {
            txtDiagnosys.setText("NA");
        }
        else
        {
            txtDiagnosys.setText(String.valueOf(previousRecords.getDiagnosys()));
        }


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        dose_list.setLayoutManager(linearLayoutManager);
        dose_list.setItemAnimator(new DefaultItemAnimator());
        DoseList_Adapter doseAdapter = new DoseList_Adapter(previousRecords.getDoseArrayList(), getActivity().getApplicationContext());
        dose_list.setAdapter(doseAdapter);
    }

}
