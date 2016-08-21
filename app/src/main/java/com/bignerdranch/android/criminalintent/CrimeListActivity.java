package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by kingakimbrel on 8/12/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    private static final String EXTRA_SUBTITLE_VISIBLE = "com.bignerdranch.android.crimeintent.subtitle";

    @Override
    protected Fragment createFragment() {

        return CrimeListFragment.newInstance(getIntent().getBooleanExtra(EXTRA_SUBTITLE_VISIBLE, false));
    }

    public static Intent newIntent(Context packageContext, boolean subVisible) {
        Intent intent = new Intent(packageContext, CrimeListActivity.class);
        intent.putExtra(EXTRA_SUBTITLE_VISIBLE, subVisible);
        return intent;
    }
}
