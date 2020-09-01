package util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nex3z.flowlayout.FlowLayout;
import com.robertlevonyan.views.chip.Chip;
import com.robertlevonyan.views.chip.OnSelectClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: MultiChipUtil
 * @Description: 多个标签的Chip工具类
 * @Author: YIN LUO FEI
 * @Date: 2020/7/23 15:36
 */
public class MultiChipUtil {

    private static MultiChipUtil chipsUtil;
    private FlowLayout flowLayout;
    private Context context;

    //事件回调
    I_MultiChip i_multiChip;

    //单例
    public static MultiChipUtil getInstance() {

        if (chipsUtil == null)
            synchronized (MultiChipUtil.class) {
                if (chipsUtil == null)
                    chipsUtil = new MultiChipUtil();
            }

        return chipsUtil;
    }

    /**
     * 动态添加 Chip View
     *
     * @param context    动态添加View时需要使用到的 上下文
     * @param tagTexts   所有的标签内容
     * @param flowLayout 流式布局，自动换行
     * @return 返回所有添加的 Chip视图
     */
    public List<Chip> addChipView(Context context, List<String> tagTexts, FlowLayout flowLayout, I_MultiChip i_multiChip) {

        if (flowLayout == null)
            throw new NullPointerException("FlowLayout is Null");

        List<Chip> chipList = new ArrayList<>();

        this.i_multiChip = i_multiChip;

        //保存相关实例
        this.flowLayout = flowLayout;
        this.context = context;

        //获取调用者需要设置的 TagView
        for (String tag :
                tagTexts) {

            chipList.add(getChips(tag));

        }

        return chipList;

    }

    /**
     * 将 Chip 动态添加到 FlowLayout 中
     *
     * @param tagText
     */
    private Chip getChips(String tagText) {

        final Chip chip = new Chip(context);

        chip.setSelectable(true);
        chip.setChipBackgroundColor(Color.WHITE);
        chip.setChipTextColor(Color.parseColor("#757575"));
        chip.setCornerRadius(50);
        chip.setChipSelectedBackgroundColor(Color.WHITE);
        chip.setChipSelectedTextColor(Color.parseColor("#757575"));
        chip.setChipSelectedCloseColor(Color.parseColor("#5197FF"));
        chip.setChipCloseColor(Color.parseColor("#c4c9cd"));
        chip.setStrokeColor(Color.parseColor("#5197FF"));

        chip.setStrokeSize(5);

        chip.setText(tagText);

        //设置 Chip 的样式
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);

        //标签点击事件
        chip.setOnClickListener((v) -> {

            chip.setChipSelected(!chip.getChipSelected());

            //事件回调
            if (i_multiChip != null)
                i_multiChip.multiChipListener(v, chip.getChipSelected());

        });

        //选择点击事件
        chip.setOnSelectClickListener((v, selected) -> {
            //事件回调
            if (i_multiChip != null)
                i_multiChip.multiChipListener(v, chip.getChipSelected());
        });

        //添加 Chip
        flowLayout.addView(chip, params);

        //返回此次添加的 View Tag
        return chip;

    }

    //修改Chip颜色
    private void setChipBg(Chip chip, String tagText) {

        switch (tagText) {
            case "user":
                chip.setChipBackgroundColor(Color.BLUE);
                break;
            case "superAdmin":
                chip.setChipBackgroundColor(Color.RED);
                break;
            case "admin":
                chip.setChipBackgroundColor(Color.GREEN);
                break;
        }

    }

}
