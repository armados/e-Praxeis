package com.armados.app.epraxeis;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {

    private final String TAG = getClass().getSimpleName();

    private ArrayList<Fragment> fragments;

    public ScreenSlidePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

}
