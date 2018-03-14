package com.example.admin.rmp.medical_condition;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TableLayout;


import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.adapter.DoseList_Adapter;
import com.example.admin.rmp.medical_condition.model.Dose;
import com.example.admin.rmp.utils.Utility;

import java.util.ArrayList;


public class Add_Prescription_Fragment extends Fragment {

    private Toolbar addPrescriptionToolbar;
    private RecyclerView doseList;
    private DoseList_Adapter doseAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Dose> doseArrayList;
    private FloatingActionButton btnAddPrescription;

    public Add_Prescription_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add__prescription, container, false);

        initializations(view);
        onClickListeners();


        return view;
    }

    private void initializations(View view)
    {
        addPrescriptionToolbar=(Toolbar)view.findViewById(R.id.add_prescription_toolbar);
        doseList = (RecyclerView) view.findViewById(R.id.dose_list);
        btnAddPrescription=(FloatingActionButton)view.findViewById(R.id.btn_floating_add_prescription);


        doseArrayList = new ArrayList<Dose>();
        doseAdapter = new DoseList_Adapter(doseArrayList, getActivity().getApplicationContext());

        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        doseList.setHasFixedSize(true);
        doseList.setLayoutManager(mLayoutManager);
        doseList.setAdapter(doseAdapter);

    }

    private void onClickListeners()
    {
        addPrescriptionToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        btnAddPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater2 = getActivity().getLayoutInflater();
                View alertLayout = inflater2.inflate(R.layout.dose_dialog_row, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                final Spinner nameOfMedicine = (Spinner) alertLayout.findViewById(R.id.medicin_name_spinner);
                final TextInputEditText doseName = (TextInputEditText) alertLayout.findViewById(R.id.et_prescription_dose);
                final TextInputEditText doseFrequency = (TextInputEditText) alertLayout.findViewById(R.id.et_frequency);
                final TextInputEditText days = (TextInputEditText) alertLayout.findViewById(R.id.et_days);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setCancelable(false);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dose dose = new Dose(doseName.getText().toString(), doseFrequency.getText().toString(), days.getText().toString());
                        doseArrayList.add(dose);
                        doseAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });
    }

}