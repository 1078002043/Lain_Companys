package com.example.sample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.example.infinitecycleviewpager.R;
import com.example.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import com.example.sample.utils.Utils;
import com.example.sample.utils.ViewPageCallBack;

import java.util.List;

import static com.example.sample.utils.Utils.setupItem;

/**
 * 水平适配器
 */
public class HorizontalPagerAdapter extends PagerAdapter {



    private List<Utils.LibraryObject> mLibarary;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    //ViewPage点击事件回调
    private ViewPageCallBack viewPageCallBack;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay, List<Utils.LibraryObject> library, ViewPageCallBack clickCall) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
        mLibarary = library;
        viewPageCallBack = clickCall;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : mLibarary.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;



        if (mIsTwoWay) {
            view = mLayoutInflater.inflate(R.layout.two_way_item, container, false);


            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
            verticalInfiniteCycleViewPager.setAdapter(
                    new VerticalPagerAdapter(mContext, mLibarary)
            );
            verticalInfiniteCycleViewPager.setCurrentItem(position);
        } else {
            view = mLayoutInflater.inflate(R.layout.item, container, false);

            setupItem(view, mLibarary.get(position));

        }
        //设置点击回调事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPageCallBack != null)
                    viewPageCallBack.viewPageCallBack(view, position);
            }
        });

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
