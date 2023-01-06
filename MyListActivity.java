package com.yazapps.mepowerbills;

import android.support.v4.app.Fragment;

public class MyListActivity extends AllFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new MyListFragment();
    }
}
