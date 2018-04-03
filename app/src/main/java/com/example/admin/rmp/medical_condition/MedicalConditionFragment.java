package com.example.admin.rmp.medical_condition;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.adapter.AdapterDiagnosys;
import com.example.admin.rmp.medical_condition.adapter.DoseList_Adapter;
import com.example.admin.rmp.medical_condition.model.Diagnosys;

import com.example.admin.rmp.medical_condition.model.Dose;

import com.example.admin.rmp.pref_manager.PrefManager;

import java.util.ArrayList;
import java.util.List;

import com.example.admin.rmp.app.ApiResponseListener;
import com.example.admin.rmp.medical_condition.apihelper.Web_Medical_ApiHelper;
import com.example.admin.rmp.medical_condition.model.Medical_Conditions;
import com.example.admin.rmp.previous_records.apihelper.Web_PatientHistory_Helper;
import com.example.admin.rmp.previous_records.model.PatientHistory;
import com.example.admin.rmp.user_login.LoginActivity;


import com.example.admin.rmp.utils.Utility;
import com.example.admin.rmp.vaccination_record.VaccinationRecord;


import cn.pedant.SweetAlert.SweetAlertDialog;

public class MedicalConditionFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Toolbar medical_toolbar;
    private TextInputEditText etComplaint1, etComplaint2, etComplaint3,
            etBreifHistory1, etBreifHistory2, etBreifHistory3,etPrevsHosptl;
    private TextInputLayout etComplaint1TextInputLayout, etComplaint2TextInputLayout,
            etComplaint3TextInputLayout, etBreifHistory1TextInputLayout,
            etBreifHistory2TextInputLayout, etBreifHistory3TextInputLayout,
            investigation_radioTextLayout,doctorName1Layout;
    private RadioGroup investigationGroup, treatmentGroup, improvementGroup;
    private RadioButton BtnInvestigationYes, BtnInvestigationNo, BtnInvestigationDontKnow,
            BtnTreatmentYes, BtnTreatmentNo, BtnTreatmentDontKnow,
            BtnImprovementYes, BtnImprovementNo, BtnImprovementDontKnow;
    private Button btnSaveMedical;
    private Medical_Conditions medicalCondition;
    private String investigationSelected = "", treatmentSelected = "", improvementSelected = "";
    /*private RecyclerView doseList;
    private DoseList_Adapter doseAdapter;
    private LinearLayoutManager mLayoutManager;*/
    private ArrayList<Dose> doseArrayList;

    ListView listView;
    private TextView diagnosys;

    AdapterDiagnosys adapter;
    ArrayList<Diagnosys> nameList;
    AlertDialog.Builder dialogBuilder;
    View dialogView;
    PrefManager prefManager;
    String[] medicineNameArray;
    boolean[] checkedMedicinesArray;
    ArrayList<Integer>medicinesArrayList=new ArrayList<>();


    /*Previous Record Variables*/
    private TextInputEditText etPreviousHsopital, etDoctorName1, etDoctorName2, etDoctorName3,etDiagnosis;
    private PatientHistory patientHistory;
    private ApiResponseListener apiResponseListener;
    private LinearLayout addPrescriptionLayout;
    private DoseList_Adapter doseAdapter;
    private RecyclerView doseList;
    private LinearLayoutManager mLayoutManager;


    public MedicalConditionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_medical_condition, container, false);

        initialization(rootview, inflater);

        medicineNameArray=getResources().getStringArray(R.array.medicine_name_array);
        checkedMedicinesArray=new boolean[medicineNameArray.length];

        saveMedicalClickListener();

        investigationRadioGrpListner();

        treatmentRadioGrpListner();

        checkInvestigationDone();

        improvementRadioGrpListner();

       // DiagnosysClickListener(inflater);

        addPrescriptionClickListener();

        return rootview;
    }

    private void initialization(View view, LayoutInflater inflater) {
        setHasOptionsMenu(true);
        medical_toolbar = (Toolbar) view.findViewById(R.id.medical_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(medical_toolbar);
        medical_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });


        setHasOptionsMenu(true);

        prefManager = new PrefManager(getActivity());
        etComplaint1 = (TextInputEditText) view.findViewById(R.id.et_complain1);
        etComplaint2 = (TextInputEditText) view.findViewById(R.id.et_complain2);
        etComplaint3 = (TextInputEditText) view.findViewById(R.id.et_complain3);
        etBreifHistory1 = (TextInputEditText) view.findViewById(R.id.et_breifhistory1);
        etBreifHistory2 = (TextInputEditText) view.findViewById(R.id.et_breifhistory2);
        etBreifHistory3 = (TextInputEditText) view.findViewById(R.id.et_breifhistory3);
        etDiagnosis=(TextInputEditText) view.findViewById(R.id.diagnosis);
        investigationGroup = (RadioGroup) view.findViewById(R.id.investigation_group);
        improvementGroup = (RadioGroup) view.findViewById(R.id.improvement_group);
        treatmentGroup = (RadioGroup) view.findViewById(R.id.treatment_group);
        BtnInvestigationYes = (RadioButton) view.findViewById(R.id.investigation_yes);
        BtnInvestigationNo = (RadioButton) view.findViewById(R.id.investigation_no);
        BtnInvestigationDontKnow = (RadioButton) view.findViewById(R.id.investigation_dontknow);
        BtnTreatmentYes = (RadioButton) view.findViewById(R.id.treatment_yes);
        BtnTreatmentNo = (RadioButton) view.findViewById(R.id.treatment_no);
        BtnTreatmentDontKnow = (RadioButton) view.findViewById(R.id.treatment_dont_know);
        BtnImprovementYes = (RadioButton) view.findViewById(R.id.improvement_yes);
        BtnImprovementNo = (RadioButton) view.findViewById(R.id.improvement_no);
        BtnImprovementDontKnow = (RadioButton) view.findViewById(R.id.improvement_dontknow);
        btnSaveMedical = (Button) view.findViewById(R.id.btn_save_medical);


        etComplaint1TextInputLayout = (TextInputLayout) view.findViewById(R.id.etComplain1_textInputLayout);
        etComplaint2TextInputLayout = (TextInputLayout) view.findViewById(R.id.etComplain2_textInputLayout);
        etComplaint3TextInputLayout = (TextInputLayout) view.findViewById(R.id.etComplain3_textInputLayout);
        etBreifHistory1TextInputLayout = (TextInputLayout) view.findViewById(R.id.etBreifhistory1_TextInputLayout);
        etBreifHistory2TextInputLayout = (TextInputLayout) view.findViewById(R.id.etBreifhistory2_TextInputLayout);
        etBreifHistory3TextInputLayout = (TextInputLayout) view.findViewById(R.id.etBreifhistory3_TextInputLayout);
        investigation_radioTextLayout = (TextInputLayout) view.findViewById(R.id.investigationRadioTextLayout);
        addPrescriptionLayout=(LinearLayout)view.findViewById(R.id.add_prescription_layout);
        doseList = (RecyclerView) view.findViewById(R.id.dose_list);
        etPrevsHosptl=(TextInputEditText)view.findViewById(R.id.prevs_hosptl);
        doctorName1Layout=(TextInputLayout)view.findViewById(R.id.doctor_name1_layout);


        /*Previous Record Variables Initializations*/

        etPreviousHsopital = (TextInputEditText)view.findViewById(R.id.prevs_hosptl);
        etDoctorName1 = (TextInputEditText)view.findViewById(R.id.drname1);
        etDoctorName2 = (TextInputEditText)view.findViewById(R.id.drname2);
        etDoctorName3 = (TextInputEditText)view.findViewById(R.id.drname3);
        //btnMedicinName=(Button)view.findViewById(R.id.btn_medicin_name);


        doseArrayList = new ArrayList<Dose>();
        doseAdapter = new DoseList_Adapter(doseArrayList, getActivity().getApplicationContext());

        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        doseList.setHasFixedSize(true);
        doseList.setLayoutManager(mLayoutManager);
        doseList.setAdapter(doseAdapter);



    }

    private void saveMedicalClickListener() {
        btnSaveMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMedicalInfo();
                final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait");

                sweetAlertDialog.show();

                Web_PatientHistory_Helper.webAddPatienHistory(getActivity(), patientHistory);
                Web_Medical_ApiHelper.webAddMedicalConditions(doseArrayList, getActivity(), medicalCondition, new ApiResponseListener() {
                    @Override
                    public void onSuccess(String message) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Done !!");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                VaccinationRecord vaccinationRecord = new VaccinationRecord();
                                fragmentTransaction.replace(R.id.framelayout, vaccinationRecord).addToBackStack(null).commit();

                                etComplaint1.setText("");
                                etComplaint2.setText("");
                                etComplaint3.setText("");
                                etBreifHistory1.setText("");
                                etBreifHistory2.setText("");
                                etBreifHistory3.setText("");
                                etPreviousHsopital.setText("");
                                etDoctorName1.setText("");
                                etDoctorName2.setText("");
                                etDoctorName3.setText("");

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

    private void DiagnosysClickListener(final LayoutInflater inflater) {
        diagnosys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLangDialog(inflater);
            }
        });
    }

    private void setMedicalInfo() {

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


        patientHistory = new PatientHistory();
        patientHistory.setPrevHospital(etPreviousHsopital.getText().toString());
        patientHistory.setDoctorName1(etDoctorName1.getText().toString());
        patientHistory.setDoctorName2(etDoctorName2.getText().toString());
        patientHistory.setDoctorName3(etDoctorName3.getText().toString());
    }

    private void addPrescriptionClickListener() {
        addPrescriptionLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater2 = getActivity().getLayoutInflater();
                View alertLayout = inflater2.inflate(R.layout.dose_dialog_row, null);
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

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

    private void investigationRadioGrpListner() {
        investigationGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.investigation_yes) {
                    investigationSelected = "Y";
                    BtnTreatmentYes.setEnabled(true);
                    BtnTreatmentNo.setEnabled(true);
                    BtnTreatmentDontKnow.setEnabled(true);
                } else if (checkedId == R.id.investigation_no) {
                    investigationSelected = "N";
                    BtnTreatmentYes.setEnabled(false);
                    BtnTreatmentNo.setEnabled(false);
                    BtnTreatmentDontKnow.setEnabled(false);
                } else if (checkedId == R.id.investigation_dontknow) {
                    investigationSelected = "DN";
                    BtnTreatmentYes.setEnabled(false);
                    BtnTreatmentNo.setEnabled(false);
                    BtnTreatmentDontKnow.setEnabled(false);
                }
                else
                {
                    BtnTreatmentYes.setEnabled(false);
                    BtnTreatmentNo.setEnabled(false);
                    BtnTreatmentDontKnow.setEnabled(false);
                }

            }
        });
    }

    private void checkInvestigationDone() {
        if(BtnInvestigationYes.isChecked()) {

            BtnTreatmentYes.setEnabled(true);
            BtnTreatmentNo.setEnabled(true);
            BtnTreatmentDontKnow.setEnabled(true);

        }
        else
        {
            BtnTreatmentYes.setEnabled(false);
            BtnTreatmentNo.setEnabled(false);
            BtnTreatmentDontKnow.setEnabled(false);

        }
    }

    private void treatmentRadioGrpListner() {
        treatmentGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.treatment_yes) {
                    treatmentSelected = "Y";
                } else if (checkedId == R.id.treatment_no) {
                    treatmentSelected = "N";
                } else if (checkedId == R.id.treatment_dont_know) {
                    treatmentSelected = "DN";
                }
            }
        });
    }

    private void improvementRadioGrpListner() {
        improvementGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.improvement_yes) {
                    improvementSelected = "Y";
                } else if (checkedId == R.id.improvement_no) {
                    improvementSelected = "N";
                } else if (checkedId == R.id.improvement_dontknow) {
                    improvementSelected = "DN";
                }
            }
        });
    }

    private ArrayList<Diagnosys> diagnosyslist() {
        ArrayList<Diagnosys> nameList = new ArrayList<Diagnosys>();
        nameList.add(new Diagnosys("Childhood Obesity", false));
        nameList.add(new Diagnosys("Constipation", false));
        nameList.add(new Diagnosys("Asthma (Pediatric)", false));
        nameList.add(new Diagnosys("Headache, Migraine", false));
        nameList.add(new Diagnosys("Substance Use Disorders", false));
        nameList.add(new Diagnosys("Depression", false));
        nameList.add(new Diagnosys("Hypertension", false));
        nameList.add(new Diagnosys("Food Allergy ", false));
        nameList.add(new Diagnosys("Anxiety Disorder", false));
        nameList.add(new Diagnosys("Speech Defects", false));

        return nameList;
    }

    public void showChangeLangDialog(final LayoutInflater inflater) {
        dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogView = inflater.inflate(R.layout.customdialog_diagnosys, null);
        dialogBuilder.setView(dialogView);

        listView = (ListView) dialogView.findViewById(R.id.listview_diagnosys);
        nameList = diagnosyslist();
        adapter = new AdapterDiagnosys(getActivity(), nameList);
        listView.setAdapter(adapter);

        dialogBuilder.setTitle("Select Diagnosys");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                diagnosys.setText(getSelectedDiagnosys());
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

    public String getSelectedDiagnosys() {
        String selectedDiagnosys = "";
        if (adapter != null) {
            for (Diagnosys diagnosys : adapter.getDiagnosys()) {
                if (diagnosys.selected) {
                    selectedDiagnosys += diagnosys.name + ",";
                }
            }
        }
        if (selectedDiagnosys.length() > 0) {
            selectedDiagnosys = selectedDiagnosys.substring(0, selectedDiagnosys.length() - 1);
        }

        return selectedDiagnosys;
    }

    private boolean checkValidation() {
        boolean response = true;


        if (etComplaint1.getText().toString().trim().length() == 0) {
            etComplaint1TextInputLayout.setErrorEnabled(true);
            etComplaint1TextInputLayout.setErrorTextAppearance(R.style.error);
            etComplaint1TextInputLayout.setError("Enter Complaint1 ");
            response = false;
        } else {
            etComplaint1TextInputLayout.setErrorEnabled(false);
            etComplaint1TextInputLayout.setError(null);
        }

        if (etComplaint2.getText().toString().trim().length() == 0) {
            etComplaint2TextInputLayout.setErrorEnabled(true);
            etComplaint2TextInputLayout.setErrorTextAppearance(R.style.error);
            etComplaint2TextInputLayout.setError("Enter Complaint2 ");
            response = false;
        } else {
            etComplaint2TextInputLayout.setErrorEnabled(false);
            etComplaint2TextInputLayout.setError(null);
        }

        if (etComplaint3.getText().toString().trim().length() == 0) {
            etComplaint3TextInputLayout.setErrorEnabled(true);
            etComplaint3TextInputLayout.setErrorTextAppearance(R.style.error);
            etComplaint3TextInputLayout.setError("Enter Complaint3 ");
            response = false;
        } else {
            etComplaint3TextInputLayout.setErrorEnabled(false);
            etComplaint3TextInputLayout.setError(null);
        }


        if (etBreifHistory1.getText().toString().trim().length() == 0) {
            etBreifHistory1TextInputLayout.setErrorEnabled(true);
            etBreifHistory1TextInputLayout.setErrorTextAppearance(R.style.error);
            etBreifHistory1TextInputLayout.setError("Enter Brief History1 ");
            response = false;
        } else {
            etBreifHistory1TextInputLayout.setErrorEnabled(false);
            etBreifHistory1TextInputLayout.setError(null);
        }

        if (etBreifHistory2.getText().toString().trim().length() == 0) {
            etBreifHistory2TextInputLayout.setErrorEnabled(true);
            etBreifHistory2TextInputLayout.setErrorTextAppearance(R.style.error);
            etBreifHistory2TextInputLayout.setError("Enter Brief History2");
            response = false;
        } else {
            etBreifHistory2TextInputLayout.setErrorEnabled(false);
            etBreifHistory2TextInputLayout.setError(null);
        }

        if (etBreifHistory3.getText().toString().trim().length() == 0) {
            etBreifHistory3TextInputLayout.setErrorEnabled(true);
            etBreifHistory3TextInputLayout.setErrorTextAppearance(R.style.error);
            etBreifHistory3TextInputLayout.setError("Enter Brief History3");
            response = false;
        } else {
            etBreifHistory3TextInputLayout.setErrorEnabled(false);
            etBreifHistory3TextInputLayout.setError(null);
        }


        if (investigationGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please check investigation done or not", Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        } else {
            // one of the radio buttons is checked

        }


        if (treatmentGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please check treatment taken or not", Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        } else {
            // one of the radio buttons is checked

        }


        if (improvementGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(), "Please check improvement done or not", Toast.LENGTH_SHORT).show();
            response = false;
            // no radio buttons are checked
        } else {
            // one of the radio buttons is checked

        }

      /*  if(!(BtnInvestigationYes.isChecked()) || !(BtnInvestigationNo.isChecked()) || !(BtnInvestigationDontKnow.isChecked()))
        {
            investigation_radioTextLayout.setErrorEnabled(true);
            investigation_radioTextLayout.setErrorTextAppearance(R.style.error);
            investigation_radioTextLayout.setError("Check investigation");
            response = false;
        } else {
            investigation_radioTextLayout.setErrorEnabled(false);
            investigation_radioTextLayout.setError(null);
        }*/
        return response;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (item.getItemId()) {
                    case R.id.logout:
                        prefManager.setLogOut();
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                        getActivity().finish();

                        break;
                }
                return super.onOptionsItemSelected(item);
        }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

