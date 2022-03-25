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

import com.armados.app.epraxeis.diavgeia.Organization;
import com.armados.app.epraxeis.diavgeia.Unit;


public class UnitInfoFragment extends BaseFragment {

    private TextView txt_text1;
    private TextView txt_text2;
    private TextView txt_text3;
    private TextView txt_text4;
    private TextView txt_text5;
    private TextView txt_text6;
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

        txt_text1 = view.findViewById(R.id.txt_text1);
        txt_text2 = view.findViewById(R.id.txt_text2);
        txt_text3 = view.findViewById(R.id.txt_text3);
        txt_text4 = view.findViewById(R.id.txt_text4);
        txt_text5 = view.findViewById(R.id.txt_text5);
        txt_text6 = view.findViewById(R.id.txt_text6);
        txt_text7 = view.findViewById(R.id.txt_text7);


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
            txt_text1.setText(parentOrg.getLabel());
            txt_text1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), OrganizationActivity.class);
                    intent.putExtra("uid", parentOrg.getUid());
                    startActivity(intent);
                }
            });
        } else {
            txt_text1.setText(parentUnit.getLabel());
            txt_text1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), UnitActivity.class);
                    intent.putExtra("uid", parentUnit.getUid());
                    startActivity(intent);
                }
            });
        }

        txt_text2.setText(data.getLabel());


        DictionaryEntity category = Database.getInstance(getActivity())
                .getDictionaryDao()
                .getEntry(Configuration.DICTIONARY_ORG_UNIT_CATEGORY, data.getCategory());

        if (category != null)
            txt_text4.setText(category.getLabel());
        else
            txt_text4.setText(R.string.missing_dict_entry);

        txt_text5.setText(UnitHelper.getActiveText(data.isActive()));

        if (data.getActiveFrom() != null) {
            final String activeFrom = DateTimeFormatters.DATE_FORMAT.format(data.getActiveFrom());
            txt_text6.setText(String.format("Ημερομηνία εγγραφής %s", activeFrom));
        } else
            txt_text6.setText("");

        txt_text7.setText("");

        updateFavoriteButton();
    }

    private void toggleFavorite() {
        if (!FavoriteHelper.isFavorite(getActivity(), FavoriteEntity.UNIT, uid)) {
            final String description = (parentOrg != null) ? parentOrg.getLabel() : parentUnit.getLabel();
            FavoriteHelper.insert(getActivity(), FavoriteEntity.UNIT, uid, data.getLabel(), description);
        } else {
            FavoriteHelper.delete(getActivity(), FavoriteEntity.UNIT, uid);
        }

        updateFavoriteButton();
    }

    private void updateFavoriteButton() {
        if (FavoriteHelper.isFavorite(getActivity(), FavoriteEntity.UNIT, uid)) {
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