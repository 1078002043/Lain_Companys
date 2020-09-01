package com.example.ffmeger_com;

import android.content.Context;
import android.widget.Toast;

/**
 * @author: AnJoiner
 * @datetime: 19-12-20
 */
public class ToastUtils {
    public static void show(String msg, Context context) {
        Toast.makeText(context, msg,
                Toast.LENGTH_SHORT).show();
    }
}
