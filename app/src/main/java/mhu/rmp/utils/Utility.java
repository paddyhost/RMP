package mhu.rmp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import mhu.rmp.R;
import mhu.rmp.patient_registration.UserVerificationFragment;

/**
 * Created by Ashwin on 29-Jan-18.
 */

public class Utility
{
    public static void closeAppDialog(final Activity activity)
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alert_exit, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);

        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                activity.finish();
               /* FragmentTransaction fragmentTransaction= new AppCompatActivity().getSupportFragmentManager().beginTransaction();
                UserVerificationFragment userVerificationFragment = new UserVerificationFragment();
                fragmentTransaction.replace(R.id.framelayout, userVerificationFragment).commit();
*/
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }
}
