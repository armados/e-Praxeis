package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diaugeia.Organizations;

public class OrganizationListFragment extends BaseFragment {

    private OrganizationListAdapter mAdapter;

    private String uid;
    private Organizations data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            uid = this.getArguments().getString("uid");
        } else {
            uid = savedInstanceState.getString("uid");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_organizations, container, false);

        mAdapter = new OrganizationListAdapter();

        RecyclerView rcv = view.findViewById(R.id.list);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        rcv.setLayoutManager(mLayoutManager);
        rcv.setAdapter(mAdapter);
        rcv.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        mBoxEmpty = view.findViewById(R.id.mBoxEmpty);
        mBoxEmpty.setVisibility(View.GONE);

        mBoxLoading = view.findViewById(R.id.mBoxLoading);
        mBoxError = view.findViewById(R.id.mBoxError);

        if (data == null)
            fetchRemoteData();
        else
            updateUI();

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("uid", uid);
    }

    public void updateUI() {
        Log.d(TAG, "frag updateUI()");

        if (data == null) {
            mBoxError.setVisibility(View.VISIBLE);
            return;
        }

        if (data.getOrganizations().size() == 0)
            mBoxEmpty.setVisibility(View.VISIBLE);
        else
            mBoxEmpty.setVisibility(View.GONE);

        /* Sort list */
        OrganizationListSorter.sort(data.getOrganizations());

        mAdapter.setItems(data.getOrganizations());
    }

    private void fetchRemoteData() {
        AsyncTaskRunner myTask = new AsyncTaskRunner();
        myTask.execute();
    }

    @Nullable
    private Boolean fetchAll() {
        data = Api.fetchOrganizationSupervisedById(uid);
        if (data == null) return null;

        return true;
    }

    @SuppressLint("StaticFieldLeak")
    final class AsyncTaskRunner extends AsyncTask<String, String, Boolean> {
        @Override
        protected void onPreExecute() {
            setFragmentIsLoadingData(true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return fetchAll();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            setFragmentIsLoadingData(false);

            if (result == null) {
                showMessage("???????????????? ???????????????? ??????????????????");
            } else {
                updateUI();
            }

        }
    }

}

