package com.armados.app.epraxeis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesSignersFragment extends BaseFragment {

    private final String TAG = getClass().getSimpleName();

    private final FavoritesSignersAdapter mAdapter = new FavoritesSignersAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_favorites, container, false);

        RecyclerView rv = view.findViewById(R.id.listWatchlist);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        /* On resume list might have changed and need update */
        updateUI();
    }

    private void updateUI() {
        final List<FavoriteEntity> list = Database.getInstance(getActivity()).getFavoriteDao().getAll(FavoriteEntity.SIGNER);

        mAdapter.setData(list);
    }

}

