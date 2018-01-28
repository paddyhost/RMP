package com.example.admin.rmp.medical_condition;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.adapter.AdapterDiagnosys;
import com.example.admin.rmp.previous_records.PreviousRecords;

import java.util.ArrayList;
import java.util.List;

public class MedicalCondition extends Fragment {

    Toolbar medical_toolbar;
    private Button btnsavemedical;
    ListView listView;
    List<String> names= new ArrayList<String>();
    private TextView diagnosys;
    public MedicalCondition() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.fragment_medical_condition, container, false);
        medical_toolbar = (Toolbar) rootview.findViewById(R.id.medical_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(medical_toolbar);
        medical_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        btnsavemedical = (Button) rootview.findViewById(R.id.btn_save_medical);
        btnsavemedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                PreviousRecords previousRecords= new PreviousRecords();
                fragmentTransaction.replace(R.id.framelayout,previousRecords).addToBackStack(null).commit();
            }
        });

        diagnosys = (TextView) rootview.findViewById(R.id.diagnosys);
        diagnosys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog(inflater);
            }
        });
        return rootview;
    }

    private List<String> diagnosyslist(){
        List<String> nameList = new ArrayList<>();
        nameList.add("Childhood Obesity");
        nameList.add("Constipation");
        nameList.add("Asthma (Pediatric)");
        nameList.add("Headache, Migraine");
        nameList.add("Substance Use Disorders");
        nameList.add("Depression");
        nameList.add("Hypertension");
        nameList.add("Food Allergy ");
        nameList.add("Anxiety Disorder");
        nameList.add("Speech Defects");
        return nameList;
    }

    public void showChangeLangDialog(LayoutInflater inflater) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());

        final View dialogView = inflater.inflate(R.layout.customdialog_diagnosys, null);
        dialogBuilder.setView(dialogView);

        listView= (ListView) dialogView.findViewById(R.id.listview_diagnosys);
        List<String> nameList= diagnosyslist();
        AdapterDiagnosys adapter= new AdapterDiagnosys(getActivity(), nameList);
        listView.setAdapter(adapter);

        dialogBuilder.setTitle("Select Diagnosys");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
