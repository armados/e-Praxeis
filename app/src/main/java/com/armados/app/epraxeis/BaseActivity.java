package com.armados.app.epraxeis;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public abstract class BaseActivity extends AppCompatActivity {

    // https://developer.android.com/studio/debug/am-logcat
    protected final String TAG = getClass().getSimpleName();

    protected String activityTitle;
    protected String activitySubTitle;

    protected TabLayout mTabLayout;
    protected ViewPager2 mViewPager;
    protected ProgressBar mProgressBar;

    protected LinearLayout mBoxLoading;

    protected void setActivityHomeAsUp() {
        ActionBar myActionBar = getSupportActionBar();
        if (myActionBar != null)
            myActionBar.setDisplayHomeAsUpEnabled(true);
    }

    protected void setActivityTitle(String str) {
        activityTitle = str;

        ActionBar myActionBar = getSupportActionBar();

        if (myActionBar != null)
            myActionBar.setTitle(activityTitle);
    }

    protected void setActivitySubTitle(String str) {
        activitySubTitle = str;

        ActionBar myActionBar = getSupportActionBar();

        if (myActionBar != null)
            myActionBar.setSubtitle(activitySubTitle);
    }

    protected void showMessage(final String text) {
        View view = findViewById(R.id.mainLayout);

        if (view == null) {
            Log.e(TAG, "Αδυναμία εμφάνισης μηνύματος");
            return;
        }

        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private void showLoadingView(boolean visibility) {
        if (mBoxLoading == null) return;
        mBoxLoading.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void showViewPager(boolean visibility) {
        if (mViewPager == null) return;
        mViewPager.setVisibility(visibility ? View.VISIBLE : View.INVISIBLE);
    }

    protected void setActivityIsLoadingData(boolean loading) {
        if (loading) {
            //setActivityEnabled(false);
            showLoadingView(true);
            showViewPager(false);
        } else {
            //setActivityEnabled(true);
            showLoadingView(false);
            showViewPager(true);
        }
    }

}
