package com.anand.loktratask.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.anand.loktratask.views.screen_contracts.PermissionResponse;

public class PermissionCheck {

    //Normally checking for permission and asking for permission
    public void requestAppPermissions(Activity activity, String requestedPermissions, final int requestCode) {
        Log.e("MapRoute", "Checking permission");

        //Check if permission granted
        if (isPermissionGranted(activity, requestedPermissions) != PackageManager.PERMISSION_GRANTED) {
            //Check if rational request needed
            if (shouldAskRational(activity, requestedPermissions)) {
                //Make rational request
                ((PermissionResponse) activity).askRationalPermission();
            } else {
                //Make irrational request ( normal request, this might be first time request as well )
                askPermission(activity, requestedPermissions, requestCode);
            }
        } else {
            //Permission granted
            //noinspection OctalInteger
            ((PermissionResponse) activity).permissionGranted(000);
        }
    }

    //Rationally asking for permission
    public void askPermission(Activity activity, String requestedPermissions, int requestCode) {
        Log.e("MapRoute", "ask permission");
        ActivityCompat.requestPermissions(activity, new String[]{requestedPermissions}, requestCode);
    }

    private int isPermissionGranted(Activity activity, String permission) {
        return ContextCompat.checkSelfPermission(activity, permission);
    }

    public Boolean shouldAskRational(Activity activity, String requestedPermissions) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, requestedPermissions);
    }
}
