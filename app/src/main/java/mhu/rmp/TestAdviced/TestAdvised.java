package mhu.rmp.TestAdviced;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import mhu.rmp.R;
import mhu.rmp.TestAdviced.apihelper.Web_TestAdviced_Helper;
import mhu.rmp.TestAdviced.model.TestAdvicedModel;
import mhu.rmp.activity.MainActivity;
import mhu.rmp.check_previous_records.CheckPreviousRecordsFragment;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.patient_previous_history.PreviousRecordsActivity;
import mhu.rmp.pref_manager.PrefManager;
import mhu.rmp.user_login.LoginActivity;

import mhu.rmp.utils.Utility;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class TestAdvised extends Fragment {

    public static final String MHU_TEST="mhu_test";
    private Toolbar test_toolbar;
    private TextInputEditText etRefrerredTxt,etRmarks,edtTestName;
    private Spinner advisedSpinner;
    private TestAdvicedModel testAdvicedModel;
    private Button submitBtn;
    private TextInputLayout referred_txtTextLayout,remarksTextLayout;
    private PrefManager prefManager;

    public TestAdvised() {
        // Required empty public constructor
    }

    public static TestAdvised getInstance() {
        TestAdvised fragment = new TestAdvised();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_test_advised, container, false);

        initialization(rootview);

        submitClickListener();

        return rootview;
    }

    private void initialization(View view)
    {
        setHasOptionsMenu(true);

        prefManager=new PrefManager(getActivity());

        test_toolbar = (Toolbar) view.findViewById(R.id.test_toolbar);
        prefManager=new PrefManager(getActivity());
        etRefrerredTxt = (TextInputEditText)view.findViewById(R.id.referred_txt);
        etRmarks = (TextInputEditText)view.findViewById(R.id.remarks);
        edtTestName= (TextInputEditText)view.findViewById(R.id.test_name_txt);
        //advisedSpinner = (Spinner)view.findViewById(R.id.advised_test);
        submitBtn = (Button) view.findViewById(R.id.submit);
        referred_txtTextLayout=(TextInputLayout)view.findViewById(R.id.referred_txt_TextLayout);
        remarksTextLayout=(TextInputLayout)view.findViewById(R.id.remarks_TextLayout);

        ((AppCompatActivity)getActivity()).setSupportActionBar(test_toolbar);
        test_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(getActivity());
            }
        });
    }

    private void submitClickListener()
    {
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAdvisedData();

                //if(checkValidation()) {

                    final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE)
                            .setTitleText("Please wait");

                    sweetAlertDialog.show();

                    Web_TestAdviced_Helper.webAddMHuTest(getActivity(), testAdvicedModel, new ApiResponseListener() {
                        @Override
                        public void onSuccess(String message) {
                            sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("Test Advice Data Saved!!");
                            sweetAlertDialog.setConfirmText("Ok");
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();

                                   /* FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                                    UserVerificationFragment general_information = new UserVerificationFragment();
                                    fragmentTransaction.replace(R.id.framelayout, general_information).addToBackStack(null).commit();*/
                                    /*Intent intent=new Intent(getActivity(), PreviousRecordsActivity.class);
                                    intent.putExtra("VISITID", MainActivity.Visit_ID);
                                    startActivity(intent);*/

                                    Intent intent=new Intent(getActivity(), PreviousRecordsActivity.class);
                                    intent.putExtra("VISITID", MainActivity.Visit_ID);
                                    startActivity(intent);
                                    getActivity().finish();
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

                //}



            }
        });
    }

     private void setAdvisedData()
    {
        testAdvicedModel=new TestAdvicedModel();
        try {
            testAdvicedModel.setTestName(edtTestName.getText().toString());
        }
        catch(Exception e) {

        }
        try
        {
            testAdvicedModel.setRerered(etRefrerredTxt.getText().toString());
        }
        catch (Exception e)
        {}

        try {
            testAdvicedModel.setRemark(etRmarks.getText().toString());
        }
        catch (Exception e)
        {

        }
    }

    private boolean checkValidation()
    {
        boolean response=true;

        if (etRefrerredTxt.getText().toString().trim().length() == 0)
        {
            referred_txtTextLayout.setErrorEnabled(true);
            referred_txtTextLayout.setErrorTextAppearance(R.style.error);
            referred_txtTextLayout.setError("Enter refer");
            response = false;
        } else {
            referred_txtTextLayout.setErrorEnabled(false);
            referred_txtTextLayout.setError(null);
        }


        if (etRmarks.getText().toString().trim().length() == 0)
        {
            remarksTextLayout.setErrorEnabled(true);
            remarksTextLayout.setErrorTextAppearance(R.style.error);
            remarksTextLayout.setError("Enter remark");
            response = false;
        } else {
            remarksTextLayout.setErrorEnabled(false);
            remarksTextLayout.setError(null);
        }

      /*  View selectedView = advisedSpinner.getSelectedView();
            if (selectedView != null && selectedView instanceof TextView) {
                TextView selectedTextView = (TextView) selectedView;
                if (advisedSpinner.getSelectedItemPosition() == 0) {
                    String errorString = "Select Advised Tests";
                    selectedTextView.setError(errorString);

                } else {
                    selectedTextView.setError(null);
                }
            response = false;
        }

*/
        return response;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
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
