package mhu.rmp.patient_registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mhu.rmp.R;
import mhu.rmp.activity.model.PreviousRecords;
import mhu.rmp.activity.previous_record_apihelper.Web_GetSinglePatientRecord_ApiHelper;
import mhu.rmp.app.ApiResponseListener;


public class GeneralInformationPreviousRecordFragment extends Fragment {

    private PreviousRecords previousRecords;
    private SweetAlertDialog sweetAlertDialog;

    private TextView txtPatientUniqueId,txtRegistrationNo,txtVisitNo,txtRegistrationDate,txtPatientCategory,
            txtPatientName,txtPatientMobile,txtPatientDob,txtPatientAddress,txtState,txtDistrict,txtCity,txtArea,
            txtLocation,txtGender;

    public GeneralInformationPreviousRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_general_information_previous_record, container, false);

        initializations(view);
        setData();
        getDataFromServer();
        return view;
    }

    private void initializations(View view)
    {
        txtPatientUniqueId=(TextView)view.findViewById(R.id.patient_unique_id);
        txtRegistrationNo=(TextView)view.findViewById(R.id.txt_registration_no);
        txtVisitNo=(TextView)view.findViewById(R.id.txt_visit_no);
        txtRegistrationDate=(TextView)view.findViewById(R.id.registration_date);
        txtPatientCategory=(TextView)view.findViewById(R.id.txt_patient_category);
        txtPatientName=(TextView)view.findViewById(R.id.txt_patient_name);
        txtPatientMobile=(TextView)view.findViewById(R.id.txt_patient_mobile);
        txtPatientDob=(TextView)view.findViewById(R.id.txt_patient_dob);
        txtState=(TextView)view.findViewById(R.id.txt_state);
        txtDistrict=(TextView)view.findViewById(R.id.txt_district);
        txtCity=(TextView)view.findViewById(R.id.txt_city);
        txtArea=(TextView)view.findViewById(R.id.txt_area);
        txtLocation=(TextView)view.findViewById(R.id.txt_location);
        txtGender=(TextView)view.findViewById(R.id.txt_gender);
    }


    private void setData()
    {

        txtPatientUniqueId.setText(String.valueOf(previousRecords.getUnique_id()));
        txtRegistrationNo.setText(String.valueOf(previousRecords.getRegistrationno()));
        txtVisitNo.setText(String.valueOf(previousRecords.getVisit_master_id()));
        txtRegistrationDate.setText(String.valueOf(previousRecords.getRegitrationdate()));
        txtPatientCategory.setText(String.valueOf(previousRecords.getPatient_category()));
        txtPatientName.setText(String.valueOf(previousRecords.getName()));
        txtPatientMobile.setText(String.valueOf(previousRecords.getMobile()));
        txtPatientDob.setText(String.valueOf(previousRecords.getDob()));
        txtPatientAddress.setText(String.valueOf(previousRecords.getAddress()));
        txtState.setText(String.valueOf(previousRecords.getState()));
        txtDistrict.setText(String.valueOf(previousRecords.getDistrict()));
        txtCity.setText(String.valueOf(previousRecords.getCity()));
        txtArea.setText(String.valueOf(previousRecords.getArea()));
        txtLocation.setText(String.valueOf(previousRecords.getLocation()));
        txtGender.setText(String.valueOf(previousRecords.getGender()));


    }


    private void getDataFromServer()
    {
        sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            sweetAlertDialog.setConfirmText("Please Wait");
            sweetAlertDialog.show();

        Web_GetSinglePatientRecord_ApiHelper.webGetSinglePatientRecord(getActivity(), previousRecords, new ApiResponseListener() {
            @Override
            public void onSuccess(String message) {
                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText(message);
                sweetAlertDialog.setConfirmText("Ok");

                sweetAlertDialog.dismissWithAnimation();
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });

            }

            @Override
            public void onError(String message) {
                sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                sweetAlertDialog.setTitleText(""+message);
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


}
