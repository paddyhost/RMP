package mhu.rmp.vaccination_record;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import mhu.rmp.R;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.mhu_test.MhuTest;
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.user_login.LoginActivity;

import mhu.rmp.utils.Utility;

import mhu.rmp.vaccination_record.apihelper.Vaccination_ApiHelper;
import mhu.rmp.vaccination_record.model.Vaccination;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class VaccinationRecord extends Fragment {

    private Toolbar vaccination_toolbar;
    private Button btn_save_vaccination;
    private CheckBox checkboxDpt,checkboxBcg,checkboxOpv,checkboxHepatitis,checkboxTt, checkBoxMeasles;
    private TextInputEditText edtOther;
    private String selected_dtp = "",selected_bcg="",selected_opv="",selected_hepatitis="",selected_tt="",selected_Measles ="";
    private Vaccination vaccination;
    private TextInputLayout edt_otherTextLayout;
    private PrefManager prefManager;

    public VaccinationRecord() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccination_record, container, false);
        initializations(view);

        setDtp();
        setBcg();
        setHepatitis();
        setOpv();
        setTt();
        setMeasles();

        onClickListeners();
        return view;
    }

    private void initializations(View view)
    {
        setHasOptionsMenu(true);

        prefManager=new PrefManager(getActivity());

        vaccination_toolbar = (Toolbar) view.findViewById(R.id.vaccination_toolbar);
        prefManager=new PrefManager(getActivity());
        btn_save_vaccination = (Button) view.findViewById(R.id.btn_save_vaccination);
        checkboxDpt=(CheckBox)view.findViewById(R.id.checkbox_dpt);
        checkboxBcg=(CheckBox)view.findViewById(R.id.checkbox_bcg);
        checkboxHepatitis=(CheckBox)view.findViewById(R.id.checkbox_hepatitis);
        checkboxOpv=(CheckBox)view.findViewById(R.id.checkbox_opv);
        checkboxTt=(CheckBox)view.findViewById(R.id.checkbox_tt);
        checkBoxMeasles = (CheckBox)view.findViewById(R.id.checkbox_measles);
        edtOther=(TextInputEditText)view.findViewById(R.id.edt_other);
        edt_otherTextLayout=(TextInputLayout)view.findViewById(R.id.edt_other_TextLayout);
    }
    
    private void onClickListeners()
    {
        vaccination_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });

        btn_save_vaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                setVaccinationData();

                final SweetAlertDialog sweetAlertDialog =new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                        .setTitleText("Please wait");

                sweetAlertDialog.show();

                Vaccination_ApiHelper.webAddVaccination(getActivity(), vaccination, new ApiResponseListener() {
                    @Override
                    public void onSuccess(String message) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("Vaccination Record Saved!!");
                        sweetAlertDialog.setConfirmText("Ok");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismissWithAnimation();

                                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                MhuTest mhuTest= new MhuTest();
                                fragmentTransaction.replace(R.id.framelayout,mhuTest).addToBackStack(null).commit();
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

    private void setDtp()
    {
        checkboxDpt.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_dtp="Y";
                }
                else
                {
                    selected_dtp="N";
                }
            }
        });

    }

    private void setBcg()
    {
        checkboxBcg.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_bcg="Y";
                }
                else
                {
                    selected_bcg="N";
                }
            }
        });

    }

    private void setOpv()
    {
        checkboxOpv.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_opv="Y";
                }
                else
                {
                    selected_opv="N";
                }
            }
        });

    }

    private void setTt()
    {
        checkboxTt.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_tt="Y";
                }
                else
                {
                    selected_tt="N";
                }
            }
        });

    }

    private void setHepatitis()
    {
        checkboxHepatitis.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_hepatitis="Y";
                }
                else
                {
                    selected_hepatitis="N";
                }
            }
        });

    }

    private void setMeasles()
    {
        checkBoxMeasles.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selected_Measles="Y";
                }
                else
                {
                    selected_Measles="N";
                }
            }
        });

    }


    private void setVaccinationData()
    {
        vaccination=new Vaccination();

        vaccination.setDpt(selected_dtp);
        vaccination.setBcg(selected_bcg);
        vaccination.setOpv(selected_opv);
        vaccination.setTt(selected_tt);
        vaccination.setHepatitis(selected_hepatitis);
        vaccination.setMeasles(selected_Measles);
        vaccination.setOther(edtOther.getText().toString());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:
                prefManager.setLogOut();
                Intent i= new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
