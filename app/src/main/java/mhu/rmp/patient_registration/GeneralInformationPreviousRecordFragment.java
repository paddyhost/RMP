package mhu.rmp.patient_registration;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mhu.rmp.R;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.patient_previous_history.model.PreviousRecords;


public class GeneralInformationPreviousRecordFragment extends Fragment {

    private PreviousRecords previousRecords;

    public  String visit_id;

    private TextView txtPatientUniqueId,txtRegistrationNo,txtVisitNo,txtRegistrationDate,txtPatientCategory,
            txtPatientFname,txtPatientLname,txtPatientMobile,txtPatientDob,txtPatientAddress,txtState,txtDistrict,txtCity,txtArea,
            txtLocation,txtGender,txtAge;


    public GeneralInformationPreviousRecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_general_information_previous_record, container, false);
        previousRecords= PreviousRecordsActivity.previousRecords;
        initializations(view);
        setData();

        return view;
    }

    private void initializations(View view)
    {
        txtPatientUniqueId=(TextView)view.findViewById(R.id.txt_patient_unique_id);
        txtRegistrationNo=(TextView)view.findViewById(R.id.txt_registration_no);
        txtVisitNo=(TextView)view.findViewById(R.id.txt_visit_no);
        txtRegistrationDate=(TextView)view.findViewById(R.id.txt_registration_date);
        txtPatientCategory=(TextView)view.findViewById(R.id.txt_patient_category);
        txtPatientFname=(TextView)view.findViewById(R.id.txt_patient_fname);
        txtPatientLname=(TextView)view.findViewById(R.id.txt_patient_lname);
        txtPatientMobile=(TextView)view.findViewById(R.id.txt_patient_mobile);
        txtPatientDob=(TextView)view.findViewById(R.id.txt_patient_dob);
        txtAge=(TextView)view.findViewById(R.id.txt_patient_age);
        txtState=(TextView)view.findViewById(R.id.txt_state);
        txtDistrict=(TextView)view.findViewById(R.id.txt_district);
        txtCity=(TextView)view.findViewById(R.id.txt_city);
        txtArea=(TextView)view.findViewById(R.id.txt_area);
        txtLocation=(TextView)view.findViewById(R.id.txt_location);
        txtGender=(TextView)view.findViewById(R.id.txt_gender);
        txtPatientAddress=(TextView)view.findViewById(R.id.txt_patient_address);
    }


    private void setData()
    {


            if (txtPatientUniqueId!=null)
            {
                txtPatientUniqueId.setText(String.valueOf(previousRecords.getUnique_id()));
            }
            else
            {

            }

        try {
            txtRegistrationNo.setText(String.valueOf(previousRecords.getRegistrationno()));
        }
        catch(Exception e)
        {}
        try {
            txtVisitNo.setText(String.valueOf(previousRecords.getVisit_master_id()));
        }
        catch(Exception e)
        {}

        try {
            txtRegistrationDate.setText(String.valueOf(previousRecords.getRegitrationdate()));
        }
        catch (Exception e)
        {
             }
        try {
            if(previousRecords.getPatient_category().equalsIgnoreCase("PW")) {
                txtPatientCategory.setText("Pregnant Women");
            }
            else if(previousRecords.getPatient_category().equalsIgnoreCase("LW"))
            {
                txtPatientCategory.setText("Lactating Women");
            }

            else if(previousRecords.getPatient_category().equalsIgnoreCase("C"))
            {
                txtPatientCategory.setText("Child Under-5 Years of Age");
            }

            else if(previousRecords.getPatient_category().equalsIgnoreCase("S"))
            {
                txtPatientCategory.setText("Senior Citizen-above 60 years of age");
            }
            else
            {
                txtPatientCategory.setText("Other");
            }
        }
        catch (Exception e)
        {

        }

        try {
            txtPatientFname.setText(String.valueOf(previousRecords.getFname()));
        }

        catch (Exception e)
    {

    }

        try {
            txtPatientLname.setText(String.valueOf(previousRecords.getLanme()));
        }

        catch (Exception e)
        {

        }

        try{
            txtPatientMobile.setText(String.valueOf(previousRecords.getMobile()));
        }

        catch (Exception e)
        {

        }
    try{
        txtPatientDob.setText(String.valueOf(previousRecords.getDob()));
    }
    catch (Exception e)
    {

    }
    try
    {
        txtPatientAddress.setText(String.valueOf(previousRecords.getAddress()));
    }
    catch (Exception e)
    {

    }
     try{
        txtState.setText(String.valueOf(previousRecords.getState()));
     }
     catch (Exception e)
     {

     }
      try {
          txtDistrict.setText(String.valueOf(previousRecords.getDistrict()));
      }
      catch (Exception e)
      {

      }
      try {
          txtCity.setText(String.valueOf(previousRecords.getCity()));
      }
      catch (Exception e)
      {

      }

      try {
          txtArea.setText(String.valueOf(previousRecords.getArea()));
      }
      catch (Exception e)
      {

      }
      try {
          txtLocation.setText(String.valueOf(previousRecords.getLocation()));
      }
      catch(Exception e)
      {

      }

      try {
            if(previousRecords.getGender().equalsIgnoreCase("M")) {
                txtGender.setText("Male");
            }
            else
            {
                txtGender.setText("Female");
            }
      }
      catch (Exception e)
      {

      }
      try
      {
          txtAge.setText(General_Information.ageYear);
      }
      catch(Exception e)
      {

      }



    }

}
