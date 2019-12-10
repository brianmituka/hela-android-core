package com.creativeconsillium.drumsforafrica.helaapp.Activity.utils;

import com.google.firebase.database.DataSnapshot;

import java.math.BigDecimal;

public interface OnGetDataListener {
    void onSuccess(DataSnapshot dataSnapshot);
    void onStart();
    void onFailure();
}
