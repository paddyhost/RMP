package mhu.rmp.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mhu.rmp.R;
import mhu.rmp.patient_registration.UserVerificationFragment;
import mhu.rmp.utils.Utility;

public class MainActivity extends AppCompatActivity {

    public static String PATIENT_ID = "", REGISTRATION_ID = "",Visit_ID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
    }

    private void loadFragment()
    {
        UserVerificationFragment generalinformation = new UserVerificationFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.framelayout, generalinformation);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        Utility.closeAppDialog(MainActivity.this);

    }
}
