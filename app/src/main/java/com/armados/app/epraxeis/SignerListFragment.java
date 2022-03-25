package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diavgeia.Signer;
import com.armados.app.epraxeis.diavgeia.Signers;

public class SignerListFragment extends BaseFragment implements AdapterView.OnItemClickListener  {

    private final SignerListAdapter mAdapter = new SignerListAdapter(this);

    private String uid;
    private Signers data;

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

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onResume() {
        super.onResume();

        /* Watch list might have changed */
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("uid", uid);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemClick");

        final Signer rec = mAdapter.getItem(position);

        Intent i = new Intent(view.getContext(), SignerActivity.class);
        i.putExtra("uid", rec.getUid());
        view.getContext().startActivity(i);
    }

    public void updateUI() {
        Log.d(TAG, "frag updateUI()");

        if (data == null) {
            mBoxError.setVisibility(View.VISIBLE);
            return;
        }

        if (data.getSigners().size() == 0)
            mBoxEmpty.setVisibility(View.VISIBLE);
        else
            mBoxEmpty.setVisibility(View.GONE);

        SignerListSorter.sort(data.getSigners());

        mAdapter.setItems(data.getSigners());
    }

    private void fetchRemoteData() {
        AsyncTaskRunner myTask = new AsyncTaskRunner();
        myTask.execute();
    }

    @Nullable
    private Boolean fetchAll() { // FIXME: Org and Unit ????
        data = Api.fetchOrganizationSignersById(uid);

        if (data == null) {
            data = Api.fetchUnitSignersByUid(uid);
            if (data == null) return null;
        }

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
            Log.d(TAG, "THREAD onPostExecute");

            if (result == null)  {
                showMessage("Αδυναμία φόρτωσης δεδομένων");
             } else {
                updateUI();
            }

            setFragmentIsLoadingData(false);
        }
    }

}

