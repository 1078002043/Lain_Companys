package com.example.sample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.example.infinitecycleviewpager.R;
import com.example.sample.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.example.sample.utils.Utils.setupItem;

/**
 * 垂直适配器
 */
public class VerticalPagerAdapter extends PagerAdapter {

    private List<Utils.LibraryObject> TWO_WAY_LIBRARIES = new ArrayList<>();


    private LayoutInflater mLayoutInflater;

    public VerticalPagerAdapter(final Context context, List<Utils.LibraryObject> mTowWay) {
        mLayoutInflater = LayoutInflater.from(context);
        TWO_WAY_LIBRARIES = mTowWay;
    }


    @Override
    public int getCount() {
        return TWO_WAY_LIBRARIES.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.item, container, false);

        setupItem(view, TWO_WAY_LIBRARIES.get(position));

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
