package com.example.sample.adapters;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.sample.screens.HorizontalPagerFragment;
import com.example.sample.screens.TwoWayPagerFragment;

/**
 *
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final static int COUNT = 3;

    private final static int HORIZONTAL = 0;
    private final static int TWO_WAY = 1;

    public MainPagerAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case TWO_WAY:
                return new TwoWayPagerFragment();
            case HORIZONTAL:
            default:
                return new HorizontalPagerFragment();
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
