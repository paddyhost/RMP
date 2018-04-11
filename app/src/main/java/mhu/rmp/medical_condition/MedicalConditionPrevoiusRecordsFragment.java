package mhu.rmp.medical_condition;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.activity.PreviousRecordsActivity;
import mhu.rmp.activity.model.PreviousRecords;
import mhu.rmp.medical_condition.adapter.DoseList_Adapter;

public class MedicalConditionPrevoiusRecordsFragment extends Fragment {

    private static final int DIVIDER_SIZE = 2;
    private TableLayout tableLayout;


    private TextView txtCheifComplaint1,txtCheifComplaint2,txtCheifComplaint3,txtBriefHistory1,
            txtBriefHistory2,txtBriefHistory3,txtDoctorName1,txtDoctorName2,txtDoctorName3,txtPreviousInvestigation,
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
    }

    private void setData()
    {
        if(txtCheifComplaint1.getText().toString().trim().length() == 0) {
                txtCheifComplaint1.setText("Cheif Complaint1: NA");
        }

        else {
                txtCheifComplaint1.setText(String.valueOf(previousRecords.getChiefcomplaints1()));
        }
        txtCheifComplaint2.setText(String.valueOf(previousRecords.getChiefcomplaints2()));
        txtCheifComplaint3.setText(String.valueOf(previousRecords.getChiefcomplaints3()));
        txtBriefHistory1.setText(String.valueOf(previousRecords.getBriefHistory1()));
        txtBriefHistory2.setText(String.valueOf(previousRecords.getBriefHistory2()));
        txtBriefHistory3.setText(String.valueOf(previousRecords.getBriefHistory3()));
        if(previousRecords.getInvestigation().equalsIgnoreCase("Y")) {
            txtPreviousInvestigation.setText("Yes");
        }
        else if(previousRecords.getInvestigation().equalsIgnoreCase("N"))
        {
            txtPreviousInvestigation.setText("No");
        }
        else if(previousRecords.getInvestigation().equalsIgnoreCase("D"))
        {
            txtPreviousInvestigation.setText("Don't Know");
        }
        else
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
        }
        else
        {
            txtTreatmentTaken.setText("NA");
        }

        txtDiagnosys.setText(String.valueOf(previousRecords.getDiagnosys()));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        dose_list.setLayoutManager(linearLayoutManager);
        dose_list.setItemAnimator(new DefaultItemAnimator());
        DoseList_Adapter doseAdapter = new DoseList_Adapter(previousRecords.getDoseArrayList(), getActivity().getApplicationContext());
        dose_list.setAdapter(doseAdapter);


    }

}
