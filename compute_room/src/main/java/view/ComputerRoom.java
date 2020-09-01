package view;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.example.compute_room.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapter.Device_DetailViewPage;
import base.BaseRecyclerViewAdapter;
import base.Lain_Application;
import bean.ConfigBean;
import bean.MenuConfigBean;
import device.DeviceInterface;
import device.NewAddDeviceUtil;
import http.OkHttpCallBack;
import http.OkHttpUtil;
import present.ComputerRoom_Presenter;
import util.LainNewApi;
import util.MyPropertiesUtil;
import util.ScaleTransitionPagerTitleView;
import v_interface.I_ComputerRoom;

/**
 * 现在将设备列表移到主页上
 * 不修改其他代码，直接对接之前的机房监控设备列表代码
 *
 * @author Create by Yin Luo Fei
 * @version 0.1
 * @Time 2019/9/18 10 : 19
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class ComputerRoom implements I_ComputerRoom, DeviceInterface {

    //加载设备列表时，等待视图
    private final SpinKitView menuConfigLoading;
    //配置名称
    private String configName = "my_config.properties";

    //顶部导航栏
    private MagicIndicator magicIndicator;
    //ViewPager 结合 顶部导航栏使用
    private ViewPager viewPager;
    //顶部导航栏配置
    private CommonNavigator magicNavigator;

    //ViewPager View 的列表
    public List<View> viewPagerList;

    //Fragment的Activity
    private FragmentActivity fgActivity;

    //设备列表显示和隐藏数据
    private volatile List<MenuConfigBean> tempConfigBeans = new ArrayList<>();

    //获取顶部导航栏的标题
    List<ConfigBean> topNavTitles = MyPropertiesUtil.getInstance().getProperties(Lain_Application.getContext(), configName, "moduleTitle");

    public ComputerRoom(FragmentActivity fgActivity, ViewPager viewPager, MagicIndicator magicIndicator, SpinKitView menuConfigLoading) {
        //Fragment Activity
        this.fgActivity = fgActivity;
        //ViewPager
        this.viewPager = viewPager;
        //顶部导航栏
        this.magicIndicator = magicIndicator;
        //加载列表时，等待的加载视频
        this.menuConfigLoading = menuConfigLoading;

        //视频的封面
        //设备列表的 轮播图
        init();

    }

    public void init() {

        //初始化Presenter
        //初始化Presenter
        ComputerRoom_Presenter presenter = new ComputerRoom_Presenter(this);

        //初始化顶部导航栏
        magicNavigator = new CommonNavigator(fgActivity);

        //初始化 ViewPager
        Device_DetailViewPage page = new Device_DetailViewPage(viewPagerList = NewAddDeviceUtil.getInstance().bindViewPager(fgActivity.getLayoutInflater()));

        //设置ViewPager
        viewPager.setAdapter(page);
        //ViewPager活动监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //滑动时回调
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //ViewPager滑动后也能与顶部导航栏联动
                magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            //滑动完之后回调
            @Override
            public void onPageSelected(int position) {

                //滑动时获取TopNav值
                LainNewApi.DEVICE_TAG = topNavTitles.get(position).getTitle();

                //顶部导航栏联动
                magicIndicator.onPageSelected(position);

                //只在第一次进入时才请求
                if (position > 0 && tempConfigBeans.isEmpty()) {

                    String url = LainNewApi.getInstance().getRootPath() + LainNewApi.menuConfig;
                    OkHttpUtil.getInstance().sendGetOkHttp("menuConfig", url, new OkHttpCallBack() {
                        @Override
                        public void httpRequestSuccess(String requestTag, String requestUrl, String responseStr) {

                            //显示加载视图
                            menuConfigLoading.setVisibility(View.VISIBLE);


                            //保存解析的数据
                            tempConfigBeans = OkHttpUtil.getInstance().formatResponse(responseStr, MenuConfigBean.class);


                            //通过线程进行加载，UI才不会卡顿
                            //更新数据库数据
                            new Thread(ComputerRoom.this::updateMenuConfig).start();


                        }

                        @Override
                        public void httpRequestFailure(String requestTag, String requestUrl, String errorMsg) {

                        }
                    });

                }

            }

            //滑动状态改变时执行
            @Override
            public void onPageScrollStateChanged(int state) {
                //顶部导航栏联动
                magicIndicator.onPageScrollStateChanged(state);
            }
        });

        //显示头部栏
        initHeadAndContent();

    }

    /**
     * 显示所有的设备列表
     */
    private void showDeviceList() {

        NewAddDeviceUtil.getInstance().setFace(this);
        //环境监控
        NewAddDeviceUtil.getInstance().getEnvironment();
        //动力监控
        NewAddDeviceUtil.getInstance().getPowerMonitor();
        //安防监控
        NewAddDeviceUtil.getInstance().getSecurityMonitor();
        //运维监控
        NewAddDeviceUtil.getInstance().getOperationMonitor();
        //系统管理
        NewAddDeviceUtil.getInstance().getSystemManagement();
        //水质监控
        NewAddDeviceUtil.getInstance().getWaterQuality();

        //隐藏加载条
        menuConfigLoading.setVisibility(View.GONE);

    }

    /**
     * 更新数据库中的数据
     */
    private void updateMenuConfig() {

        //查询 title isShow 两列的所有数据
        List<ConfigBean> configBeans = LitePal.select("title", "isshow").find(ConfigBean.class);

        //遍历查询到的数据
        for (ConfigBean config :
                configBeans) {

            //如果该设备是显示则为 true，不显示false
            boolean isShow = false;

            //遍历获取到的后台数据
            for (MenuConfigBean menuConfigBean : tempConfigBeans) {

                //判断是否显示，名称相同代码显示，必须使用 trim() 去除多余的空格，才能做出正确的判断
                if (config.getTitle().trim().equals(menuConfigBean.getName().trim())) {

                    //修改 isShow 字段为显示该设备 1
                    config.setIsShow(1);
                    //更新数据
                    config.save();
                    //修改标识为 显示设备
                    isShow = true;
                    //结果内层循环
                    break;
                }

            }

            //如果不显示，则修改成5，0设置无效
            if (!isShow) {
                config.setIsShow(5);
                config.save();
            }

        }

        //回调到主线程中进行显示数据
        fgActivity.runOnUiThread(ComputerRoom.this::showDeviceList);

    }

    /**
     * 初始化顶部导航栏和环境监控设备
     */
    private void initHeadAndContent() {
        //获取顶部
        List<ConfigBean> topList = MyPropertiesUtil.getInstance().getProperties(Lain_Application.getContext(), "my_config.properties", "moduleTitle");

        setTopNavigation(topList);

    }

    /**
     * 设置 机房监控 顶部导航栏
     */
    @Override
    public void setTopNavigation(List<ConfigBean> topNavigation) {
        //数据获取成功后，设置顶部导航栏
        magicNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                //获取顶部导航栏的长度
                return MyPropertiesUtil.getInstance().getProperties(Lain_Application.getContext(), configName, "moduleTitle").size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                //放大缩小动画
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                //设置标题
                simplePagerTitleView.setText(MyPropertiesUtil.getInstance().getProperties(Lain_Application.getContext(), configName, "moduleTitle").get(index).getTitle());
                //设置字体
                simplePagerTitleView.setTextSize(16);
                //未选中字体颜色
                simplePagerTitleView.setNormalColor(Color.parseColor("#303252"));
                //选中字体颜色
                simplePagerTitleView.setSelectedColor(Color.parseColor("#5097FF"));
                //顶部导航栏监听
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击时，改变ViewPage的位置
                        viewPager.setCurrentItem(index);
                        //保存当前所在顶部导航栏的位置
                        LainNewApi.DEVICE_TAG = topNavigation.get(index).getTitle();
                    }
                });
                //返回顶部导航栏的配置对象
                return simplePagerTitleView;
            }

            //顶部导航栏的 下划线
            @Override
            public IPagerIndicator getIndicator(Context context) {
                //线性
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                //高度
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                //宽度
                indicator.setLineWidth(UIUtil.dip2px(context, 60));
                //圆角
                indicator.setRoundRadius(UIUtil.dip2px(context, 2));
                //开始动画
                indicator.setStartInterpolator(new AccelerateInterpolator());
                //结束动画
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                //颜色
                indicator.setColors(Color.parseColor("#5097FF"));

                return indicator;

            }

        });
        //设置顶部导航栏生效
        magicIndicator.setNavigator(magicNavigator);

    }

    @Override
    public void getClickDevice(String tag, String name, String image) {

        Intent intent = new Intent(fgActivity, Device_Detail.class);
        intent.putExtra("device_tag", tag);
        intent.putExtra("device_name", name);
        intent.putExtra("device_image", image);
        fgActivity.startActivity(intent);


    }
}
