package com.anand.loktratask.views.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.anand.loktratask.R;
import com.anand.loktratask.dagger.TaskApplication;
import com.anand.loktratask.utils.AlertDialogUtils;
import com.anand.loktratask.utils.PermissionCheck;
import com.anand.loktratask.views.presenters.MapPresenter;
import com.anand.loktratask.views.screen_contracts.AlertDialogAction;
import com.anand.loktratask.views.screen_contracts.MapScreen;
import com.anand.loktratask.views.screen_contracts.PermissionResponse;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import ng.max.slideview.SlideView;

public class MapRoutingActivity extends GoogleApiClientActivity
        implements
        MapScreen,
        PermissionResponse,
        OnMapReadyCallback,
        AlertDialogAction,
        LocationListener {

    private static final int REQUEST_PERMISSIONS = 9;
    private static final int ALERT_REQUEST_PERMISSIONS_RATIONALLY = 8;
    private static final int ALERT_REQUEST_OPEN_APP_SETTINGS = 7;
    private static final int ALERT_REQUEST_ENABLE_GPS = 6;
    private GoogleMap googleMap;
    private Location location;
    private static Double latitude;
    private static Double longitude;

    @Inject
    MapPresenter mapPresenter;

    @Inject
    PermissionCheck permissionCheck;

    @BindView(R.id.slideView)
    SlideView _slideView;

    @BindString(R.string.alert_header_use_gps) String alert_header_use_gps;
    @BindString(R.string.alert_header_open_app_settings) String alert_header_open_app_settings;
    @BindString(R.string.alert_message_permission_rationally) String alert_message_permission_rationally;
    @BindString(R.string.alert_message_permission_denied) String alert_message_permission_denied;
    @BindString(R.string.alert_header_enable_gps) String alert_header_enable_gps;
    @BindString(R.string.alert_message_enable_gps) String alert_message_enable_gps;
    @BindString(R.string.btn_yes) String btn_yes;
    @BindString(R.string.btn_no) String btn_no;
    @BindString(R.string.btn_allow) String btn_allow;
    @BindString(R.string.btn_deny) String btn_deny;
    @BindString(R.string.btn_enable) String btn_enable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_routing);

        ((TaskApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        createGoogleMap();

    }

    //Create google map and set onCreate listener for the map
    private void createGoogleMap() {
        //Create map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        super.connectGoogleAPiClient();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.e("MapRoute", "onResume");
        getLocationPermission();
    }

    private void getLocationPermission() {
        mapPresenter.getLocationPermission(this, REQUEST_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionGranted = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionGranted = permissionGranted + permission;
        }
        if ((grantResults.length > 0) && permissionGranted == PackageManager.PERMISSION_GRANTED) {
            permissionGranted(REQUEST_PERMISSIONS);
            Log.e("Result", "Granted");
        } else {
            if (permissionCheck.shouldAskRational(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                askRationalPermission();
            } else {
                askPermissionDisabled();
            }
            Log.e("Result", "Denied");
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void permissionGranted(int requestCode) {
        final LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        //Check if GPS is enabled
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(getGoogleApiClient());
            //Get user current location and set  up the map
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                //Set up the google map with the respective attributes
                setGoogleMap();
            } else {
                Log.e("Location", "null");
            }
        }
    }

    private void buildAlertMessageNoGps() {
        AlertDialogUtils.showDialog(
                this,
                alert_header_enable_gps,
                alert_message_enable_gps,
                btn_enable,
                btn_no,
                ALERT_REQUEST_ENABLE_GPS);
    }

    @Override
    public void permissionDenied() {
        AlertDialogUtils.showDialog(
                this,
                alert_header_open_app_settings,
                alert_message_permission_denied,
                btn_yes,
                btn_no,
                ALERT_REQUEST_OPEN_APP_SETTINGS);
    }

    @Override
    public void askRationalPermission() {
        AlertDialogUtils.showDialog(
                this,
                alert_header_use_gps,
                alert_message_permission_rationally,
                btn_allow,
                btn_deny,
                ALERT_REQUEST_PERMISSIONS_RATIONALLY);
    }

    @Override
    public void askPermissionDisabled() {
        AlertDialogUtils.showDialog(
                this,
                alert_header_open_app_settings,
                alert_message_permission_denied,
                btn_yes,
                btn_no,
                ALERT_REQUEST_OPEN_APP_SETTINGS);
    }

    @Override
    public void onPositiveClick(int requestCode) {
        switch (requestCode) {
            case ALERT_REQUEST_PERMISSIONS_RATIONALLY:
                mapPresenter.askPermission(this, REQUEST_PERMISSIONS);
                break;
            case ALERT_REQUEST_OPEN_APP_SETTINGS:
                opeAppSettings();
                break;
            case ALERT_REQUEST_ENABLE_GPS:
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                finish();
                break;
        }
    }

    @Override
    public void onNegativeClick(int requestCode) {
        finish();
    }

    //Open app settings if ask permissions are being denied
    private void opeAppSettings() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + getPackageName()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(intent);
        finish();
    }

    //Set the map depending on the location attributes
    private void setGoogleMap() {

        //If google map is null, create the map once again
        if (null == googleMap) {
            createGoogleMap();
            return;
        }

        //Clear all the previous map markers
        googleMap.clear();

        //Set google map type
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }

        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(false);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude) , 14.0f));

    }

    @SuppressWarnings("unused")
    private LatLng getLatLang() {
        return new LatLng(location.getLatitude(), longitude);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        permissionGranted(REQUEST_PERMISSIONS);
    }

    @Override
    public void onProviderDisabled(String provider) {
        permissionGranted(REQUEST_PERMISSIONS);
    }
}
