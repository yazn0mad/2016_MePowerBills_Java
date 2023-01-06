package com.yazapps.mepowerbills;

import android.support.v4.app.Fragment;

public class WebActivity extends AllFragmentActivity {

    public static char searchChar;

    @Override
    protected Fragment createFragment() {
        return new WebFragment();
    }
}
