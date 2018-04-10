package mhu.rmp.vaccination_record;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.activity.PreviousRecordsActivity;
import mhu.rmp.activity.model.PreviousRecords;


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


    private void setData()
    {
        txtDpt.setText(String.valueOf(previousRecords.getDpt()));
        txtBcg.setText(String.valueOf(previousRecords.getBcg()));
        txtMeasles.setText(String.valueOf(previousRecords.getMeasles()));
        txtOpv.setText(String.valueOf(previousRecords.getOpv()));
        txtHepatitis.setText(String.valueOf(previousRecords.getHepatitis()));
        txtTt.setText(String.valueOf(previousRecords.getTtt()));
        txtOther.setText(String.valueOf(previousRecords.getOther()));
    }
}
