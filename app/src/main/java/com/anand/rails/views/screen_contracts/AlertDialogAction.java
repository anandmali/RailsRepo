package com.anand.rails.views.screen_contracts;

public interface AlertDialogAction {
    void onPositiveClick(int requestCode);
    void onNegativeClick(int requestCode);
}
