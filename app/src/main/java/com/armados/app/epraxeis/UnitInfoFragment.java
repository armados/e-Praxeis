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
import com.armados.app.epraxeis.diaugeia.Unit;


public class UnitInfoFragment extends BaseFragment {

    private TextView txtParentOrgUnit;
    private TextView txtTitle;
    private TextView txtCategory;
    private TextView txtStatus;
    private TextView txtActiveFrom;
    private TextView txt_text7;
    private TextView txtFavorite;

    private String uid;
    private Unit data;
    private Organization parentOrg;
    private Unit parentUnit;

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

        View view = inflater.inflate(R.layout.fragment_info_unit, container, false);

        txtParentOrgUnit = view.findViewById(R.id.txtParentOrgUnit);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtCategory = view.findViewById(R.id.txtCategory);
        txtStatus = view.findViewById(R.id.txtStatus);
        txtActiveFrom = view.findViewById(R.id.txtActiveFrom);

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

    private void updateUI() {
        Log.d(TAG, "frag updateUIData()");

        ((UnitActivity) getActivity()).setActivitySubTitle(data.getLabel());

        if (parentOrg != null) {
            txtParentOrgUnit.setText(parentOrg.getLabel());
            txtParentOrgUnit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), OrganizationActivity.class);
                    intent.putExtra("uid", parentOrg.getUid());
                    startActivity(intent);
                }
            });
        } else {
            txtParentOrgUnit.setText(parentUnit.getLabel());
            txtParentOrgUnit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), UnitActivity.class);
                    intent.putExtra("uid", parentUnit.getUid());
                    startActivity(intent);
                }
            });
        }

        txtTitle.setText(data.getLabel());

        DictionaryEntity category = Database.getInstance(getActivity())
                .getDictionaryDao()
                .getEntry(Configuration.DICTIONARY_ORG_UNIT_CATEGORY, data.getCategory());

        if (category != null)
            txtCategory.setText(category.getLabel());
        else
            txtCategory.setText(R.string.missing_dict_entry);

        txtStatus.setText(UnitHelper.getActiveText(data.isActive()));

        if (data.getActiveFrom() != null) {
            final String activeFrom = DateTimeFormatters.DATE_FORMAT.format(data.getActiveFrom());
            txtActiveFrom.setText(String.format("Ημερομηνία εγγραφής %s", activeFrom));
        } else
            txtActiveFrom.setVisibility(View.GONE);

        updateFavoriteButton();
    }

    private void toggleFavorite() {
        if (!FavoritesHelper.isFavorite(getActivity(), FavoriteEntity.UNIT, uid)) {
            final String description = (parentOrg != null) ? parentOrg.getLabel() : parentUnit.getLabel();
            FavoritesHelper.insert(getActivity(), FavoriteEntity.UNIT, uid, data.getLabel(), description);
        } else {
            FavoritesHelper.delete(getActivity(), FavoriteEntity.UNIT, uid);
        }

        updateFavoriteButton();
    }

    private void updateFavoriteButton() {
        if (FavoritesHelper.isFavorite(getActivity(), FavoriteEntity.UNIT, uid)) {
            txtFavorite.setText("Αποθηκεύτηκε");
            txtFavorite.setTextColor(Color.BLACK);
            txtFavorite.setBackgroundResource(R.drawable.bg_follow_true);
        } else {
            txtFavorite.setText("Αποθήκευση");
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
        data = Api.fetchUnitInfoByUid(uid);
        if (data == null) return null;

        if (data.getParentId() != null) {
            parentOrg = Api.fetchOrganizationInfoById(data.getParentId());
            if (parentOrg == null) {
                parentUnit = Api.fetchUnitInfoByUid(data.getParentId());
                if (parentUnit == null) return null;
            }
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
            setFragmentIsLoadingData(false);

            if (result == null) // error fetching remote data
                showMessage("Αδυναμία φόρτωσης των δεδομένων");
            else
                updateUI();
        }
    }


}