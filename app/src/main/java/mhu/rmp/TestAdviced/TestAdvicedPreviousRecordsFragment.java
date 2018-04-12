package mhu.rmp.TestAdviced;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.patient_previous_history.model.PreviousRecords;


public class TestAdvicedPreviousRecordsFragment extends Fragment {

    private TextView txtTestName,txtReferred,txtRemarks;
    private PreviousRecords previousRecords;


    public TestAdvicedPreviousRecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_test_adviced_previous_records, container, false);
        previousRecords= PreviousRecordsActivity.previousRecords;
        initializations(view);
        setData();

        return view;
    }

    private void initializations(View view)
    {
        txtTestName=(TextView)view.findViewById(R.id.test_name);
        txtReferred=(TextView)view.findViewById(R.id.txt_referred);
        txtRemarks=(TextView)view.findViewById(R.id.txt_remarks);
    }

    private void setData()
    {
        txtTestName.setText(String.valueOf(previousRecords.getAdviced()));
        txtReferred.setText(String.valueOf(previousRecords.getFerered()));
        txtRemarks.setText(String.valueOf(previousRecords.getRemark()));
    }

}
