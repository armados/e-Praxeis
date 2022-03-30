package com.armados.app.epraxeis;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;


public abstract class BaseFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();

    protected View view;

    protected LinearLayout mBoxLoading;
    protected LinearLayout mBoxEmpty;
    protected LinearLayout mBoxError;

    protected void showMessage(final String text) {
        View view = getActivity().findViewById(R.id.mainLayout);

        if (view == null) {
            Log.e(TAG, "Αδυναμία εμφάνισης μηνύματος - Απουσιάζει το γραφικό mainLayout");
            return;
        }

        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    protected void showErrorView(boolean visibility) {
        if (mBoxError == null) return;
        mBoxError.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    protected void showEmptyView(boolean visibility) {
        if (mBoxEmpty == null) return;
        mBoxEmpty.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    private void showLoadingView(boolean visibility) {
        if (mBoxLoading == null) return;
        mBoxLoading.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    protected void setFragmentIsLoadingData(boolean isLoading) {
        if (isLoading) {
            showErrorView(false); // reset view
            showEmptyView(false);  // reset view
            showLoadingView(true);
        } else {
            showLoadingView(false);
         }
    }

}
