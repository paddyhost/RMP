package mhu.rmp.vital_info;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.patient_previous_history.model.PreviousRecords;


public class VitalInfoPreviousRecordFragment extends Fragment {

    private PreviousRecords previousRecords;
    private TextView txtHeight,txtWeight,txtBp,txtTemperature,txtRespiration;

    public VitalInfoPreviousRecordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vital_info_previous_record, container, false);
        previousRecords= PreviousRecordsActivity.previousRecords;
        initializations(view);
        setData();
        return view;
    }

    private void initializations(View view)
    {
        txtHeight=(TextView)view.findViewById(R.id.txt_height);
        txtWeight=(TextView)view.findViewById(R.id.txt_weight);
        txtBp=(TextView)view.findViewById(R.id.txt_bp);
        txtTemperature=(TextView)view.findViewById(R.id.txt_temperature);
        txtRespiration=(TextView)view.findViewById(R.id.txt_respiration);
    }

    private void setData()
    {

        try
        {
            if(txtHeight.getText().toString().trim().length()<=0 && previousRecords.getHeight().equalsIgnoreCase("0"))
            {
                txtHeight.setText("NA");

            }
            else
            {
                txtHeight.setText(String.valueOf(previousRecords.getHeight()));
            }
        }
        catch(Exception e){}


        try {
            if(txtWeight.getText().toString().trim().length()<=0 && previousRecords.getWeight().equalsIgnoreCase("0")) {
                txtWeight.setText("NA");
            }

            else{
                txtWeight.setText(String.valueOf(previousRecords.getWeight()));
            }
        }
        catch(Exception e){}


        try {

            if(txtBp.getText().toString().trim().length()<=0 && previousRecords.getBloodpresure().equalsIgnoreCase("0")) {
                txtBp.setText("NA");
            }

            else{
                txtBp.setText(String.valueOf(previousRecords.getBloodpresure()));
            }


        }
        catch(Exception e){}

        try {

            if(txtTemperature.getText().toString().trim().length()<=0 && previousRecords.getTempreture().equalsIgnoreCase("0")) {
                txtTemperature.setText("NA");
            }
            else
            {
                txtTemperature.setText(String.valueOf(previousRecords.getTempreture()));
            }
        }
        catch(Exception e){}


        try {

            if(txtRespiration.getText().toString().trim().length()<=0 && previousRecords.getRespiration().equalsIgnoreCase("0")) {
                txtRespiration.setText("NA");
            }
            else
            {
                txtRespiration.setText(String.valueOf(previousRecords.getRespiration()));
            }

        }
        catch(Exception e){}



    }

}
