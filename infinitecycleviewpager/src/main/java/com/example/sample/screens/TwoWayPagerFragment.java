package com.example.sample.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.example.infinitecycleviewpager.R;
import com.example.sample.adapters.HorizontalPagerAdapter;
import com.example.sample.utils.Utils;
import com.example.sample.utils.ViewPageCallBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 垂直，水平 Viewpager
 */
public class TwoWayPagerFragment extends Fragment implements ViewPageCallBack {

    private final List<Utils.LibraryObject> mLibarary = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two_way, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_strategy, "Strategy"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_design, "Design"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_development, "Development"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_qa, "Quality Assurance"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_strategy, "Strategy"));

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), true, mLibarary, this));
//
//        horizontalInfiniteCycleViewPager.setScrollDuration(500);
//        horizontalInfiniteCycleViewPager.setPageDuration(1000);
//        horizontalInfiniteCycleViewPager.setInterpolator(null);
//        horizontalInfiniteCycleViewPager.setMediumScaled(true);
//        horizontalInfiniteCycleViewPager.setMaxPageScale(1.0F);
//        horizontalInfiniteCycleViewPager.setMinPageScale(0.7F);
//        horizontalInfiniteCycleViewPager.setCenterPageScaleOffset(0.0F);
//        horizontalInfiniteCycleViewPager.setMinPageScaleOffset(0.0F);
    }

    @Override
    public void viewPageCallBack(View view, int position) {

    }
}
