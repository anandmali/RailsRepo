package com.anand.loktratask.views.screen_contracts;

/**
 * Created by user on 10-01-2017.
 *
 */

public interface PermissionResponse {
    void permissionGranted(int requestCode);
    void permissionDenied();
    void askRationalPermission();
    void askPermissionDisabled();
}
