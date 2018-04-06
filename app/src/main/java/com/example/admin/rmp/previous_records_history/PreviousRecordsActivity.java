package com.example.admin.rmp.previous_records_history;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.admin.rmp.R;
import com.example.admin.rmp.medical_condition.MedicalConditionFragment;
import com.example.admin.rmp.utils.Utility;

public class PreviousRecordsActivity extends AppCompatActivity {


    private Toolbar previousRecordsToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
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
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
        tabLayout.getTabAt(5).setIcon(tabIcons[5]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GeneralInformationPreviousRecordFragment(), "General\n\nInformation");
        adapter.addFragment(new VitalInfoPreviousRecordFragment(), "Vital\n\nInformation");
        adapter.addFragment(new MedicalConditionPrevoiusRecordsFragment(), "Medical\n\nCondition");
        adapter.addFragment(new VaccinationPreviousRecordsFragment(), "Vaccination\n\nRecords");
        adapter.addFragment(new TestByMhuPreviousRecordFragment(), "Test By\n\nMHU");
        adapter.addFragment(new TestAdvicedPreviousRecordsFragment(), "Test\n\nAdvice");
        viewPager.setAdapter(adapter);
    }

}
