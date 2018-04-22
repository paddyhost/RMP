package mhu.rmp.check_previous_records;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mhu.rmp.R;
import mhu.rmp.activity.MainActivity;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.check_previous_records.apihelper.Web_Check_Previous_Records_ApiHelper;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.patient_registration.General_Information;
import mhu.rmp.patient_registration.model.PatientRegistration;


public class CheckPreviousRecordsFragment extends Fragment {

    private Button btnVerifyPatientHistory;
    private TextView txtPatientName,txtPatientUniqueId,totalVisit,txtItemSpinner;
    private Spinner selectedVisitSpinner;
    String[] listVisitid;
    //public PatientRegistration patientRegistration;

    String patientUniqueId,patientName;
    public static CheckPreviousRecordsFragment getInstance(String txtPatientName,String txtPatientUniqueId) {
        CheckPreviousRecordsFragment fragment = new CheckPreviousRecordsFragment();
        Bundle args = new Bundle();
        args.putString("txtPatientName",txtPatientName);
        args.putString("txtPatientUniqueId",txtPatientUniqueId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patientUniqueId=getArguments().getString("txtPatientUniqueId");
            patientName=getArguments().getString("txtPatientName");

        }
    }


    public CheckPreviousRecordsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_check_previous_records, container, false);



        initializations(view);
        setData();
        final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText("Please wait");

        sweetAlertDialog.show();

        Web_Check_Previous_Records_ApiHelper.webGetVisitPatientRecord(getActivity(), patientUniqueId, new ApiResponseListener() {
            @Override
            public void onSuccess(final String message) {

                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText(String.valueOf(getResources().getText(R.string.patient_history)));
                sweetAlertDialog.setConfirmText("Ok");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        try
                        {
                             listVisitid = message.split(",");
                            totalVisit.setText(listVisitid.length+"");
                            ArrayList<String> visitlist = new ArrayList<>();
                            TextView tv=new TextView(getActivity());
                            tv.setText("Select Visit No");
                            tv.setTextSize(getResources().getDimension(R.dimen.defaultTextSize));
                            tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                            visitlist.add(String.valueOf(tv.getText()));
                            for (int i = 1; listVisitid.length >= i; i++) {
                                visitlist.add("Visit no-" + i);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.spinner_list_row,R.id.txt_item_spinner,visitlist);
                            selectedVisitSpinner.setAdapter(adapter);
                        }
                        catch (Exception e)
                        {

                        }
                      /* */


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






        onClickListeners();

        return view;
    }


    private void initializations(View view)
    {
        btnVerifyPatientHistory=(Button)view.findViewById(R.id.btn_verify_patient_history);
        txtPatientName=(TextView)view.findViewById(R.id.txt_patient_name);
        txtPatientUniqueId=(TextView)view.findViewById(R.id.txt_patient_unique_id);
        totalVisit=(TextView)view.findViewById(R.id.txt_total_visit);
        selectedVisitSpinner=(Spinner)view.findViewById(R.id.selected_visit_spinner);
        txtItemSpinner=(TextView)view.findViewById(R.id.txt_item_spinner);

    }


    private void setData()
    {

        txtPatientName.setText((patientName));
        txtPatientUniqueId.setText((patientUniqueId));
        //totalVisit.setText();

    }


    private void onClickListeners()
    {


        btnVerifyPatientHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    try {

                        int position = selectedVisitSpinner.getSelectedItemPosition();
                        String visitid = listVisitid[position - 1];
                        Intent intent = new Intent(getActivity(), PreviousRecordsActivity.class);
                        intent.putExtra("VISITID", visitid);
                        startActivity(intent);

                    } catch (Exception e) {
                        e.printStackTrace();
                        ;

                    }

            }
        });

    }


    private boolean checkValidation()
    {
        boolean response=true;

        if (selectedVisitSpinner.getSelectedItem().toString().trim().equalsIgnoreCase("Please Select District")) {

            View selectedView = selectedVisitSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (selectedVisitSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select Visit No";
                    selectedTextView.setError(errorString);
                } else {
                    selectedTextView.setError(null);
                }
            }
            response = false;
        }
        return response;
    }

}
