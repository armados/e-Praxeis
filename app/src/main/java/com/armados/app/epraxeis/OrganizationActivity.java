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

public class OrganizationActivity extends BaseActivity {

    private OrganizationInfoFragment fragInfo;
    private DecisionListFragment frag2;
    private OrganizationListFragment fragSupervisedOrganizations;
    private SignerListFragment fragSigners;
    private UnitListFragment fragUnits;

    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);

        if (savedInstanceState != null) {
            uid = savedInstanceState.getString("uid");
            activityTitle = savedInstanceState.getString("activityTitle");
            activitySubTitle = savedInstanceState.getString("activitySubTitle");
        } else {
            uid = getIntent().getExtras().getString("uid");
            activityTitle = "Φορέας";
            activitySubTitle = "Φόρτωση...";
        }

        if (uid == null) {
            Log.e(TAG, "Required parameter missing");
            finish();
            return;
        }

        setActivityTitle(activityTitle);
        setActivitySubTitle(activitySubTitle);
        setActivityHomeAsUp();

        Bundle bundleUid = new Bundle();
        bundleUid.putString("uid", uid);

        fragInfo = new OrganizationInfoFragment();
        fragInfo.setArguments(bundleUid);

        SearchTerm term = new SearchTerm(SearchTerm.TERM_ORGANIZATION_UID);
        term.add(uid);

        SearchQuery searchParams = new SearchQuery();
        searchParams.setTerms(term);

        Bundle bundle = new Bundle();
        bundle.putSerializable("QUERY_PARAMETERS", searchParams);

        frag2 = new DecisionListFragment();
        frag2.setArguments(bundle);

        fragSupervisedOrganizations = new OrganizationListFragment();
        fragSupervisedOrganizations.setArguments(bundleUid);

        fragSigners = new SignerListFragment();
        fragSigners.setArguments(bundleUid);

        fragUnits = new UnitListFragment();
        fragUnits.setArguments(bundleUid);

        mBoxLoading = findViewById(R.id.mBoxLoading);

        ScreenSlidePagerAdapter sa = new ScreenSlidePagerAdapter(getSupportFragmentManager(), getLifecycle());

        mViewPager = findViewById(R.id.mViewPager);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragInfo);
        fragmentList.add(frag2);
        fragmentList.add(fragSupervisedOrganizations);
        fragmentList.add(fragSigners);
        fragmentList.add(fragUnits);

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
                        case 2:
                            tab.setText("Φορείς που εποπτεύει");
                            break;
                        case 3:
                            tab.setText("Υπογράφοντες");
                            break;
                        case 4:
                            tab.setText("Οργανωτικές Μονάδες");
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
