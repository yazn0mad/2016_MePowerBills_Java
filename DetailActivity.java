package com.yazapps.mepowerbills;

import android.support.v4.app.Fragment;

public class DetailActivity extends AllFragmentActivity {

    public static char type;
    public static char size;
    public static String contractName;
    public static MyContract sMyContract;

    @Override
    protected Fragment createFragment() {

        switch (type) {
            case 'H':   // Detail setup for 'Hot Time 22 Long'
                return new DetailFragment_2();
            default:    // For all the rest
                return new DetailFragment_1();
        }

    }

}
