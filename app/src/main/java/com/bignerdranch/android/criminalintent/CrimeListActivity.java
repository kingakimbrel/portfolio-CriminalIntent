package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by kingakimbrel on 8/12/16.
 */
public class CrimeListActivity extends SingleFragmentActivity implements CrimeListFragment.Callbacks, CrimeFragment.Callbacks {

    private static final String EXTRA_SUBTITLE_VISIBLE = "com.bignerdranch.android.crimeintent.subtitle";

    @Override
    protected Fragment createFragment() {

        return CrimeListFragment.newInstance(getIntent().getBooleanExtra(EXTRA_SUBTITLE_VISIBLE, false));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    public static Intent newIntent(Context packageContext, boolean subVisible) {
        Intent intent = new Intent(packageContext, CrimeListActivity.class);
        intent.putExtra(EXTRA_SUBTITLE_VISIBLE, subVisible);
        return intent;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail = CrimeFragment.newInstance(crime.getId());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdate(Crime crime) {
        CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
        listFragment.updateUI();
    }

    @Override
    public void onCrimeDelete() {

        CrimeFragment crimeFragment = (CrimeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.detail_fragment_container);

        if (crimeFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(crimeFragment).commit();

            CrimeListFragment listFragment = (CrimeListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);
            listFragment.updateUI();
        }
    }
}
