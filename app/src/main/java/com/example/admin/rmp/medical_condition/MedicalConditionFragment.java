package com.example.admin.rmp.medical_condition;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.adapter.AdapterDiagnosys;
import com.example.admin.rmp.medical_condition.model.Diagnosys;
import com.example.admin.rmp.previous_records.PreviousRecords;

import java.util.ArrayList;
import java.util.List;

import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.medical_condition.apihelper.Web_Medical_ApiHelper;
import com.example.admin.rmp.medical_condition.model.Medical_Conditions;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MedicalConditionFragment extends Fragment {

    Toolbar medical_toolbar;
    private TextInputEditText etComplaint1, etComplaint2, etComplaint3,
            etBreifHistory1, etBreifHistory2, etBreifHistory3;
    private RadioGroup investigationGroup, treatmentGroup, improvementGroup;
    private RadioButton BtnInvestigationYes,BtnInvestigationNo,BtnInvestigationDontKnow,
            BtnTreatmentYes,BtnTreatmentNo,BtnTreatmentDontKnow,
            BtnImprovementYes,BtnImprovementNo,BtnImprovementDontKnow;
    private Button btnAddPrescrption,btnSaveMedical;
    private Medical_Conditions medicalCondition;
    private String investigationSelected ="", treatmentSelected = "", improvementSelected ="";

    ListView listView;
    private TextView diagnosys;
    AdapterDiagnosys adapter;
    ArrayList<Diagnosys> nameList;
    AlertDialog.Builder dialogBuilder;
    View dialogView;

    public MedicalConditionFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_medical_condition, container, false);

        initialization(rootview,inflater);

        saveMedicalClickListener();

        investigationRadioGrpListner();

        treatmentRadioGrpListner();

        improvementRadioGrpListner();

        DiagnosysClickListener(inflater);

        return rootview;
    }

    private void initialization(View view, LayoutInflater inflater)
    {
        medical_toolbar = (Toolbar) view.findViewById(R.id.medical_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(medical_toolbar);
        medical_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        etComplaint1 = (TextInputEditText)view.findViewById(R.id.et_complain1);
        etComplaint2 = (TextInputEditText)view.findViewById(R.id.et_complain2);
        etComplaint3 = (TextInputEditText)view.findViewById(R.id.et_complain3);
        etBreifHistory1 = (TextInputEditText)view.findViewById(R.id.et_breifhistory1);
        etBreifHistory2 = (TextInputEditText)view.findViewById(R.id.et_breifhistory2);
        etBreifHistory3 = (TextInputEditText)view.findViewById(R.id.et_breifhistory3);
        investigationGroup = (RadioGroup)view.findViewById(R.id.investigation_group);
        improvementGroup = (RadioGroup)view.findViewById(R.id.improvement_group);
        treatmentGroup = (RadioGroup)view.findViewById(R.id.treatment_group);
        BtnInvestigationYes= (RadioButton) view.findViewById(R.id.investigation_yes);
        BtnInvestigationNo= (RadioButton)view.findViewById(R.id.investigation_no);
        BtnInvestigationDontKnow= (RadioButton)view.findViewById(R.id.investigation_dontknow);
        BtnTreatmentYes= (RadioButton)view.findViewById(R.id.treatment_yes);
        BtnTreatmentNo= (RadioButton)view.findViewById(R.id.treatment_no);
        BtnTreatmentDontKnow= (RadioButton)view.findViewById(R.id.treatment_dont_know);
        BtnImprovementYes= (RadioButton)view.findViewById(R.id.improvement_yes);
        BtnImprovementNo= (RadioButton)view.findViewById(R.id.improvement_no);
        BtnImprovementDontKnow= (RadioButton)view.findViewById(R.id.improvement_dontknow);
        btnAddPrescrption = (Button)view.findViewById(R.id.btn_add_prescription);
        btnSaveMedical = (Button)view.findViewById(R.id.btn_save_medical);
        diagnosys = (TextView) view.findViewById(R.id.diagnosys);

        dialogBuilder = new AlertDialog.Builder(getActivity());

        dialogView = inflater.inflate(R.layout.customdialog_diagnosys, null);
        dialogBuilder.setView(dialogView);

        listView= (ListView) dialogView.findViewById(R.id.listview_diagnosys);
        nameList = diagnosyslist();
        adapter= new AdapterDiagnosys(getActivity(), nameList);
        listView.setAdapter(adapter);
    }

    private void saveMedicalClickListener()
    {
        btnSaveMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setMedicalInfo();
                final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait");

                sweetAlertDialog.show();

                Web_Medical_ApiHelper.webAddMedicalConditions(getActivity(), medicalCondition, new ApiResponseListener() {
                    @Override
                    public void onSuccess(String message) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText(message);
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                PreviousRecords previousRecords= new PreviousRecords();
                                fragmentTransaction.replace(R.id.framelayout,previousRecords).addToBackStack(null).commit();

                                etComplaint1.setText("");
                                etComplaint2.setText("");
                                etComplaint3.setText("");
                                etBreifHistory1.setText("");
                                etBreifHistory2.setText("");
                                etBreifHistory3.setText("");

                                BtnInvestigationYes.setChecked(false);
                                BtnInvestigationNo.setChecked(false);
                                BtnInvestigationDontKnow.setChecked(false);
                                BtnTreatmentYes.setChecked(false);
                                BtnTreatmentNo.setChecked(false);
                                BtnTreatmentDontKnow.setChecked(false);
                                BtnImprovementYes.setChecked(false);
                                BtnImprovementNo.setChecked(false);
                                BtnImprovementDontKnow.setChecked(false);

                            }
                        });
                    }

                    @Override
                    public void onError(String message) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        sweetAlertDialog.setTitleText(message);
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();
                            }
                        });
                    }
                });

            }
        });
    }

    private void DiagnosysClickListener(final LayoutInflater inflater)
    {
        diagnosys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog();
            }
        });
    }

    private void setMedicalInfo()
    {
        medicalCondition = new Medical_Conditions();

        medicalCondition.setChiefcomplaints1(etComplaint1.getText().toString());
        medicalCondition.setChiefcomplaints2(etComplaint2.getText().toString());
        medicalCondition.setChiefcomplaints3(etComplaint3.getText().toString());
        medicalCondition.setBriefHistory1(etBreifHistory1.getText().toString());
        medicalCondition.setBriefHistory2(etBreifHistory2.getText().toString());
        medicalCondition.setBriefHistory3(etBreifHistory3.getText().toString());
        medicalCondition.setInvestigation(investigationSelected);
        medicalCondition.setTratementtaken(treatmentSelected);
        medicalCondition.setAnyimprovement(improvementSelected);
        medicalCondition.setDiagnosysList(getSelectedDiagnosys());
    }

    private void investigationRadioGrpListner()
    {
        investigationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.investigation_yes)
                {
                    investigationSelected = "Y";
                }
                else if(checkedId == R.id.investigation_no)
                {
                    investigationSelected = "N";
                }
                else if(checkedId == R.id.investigation_dontknow)
                {
                    investigationSelected = "DN";
                }
            }
        });
    }

    private void treatmentRadioGrpListner()
    {
        treatmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.treatment_yes)
                {
                    treatmentSelected = "Y";
                }
                else if(checkedId == R.id.treatment_no)
                {
                    treatmentSelected = "N";
                }
                else if(checkedId == R.id.treatment_dont_know)
                {
                    treatmentSelected = "DN";
                }
            }
        });
    }

    private void improvementRadioGrpListner()
    {
        improvementGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == R.id.improvement_yes)
                {
                    improvementSelected = "Y";
                }
                else if(checkedId == R.id.improvement_no)
                {
                    improvementSelected = "N";
                }
                else if(checkedId == R.id.improvement_dontknow)
                {
                    improvementSelected = "DN";
                }
            }
        });
    }

    private ArrayList<Diagnosys> diagnosyslist()
    {
        ArrayList<Diagnosys> nameList = new ArrayList<Diagnosys>();
        nameList.add(new Diagnosys("Childhood Obesity",false));
        nameList.add(new Diagnosys("Constipation",false));
        nameList.add(new Diagnosys("Asthma (Pediatric)",false));
        nameList.add(new Diagnosys("Headache, Migraine",false));
        nameList.add(new Diagnosys("Substance Use Disorders",false));
        nameList.add(new Diagnosys("Depression",false));
        nameList.add(new Diagnosys("Hypertension",false));
        nameList.add(new Diagnosys("Food Allergy ",false));
        nameList.add(new Diagnosys("Anxiety Disorder",false));
        nameList.add(new Diagnosys("Speech Defects",false));

        return nameList;
    }

    public void showChangeLangDialog()
    {
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

    public String getSelectedDiagnosys()
    {
        String selectedDiagnosys="";

        for (Diagnosys diagnosys : adapter.getDiagnosys())
        {
            if (diagnosys.selected)
            {
                selectedDiagnosys += diagnosys.name + ",";
            }
        }
        if(selectedDiagnosys.length() > 0)
        {
            selectedDiagnosys = selectedDiagnosys.substring(0, selectedDiagnosys.length() - 1);
        }

        return selectedDiagnosys;
    }


}

