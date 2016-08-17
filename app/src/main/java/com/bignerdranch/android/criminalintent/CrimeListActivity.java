package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by kingakimbrel on 8/12/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
