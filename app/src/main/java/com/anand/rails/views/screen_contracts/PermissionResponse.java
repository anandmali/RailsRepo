package com.anand.rails.views.screen_contracts;

public interface PermissionResponse {
    void permissionGranted(int requestCode);
    void permissionDenied();
    void askRationalPermission();
    void askPermissionDisabled();
}
