package mhu.rmp.vaccination_record;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.patient_previous_history.model.PreviousRecords;


public class VaccinationPreviousRecordsFragment extends Fragment {

    private TextView txtDpt,txtBcg,txtMeasles,txtOpv,txtHepatitis,txtTt,txtOther;
    private PreviousRecords previousRecords;

    public VaccinationPreviousRecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_vaccination_previous_records, container, false);
        previousRecords = PreviousRecordsActivity.previousRecords;
        initializations(view);
        setData();
        return view;
    }

    private void initializations(View view)
    {
        txtDpt=(TextView)view.findViewById(R.id.txt_dpt);
        txtBcg=(TextView)view.findViewById(R.id.txt_bcg);
        txtMeasles=(TextView)view.findViewById(R.id.txt_measles);
        txtOpv=(TextView)view.findViewById(R.id.txt_opv);
        txtHepatitis=(TextView)view.findViewById(R.id.txt_hepatitis);
        txtTt=(TextView)view.findViewById(R.id.txt_tt);
        txtOther=(TextView)view.findViewById(R.id.txt_other);
    }


    private void setData() {
        if (previousRecords.getDpt().equalsIgnoreCase("Y")) {
            txtDpt.setText("Yes");
        } else {
            txtDpt.setText("No");
        }


        if (previousRecords.getBcg().equalsIgnoreCase("Y")) {
            txtBcg.setText("Yes");
        } else {
            txtBcg.setText("No");
        }


        if (previousRecords.getMeasles().equalsIgnoreCase("Y")) {
            txtMeasles.setText("Yes");
        }
        else {
            txtMeasles.setText("No");
        }


        if (previousRecords.getOpv().equalsIgnoreCase("Y"))
        {
            txtOpv.setText("Yes");
        }
        else
        {
            txtOpv.setText("No");
        }


        if(previousRecords.getHepatitis().equalsIgnoreCase("Y")) {
            txtHepatitis.setText("Yes");
        }
        else
        {
            txtHepatitis.setText("No");
        }


        if(previousRecords.getTtt().equalsIgnoreCase("Y")) {
            txtTt.setText("Yes");
        }
        else
        {
            txtTt.setText("No");
        }


            txtOther.setText(String.valueOf(previousRecords.getOther()));

    }
}
