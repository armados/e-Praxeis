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

import com.armados.app.epraxeis.diaugeia.Decision;
import com.armados.app.epraxeis.diaugeia.Signer;
import com.armados.app.epraxeis.diaugeia.Unit;

import java.util.ArrayList;

public class DecisionActivity extends BaseActivity {

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

        btn_downloadDecision = findViewById(R.id.btn_downloadDecision);


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
                } else if (mAdapter.getItem(position)  instanceof SimpleAFM) {
                    SimpleAFM rec = (SimpleAFM) mAdapter.getItem(position);
                    Intent i = new Intent(view.getContext(), SearchAFMActivity.class);
                    i.putExtra("afm", rec.getUid());
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

        mAdapter.removeAllItems();

        {
            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("Θέμα");
            rec.setLabel(dec.getSubject());
            mAdapter.addItem(rec);
        }

        {
            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("ΑΔΑ");
            rec.setLabel(dec.getAda());
            mAdapter.addItem(rec);
        }

        {
            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("Κατάσταση");
            rec.setLabel(DecisionHelper.getStatusText(dec.getStatus()));
            mAdapter.addItem(rec);
        }

        {
            DictionaryEntity decisionType = Database.getInstance(this)
                    .getDictionaryDao()
                    .getEntry(Config.DECISION_TYPES, dec.getDecisionTypeId());

            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("Τύπος πράξης");

            if (decisionType != null)
                rec.setLabel(String.format("%s (%s)", decisionType.getLabel(), decisionType.getUid()));
            else
                rec.setLabel(String.format("%s", R.string.missing_dict_entry));

            mAdapter.addItem(rec);
        }

        {
            final String pubDateStr = DateTimeFormatters.TIMESTAMP_FORMAT.format(dec.getPublishTimestamp());

            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("Ημερομηνία ανάρτησης");
            rec.setLabel(pubDateStr);
            mAdapter.addItem(rec);
        }

        {
            final String subDateStr = DateTimeFormatters.TIMESTAMP_FORMAT.format(dec.getSubmissionTimestamp());

            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("Ημερομηνία τελευταίας τροποποίησης");
            rec.setLabel(subDateStr);
            mAdapter.addItem(rec);
        }

        {
            final String issueDateStr = DateTimeFormatters.DATE_FORMAT.format(dec.getSubmissionTimestamp());

            SimpleLabel rec = new SimpleLabel();
            rec.setLabelHeader("Αρ. πρωτοκόλλου");
            rec.setLabel(dec.getProtocolNumber() + "\n" + issueDateStr);
            mAdapter.addItem(rec);
        }

        {
            DictionaryEntity organization = Database.getInstance(this)
                    .getDictionaryDao()
                    .getEntry(Config.ORGANIZATIONS, dec.getOrganizationId());

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
                    .getEntry(Config.ORGANIZATIONS, s.getOrganizationId());

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
                    .getEntry(Config.ORGANIZATIONS, s.getParentId());

            if (parent != null)
                rec.setDescription(parent.getLabel());
            else
                rec.setDescription("Οργανωτική Μονάδα");

            mAdapter.addItem(rec);
        }

         mAdapter.addItems(dec.getExtraFields().getDetailInfoItems(this));


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
