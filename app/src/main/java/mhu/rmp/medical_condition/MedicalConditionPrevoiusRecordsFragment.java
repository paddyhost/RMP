package mhu.rmp.medical_condition;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import mhu.rmp.R;


public class MedicalConditionPrevoiusRecordsFragment extends Fragment {

    private static final int DIVIDER_SIZE = 2;
    private TableLayout tableLayout;

    public MedicalConditionPrevoiusRecordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_medication_condition_prevoius_records, container, false);

       /* tableLayout=(TableLayout)view.findViewById(R.id.table_layout);


            TableRow tr_head = new TableRow(getActivity());
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

                    //count++;
        //}*/
        return view;
    }





}
