package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by kingakimbrel on 8/16/16.
 */
public class CrimePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private boolean mSubtitleVisible;

    private static final String EXTRA_CRIME_ID = "com.bignerdranch.android.crimeintent.crime_id";
    private static final String EXTRA_SUBTITLE_VISIBLE = "com.bignerdranch.android.crimeintent.subtitle";


    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    public static Intent newIntent(Context packageContext, UUID crimeId, boolean subVisible) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(EXTRA_SUBTITLE_VISIBLE, subVisible);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_vp);

        mCrimes = CrimeLab.getCrimeLab(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mSubtitleVisible = getIntent().getBooleanExtra(EXTRA_SUBTITLE_VISIBLE, false);

        this.setCurrentCrime();
    }

    private void setCurrentCrime() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals((crimeId))) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Nullable
    @Override
    public Intent getParentActivityIntent() {
        Intent intent = CrimeListActivity.newIntent(this, mSubtitleVisible);
        return intent;
    }
}
