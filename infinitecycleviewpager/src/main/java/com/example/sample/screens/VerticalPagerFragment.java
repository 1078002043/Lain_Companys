package com.example.sample.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.infinitecycleviewpager.R;
import com.example.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import com.example.sample.adapters.VerticalPagerAdapter;
import com.example.sample.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 垂直 ViewPager
 */
public class VerticalPagerFragment extends Fragment {

    private final List<Utils.LibraryObject> mLibarary = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vertical, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_strategy, "Strategy"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_design, "Design"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_development, "Development"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_qa, "Quality Assurance"));
        mLibarary.add(new Utils.LibraryObject(R.drawable.ic_strategy, "Strategy"));

        final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
        verticalInfiniteCycleViewPager.setAdapter(new VerticalPagerAdapter(getContext(), mLibarary));

        verticalInfiniteCycleViewPager.setScrollDuration(1000);
        verticalInfiniteCycleViewPager.startAutoScroll(true);
    }
}
