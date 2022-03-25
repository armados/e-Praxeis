package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.armados.app.epraxeis.search.SearchQuery;
import com.armados.app.epraxeis.search.SearchTerm;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class SignerActivity extends BaseActivity {

    private SignerInfoFragment fragInfo;
    private DecisionListFragment frag2;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "func onCreate()");
        setContentView(R.layout.activity_signer);

        if (savedInstanceState != null) {
            uid = savedInstanceState.getString("uid");
            activityTitle = savedInstanceState.getString("activityTitle");
            activitySubTitle = savedInstanceState.getString("activitySubTitle");
        } else {
            uid = getIntent().getExtras().getString("uid");
            activityTitle = "Υπογράφων";
            activitySubTitle = "Φόρτωση...";
        }

        if (uid == null) {
            Log.e(TAG, "Required parameter missing");
            finish();
            return;
        }

        setActivityHomeAsUp();
        setActivityTitle(activityTitle);
        setActivitySubTitle(activitySubTitle);

        Bundle bundleUid = new Bundle();
        bundleUid.putString("uid", uid);

        fragInfo = new SignerInfoFragment();
        fragInfo.setArguments(bundleUid);

        SearchTerm term = new SearchTerm(SearchTerm.TERM_SIGNER_UID);
        term.add(uid);

        SearchQuery searchParams = new SearchQuery();
        searchParams.setTerms(term);


        Bundle bundle = new Bundle();
        bundle.putSerializable("QUERY_PARAMETERS", searchParams);
        frag2 = new DecisionListFragment();
        frag2.setArguments(bundle);

        mProgressBar = findViewById(R.id.mProgressBar);

        ScreenSlidePagerAdapter sa = new ScreenSlidePagerAdapter(getSupportFragmentManager(), getLifecycle());

        mViewPager = findViewById(R.id.mViewPager);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragInfo);
        fragmentList.add(frag2);

        sa.setFragments(fragmentList);
        mViewPager.setAdapter(sa);

        mTabLayout = findViewById(R.id.mTabLayout);

        new TabLayoutMediator(mTabLayout, mViewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Αρχική");
                            break;
                        case 1:
                            tab.setText("Πράξεις");
                            break;
                    }
                }).attach();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("uid", uid);
        outState.putString("activityTitle", activityTitle);
        outState.putString("activitySubTitle", activitySubTitle);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        uid = inState.getString("uid");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "func onOptionsItemSelected()");
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
