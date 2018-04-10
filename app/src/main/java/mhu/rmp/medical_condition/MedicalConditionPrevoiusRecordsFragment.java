package mhu.rmp.medical_condition;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.activity.PreviousRecordsActivity;
import mhu.rmp.activity.model.PreviousRecords;

public class MedicalConditionPrevoiusRecordsFragment extends Fragment {

    private static final int DIVIDER_SIZE = 2;
    private TableLayout tableLayout;


    private TextView txtCheifComplaint1,txtCheifComplaint2,txtCheifComplaint3,txtBriefHistory1,
            txtBriefHistory2,txtBriefHistory3,txtDoctorName1,txtDoctorName2,txtDoctorName3,txtPreviousInvestigation,
            txtTreatmentTaken,txtDiagnosys,txtMedicineName,txtFrequency,txtDays;

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

      /*  TableRow tr_head = new TableRow(getActivity());
        //tr_head.setId(10+count);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        TextView medicine_name = new TextView(getActivity());

        //medicine_name.setId(20);
        medicine_name.setText("Medicine Name");
        medicine_name.setTextColor(Color.WHITE);
        medicine_name.setPadding(5, 5, 5, 5);
        tr_head.addView(medicine_name);// add the column to the table row here


        TextView frequency = new TextView(getActivity());
        //frequency.setId(30);
        frequency.setText("Frequency");
        frequency.setTextColor(Color.WHITE);
        frequency.setPadding(5, 5, 5, 5);
        tr_head.addView(frequency);// add the column to the table row here


        TextView days = new TextView(getActivity());
        //days.setId(40+count);
        days.setText("Days");
        days.setTextColor(Color.WHITE);
        days.setPadding(5, 5, 5, 5);
        tr_head.addView(days);// add the column to the table row here


        tableLayout.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        for(int i = 0; i <tableLayout.getChildCount(); i++) {

            TableRow tr = new TableRow(getActivity());
            //tr_head.setId(10+count);
            tr.setBackgroundColor(Color.GRAY);
            tr.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));


            TextView txt_medicine_name = new TextView(getActivity());

            //medicine_name.setId(20);
            //txt_medicine_name.setText("Medicine Name");
            txt_medicine_name.setText(String.valueOf(previousRecords.getName()));
            txt_medicine_name.setTextColor(Color.WHITE);
            txt_medicine_name.setPadding(5, 5, 5, 5);
            tr.addView(txt_medicine_name);// add the column to the table row here


            TextView txt_frequency = new TextView(getActivity());
            //frequency.setId(30);
            //txt_frequency.setText("Frequency");
            txt_frequency.setText(String.valueOf(previousRecords.getFrequency()));
            txt_frequency.setTextColor(Color.WHITE);
            txt_frequency.setPadding(5, 5, 5, 5);
            tr.addView(txt_frequency);// add the column to the table row here


            TextView txt_days = new TextView(getActivity());
            //days.setId(40+count)
            // txt_days.setText("Days");
            txt_days.setText(String.valueOf(previousRecords.getDays()));
            txt_days.setTextColor(Color.WHITE);
            txt_days.setPadding(5, 5, 5, 5);
            tr.addView(txt_days);// add the column to the table row here



            tableLayout.addView(tr, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.FILL_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));}

*/

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
        tableLayout=(TableLayout)view.findViewById(R.id.table_layout);
        txtMedicineName=(TextView)view.findViewById(R.id.txt_medicine_name);
        txtFrequency=(TextView)view.findViewById(R.id.txt_frequency);
        txtDays=(TextView)view.findViewById(R.id.txt_days);
    }

    private void setData()
    {
        txtCheifComplaint1.setText(String.valueOf(previousRecords.getChiefcomplaints1()));
        txtCheifComplaint2.setText(String.valueOf(previousRecords.getChiefcomplaints2()));
        txtCheifComplaint3.setText(String.valueOf(previousRecords.getChiefcomplaints3()));
        txtBriefHistory1.setText(String.valueOf(previousRecords.getBriefHistory1()));
        txtBriefHistory2.setText(String.valueOf(previousRecords.getBriefHistory2()));
        txtBriefHistory3.setText(String.valueOf(previousRecords.getBriefHistory3()));
        txtPreviousInvestigation.setText(String.valueOf(previousRecords.getInvestigation()));
        txtTreatmentTaken.setText(String.valueOf(previousRecords.getTratementtaken()));
        txtDiagnosys.setText(String.valueOf(previousRecords.getDiagnosys()));
        /*txtMedicineName.setText(String.valueOf(previousRecords.getName()));
        txtFrequency.setText(String.valueOf(previousRecords.getFrequency()));
        txtDays.setText(String.valueOf(previousRecords.getDays()));*/
    }

}
