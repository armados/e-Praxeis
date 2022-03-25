package com.armados.app.epraxeis;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.armados.app.epraxeis.search.SearchQuery;
import com.armados.app.epraxeis.search.SearchTerm;

public class SearchActivity extends BaseActivity {

    private EditText txtTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setActivityTitle("Αναζήτηση");
        setActivityHomeAsUp();

        txtTextBox = findViewById(R.id.txtTextBox);

        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideVirtualKeyboard();

                startSearch();
            }
        });
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

    private void hideVirtualKeyboard() {
        // this will give us the view
        // which is currently focus in UI
        View view = this.getCurrentFocus();

        if (view == null) return;

        try {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }

    private void startSearch() {
        final String str = txtTextBox.getText().toString();

        SearchTerm term = new SearchTerm(SearchTerm.TERM_SUBJECT);
        term.add(str);

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