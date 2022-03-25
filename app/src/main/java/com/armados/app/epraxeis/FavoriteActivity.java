package com.armados.app.epraxeis;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class FavoriteActivity extends BaseActivity {

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        setActivityTitle("Λίστα παρακολούθησης");
        setActivityHomeAsUp();

        ScreenSlidePagerAdapter sa = new ScreenSlidePagerAdapter(getSupportFragmentManager(), getLifecycle());

        ViewPager2 mViewPager = findViewById(R.id.mViewPager);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FavoriteOrganizationsFragment());
        fragmentList.add(new FavoriteSignersFragment());
        fragmentList.add(new FavoriteUnitsFragment());

        sa.setFragments(fragmentList);
        mViewPager.setAdapter(sa);

        TabLayout mTabLayout = findViewById(R.id.mTabLayout);

        new TabLayoutMediator(mTabLayout, mViewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Φορείς");
                            break;
                        case 1:
                            tab.setText("Υπογράφοντες");
                            break;
                        case 2:
                            tab.setText("Οργανωτικές Μονάδες");
                            break;
                    }
                }).attach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "func onOptionsItemSelected()");
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}