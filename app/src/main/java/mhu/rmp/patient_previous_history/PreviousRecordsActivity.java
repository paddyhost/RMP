package mhu.rmp.patient_previous_history;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mhu.rmp.R;
import mhu.rmp.TestAdviced.TestAdvicedPreviousRecordsFragment;
import mhu.rmp.patient_previous_history.adapter.ViewPagerAdapter;
import mhu.rmp.patient_previous_history.model.PreviousRecords;
import mhu.rmp.patient_previous_history.web_apihelper.Web_GetSinglePatientRecord_ApiHelper;
import mhu.rmp.app.ApiResponseListener;
import mhu.rmp.medical_condition.MedicalConditionPrevoiusRecordsFragment;
import mhu.rmp.mhu_test.TestByMhuPreviousRecordFragment;
import mhu.rmp.patient_registration.GeneralInformationPreviousRecordFragment;
import mhu.rmp.utils.Utility;
import mhu.rmp.vaccination_record.VaccinationPreviousRecordsFragment;
import mhu.rmp.vital_info.VitalInfoPreviousRecordFragment;

public class PreviousRecordsActivity extends AppCompatActivity {

public static PreviousRecords previousRecords=new PreviousRecords();
    private Toolbar previousRecordsToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public  String visit_id;

    private int[] tabIcons = {
            R.drawable.ic_general_information,
            R.drawable.ic_brief_history,
            R.drawable.ic_medical_condition,
            R.drawable.ic_vaccination_records,
            R.drawable.ic_blood,
            R.drawable.ic_referred
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_records);



        visit_id=getIntent().getStringExtra("VISITID");


        previousRecordsToolbar = (Toolbar) findViewById(R.id.previous_records_toolbar);
        setSupportActionBar(previousRecordsToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        previousRecordsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.closeAppDialog(PreviousRecordsActivity.this);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //Web_GetSinglePatientRecord_ApiHelper.
        getDataFromServer();

        //setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GeneralInformationPreviousRecordFragment(), "General\nInformation");
        adapter.addFragment(new VitalInfoPreviousRecordFragment(), "Vital\nInformation");
        adapter.addFragment(new MedicalConditionPrevoiusRecordsFragment(), "Medical\nCondition");
        adapter.addFragment(new VaccinationPreviousRecordsFragment(), "Vaccination\nRecords");
        adapter.addFragment(new TestByMhuPreviousRecordFragment(), "Test By\nMHU");
        adapter.addFragment(new TestAdvicedPreviousRecordsFragment(), "Test\nAdvice");
        viewPager.setAdapter(adapter);
    }


    private void getDataFromServer()
    {
      final SweetAlertDialog  sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setConfirmText("Please Wait");
        sweetAlertDialog.show();

        Web_GetSinglePatientRecord_ApiHelper.webGetSinglePatientRecord(this, previousRecords, new ApiResponseListener() {
            @Override
            public void onSuccess(String message) {
                sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                //sweetAlertDialog.setTitleText(message);
                sweetAlertDialog.setTitleText("Previous Records History");
                sweetAlertDialog.setConfirmText("Ok");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();

                        setupViewPager(viewPager);
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
        },visit_id);
    }

}
