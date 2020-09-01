package fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.compute_room.R;

import base.Lain_Base_Activity;

/**
 * @ClassName: AboutMe
 * @Description: 关于我们详情页，文字内容都在布局中实现了
 * @Author: YIN LUO FEI
 * @Date: 2020/7/10 13:58
 */
public class AboutMe extends Lain_Base_Activity {
    @Override
    protected String getToolbarTitle() {
        return "关于我们";
    }

    @Override
    public int setLayoutView() {
        return R.layout.about_me;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
