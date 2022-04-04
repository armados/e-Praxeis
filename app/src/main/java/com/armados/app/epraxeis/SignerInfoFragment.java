package com.armados.app.epraxeis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.armados.app.epraxeis.diaugeia.Organization;
import com.armados.app.epraxeis.diaugeia.Signer;

public class SignerInfoFragment extends BaseFragment {

    private TextView txtOrganization;
    private TextView txtName;
    private TextView txtActiveFrom;
    private TextView txtStatus;
    private TextView txtHasOrgRights;
    private TextView txtFavorite;

    private String uid;
    private Signer data;
    private Organization dataOrg;

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

        View view = inflater.inflate(R.layout.fragment_info_signer, container, false);

        mBoxLoading = view.findViewById(R.id.mBoxLoading);
        mBoxError = view.findViewById(R.id.mBoxError);

        txtOrganization = view.findViewById(R.id.txtOrganization);
        txtName = view.findViewById(R.id.txtName);
        txtActiveFrom = view.findViewById(R.id.txtActiveFrom);
        txtStatus = view.findViewById(R.id.txtStatus);
        txtHasOrgRights = view.findViewById(R.id.txtHasOrgRights);

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

        ((SignerActivity) getActivity()).setActivitySubTitle(SignerHelper.getFullNameText(data.getLastName(), data.getFirstName()));

        DictionaryEntity dictRec = Database.getInstance(getActivity())
                .getDictionaryDao()
                .getEntry(Configuration.ORGANIZATIONS, data.getOrganizationId());

        if (dictRec != null)
            txtOrganization.setText(dictRec.getLabel());
        else
            txtOrganization.setText(R.string.missing_dict_entry);

        txtOrganization.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrganizationActivity.class);
                intent.putExtra("uid", data.getOrganizationId());
                startActivity(intent);
            }
        });

        txtName.setText(SignerHelper.getFullNameText(data.getLastName(), data.getFirstName()));

        if (data.getActiveFrom() != null) {
            final String activeFrom = DateTimeFormatters.DATE_FORMAT.format(data.getActiveFrom());
            txtActiveFrom.setText(String.format("Ημερομηνία εγγραφής %s", activeFrom));
        } else
            txtActiveFrom.setVisibility(View.GONE);

        txtStatus.setText(SignerHelper.getActiveText(data.isActive()));

        txtHasOrgRights.setText(SignerHelper.getHasOrganizationSignRightsText(data.isHasOrganizationSignRights()));

        updateFavoriteButton();
    }

    private void toggleFavorite() {
        if (!FavoritesHelper.isFavorite(getActivity(), FavoriteEntity.SIGNER, uid)) {
            FavoritesHelper.insert(getActivity(), FavoriteEntity.SIGNER, uid, SignerHelper.getFullNameText(data.getLastName(), data.getFirstName()), dataOrg.getLabel());
        } else {
            FavoritesHelper.delete(getActivity(), FavoriteEntity.SIGNER, uid);
        }

        updateFavoriteButton();
    }

    private void updateFavoriteButton() {
        if (FavoritesHelper.isFavorite(getActivity(), FavoriteEntity.SIGNER, uid)) {
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
        data = Api.fetchSignerInfoByUid(uid);
        if (data == null) return null;

        dataOrg = Api.fetchOrganizationInfoById(data.getOrganizationId());
        if (dataOrg == null) return null;

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