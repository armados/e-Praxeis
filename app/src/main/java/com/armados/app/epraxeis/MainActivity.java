package com.armados.app.epraxeis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null) {
            myActionBar.setDisplayOptions(
                    ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO
            );
            myActionBar.setLogo(R.drawable.ic_drawing_logo_e_praxeis_path_small_22dp);
        }

        final Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

        final Button btnListOrganizations = findViewById(R.id.btnListOrganizations);
        btnListOrganizations.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListOrganizationsActivity.class);
            startActivity(intent);
        });

        final Button btnFav = findViewById(R.id.btnFav);
        btnFav.setOnClickListener(v -> {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        });

        final Button btnDictUpdate = findViewById(R.id.btnDictUpdate);
        btnDictUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(this, DictionaryActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "func onBackPressed()");
        finish();
    }

}