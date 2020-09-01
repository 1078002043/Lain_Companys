package com.example.sample.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.infinitecycleviewpager.R;
import com.example.sample.screens.GlideBlurTransformation;

/**
 * ViewPager Item 的属性设置
 */
public class Utils {

    static View vview;

    private static TextView txt;

    public static void setItemTextSize(int size) {
        if (txt == null)
            throw new NullPointerException("TextView 未实例化");

        txt.setTextSize(size);
    }

    /**
     * 通过适配器，将加载的View传入来设置资源
     *
     * @param view
     * @param libraryObject
     */
    public static void setupItem(final View view, final LibraryObject libraryObject) {


        //卡片
        final CardView cardView = view.findViewById(R.id.item_card_view);
        final LinearLayout linearLayout = view.findViewById(R.id.card_liner);
        vview = view;

        final TextView txt = view.findViewById(R.id.txt_item);
        txt.setText(libraryObject.getTitle());

        try {

            //设置标题颜色
            if (libraryObject.mTextColor != 0)
                txt.setTextColor(libraryObject.mTextColor);

            //设置卡片的背影颜色
            if (libraryObject.mCardBackground != null)
                linearLayout.setBackgroundColor(Color.parseColor(libraryObject.mCardBackground));
            else
                linearLayout.setBackgroundColor(Color.WHITE);   //默认白色背影

            //设置卡片的背影图片
            if (libraryObject.mCardDrawable != null) {

                setCardBackgroundImage(linearLayout);

            }


        } catch (Exception e) {
            Log.d("Utils", "setupItem: set Color or background error  "+libraryObject.getTitle());
        }

        final ImageView img = view.findViewById(R.id.img_item);
        img.setImageResource(libraryObject.getRes());
    }

    /**
     * 设置卡片背影颜色
     * @param cardView
     */
    private static void setCardBackgroundImage(final LinearLayout cardView) {

        SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                cardView.setBackground(resource);
            }
        };

//        GlideApp.with(vview.getContext()).load("https://img.zcool.cn/community/010aec5b286bd7a80121bbec789be3.png@1280w_1l_2o_100sh.png")
//                .apply(RequestOptions.bitmapTransform(new GlideBlurTransformation(vview.getContext())))
//                .into(simpleTarget);

    }

    /**
     * ViewPager中的Item都由这个对象来构建
     */
    public static class LibraryObject {

        private String mTitle;
        private int mRes;
        private int mTextColor;
        private String mCardBackground;
        private Drawable mCardDrawable;

        /**
         * 默认白色背影，蓝色字体
         *
         * @param res
         * @param title
         */
        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        /**
         * 默认白色背影，字体颜色自定义
         *
         * @param res
         * @param title
         * @param textColor
         */
        public LibraryObject(final int res, final String title, int textColor) {
            mRes = res;
            mTitle = title;
            mTextColor = textColor;
        }

        /**
         * 自定义卡片背影颜色，字体默认蓝色
         *
         * @param res
         * @param title
         * @param cardBackground
         */
        public LibraryObject(final int res, final String title, String cardBackground) {
            mRes = res;
            mTitle = title;
            mCardBackground = cardBackground;
        }

        /**
         * 自定义 字体颜色，卡片背影
         *
         * @param res
         * @param title
         * @param textColor
         * @param cardBackground
         */
        public LibraryObject(final int res, final String title, int textColor, String cardBackground) {
            mRes = res;
            mTitle = title;
            mCardBackground = cardBackground;
            mTextColor = textColor;
        }

        /**
         * 设置背影图片
         * @param res
         * @param title
         * @param textColor
         * @param cardBackgroundImg
         */
        public LibraryObject(final int res, final String title, int textColor, Drawable cardBackgroundImg) {
            mRes = res;
            mTitle = title;
            mCardDrawable = cardBackgroundImg;
            mTextColor = textColor;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }

        public int getmTextColor() {
            return mTextColor;
        }

        public String getmCardBackground() {
            return mCardBackground;
        }

        public String getmTitle() {
            return mTitle;
        }


    }
}
