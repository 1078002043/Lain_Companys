package com.example.sample.screens;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.example.infinitecycleviewpager.R;
import com.example.sample.adapters.HorizontalPagerAdapter;
import com.example.sample.system_lib.SystemInfoBean;
import com.example.sample.system_lib.SystemUtil;
import com.example.sample.utils.Utils;
import com.example.sample.utils.ViewPageCallBack;

import java.util.ArrayList;
import java.util.List;

import searchablespinner.SearchableSpinner;
import searchablespinner.interfaces.IStatusListener;
import searchablespinner.interfaces.OnItemSelectedListener;
import view.Lain_View;

/**
 *
 * 水平ViewPage
 *
 */
public class HorizontalPagerFragment extends Fragment implements ViewPageCallBack {
    //搜索框
    private SearchableSpinner mSearchableSpinner3;
    private SimpleListAdapter mSimpleListAdapter;
    private SimpleArrayListAdapter mSimpleArrayListAdapter;
    private final ArrayList<String> mStrings = new ArrayList<>();


    private ImageView frameLayout;

    private List<Utils.LibraryObject> mLibarary = new ArrayList<>();

    private HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_horizontal, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frameLayout = view.findViewById(R.id.frame_bg);
        frameLayout.setBackground(getResources().getDrawable(R.drawable.login_bg));
        mSearchableSpinner3 = view.findViewById(R.id.searchable_spinner);

        List<SystemInfoBean> systemInfoBeanList = SystemUtil.getInstance().getSystemInfo();

        for (SystemInfoBean b :
                systemInfoBeanList) {

            mLibarary.add(new Utils.LibraryObject(b.getSystemCover(), b.getSystemTitle()));
            mStrings.add(b.getSystemTitle());

        }

        horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), false, mLibarary,this));
        horizontalInfiniteCycleViewPager.setScrollDuration(1000);
        horizontalInfiniteCycleViewPager.setInterpolator(
                AnimationUtils.loadInterpolator(getContext(), android.R.anim.overshoot_interpolator)
        );
        horizontalInfiniteCycleViewPager.setMediumScaled(false);
        horizontalInfiniteCycleViewPager.setMaxPageScale(0.8F);
        horizontalInfiniteCycleViewPager.setMinPageScale(0.5F);
        horizontalInfiniteCycleViewPager.setCenterPageScaleOffset(30.0F);
        horizontalInfiniteCycleViewPager.setMinPageScaleOffset(5.0F);

        //监听滑动
        horizontalInfiniteCycleViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //获取获取的位置
//                Log.d("lkjdlfer", "onPageSelected: "+horizontalInfiniteCycleViewPager.getRealItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        horizontalInfiniteCycleViewPager.setCurrentItem(
                horizontalInfiniteCycleViewPager.getRealItem() + 1
        );


//        initListValues();
        mSimpleListAdapter = new SimpleListAdapter(view.getContext(), mStrings);
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(view.getContext(), mStrings);


        mSearchableSpinner3.setAdapter(mSimpleListAdapter);

        mSearchableSpinner3.setOnItemSelectedListener(mOnItemSelectedListener);
        mSearchableSpinner3.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
//                mSearchableSpinner.hideEdit();
                mSearchableSpinner3.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });


    }

    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            //如果为0，则不做任何选择
            if (position == 0)
                return;

            //绑定控件，获取该控件的文本，来确定所在的位置
            TextView textView = view.findViewById(R.id.TxtVw_DisplayName);
            String title = textView.getText().toString();

            //跳转到指定页
            if (mStrings.contains(title)) {
                horizontalInfiniteCycleViewPager.setCurrentItem(mStrings.indexOf(title), true);
            }



        }

        @Override
        public void onNothingSelected() {
//            Toast.makeText(MainActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };


    /**
     * ViewPager的点击回调

     * @param position
     */
    @Override
    public void viewPageCallBack(View view, int position) {

        String title = mStrings.get(position);

        switch (title) {
            case "机房监控":
                Intent intent = new Intent(getActivity(), Lain_View.class);
                getActivity().startActivity(intent);
            break;
        }

    }
}
