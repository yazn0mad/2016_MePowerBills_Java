package com.yazapps.mepowerbills;

import android.support.v4.app.Fragment;

public class MeterActivity extends AllFragmentActivity {

    public static MyContract sMyContract;

    @Override
    protected Fragment createFragment() {
        return new MeterFragment();
    }
}


