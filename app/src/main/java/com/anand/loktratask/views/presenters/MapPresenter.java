package com.anand.loktratask.views.presenters;

import android.util.Log;

import com.anand.loktratask.utils.PermissionCheck;
import com.anand.loktratask.views.activities.MapRoutingActivity;

import javax.inject.Inject;

public class MapPresenter {

    private PermissionCheck permissionCheck;

    @Inject
    MapPresenter(PermissionCheck permissionCheck) {
        this.permissionCheck = permissionCheck;
    }

    //Run time permission
    //Ask permission to access location
    public void getLocationPermission(MapRoutingActivity mapRoutingActivity, int REQUEST_PERMISSIONS) {
        Log.e("MapRoute", "Check permission");
        permissionCheck.requestAppPermissions(mapRoutingActivity, android.Manifest.permission.ACCESS_FINE_LOCATION,
                REQUEST_PERMISSIONS );
    }

    public void askPermission(MapRoutingActivity mapRoutingActivity, int REQUEST_PERMISSIONS) {
        permissionCheck.askPermission(mapRoutingActivity, android.Manifest.permission.ACCESS_FINE_LOCATION,
                REQUEST_PERMISSIONS);
    }
}
