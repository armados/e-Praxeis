package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListOrganizationsActivity extends BaseActivity {

    private ListOrganizationsAdapter mAdapter;

    private List<DictionaryEntity> listOrganizations;

    private Handler mHandler;
    private Runnable longRunningTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_organizations);

        setActivityTitle("Κατάλογος φορέων");
        setActivityHomeAsUp();

        mHandler = new Handler();

        mAdapter = new ListOrganizationsAdapter();
        listOrganizations = Database.getInstance(getApplicationContext())
                .getDictionaryDao()
                .getAllEntries(Config.ORGANIZATIONS);

        mAdapter.setData(listOrganizations);

        RecyclerView rcv = findViewById(R.id.listOrganizations);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setAdapter(mAdapter);
        rcv.setHasFixedSize(true);
        rcv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        EditText editTextSearch = findViewById(R.id.editTextFilterList);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() < 3)
                    return;

                if (longRunningTask != null)
                    mHandler.removeCallbacks(longRunningTask);

                longRunningTask = new Runnable() {
                    @Override
                    public void run() {
                        filter(editable.toString());
                    }
                };

                mHandler.post(longRunningTask);
            }

        });

    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        List<DictionaryEntity> filteredItems = new ArrayList<>();

        //looping through existing elements
        for (DictionaryEntity s : listOrganizations) {
            //if the existing elements contains the search input
            if (s.getLabel().toUpperCase().contains(text.toUpperCase())) {
                //adding the element to filtered list
                filteredItems.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        mAdapter.setData(filteredItems);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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