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
        txtHeight.setText(String.valueOf(previousRecords.getHeight()));
        txtWeight.setText(String.valueOf(previousRecords.getWeight()));
        txtBp.setText(String.valueOf(previousRecords.getBloodpresure()));
        txtTemperature.setText(String.valueOf(previousRecords.getTempreture()));
        txtRespiration.setText(String.valueOf(previousRecords.getRespiration()));
    }

}
