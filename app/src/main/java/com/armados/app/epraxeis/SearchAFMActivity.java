package com.armados.app.epraxeis;

import android.os.Bundle;
import android.view.MenuItem;

import com.armados.app.epraxeis.search.SearchQuery;
import com.armados.app.epraxeis.search.SearchTerm;

public class SearchAFMActivity extends BaseActivity  {

    private String afm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_afm_activity);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            afm = extras.getString("afm");
        }

        setActivityTitle("Πράξεις Δικαιούχου");
        setActivityHomeAsUp();

        startSearch();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startSearch() {
        SearchTerm term = new SearchTerm(SearchTerm.TERM_AFM);
        term.add(afm);

        SearchQuery searchParams = new SearchQuery();
        searchParams.setTerms(term);

        DecisionListEventListener mListener = new DecisionListEventListener() {
            @Override
            public void onSearchStarted() {

            }

            @Override
            public void onSearchSuccess() {
            }

            @Override
            public void onSearchFailure() {
                showMessage("Παρουσιάστηκε σφάλμα");
            }
        };

        Bundle bundle = new Bundle();
        bundle.putSerializable("QUERY_PARAMETERS", searchParams);

        DecisionListFragment frag2 = new DecisionListFragment();
        frag2.setListener(mListener);
        frag2.setArguments(bundle);

        androidx.fragment.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, frag2);
        transaction.commit();
    }

}