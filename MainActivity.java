package com.yazapps.mepowerbills;

import android.support.v4.app.Fragment;

import static com.yazapps.mepowerbills.StaticData.isFirstTime;


// 1. ADD TOAST "WELCOME" FOR FIRST TIMERS.
// 2. ADD CARD VIEW FRAGMENT.

public class MainActivity extends AllFragmentActivity {

    @Override
    protected Fragment createFragment() {

        if (MyList.mySelections.isEmpty()) {
            isFirstTime = true;
        }

        if (isFirstTime) {

            return new MainContractListFragment();
        }
        return new MyListFragment();
    }

}
