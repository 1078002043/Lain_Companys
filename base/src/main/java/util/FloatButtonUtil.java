package util;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.base.R;
import com.yw.game.floatmenu.FloatItem;
import com.yw.game.floatmenu.FloatLogoMenu;
import com.yw.game.floatmenu.FloatMenuView;

import java.util.ArrayList;

import base.Lain_Application;

/**
 * @ClassName: FloatButtonUtil
 * @Description: 浮窗按钮工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/11 21:07
 */
public class FloatButtonUtil {

    FloatLogoMenu mFloatMenu;

    FloatLogoMenu electricityFloatMenu;

    FloatLogoMenu mFloatMenu1;
    ArrayList<FloatItem> itemList = new ArrayList<>();

    //电量仪的ITEM
    ArrayList<FloatItem> eleItemList = new ArrayList<>();

    private Activity mActivity;

    String HOME = "曲线";
    String FEEDBACK = "报警";
    String FEEDBACK2 = "历史";
    String FEEDBACK3 = "添加";

    String[] MENU_ITEMS = {HOME, FEEDBACK, FEEDBACK2, FEEDBACK3};

    //设备管理中的标识
    String[] DEVICE_ITEMS = {"IP", "8060", "8052", "资产管理"};
    //用户管理中的标识
    String[] USER_ITEMS = {"用户", "角色", "权限"};

    //电量仪历史数据中的标识
    String[] ELE_HISTORY_ITEMS = {"电压电流", "功率电量"};


    private int[] menuIcons = new int[]{R.drawable.line_chart, R.drawable.menu_alert, R.drawable.time_search, R.drawable.menu_add};
    //设备管理图标
    private int[] deviceIcons = new int[]{R.drawable.menu_add, R.drawable.menu_add, R.drawable.menu_add, R.drawable.menu_add};
    //用户管理图标
    private int[] userIcons = new int[]{R.drawable.menu_add, R.drawable.menu_add, R.drawable.menu_add};

    //电量仪图标
    private int[] eleIcons = new int[]{R.drawable.menu_add, R.drawable.menu_add};

    private static FloatButtonUtil floatButtonUtil;

    public static FloatButtonUtil getInstance() {
        if (floatButtonUtil == null)
            synchronized (FloatButtonUtil.class) {
                if (floatButtonUtil == null)
                    floatButtonUtil = new FloatButtonUtil();
            }

        return floatButtonUtil;
    }

    private FloatButtonUtil() {

    }

    public void initFloat(Context context, Activity activity, FloatMenuView.OnMenuClickListener callback, LinearLayout historyItem, LinearLayout deviceItem, LinearLayout alertItem) {

        if (mFloatMenu == null) {

            if (itemList.isEmpty())

                for (int i = 0; i < menuIcons.length; i++) {


                    if (MENU_ITEMS[i].equals("历史") && historyItem.getVisibility() == View.GONE) {
                        continue;
                    } else if (MENU_ITEMS[i].equals("添加") && deviceItem.getVisibility() == View.GONE)
                        continue;
                    else if (MENU_ITEMS[i].equals("曲线") && historyItem.getVisibility() == View.GONE)
                        continue;
                    else if (MENU_ITEMS[i].equals("报警") && alertItem.getVisibility() == View.GONE)
                        continue;

                    itemList.add(new FloatItem(MENU_ITEMS[i], 0x99000000, 0x99000000, BitmapFactory.decodeResource(context.getResources(), menuIcons[i]), String.valueOf(i + 1)));
                }

            mFloatMenu = new FloatLogoMenu.Builder()
                    .withActivity(activity)
//                    .withContext(mActivity.getApplication())//这个在7.0（包括7.0）以上以及大部分7.0以下的国产手机上需要用户授权，需要搭配<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
                    .logo(BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_icon))
                    .drawCicleMenuBg(true)
                    .backMenuColor(0xffe4e3e1)
                    .setBgDrawable(context.getResources().getDrawable(R.drawable.yw_game_float_menu_bg))
                    //这个背景色需要和logo的背景色一致
                    .setFloatItems(itemList)
                    .defaultLocation(FloatLogoMenu.RIGHT)
                    .showWithListener(callback);

        } else {
            //如果不为空，则显示
            showFloat();
        }

    }

    public void deviceManageFloat(Context context, Activity activity, FloatMenuView.OnMenuClickListener callback) {
        if (mFloatMenu == null) {

            if (itemList.isEmpty())

                for (int i = 0; i < menuIcons.length; i++) {

                    itemList.add(new FloatItem(DEVICE_ITEMS[i], 0x99000000, 0x99000000, BitmapFactory.decodeResource(context.getResources(), deviceIcons[i]), String.valueOf(i + 1)));
                }

            mFloatMenu = new FloatLogoMenu.Builder()
                    .withActivity(activity)
//                    .withContext(mActivity.getApplication())//这个在7.0（包括7.0）以上以及大部分7.0以下的国产手机上需要用户授权，需要搭配<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
                    .logo(BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_icon))
                    .drawCicleMenuBg(true)
                    .backMenuColor(0xffe4e3e1)
                    .setBgDrawable(context.getResources().getDrawable(R.drawable.yw_game_float_menu_bg))
                    //这个背景色需要和logo的背景色一致
                    .setFloatItems(itemList)
                    .defaultLocation(FloatLogoMenu.RIGHT)
                    .showWithListener(callback);

        } else {
            //如果不为空，则显示
            showFloat();
        }

    }

    public void ElectricityHistoryFloat(Context context, Activity activity, FloatMenuView.OnMenuClickListener callback) {

        if (electricityFloatMenu == null) {

            if (eleItemList.isEmpty())

                for (int i = 0; i < eleIcons.length; i++) {

                    eleItemList.add(new FloatItem(ELE_HISTORY_ITEMS[i], 0x99000000, 0x99000000, BitmapFactory.decodeResource(context.getResources(), eleIcons[i]), String.valueOf(i + 1)));
                }

            electricityFloatMenu = new FloatLogoMenu.Builder()
                    .withActivity(activity)
//                    .withContext(mActivity.getApplication())//这个在7.0（包括7.0）以上以及大部分7.0以下的国产手机上需要用户授权，需要搭配<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
                    .logo(BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_icon))
                    .drawCicleMenuBg(true)
                    .backMenuColor(0xffe4e3e1)
                    .setBgDrawable(context.getResources().getDrawable(R.drawable.yw_game_float_menu_bg))
                    //这个背景色需要和logo的背景色一致
                    .setFloatItems(eleItemList)
                    .defaultLocation(FloatLogoMenu.RIGHT)
                    .showWithListener(callback);

        } else {
            //如果不为空，则显示
            showFloat();
        }

    }

    /**
     * 用户管理的 Float
     *
     * @param context
     * @param activity
     * @param callback
     */
    public void UserManageFloat(Context context, Activity activity, FloatMenuView.OnMenuClickListener callback) {
        if (mFloatMenu == null) {

            if (itemList.isEmpty())

                for (int i = 0; i < userIcons.length; i++) {

                    itemList.add(new FloatItem(USER_ITEMS[i], 0x99000000, 0x99000000, BitmapFactory.decodeResource(context.getResources(), deviceIcons[i]), String.valueOf(i + 1)));
                }

            mFloatMenu = new FloatLogoMenu.Builder()
                    .withActivity(activity)
//                    .withContext(mActivity.getApplication())//这个在7.0（包括7.0）以上以及大部分7.0以下的国产手机上需要用户授权，需要搭配<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
                    .logo(BitmapFactory.decodeResource(context.getResources(), R.drawable.menu_icon))
                    .drawCicleMenuBg(true)
                    .backMenuColor(0xffe4e3e1)
                    .setBgDrawable(context.getResources().getDrawable(R.drawable.yw_game_float_menu_bg))
                    //这个背景色需要和logo的背景色一致
                    .setFloatItems(itemList)
                    .defaultLocation(FloatLogoMenu.RIGHT)
                    .showWithListener(callback);

        } else {
            //如果不为空，则显示
            showFloat();
        }

    }

    public void showFloat() {

        if (electricityFloatMenu != null) {
            electricityFloatMenu.show();
        }

        //这里不能使用 if else，这样是无法获取到数据的
        if (mFloatMenu != null) {
            mFloatMenu.show();
        }
    }

    public void hideFloat() {
        if (mFloatMenu != null) {
            mFloatMenu.hide();
        }
    }

    public void destroyElectricityFloat() {
        try {
            if (electricityFloatMenu != null) {
                electricityFloatMenu.destroyFloat();
            }
        } catch (Exception e) {
            Log.d(Lain_Application.getClassName(getClass()), "destroyFloat:  error");
        }
        Log.d("kjdsfsdf", "destroyElectricityFloat: " + electricityFloatMenu);
        electricityFloatMenu = null;
        Log.d("kjdsfsdf", "destroyElectricityFloat: " + electricityFloatMenu);
        //一定要清空
        eleItemList.clear();
    }

    public void destroyFloat() {
        try {
            if (mFloatMenu != null) {
                mFloatMenu.destroyFloat();
            }
        } catch (Exception e) {
            Log.d(Lain_Application.getClassName(getClass()), "destroyFloat:  error");
        }
        mFloatMenu = null;
        //一定要清空
        itemList.clear();
        mActivity = null;
    }

}
