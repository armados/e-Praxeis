package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.armados.app.epraxeis.diavgeia.Decision;
import com.armados.app.epraxeis.diavgeia.Signer;
import com.armados.app.epraxeis.diavgeia.Unit;

import java.util.ArrayList;

public class DecisionActivity extends BaseActivity {

    private TextView txt_ada;
    private TextView txt_protocolNumber;
    private TextView txt_issueDate;
    private TextView txt_subject;
    private TextView txt_publishTimestamp;
    private TextView txt_submissionTimestamp;
    private TextView txt_decisionType;
    private TextView txt_status;

    private TextView txtDebugJSON;

    private Button btn_downloadDecision;

    private SimpleAdapter mAdapter;

    private String ada;
    private Decision dec;
    private final ArrayList<Signer> signersList = new ArrayList<>();
    private final ArrayList<Unit> unitsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ada = extras.getString("ada");
        }

        setActivityTitle("Πράξη");
        setActivitySubTitle("Φόρτωση...");
        setActivityHomeAsUp();

        mProgressBar = findViewById(R.id.mProgressBar);

        txt_ada = findViewById(R.id.txt_ada);
        txt_status = findViewById(R.id.txt_status);
        txt_subject = findViewById(R.id.txt_subject);
        txt_publishTimestamp = findViewById(R.id.txt_publishTimestamp);
        txt_submissionTimestamp = findViewById(R.id.txt_submissionTimestamp);
        txt_decisionType = findViewById(R.id.txt_decisionType);
        txt_protocolNumber = findViewById(R.id.txt_protocolNumber);
        txt_issueDate = findViewById(R.id.txt_issueDate);
        btn_downloadDecision = findViewById(R.id.btn_downloadDecision);

        txtDebugJSON = findViewById(R.id.txtDebugJSON);
        txtDebugJSON.setOnClickListener(v -> {
            String url = "https://diavgeia.gov.gr/opendata/decisions/" + ada;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Define what your app should do if no activity can handle the intent
                showMessage("Δεν βρέθηκε πρόγραμμα προβολής ιστοσελίδων");
            }
        });


        mAdapter = new SimpleAdapter();
        mAdapter.setClickListener(new SimpleAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (mAdapter.getItem(position) instanceof SimpleSigner) {
                    SimpleSigner rec = (SimpleSigner) mAdapter.getItem(position);
                    Intent i = new Intent(view.getContext(), SignerActivity.class);
                    i.putExtra("uid", rec.getUid());
                    view.getContext().startActivity(i);
                } else if (mAdapter.getItem(position)  instanceof SimpleOrganization) {
                    SimpleOrganization rec = (SimpleOrganization) mAdapter.getItem(position);
                    Intent i = new Intent(view.getContext(), OrganizationActivity.class);
                    i.putExtra("uid", rec.getUid());
                    view.getContext().startActivity(i);
                } else if (mAdapter.getItem(position)  instanceof SimpleUnit) {
                    SimpleUnit rec = (SimpleUnit) mAdapter.getItem(position);
                    Intent i = new Intent(view.getContext(), UnitActivity.class);
                    i.putExtra("uid", rec.getUid());
                    view.getContext().startActivity(i);
                }
            }
        });

        RecyclerView rvInfo = findViewById(R.id.listInfo);
        rvInfo.setLayoutManager(new LinearLayoutManager(this));
        rvInfo.setHasFixedSize(false);
        rvInfo.setAdapter(mAdapter);
        rvInfo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        if (dec == null)
            fetchRemoteData();
        else
            updateUI();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ada", ada);
    }

    @Override
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        ada = inState.getString("ada");
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

    private void updateUI() {
        setActivitySubTitle(String.format("ΑΔΑ %s", dec.getAda()));

        txt_ada.setText(dec.getAda());

        txt_status.setText(DecisionHelper.getStatusText(dec.getStatus()));

        DictionaryEntity decisionType = Database.getInstance(this)
                .getDictionaryDao()
                .getEntry(Configuration.DECISION_TYPES, dec.getDecisionTypeId());

        if (decisionType != null)
            txt_decisionType.setText(String.format("%s (%s)", decisionType.getLabel(), decisionType.getUid()));
        else
            txt_decisionType.setText(R.string.missing_dict_entry);

        txt_subject.setText(dec.getSubject());

        final String pubDateStr = DateTimeFormatters.TIMESTAMP_FORMAT.format(dec.getPublishTimestamp());

        txt_publishTimestamp.setText(pubDateStr);

        final String subDateStr = DateTimeFormatters.TIMESTAMP_FORMAT.format(dec.getSubmissionTimestamp());
        txt_submissionTimestamp.setText(subDateStr);

        txt_protocolNumber.setText(dec.getProtocolNumber());

        final String issueDateStr = DateTimeFormatters.DATE_FORMAT.format(dec.getSubmissionTimestamp());
        txt_issueDate.setText(issueDateStr);

        if (dec.getDocumentUrl() == null) {
            btn_downloadDecision.setVisibility(View.GONE);
        } else {
            btn_downloadDecision.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(dec.getDocumentUrl()), "application/pdf");

                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        // Define what your app should do if no activity can handle the intent
                        showMessage("Δεν βρέθηκε πρόγραμμα προβολής PDF εγγράφων");
                    }
                }
            });
        }

        mAdapter.removeAllItems();

        {
            DictionaryEntity organization = Database.getInstance(this)
                    .getDictionaryDao()
                    .getEntry(Configuration.ORGANIZATIONS, dec.getOrganizationId());

            SimpleOrganization rec = new SimpleOrganization();
            rec.setUid(dec.getOrganizationId());

            if (organization != null)
                rec.setLabel(organization.getLabel());
            else
                rec.setLabel(String.format("%s", R.string.missing_dict_entry));

            rec.setLabelHeader("Φορέας");

            mAdapter.addItem(rec);
        }


        for (Signer s : signersList) {
            SimpleSigner rec = new SimpleSigner();
            rec.setUid(s.getUid());
            rec.setLabel(s.getLabel());

            rec.setLabelHeader("Υπογράφων");

            DictionaryEntity organization = Database.getInstance(this)
                    .getDictionaryDao()
                    .getEntry(Configuration.ORGANIZATIONS, s.getOrganizationId());

            if (organization != null)
                rec.setDescription(organization.getLabel());
            else
                rec.setDescription(String.format("%s", R.string.missing_dict_entry));

            mAdapter.addItem(rec);
        }

        for (Unit s : unitsList) {
            SimpleUnit rec = new SimpleUnit();
            rec.setUid(s.getUid());
            rec.setLabel(s.getLabel());

            rec.setLabelHeader("Οργανωτική μονάδα");

            DictionaryEntity parent = Database.getInstance(this)
                    .getDictionaryDao()
                    .getEntry(Configuration.ORGANIZATIONS, s.getParentId());

            if (parent != null)
                rec.setDescription(parent.getLabel());
            else
                rec.setDescription("Οργανωτική Μονάδα");

            mAdapter.addItem(rec);
        }

         mAdapter.addItems(dec.getExtraFields().getDetailInfoItems(this));

    }

    /* =========================================== */

    private void fetchRemoteData() {
        AsyncTaskRunner myTask = new AsyncTaskRunner();
        myTask.execute();
    }

    @Nullable
    private Boolean fetchAll() {

        // Fetch Decision by ada
        dec = Api.fetchDecisionDetailsByAda(ada);
        if (dec == null) return null;

        // Fetch extra info for each signer
        for (String strSigner : dec.getSignerIds()) {
            final Signer rec = Api.fetchSignerInfoByUid(strSigner);
            if (rec == null) return null;
            signersList.add(rec);
        }

        // Fetch extra info for each unit
        for (String strUnit : dec.getUnitIds()) {
            final Unit rec = Api.fetchUnitInfoByUid(strUnit);
            if (rec == null) return null;
            unitsList.add(rec);
        }

        return true;
    }

    // =================

    @SuppressLint("StaticFieldLeak")
    class AsyncTaskRunner extends AsyncTask<String, String, Boolean> {
        @Override
        protected void onPreExecute() {
            setActivityIsLoadingData(true);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return fetchAll();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            setActivityIsLoadingData(false);

            if (result != null)
                updateUI();
            else
                showMessage("Αδυναμία φόρτωσης δεδομένων");

        }


    }


}
