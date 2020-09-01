package cn.com.lain.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sample.adapters.HorizontalPagerAdapter;
import com.example.sample.screens.HorizontalPagerFragment;

import java.util.LinkedHashMap;
import java.util.Map;

import base.Lain_Base_Activity;
import cn.com.lain.R;

/**
 * 选择系统页面，首页面
 */
public class SelectSystem extends Lain_Base_Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HorizontalPagerFragment horizontalPagerFragment = new HorizontalPagerFragment();

        replaceFragment(horizontalPagerFragment);

    }

    /**
     * 替换 Fragment
     * @param horizontalPagerFragment
     */
    private void replaceFragment(HorizontalPagerFragment horizontalPagerFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_select_system, horizontalPagerFragment);
        transaction.commit();
    }

    @Override
    protected String getToolbarTitle() {
        return "选择系统";
    }

    @Override
    public int setLayoutView() {
//        return 0;
        return R.layout.main_select_system;
    }
}
