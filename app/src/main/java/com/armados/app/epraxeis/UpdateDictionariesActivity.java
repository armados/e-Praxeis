package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.armados.app.epraxeis.diaugeia.DecisionType;
import com.armados.app.epraxeis.diaugeia.DecisionTypes;
import com.armados.app.epraxeis.diaugeia.Dictionaries;
import com.armados.app.epraxeis.diaugeia.Dictionary;
import com.armados.app.epraxeis.diaugeia.DictionaryItem;
import com.armados.app.epraxeis.diaugeia.DictionaryItems;
import com.armados.app.epraxeis.diaugeia.Organization;
import com.armados.app.epraxeis.diaugeia.Organizations;

public class UpdateDictionariesActivity extends BaseActivity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dictionaries);

        setActivityTitle("Ενημέρωση Λεξικών");
        setActivityHomeAsUp();

        progressBar = new ProgressDialog(this);

        final Button btnRefreshDictionaries = findViewById(R.id.btnRefreshDictionaries);
        btnRefreshDictionaries.setOnClickListener(v -> {
            AsyncTaskRunner myTask = new AsyncTaskRunner();
            myTask.execute();
        });

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

    @SuppressLint("StaticFieldLeak")
    final class AsyncTaskRunner extends AsyncTask<String, String, Boolean> {
        @Override
        protected void onPreExecute() {
            Log.d(TAG, "THREAD onPreExecute");
            // progressBar = new ProgressDialog(getA);
            progressBar.setCancelable(false);//you can cancel it by pressing back button
            progressBar.setMessage("Παρακαλώ περιμένετε...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.setProgress(0);//initially progress is 0
            progressBar.setMax(100);//sets the maximum value 100
            progressBar.show();//displays the progress bar
        }

        @Override
        protected Boolean doInBackground(String... params) {

            /* Λήψη φορεών */
            final Organizations orgList = Api.fetchOrganizations();
            if (orgList == null) return null;

            for (int i = 0; i < orgList.getOrganizations().size(); i++) {
                final Organization res = orgList.getOrganizations().get(i);

                DictionaryEntity dd = new DictionaryEntity();
                dd.setDictionary(Config.ORGANIZATIONS);
                dd.setUid(res.getUid());
                dd.setLabel(res.getLabel());

                Database.getInstance(getApplicationContext())
                        .getDictionaryDao()
                        .insert(dd);
            }

            /* Λήψη Λεξικών */
            Dictionaries dictList = Api.fetchDictionaries();
            if (dictList == null) return null;

            for (int i = 0; i < dictList.getDictionaries().size(); i++) {
                final Dictionary rec = dictList.getDictionaries().get(i);

                final DictionaryItems res = Api.fetchDictionaryItemsById(rec.getUid());
                if (res == null) return null;

                for (int j = 0; j < res.getItems().size(); j++) {
                    final DictionaryItem item = res.getItems().get(j);

                    DictionaryEntity dd = new DictionaryEntity();
                    dd.setDictionary(res.getName());
                    dd.setUid(item.getUid());
                    dd.setLabel(item.getLabel());

                    Database.getInstance(getApplicationContext())
                            .getDictionaryDao()
                            .insert(dd);
                }

            }


            /* Λήψη Τύπων Πράξεων */
            DecisionTypes typesList = Api.fetchDecisionTypes();
            if (typesList == null) {
                return null;
            }

            for (int i = 0; i < typesList.getDecisionTypes().size(); i++) {
                final DecisionType res = typesList.getDecisionTypes().get(i);

                DictionaryEntity dd = new DictionaryEntity();
                dd.setDictionary(Config.DECISION_TYPES);
                dd.setUid(res.getUid());
                dd.setLabel(res.getLabel());

                Database.getInstance(getParent())
                        .getDictionaryDao()
                        .insert(dd);
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressBar.dismiss();

            if (result == null) {
                showMessage("Η ενημέρωση των λεξικών απέτυχε");
                return;
            }

            showMessage("Η ενημέρωση των λεξικών ολοκληρώθηκε επιτυχώς");
        }

    }

}