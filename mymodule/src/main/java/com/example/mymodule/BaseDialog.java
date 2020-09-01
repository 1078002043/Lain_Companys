package com.example.mymodule;

import android.app.Dialog;
import android.content.Context;

/**
 * 自定义的 Dialog
 */
public class BaseDialog extends Dialog {

    public BaseDialog(Context context, int theme, int res) {
        super(context, theme);
        // TODO 自动生成的构造函数存根
        setContentView(res);
        setCanceledOnTouchOutside(false);
    }

}