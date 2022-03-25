package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.armados.app.epraxeis.diavgeia.Organization;

public class OrganizationInfoFragment extends BaseFragment {

    private TextView txt_website;
    private TextView txt_organization;
    private TextView txt_organization_category;
    private TextView txt_supervisor;
    private TextView txt_email;
    private TextView txt_active;
    private TextView txtFavorite;

    private String uid;
    private Organization data;

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
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_info_organization, container, false);

        mBoxLoading = view.findViewById(R.id.mBoxLoading);
        mBoxError = view.findViewById(R.id.mBoxError);

        txt_supervisor = view.findViewById(R.id.txt_supervisor);
        txt_organization = view.findViewById(R.id.txt_organization);
        txt_organization_category = view.findViewById(R.id.txt_organization_category);
        txt_website = view.findViewById(R.id.txt_website);
        txt_email = view.findViewById(R.id.txt_email);
        txt_active = view.findViewById(R.id.txt_active);

        txtFavorite = view.findViewById(R.id.txtFavorite);
        txtFavorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                toggleFavorite();
             }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (data == null)
            fetchRemoteData();
        else
            updateUI();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("uid", uid);
    }

    public void updateUI() {
        Log.d(TAG, "frag updateUI()");

        ((OrganizationActivity)getActivity()).setActivitySubTitle(data.getLabel());

        if (data.getSupervisorLabel() != null)
            txt_supervisor.setText(data.getSupervisorLabel());
        else
            txt_supervisor.setText(" ");

        txt_supervisor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (data.getSupervisorLabel() == null)
                    return;

                Intent intent = new Intent(getActivity(), OrganizationActivity.class);
                intent.putExtra("uid", data.getSupervisorId());
                startActivity(intent);
            }
        });

        txt_organization.setText(data.getLabel());

        DictionaryEntity category = Database.getInstance(getActivity())
                .getDictionaryDao()
                .getEntry(Configuration.DICTIONARY_ORG_CATEGORY, data.getCategory());

        if (category != null)
            txt_organization_category.setText(category.getLabel());
        else
            txt_organization_category.setText(String.format("%s", R.string.missing_dict_entry));

        txt_website.setText("Ιστότοπος: " + data.getWebsite());
        txt_website.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (data.getWebsite() == null)
                    return;

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(data.getWebsite()));
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent
                    showMessage("Δεν βρέθηκε πρόγραμμα προβολής ιστοσελίδων");
                }
            }
        });

        txt_email.setText("Διεύθυνση email: " + data.getOdeManagerEmail());
        txt_email.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (data.getOdeManagerEmail() == null)
                    return;

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{data.getOdeManagerEmail()});
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent
                    showMessage("Δεν βρέθηκε πρόγραμμα αλληλογραφίας");
                }
            }
        });

        txt_active.setText(OrganizationHelper.getStatusText(data.getStatus()));

        updateFavoriteButton();
    }

    private void toggleFavorite() {
        if (!FavoriteHelper.isFavorite(getActivity(), FavoriteEntity.ORGANIZATION, uid)) {
            FavoriteHelper.insert(getActivity(), FavoriteEntity.ORGANIZATION, uid, data.getLabel(), data.getSupervisorLabel());
        } else {
            FavoriteHelper.delete(getActivity(), FavoriteEntity.ORGANIZATION, uid);
        }

        updateFavoriteButton();
    }

    private void updateFavoriteButton() {
        if (FavoriteHelper.isFavorite(getActivity(), FavoriteEntity.ORGANIZATION, uid)) {
            txtFavorite.setText("Αφαίρεση από τα αγαπημένα");
            txtFavorite.setTextColor(Color.BLACK);
            txtFavorite.setBackgroundResource(R.drawable.bg_follow_true);
        } else {
            txtFavorite.setText("Προσθήκη στα αγαπημένα");
            txtFavorite.setTextColor(Color.WHITE);
            txtFavorite.setBackgroundResource(R.drawable.bg_follow_false);
        }
    }

    private void fetchRemoteData() {
        AsyncTaskRunner myTask = new AsyncTaskRunner();
        myTask.execute();
    }

    @Nullable
    private Boolean fetchAll() {
        data = Api.fetchOrganizationInfoById(uid);
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

            if (result == null) // error fetching remote data
                showMessage("Αδυναμία φόρτωσης των δεδομένων");
            else
                updateUI();
        }
    }

}